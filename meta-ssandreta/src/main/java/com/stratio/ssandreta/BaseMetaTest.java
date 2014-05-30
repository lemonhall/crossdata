package com.stratio.ssandreta;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseMetaTest {
	private CassandraServer cassandraServer;
	public static final int CQL_PORT = 9142;
	
	@BeforeSuite
	public void setup() throws Exception {
		try {
			cassandraServer = new CassandraServer();
			cassandraServer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@AfterSuite
	public void closeAndClean() throws Exception {
		if (cassandraServer != null) {
			cassandraServer.shutdown();
		}
	}


}
