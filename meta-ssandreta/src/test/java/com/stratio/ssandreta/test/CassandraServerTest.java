package com.stratio.ssandreta.test;

import java.io.IOException;

import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.thrift.transport.TTransportException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.stratio.ssandreta.CassandraServer;

public class CassandraServerTest {

	private Session _session = null;
	private String cassandraHost = "127.0.0.1";
	private CassandraServer cassandra;

	@BeforeClass
	private void setup() throws ConfigurationException, TTransportException,
			IOException, InterruptedException {
		cassandra = new CassandraServer();
		cassandra.start();
	}

	private boolean connect(String host) {
		boolean result = false;
		Cluster c = Cluster.builder().addContactPoint(host).build();
		_session = c.connect();
		result = null == _session.getLoggedKeyspace();
		return result;
	}

	@Test
	public void cassandraConnection() {
		Assert.assertTrue(connect(cassandraHost));
	}
	
}
