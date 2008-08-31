/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/

package org.apache.jsieve;

import org.apache.commons.logging.Log;
import org.apache.jsieve.comparators.Comparator;
import org.apache.jsieve.exception.LookupException;
import org.apache.jsieve.tests.ExecutableTest;

/**
 * Context for sieve operations.
 * 
 */
public abstract class SieveContext {

    /**
     * Gets the script position of the current operation.
     * 
     * @return <code>ScriptCoordinate</code>, not null
     */
    public abstract ScriptCoordinate getCoordinate();

    /**
     * Sets the script position of the current operation.
     * 
     * @param coordinate
     *                <code>ScriptCoordinate</code>, not null
     */
    public abstract void setCoordinate(ScriptCoordinate coordinate);
    
    //TODO: simplify interface
    public abstract CommandStateManager getCommandStateManager();
    
    //TODO: simplify interface
    public abstract ConditionManager getConditionManager();
    //TODO: simplify interface
    public abstract void setConditionManager(final ConditionManager manager);
    //TODO: consider whether API can be consolidated
    public abstract ExecutableCommand getExecutableManager(String name) throws LookupException;
    //TODO: consider whether API can be consolidated
    public abstract Comparator getComparator(String name) throws LookupException;
    //TODO: consider whether API can be consolidated
    public abstract ExecutableTest getExecutableTest(String name) throws LookupException;
    
    public abstract Log getLog();
}
