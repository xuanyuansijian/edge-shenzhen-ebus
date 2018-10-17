package org.zero.studio.edge.shenzhen.ebus.service;

import static org.junit.Assert.fail;
import javax.annotation.Resource;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:*.xml")
public class LineServiceTest
{
	@Resource(name = "lineService")
	LineService lineService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		PropertyConfigurator.configure(ClassLoader.getSystemResource("log4j.properties"));
	}

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

	public void testCreateLine()
	{
		try
		{
			lineService.createLine(null);
			Assert.assertTrue(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void testCreateAllLines()
	{
		try
		{
			lineService.createAllLines();
			Assert.assertTrue(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}
}
