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

package com.stratio.crossdata.common.executionplan;

import com.stratio.crossdata.common.data.ClusterName;
import com.stratio.crossdata.common.exceptions.validation.CoordinationException;
import com.stratio.crossdata.communication.SqlInsert;
import com.stratio.crossdata.communication.SqlOperation;
import com.stratio.crossdata.communication.SqlSelect;

public class SqlWorkflow extends ExecutionWorkflow {

    private static final long serialVersionUID = 760343968145632806L;

    private String sqlQuery;

    private ClusterName clusterName;

    /**
     * Class constructor.
     *
     * @param queryId       Query identifier.
     * @param actorRef      Target actor reference.
     * @param executionType Type of execution.
     * @param type          Type of results.
     */
    public SqlWorkflow(
            String queryId,
            String actorRef,
            ExecutionType executionType,
            ResultType type) {
        super(queryId, actorRef, executionType, type);
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public ClusterName getClusterName() {
        return clusterName;
    }

    public void setClusterName(ClusterName clusterName) {
        this.clusterName = clusterName;
    }

    /**
     * Get the SQL operation to be execution.
     * @return A {@link com.stratio.crossdata.communication.SqlOperation}.
     */
    public SqlOperation getStorageOperation() throws CoordinationException {
        SqlOperation result;
        if(ExecutionType.SQL_INSERT.equals(this.executionType)){
            result = new SqlInsert(queryId, clusterName, this.getSqlQuery());
        } else if(ExecutionType.SQL_SELECT.equals(this.executionType)){
            result = new SqlSelect(queryId, clusterName, this.getSqlQuery());
        } else {
            throw new CoordinationException("Operation " + this.executionType + " not supported yet.");
        }
        return result;
    }

}
