package com.stratio.ssandreta;

import java.io.IOException;

public class MainTest {
	private static CassandraServer cassandraServer;
	public static final int CQL_PORT = 9142;

	public static void main(String[] args) {

		// File dir = Files.createTempDir();
		// String dirPath = dir.getAbsolutePath();
		//
		// URL url = Resources.getResource("cassandra.yaml");
		// String yaml = Resources.toString(url, Charsets.UTF_8); //apache?
		// yaml = yaml.replaceAll("REPLACEDIR", dirPath);
		// String yamlPath = dirPath + File.separatorChar + "cassandra.yaml";
		// org.apache.commons.io.FileUtils.writeStringToFile(new File(yamlPath),
		// yaml);
		//
		// Constructor constructor = new Constructor();
		// PropertyUtils propertiesChecker = new PropertyUtils();
		// constructor.setPropertyUtils(propertiesChecker);
		//
		//
		// DatabaseDescriptor.createAllDirectories();
		// System.out.println("completed");



	}
	public void startCassandra(){
		try {
			cassandraServer = new CassandraServer();
			cassandraServer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stopCassandra(){
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
