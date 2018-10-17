package org.zero.studio.edge.shenzhen.ebus.model;

public class LineStation
{
	private Integer lineId;
	private Integer stationId;
	private String vehTime;
	private String vehCode;
	private String qq;
	private Integer sequence;

	public Integer getSequence()
	{
		return sequence;
	}

	public void setSequence(Integer sequence)
	{
		this.sequence = sequence;
	}

	public Integer getLineId()
	{
		return lineId;
	}

	public void setLineId(Integer lineId)
	{
		this.lineId = lineId;
	}

	public Integer getStationId()
	{
		return stationId;
	}

	public void setStationId(Integer stationId)
	{
		this.stationId = stationId;
	}

	public String getVehTime()
	{
		return vehTime;
	}

	public void setVehTime(String vehTime)
	{
		this.vehTime = vehTime;
	}

	public String getVehCode()
	{
		return vehCode;
	}

	public void setVehCode(String vehCode)
	{
		this.vehCode = vehCode;
	}

	public String getQq()
	{
		return qq;
	}

	public void setQq(String qq)
	{
		this.qq = qq;
	}
}
