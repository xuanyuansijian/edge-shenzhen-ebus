package org.zero.studio.edge.shenzhen.ebus.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import org.apache.camel.ProducerTemplate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zero.studio.carriage.api.edge.shenzhen.ebus.DetailReq;
import org.zero.studio.carriage.api.edge.shenzhen.ebus.DetailResp;
import org.zero.studio.carriage.api.edge.shenzhen.ebus.model.Detail;
import org.zero.studio.edge.shenzhen.ebus.model.LineStation;
import org.zero.studio.edge.shenzhen.ebus.model.Station;
import org.zero.studio.edge.shenzhen.ebus.service.StationService;
import org.zero.studio.toolbox.bean.JSON;
import org.zero.studio.toolbox.dao.EntityDao;

@Service(value = "stationService")
public class StationServiceImpl implements StationService
{
	final static Logger log = Logger.getLogger(StationServiceImpl.class);
	@Resource(name = "ebusProducer")
	ProducerTemplate ebusProducer;
	@Resource(name = "ebusDao")
	EntityDao dao;

	public boolean createAllStations()
	{
		try
		{
			this.operate();
			return true;
		}
		catch (Exception e)
		{
			log.error("Station operation failed", e);
			return false;
		}
	}

	@Transactional(rollbackFor = { Exception.class })
	public void operate() throws Exception
	{
		List<Integer> lineIds = this.dao.selectObjects("select distinct(line_id) from line", Integer.class);
		log.info("operate:action[start],size[" + lineIds.size() + "]");
		int i = 1;
		for (Integer lineId : lineIds)
		{
			log.info("operate:number[" + i + "]");
			DetailReq req = new DetailReq();
			req.setLineId(lineId + "");
			String reqJson = JSON.bean2HttpUrlParams(req);
			String respJson = this.getStationJSON(reqJson);
			DetailResp resp = JSON.json2Bean(respJson, DetailResp.class);
			Detail detail = resp.getReturnData();
			final List<Station> stations = new ArrayList<>();
			final List<LineStation> lineStations = new ArrayList<>();
			this.convertStation(detail, lineId, stations, lineStations);
			for (Station s : stations)
			{
				dao.insertEntity(s, Station.class);
			}
			for (LineStation ls : lineStations)
			{
				dao.insertEntity(ls, LineStation.class);
			}
			i++;
		}
		log.info("operate:action[end],total[" + i + "]");
	}

	private String getStationJSON(String reqJson)
	{
		String endpoint = "direct:ebus.web.api.detail";
		log.info("getStation:requestHttpParams[" + reqJson + "]");
		String respJson = this.ebusProducer.requestBody(endpoint, reqJson, String.class);
		if (respJson == null || "".equalsIgnoreCase(respJson.trim()))
		{
			return this.getStationJSON(reqJson);
		}
		log.info("getStation:responseJson[" + respJson + "]");
		return respJson;
	}

	private void convertStation(Detail detail, Integer lineId, final List<Station> stations, final List<LineStation> lineStations)
	{
		String[] onIds = detail.getOnStationIds().split(";");
		String[] onNames = detail.getOnStations().split(";");
		String[] onTimes = detail.getOnTimes().split(";");
		String[] onLngLats = detail.getOnLngLat().split(";");
		//
		String[] offIds = detail.getOffStationIds().split(";");
		String[] offNames = detail.getOffStations().split(";");
		String[] offTimes = detail.getOffTimes().split(";");
		String[] offLngLats = detail.getOffLngLat().split(";");
		//
		List<String> onIdList = new ArrayList<String>(Arrays.asList(onIds));
		List<String> offIdList = Arrays.asList(offIds);
		onIdList.addAll(offIdList);
		//
		List<String> onNameList = new ArrayList<String>(Arrays.asList(onNames));
		List<String> offNameList = Arrays.asList(offNames);
		onNameList.addAll(offNameList);
		//
		List<String> onLngLatList = new ArrayList<String>(Arrays.asList(onLngLats));
		List<String> offLngLatList = Arrays.asList(offLngLats);
		onLngLatList.addAll(offLngLatList);
		//
		List<String> onTimeList = new ArrayList<String>(Arrays.asList(onTimes));
		List<String> offTimeList = Arrays.asList(offTimes);
		onTimeList.addAll(offTimeList);
		//
		for (int i = 0; i < onIdList.size(); i++)
		{
			Integer stationId = Integer.parseInt(onIdList.get(i));
			/*
			 * station
			 */
			Station stat = new Station();
			stat.setId(stationId);
			//
			String[] lngLat = onLngLatList.get(i).split(",");
			BigDecimal lngLatX = new BigDecimal(lngLat[0]);
			BigDecimal lngLatY = new BigDecimal(lngLat[1]);
			stat.setLngLatX(lngLatX);
			stat.setLngLatY(lngLatY);
			//
			stat.setName(onNameList.get(i));
			stations.add(stat);
			/*
			 * line_station
			 */
			LineStation lineStat = new LineStation();
			lineStat.setLineId(lineId);
			lineStat.setQq(detail.getQq());
			lineStat.setStationId(stationId);
			lineStat.setVehCode(detail.getVehCode());
			if (onTimeList != null && !onTimeList.isEmpty() && i < onTimeList.size())
				lineStat.setVehTime(onTimeList.get(i));
			lineStat.setSequence(i);
			lineStations.add(lineStat);
		}
	}
}
