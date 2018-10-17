package org.zero.studio.edge.shenzhen.ebus.service.impl;

import javax.annotation.Resource;
import org.apache.camel.ProducerTemplate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.zero.studio.carriage.api.edge.shenzhen.ebus.LineReq;
import org.zero.studio.carriage.api.edge.shenzhen.ebus.LineResp;
import org.zero.studio.carriage.api.edge.shenzhen.ebus.model.Line;
import org.zero.studio.edge.shenzhen.ebus.service.LineService;
import org.zero.studio.toolbox.bean.JSON;
import org.zero.studio.toolbox.dao.EntityDao;

@Service(value = "lineService")
public class LineServiceImpl implements LineService
{
	final static Logger log = Logger.getLogger(LineServiceImpl.class);
	@Resource(name = "ebusProducer")
	ProducerTemplate ebusProducer;
	@Resource(name = "ebusDao")
	EntityDao dao;

	public LineResp getLine(LineReq req) throws Exception
	{
		log.info("getLine:request[" + JSON.bean2Json(req) + "]");
		String endpoint = "direct:ebus.web.api.line";
		String reqJson = JSON.bean2HttpUrlParams(req);
		log.info("getLine:requestHttpParams[" + reqJson + "]");
		String respJson = this.ebusProducer.requestBody(endpoint, reqJson, String.class);
		if(respJson == null || "".equalsIgnoreCase(respJson.trim()))
		{
			return this.getLine(req);
		}
		log.info("getLine:response[" + respJson + "]");
		LineResp resp = JSON.json2Bean(respJson, LineResp.class);
		log.info("getLine:response[" + resp.getReturnCode() + "," + resp.getReturnInfo() + "," + resp.getReturnSize() + "]");
		return resp;
	}

	public int createLine(Line line) throws Exception
	{
		LineReq lineReq = new LineReq();
		lineReq.setLineNo("P");
		lineReq.setPageNo("1");
		lineReq.setPageSize("2");
		LineResp lineResp = this.getLine(lineReq);
		return dao.insertEntities(lineResp.getReturnData(), Line.class);
	}

	public int createAllLines() throws Exception
	{
		String lineNo = "P";
		LineReq countReq = new LineReq();
		countReq.setLineNo(lineNo);
		countReq.setPageNo("1");
		countReq.setPageSize("0");
		LineResp countResp = this.getLine(countReq);
		int size = countResp.getReturnSize();
		int pageSize = 50;
		int pageTotal = size / pageSize + 1;
		for (int pageNo = 1; pageNo <= pageTotal; pageNo++)
		{
			LineReq lineReq = new LineReq();
			lineReq.setLineNo(lineNo);
			lineReq.setPageNo(pageNo + "");
			lineReq.setPageSize(pageSize + "");
			LineResp lineResp = this.getLine(lineReq);
			dao.insertEntities(lineResp.getReturnData(), Line.class);
			log.info("createAllLines:times[" + pageNo + "]");
		}
		return 0;
	}
}
