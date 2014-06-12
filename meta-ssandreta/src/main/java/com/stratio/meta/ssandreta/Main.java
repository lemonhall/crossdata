package com.stratio.meta.ssandreta;

import java.io.IOException;

public class Main {
	public static CassandraServer cassandraServer;
	public static final int CQL_PORT = 9142;

	public static void main(String[] args) {
		startCassandra();
	}
	public static void startCassandra(){
		try {
			cassandraServer = new CassandraServer();
			cassandraServer.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void stopCassandra(){
		if (cassandraServer != null) {
			try {
				cassandraServer.shutdown();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
