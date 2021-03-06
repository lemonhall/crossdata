/*
 * Licensed to STRATIO (C) under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.  The STRATIO (C) licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.stratio.crossdata.core.api;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.stratio.crossdata.common.ask.APICommand;
import com.stratio.crossdata.common.ask.Command;
import com.stratio.crossdata.common.data.ConnectorName;
import com.stratio.crossdata.common.data.DataStoreName;
import com.stratio.crossdata.common.data.Name;
import com.stratio.crossdata.common.exceptions.ManifestException;
import com.stratio.crossdata.common.manifest.BehaviorsType;
import com.stratio.crossdata.common.manifest.ConnectorType;
import com.stratio.crossdata.common.manifest.DataStoreRefsType;
import com.stratio.crossdata.common.manifest.DataStoreType;
import com.stratio.crossdata.common.manifest.PropertiesType;
import com.stratio.crossdata.common.manifest.PropertyType;
import com.stratio.crossdata.common.manifest.SupportedOperationsType;
import com.stratio.crossdata.common.result.CommandResult;
import com.stratio.crossdata.common.result.ErrorResult;
import com.stratio.crossdata.common.result.Result;
import com.stratio.crossdata.core.MetadataManagerTestHelper;
import com.stratio.crossdata.core.metadata.MetadataManager;
import com.stratio.crossdata.core.parser.Parser;
import com.stratio.crossdata.core.planner.Planner;
import com.stratio.crossdata.core.validator.Validator;

public class APIManagerTest {

    private final Parser parser = new Parser();
    private final Validator validator = new Validator();
    private final Planner planner = new Planner();

    @BeforeClass
    public void setUp() throws ManifestException {
        MetadataManagerTestHelper.HELPER.initHelper();
        MetadataManagerTestHelper.HELPER.createTestEnvironment();
    }

    @AfterClass
    public void tearDown() throws Exception {
        MetadataManagerTestHelper.HELPER.closeHelper();
    }

    @Test
    public void testPersistDataStore() throws Exception {

        DataStoreType dataStoreType = new DataStoreType();

        dataStoreType.setName("dataStoreMock");

        dataStoreType.setVersion("0.2.0");

        PropertiesType requiredProperties = new PropertiesType();
        List<PropertyType> property = new ArrayList<>();
        PropertyType propertyType = new PropertyType();
        propertyType.setPropertyName("RequiredProperty");
        propertyType.setDescription("Test");
        property.add(propertyType);
        requiredProperties.setProperty(property);
        dataStoreType.setRequiredProperties(requiredProperties);

        PropertiesType optionalProperties = new PropertiesType();
        property = new ArrayList<>();
        propertyType = new PropertyType();
        propertyType.setPropertyName("OptionalProperty");
        propertyType.setDescription("Test");
        property.add(propertyType);
        optionalProperties.setProperty(property);
        dataStoreType.setOptionalProperties(optionalProperties);

        BehaviorsType behaviorsType = new BehaviorsType();
        List<String> behavior = new ArrayList<>();
        behavior.add("Test");
        behaviorsType.setBehavior(behavior);
        dataStoreType.setBehaviors(behaviorsType);

        List params = new ArrayList();
        params.add(dataStoreType);

        Command cmd = new Command("QID", APICommand.ADD_MANIFEST(), params);

        String expectedResult =
                "CrossdataManifest added " + System.lineSeparator() + "DATASTORE" + System.lineSeparator() +
                        "Name: dataStoreMock" + System.lineSeparator()
                        + "Version: 0.2.0" + System.lineSeparator() + "Required properties: " + System.lineSeparator() +
                        "\tProperty: " + System.lineSeparator() + "\t\tPropertyName: RequiredProperty" +
                        System.lineSeparator() + "\t\tDescription: Test" + System.lineSeparator()
                        + "Optional properties: " +
                        System.lineSeparator() + "\tProperty: " + System.lineSeparator() + "\t\tPropertyName: " +
                        "OptionalProperty" + System.lineSeparator() + "\t\tDescription: Test" + System.lineSeparator() +
                        "Behaviors: " + System.lineSeparator() + "\tBehavior: Test" + System.lineSeparator();

        Result result = MetadataManagerTestHelper.HELPER.getApiManager().processRequest(cmd);

        if(result instanceof ErrorResult){
            fail(System.lineSeparator() +
                "testPersistDataStore failed." + System.lineSeparator() +
                ((ErrorResult)result).getErrorMessage());
        }

        CommandResult commandResult = (CommandResult) result;

        String str = String.valueOf(commandResult.getResult());

        assertTrue(str.equalsIgnoreCase(expectedResult), "- Expected: " + System.lineSeparator() +
                expectedResult + System.lineSeparator() + "-    Found: " + System.lineSeparator() + str);
    }

    @Test
    public void testPersistDataStoreFail() throws Exception {
        APIManager ApiManager = new APIManager(parser, validator, planner);

        DataStoreType dataStoreType = new DataStoreType();

        dataStoreType.setVersion("0.2.0");

        PropertiesType requiredProperties = new PropertiesType();
        List<PropertyType> property = new ArrayList<>();
        PropertyType propertyType = new PropertyType();
        propertyType.setPropertyName("RequiredProperty");
        propertyType.setDescription("Test");
        property.add(propertyType);
        requiredProperties.setProperty(property);
        dataStoreType.setRequiredProperties(requiredProperties);

        PropertiesType optionalProperties = new PropertiesType();
        property = new ArrayList<>();
        propertyType = new PropertyType();
        propertyType.setPropertyName("OptionalProperty");
        propertyType.setDescription("Test");
        property.add(propertyType);
        optionalProperties.setProperty(property);
        dataStoreType.setOptionalProperties(optionalProperties);

        BehaviorsType behaviorsType = new BehaviorsType();
        List<String> behavior = new ArrayList<>();
        behavior.add("Test");
        behaviorsType.setBehavior(behavior);
        dataStoreType.setBehaviors(behaviorsType);

        List params = new ArrayList();
        params.add(dataStoreType);

        Command cmd = new Command("QID", APICommand.ADD_MANIFEST(), params);

        String expectedResult =
                "CrossdataManifest added " + System.lineSeparator() + "DATASTORE" + System.lineSeparator() +
                        "Name: dataStoreTest" + System.lineSeparator()
                        + "Version: 0.2.0" + System.lineSeparator() + "Required properties: " + System.lineSeparator() +
                        "\tProperty: " + System.lineSeparator() + "\t\tPropertyName: RequiredProperty" +
                        System.lineSeparator() + "\t\tDescription: Test" + System.lineSeparator()
                        + "Optional properties: " +
                        System.lineSeparator() + "\tProperty: " + System.lineSeparator() + "\t\tPropertyName: " +
                        "OptionalProperty" + System.lineSeparator() + "\t\tDescription: Test" + System.lineSeparator() +
                        "Behaviors: " + System.lineSeparator() + "\tBehavior: Test" + System.lineSeparator();

        Result result = ApiManager.processRequest(cmd);

        assertTrue(result instanceof ErrorResult,
                "ErrorResult expected." + System.lineSeparator() +
                "Found: " + result.getClass().getCanonicalName());
    }

    @Test
    public void testPersistConnector() throws Exception {
        APIManager ApiManager = new APIManager(parser, validator, planner);

        ConnectorType connectorType = new ConnectorType();

        connectorType.setConnectorName("connectorTest");

        connectorType.setVersion("0.2.0");

        connectorType.setDataStores(new DataStoreRefsType());

        PropertiesType requiredProperties = new PropertiesType();
        List<PropertyType> property = new ArrayList<>();
        PropertyType propertyType = new PropertyType();
        propertyType.setPropertyName("RequiredProperty");
        propertyType.setDescription("Test");
        property.add(propertyType);
        requiredProperties.setProperty(property);
        connectorType.setRequiredProperties(requiredProperties);

        PropertiesType optionalProperties = new PropertiesType();
        property = new ArrayList<>();
        propertyType = new PropertyType();
        propertyType.setPropertyName("OptionalProperty");
        propertyType.setDescription("Test");
        property.add(propertyType);
        optionalProperties.setProperty(property);
        connectorType.setOptionalProperties(optionalProperties);

        SupportedOperationsType supportedOperationsType = new SupportedOperationsType();
        List<String> operation = new ArrayList<>();
        operation.add("PROJECT");
        supportedOperationsType.setOperation(operation);
        connectorType.setSupportedOperations(supportedOperationsType);

        List params = new ArrayList();
        params.add(connectorType);

        Command cmd = new Command("QID", APICommand.ADD_MANIFEST(), params);

        String expectedResult = "CrossdataManifest added " + System.lineSeparator() + "CONNECTOR" +
                System.lineSeparator() + "ConnectorName: connectorTest" + System.lineSeparator()
                + "DataStores: " + System.lineSeparator()
                + "Version: 0.2.0" + System.lineSeparator() + "Required properties: " + System.lineSeparator() +
                "\tProperty: " + System.lineSeparator() + "\t\tPropertyName: RequiredProperty" +
                System.lineSeparator() + "\t\tDescription: Test" + System.lineSeparator() + "Optional properties: " +
                System.lineSeparator() + "\tProperty: " + System.lineSeparator() + "\t\tPropertyName: " +
                "OptionalProperty" + System.lineSeparator() + "\t\tDescription: Test" + System.lineSeparator() +
                "Supported operations: " + System.lineSeparator() + "\tOperation: PROJECT" + System.lineSeparator();

        CommandResult result = (CommandResult) ApiManager.processRequest(cmd);

        String str = String.valueOf(result.getResult());

        assertTrue(str.equalsIgnoreCase(expectedResult), "- Expected: " + System.lineSeparator() +
                expectedResult + System.lineSeparator() + "-    Found: " + System.lineSeparator() + str);
    }

    @Test
    public void testPersistConnectorFail() throws Exception {
        APIManager ApiManager = new APIManager(parser, validator, planner);

        ConnectorType connectorType = new ConnectorType();

        connectorType.setVersion("0.2.0");

        connectorType.setDataStores(new DataStoreRefsType());

        PropertiesType requiredProperties = new PropertiesType();
        List<PropertyType> property = new ArrayList<>();
        PropertyType propertyType = new PropertyType();
        propertyType.setPropertyName("RequiredProperty");
        propertyType.setDescription("Test");
        property.add(propertyType);
        requiredProperties.setProperty(property);
        connectorType.setRequiredProperties(requiredProperties);

        PropertiesType optionalProperties = new PropertiesType();
        property = new ArrayList<>();
        propertyType = new PropertyType();
        propertyType.setPropertyName("OptionalProperty");
        propertyType.setDescription("Test");
        property.add(propertyType);
        optionalProperties.setProperty(property);
        connectorType.setOptionalProperties(optionalProperties);

        SupportedOperationsType supportedOperationsType = new SupportedOperationsType();
        List<String> operation = new ArrayList<>();
        operation.add("PROJECT");
        supportedOperationsType.setOperation(operation);
        connectorType.setSupportedOperations(supportedOperationsType);

        List params = new ArrayList();
        params.add(connectorType);

        Command cmd = new Command("QID", APICommand.ADD_MANIFEST(), params);

        String expectedResult = "CrossdataManifest added " + System.lineSeparator() + "CONNECTOR" +
                System.lineSeparator() + "ConnectorName: connectorTest" + System.lineSeparator()
                + "DataStores: " + System.lineSeparator()
                + "Version: 0.2.0" + System.lineSeparator() + "Required properties: " + System.lineSeparator() +
                "\tProperty: " + System.lineSeparator() + "\t\tPropertyName: RequiredProperty" +
                System.lineSeparator() + "\t\tDescription: Test" + System.lineSeparator() + "Optional properties: " +
                System.lineSeparator() + "\tProperty: " + System.lineSeparator() + "\t\tPropertyName: " +
                "OptionalProperty" + System.lineSeparator() + "\t\tDescription: Test" + System.lineSeparator() +
                "Supported operations: " + System.lineSeparator() + "\tOperation: PROJECT" + System.lineSeparator();

        Result result = ApiManager.processRequest(cmd);

        assertTrue(result instanceof ErrorResult,
                "ErrorResult expected." + System.lineSeparator() +
                "Found: " + result.getClass().getCanonicalName());
    }

    @Test(dependsOnMethods = { "testPersistConnector" })
    public void testListConnectors() throws Exception {
        APIManager ApiManager = new APIManager(parser, validator, planner);
        Command cmd = new Command("QID", APICommand.DESCRIBE_CONNECTORS(), null);
        MetadataManagerTestHelper.HELPER.createTestConnector("connectorTest", new DataStoreName("datastoreTest"), "akkaActorRef");
        CommandResult result = (CommandResult) ApiManager.processRequest(cmd);

        /*
        String expectedResult = System.lineSeparator() + "Connector: connector.connectortest" +
                "\tONLINE\t[]\t[datastore.datastoretest]\takkaActorRef" + System.lineSeparator();
        */

        String str = String.valueOf(result.getResult());
        String[] connectors = str.split(System.lineSeparator());

        int expectedSize = 1;

        assertEquals((connectors.length-1), expectedSize,
                System.lineSeparator() +
                "testListConnectors failed." + System.lineSeparator() +
                "Expected number of connectors: " + expectedSize + System.lineSeparator() +
                "Number of connectors found:    " + (connectors.length-1));

        /*
        assertTrue(str.equalsIgnoreCase(expectedResult), "Expected: " + expectedResult + System.lineSeparator() +
                "   Found: " + str);
        */
    }

    @Test(dependsOnMethods = { "testListConnectors" })
    public void testResetMetadata() throws Exception {
        APIManager ApiManager = new APIManager(parser, validator, planner);
        MetadataManagerTestHelper.HELPER.createTestConnector("connectorTest2", new DataStoreName("datastoreTest"), "akkaActorRef");
        Command cmd = new Command("QID", APICommand.RESET_SERVERDATA(), null);
        CommandResult result = (CommandResult) ApiManager.processRequest(cmd);

        String str = String.valueOf(result.getResult());
        String expectedAnswer = "Crossdata server reset.";
        assertTrue(str.equals(expectedAnswer), System.lineSeparator() + "Expected: " + expectedAnswer +
                System.lineSeparator() + "   Found: " + str);

        assertTrue(MetadataManager.MANAGER.getCatalogs().isEmpty(), "Catalogs should be empty.");
        assertTrue(MetadataManager.MANAGER.getClusters().isEmpty(), "Clusters should be empty.");
        assertTrue(MetadataManager.MANAGER.getColumns().isEmpty(), "Columns should be empty");
        assertTrue(MetadataManager.MANAGER.getDatastores().isEmpty(), "Datastores should be empty");
        assertTrue(MetadataManager.MANAGER.getNodes().isEmpty(), "Nodes should be empty");
        assertTrue(MetadataManager.MANAGER.getTables().isEmpty(), "Tables should be empty");
        assertTrue(MetadataManager.MANAGER.getIndexes().isEmpty(), "Indexes should be empty");

        Name n = new ConnectorName("connectorTest2");
        assertTrue(MetadataManager.MANAGER.exists(n), "MetadataManager should maintain the connector basic info");
    }

    @Test
    public void testConstructor() throws Exception {
        APIManager ApiManager = new APIManager(parser, validator, planner);
        assertNotNull(ApiManager, "ApiManager shouldn't be null");
    }

    @Test
    public void testAddDataStore() throws Exception {
        APIManager ApiManager = new APIManager(parser, validator, planner);
        List params = new ArrayList<DataStoreType>();
        DataStoreType dataStoreType = new DataStoreType();
        dataStoreType.setName("CassandraDataStore");
        dataStoreType.setVersion("1.0");

        PropertiesType propertiesType = new PropertiesType();
        PropertyType prop = new PropertyType();
        prop.setPropertyName("DefaultLimit");
        prop.setDescription("Description");
        List<PropertyType> list = new ArrayList<>();
        list.add(prop);
        propertiesType.setProperty(list);
        dataStoreType.setRequiredProperties(propertiesType);

        params.add(dataStoreType);
        Command cmd = new Command("QID", APICommand.ADD_MANIFEST(), params);
        Result result = ApiManager.processRequest(cmd);
        assertTrue(result instanceof CommandResult, "testProcessRequest should return a CommandResult");
        CommandResult cmdR = (CommandResult) result;
        String resultStr = (String) cmdR.getResult();

        assertTrue(resultStr.startsWith("CrossdataManifest added"),
                "Expected: " + "CrossdataManifest added" + System.lineSeparator() +
                "Found:    " + resultStr);
    }

}
