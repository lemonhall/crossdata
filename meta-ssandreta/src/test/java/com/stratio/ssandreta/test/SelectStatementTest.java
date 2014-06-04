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

package com.stratio.ssandreta.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class SelectStatementTest extends BasicCoreCassandraTest {


    @BeforeClass
    public static void setUpBeforeClass(){
        BasicCoreCassandraTest.setUpBeforeClass();
        BasicCoreCassandraTest.loadTestData("demo", "/demoKeyspace.cql");
    }

    @Test
    public void simpleCassandraSelectTest(){
    	_session.execute("SELECT * FROM demo.users");
    	System.out.println("llego a ejecutar test");
    }
    
    
}
