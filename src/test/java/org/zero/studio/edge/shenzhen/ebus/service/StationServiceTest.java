package org.zero.studio.edge.shenzhen.ebus.service;

import static org.junit.Assert.fail;
import javax.annotation.Resource;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:*.xml")
public class StationServiceTest
{
	@Resource(name = "stationService")
	StationService stationService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		PropertyConfigurator.configure(ClassLoader.getSystemResource("log4j.properties"));
	}

	@Test
	public void testCreateAllStations()
	{
		boolean b = this.stationService.createAllStations();
		if (b)
		{
			Assert.assertTrue(b);
		}
		else
		{
			fail("Not yet implemented");
		}
	}
}
