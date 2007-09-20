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


package org.apache.jsieve.commands;

import java.util.Iterator;
import java.util.List;

import org.apache.jsieve.Arguments;
import org.apache.jsieve.Block;
import org.apache.jsieve.CommandManager;
import org.apache.jsieve.SieveContext;
import org.apache.jsieve.StringListArgument;
import org.apache.jsieve.TestManager;
import org.apache.jsieve.exception.FeatureException;
import org.apache.jsieve.exception.LookupException;
import org.apache.jsieve.exception.SieveException;
import org.apache.jsieve.mail.MailAdapter;

/**
 * Class Require implements the Require Command as defined in RFC 3028, section 3.2.
 */
public class Require extends AbstractPrologCommand
{

    /**
     * Constructor for Require.
     */
    public Require()
    {
        super();
    }

    /**
     * <p>Ensure the required feature is configured.</p>
     * <p>Also,
     * @see org.apache.jsieve.commands.AbstractCommand#executeBasic(MailAdapter, Arguments, Block, SieveContext)
     * </p>
     */ 
    protected Object executeBasic(
        MailAdapter mail,
        Arguments arguments,
        Block block, SieveContext context)
        throws SieveException
    {
        Iterator stringsIter =
            ((StringListArgument) arguments.getArgumentList().get(0))
                .getList()
                .iterator();

        while (stringsIter.hasNext())
        {
            validateFeature((String) stringsIter.next(), mail);
        }
        return null;
    }

    /**
     * Method validateFeature validates the required feature is configured as either
     * a Command or a Test.
     * @param name
     * @param mail
     * @throws FeatureException
     */
    protected void validateFeature(String name, MailAdapter mail)
        throws FeatureException
    {
        // Validate as a Command
        try
        {
            validateCommand(name);
            return;
        }
        catch (LookupException e)
        {
            // Not a command
        }       
        
        // Validate as a Test             
        try
        {
            validateTest(name);
        }
        catch (LookupException e)
        {
            throw new FeatureException(
                "Feature \"" + name + "\" is not supported.");
        }
    }
    
    /**
     * Method validateCommand.
     * @param name
     * @throws LookupException
     */
    protected void validateCommand(String name)
        throws LookupException
    {
        CommandManager.getInstance().lookup(name);
    }
    
    /**
     * Method validateTest.
     * @param name
     * @throws LookupException
     */
    protected void validateTest(String name)
        throws LookupException
    {
        TestManager.getInstance().lookup(name);
    }
    
    /**
     * @see org.apache.jsieve.commands.AbstractCommand#validateArguments(Arguments, SieveContext)
     */
    protected void validateArguments(Arguments arguments, SieveContext context) throws SieveException
    {
        List args = arguments.getArgumentList();
        if (args.size() != 1)
            throw context.getCoordinate().syntaxException(
                "Exactly 1 argument permitted. Found " + args.size());

        Object argument = args.get(0);
        if (!(argument instanceof StringListArgument))
            throw context.getCoordinate().syntaxException("Expecting a string-list");
    }
    
}
