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

package com.stratio.crossdata.common.exceptions.validation;

import com.stratio.crossdata.common.exceptions.ValidationException;
import com.stratio.crossdata.common.data.Name;

/**
 * Exception class that is thrown by the normalizator when a Name not exists yet in Metadata Manager.
 */
public class NotExistNameException extends ValidationException {
    /**
     * Constructor Class.
     * @param name The Name that is not found in Metadata Manager.
     */
    public NotExistNameException(Name name) {
        super("[" + name + "]  doesn't exist yet");
    }
}
