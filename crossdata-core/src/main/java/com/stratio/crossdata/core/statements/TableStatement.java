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

package com.stratio.crossdata.core.statements;

import com.stratio.crossdata.common.data.CatalogName;
import com.stratio.crossdata.common.data.TableName;

/**
 * Class with the logic of the tables statement.
 */
public class TableStatement implements ITableStatement {
    /**
     * The name of the table.
     */
    protected TableName tableName;

    /**
     * The catalog name.
     */
    protected CatalogName catalog;

    /**
     * The catalog name session.
     */
    protected CatalogName sessionCatalog;

    /**
     * Get the table name.
     * @return A {@link com.stratio.crossdata.common.data.TableName} .
     */
    public TableName getTableName() {
        return tableName;
    }

    /**
     * Set the table name.
     * @param tableName The name.
     */
    public void setTableName(TableName tableName) {
        this.tableName = tableName;
    }
    
    @Override
    public CatalogName getEffectiveCatalog() {
        CatalogName effective;
        if (tableName != null) {
            effective = tableName.getCatalogName();
        } else {
            effective = catalog;
        }
        if (sessionCatalog != null) {
            effective = sessionCatalog;
        }
        return effective;
    }

}
