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

package com.stratio.crossdata.core.grammar.statements;

import org.testng.annotations.Test;

import com.stratio.crossdata.core.grammar.ParsingTest;

public class DropTableStatementTest extends ParsingTest {

    @Test
    public void dropTable() {
        String inputText = "DROP TABLE IF EXISTS lastTable;";
        String expectedText = "DROP TABLE IF EXISTS demo.lastTable;";
        testRegularStatementSession("demo", inputText, expectedText, "dropTable");
    }

    @Test
    public void dropNotMissing() {
        String inputText = "DROP TABLE IF EXISTS _lastTable;";
        testParserFails(inputText, "dropNotMissing");
    }

    @Test
    public void dropTableWithCatalog() {
        String inputText = "[ oldcatalog ], DROP TABLE lastTable;";
        String expectedText = "DROP TABLE oldcatalog.lastTable;";
        testRegularStatement(inputText, expectedText, "dropTableWithCatalog");
    }

    @Test
    public void dropTableWithEmptyCatalog() {
        String inputText = "[test], DROP TABLE lastTable;";
        String expectedText = "DROP TABLE test.lastTable;";
        testRegularStatement(inputText, expectedText, "dropTableWithEmptyCatalog");
    }

}
