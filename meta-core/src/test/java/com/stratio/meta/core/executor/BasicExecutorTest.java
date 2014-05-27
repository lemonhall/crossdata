/*
 * Stratio Meta
 *
 * Copyright (c) 2014, Stratio, All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */

package com.stratio.meta.core.executor;

import com.stratio.deep.context.DeepSparkContext;
import com.stratio.meta.common.result.QueryResult;
import com.stratio.meta.common.result.Result;
import com.stratio.meta.core.cassandra.BasicCoreCassandraTest;
import com.stratio.meta.core.engine.EngineConfig;
import com.stratio.meta.core.metadata.MetadataManager;
import com.stratio.meta.core.utils.MetaQuery;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.*;

public class BasicExecutorTest extends BasicCoreCassandraTest {

    protected static Executor executor = null;

    protected static DeepSparkContext deepContext = null;

    protected static MetadataManager metadataManager = null;

    private final static Logger LOG = Logger.getLogger(BasicExecutorTest.class);

    @BeforeClass
    public static void setUpBeforeClass() {
        BasicCoreCassandraTest.setUpBeforeClass();
        BasicCoreCassandraTest.loadTestData("demo", "demoKeyspace.cql");
        EngineConfig config = initConfig();
        deepContext = new DeepSparkContext(config.getSparkMaster(), config.getJobName());
        executor = new Executor(_session, deepContext, config);
        metadataManager = new MetadataManager(_session);
        metadataManager.loadMetadata();
    }

    @AfterClass
    public static void tearDownAfterClass(){
        deepContext.stop();
    }
    public static EngineConfig initConfig() {
        String[] cassandraHosts = {"127.0.0.1"};
        EngineConfig engineConfig = new EngineConfig();
        engineConfig.setCassandraHosts(cassandraHosts);
        engineConfig.setCassandraPort(9042);
        engineConfig.setSparkMaster("local");
        return engineConfig;
    }

    public Result validateOk(MetaQuery metaQuery, String methodName) {
        MetaQuery result = executor.executeQuery(metaQuery);
        assertNotNull(result.getResult(), "Result null - " + methodName);
        assertFalse(result.hasError(), metaQuery.getPlan().getNode().getPath()+" execution failed - " + methodName + ": " + result.getResult().getErrorMessage());
        return result.getResult();
    }

    public Result validateRows(MetaQuery metaQuery, String methodName, int expectedNumber) {
        QueryResult result = (QueryResult) validateOk(metaQuery, methodName);
        if (expectedNumber > 0) {
            assertFalse(result.getResultSet().isEmpty(), "Expecting non-empty resultset");
            assertEquals(result.getResultSet().size(), expectedNumber, methodName + ":" + result.getResultSet().size() + " rows found, " + expectedNumber + " rows expected.");
        } else {
            assertTrue(result.getResultSet().isEmpty(), "Expecting empty resultset.");
            assertNull(result.getResultSet(), methodName + ": Result should be null");
        }

        return result;
    }

    public void validateFail(MetaQuery metaQuery, String methodName) {
        try {
            MetaQuery result = executor.executeQuery(metaQuery);
        } catch (Exception ex) {
            LOG.info("Correctly caught exception");
        }
    }

}