package org.zero.studio.edge.shenzhen.ebus.service;

import org.zero.studio.carriage.api.edge.shenzhen.ebus.LineReq;
import org.zero.studio.carriage.api.edge.shenzhen.ebus.LineResp;
import org.zero.studio.carriage.api.edge.shenzhen.ebus.model.Line;

/**
 * 
 * @author Administrator
 *
 */
public interface LineService
{
	LineResp getLine(LineReq req) throws Exception;

	int createLine(Line line) throws Exception;

	int createAllLines() throws Exception;
}
