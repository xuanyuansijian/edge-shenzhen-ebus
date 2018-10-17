package org.zero.studio.edge.shenzhen.ebus.model;

import java.math.BigDecimal;

public class Station
{
	private Integer id;
	private BigDecimal lngLatX;
	private BigDecimal lngLatY;
	private String name;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public BigDecimal getLngLatX()
	{
		return lngLatX;
	}

	public void setLngLatX(BigDecimal lngLatX)
	{
		this.lngLatX = lngLatX;
	}

	public BigDecimal getLngLatY()
	{
		return lngLatY;
	}

	public void setLngLatY(BigDecimal lngLatY)
	{
		this.lngLatY = lngLatY;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
