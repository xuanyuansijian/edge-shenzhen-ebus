package org.zero.studio.edge.shenzhen.ebus.service;

import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import org.zero.studio.toolbox.bean.JSON;
import org.zero.studio.toolbox.dao.EntityDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:data-source-access.xml")
public class TempTest
{
	@Resource(name = "ebusDao")
	EntityDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		PropertyConfigurator.configure(ClassLoader.getSystemResource("log4j.properties"));
	}

	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void testCreateLine()
	{
		try
		{
			List<String> a = new ArrayList<String>(Arrays.asList(new String[] { "1", "2" }));
			List<String> b = Arrays.asList(new String[] { "3", "4", "t" });
			a.addAll(b);
			System.out.println(JSON.bean2Json(a));
			List<Integer> lineIds = this.dao.selectObjects("select distinct(line_id) from line", Integer.class);
			System.out.println(lineIds.size());
			System.out.println(lineIds.get(1290));
			Assert.assertTrue(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@After
	public void tearDown() throws Exception
	{
	}
}
