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

package com.stratio.meta.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class EmbeddedCassandraHandler {

    /**
     * Class logger.
     */
    private static final Logger LOG = Logger.getLogger(EmbeddedCassandraHandler.class);

    /**
     * Private class constructor as all methods are static.
     */
    private EmbeddedCassandraHandler(){
    }

    /**
     * Start a test Cassandra embedded server to execute the unit tests. The method creates a
     * temporal file with the contents of {@code /com/stratio/meta/test/cassandraEmbedded.sh} and proceeds
     * with its execution.
     */
    public static void startEmbeddedCassandra(){
        BufferedReader in = null;
        try {
            File tempFile = File.createTempFile("stratio-embedded-cassandra-start",".sh");
            InputStream resourceStream = EmbeddedCassandraHandler.class.getResourceAsStream("/com/stratio/meta/test/cassandraEmbedded.sh");
            FileUtils.copyInputStreamToFile(resourceStream,tempFile);
            tempFile.setExecutable(true);

            Process p = Runtime.getRuntime().exec(tempFile.getAbsolutePath());

            in = new BufferedReader(new InputStreamReader(p.getInputStream(), Charset.forName("UTF-8")));

            String line;
            while ((line = in.readLine()) != null) {
                LOG.debug(line);
            }
            FileUtils.forceDeleteOnExit(tempFile);

        } catch (IOException e) {
            LOG.error("Error starting embedded Cassandra", e);
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    LOG.error("IO exception closing embeddedCassandra output.", e);
                }
            }
        }
    }

}
