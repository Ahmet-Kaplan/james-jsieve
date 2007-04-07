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


package org.apache.jsieve.commands.optional;

import java.util.List;

import org.apache.jsieve.Arguments;
import org.apache.jsieve.Block;
import org.apache.jsieve.CommandException;
import org.apache.jsieve.SieveContext;
import org.apache.jsieve.SieveException;
import org.apache.jsieve.StringListArgument;
import org.apache.jsieve.commands.AbstractActionCommand;
import org.apache.jsieve.commands.CommandStateManager;
import org.apache.jsieve.mail.ActionReject;
import org.apache.jsieve.mail.MailAdapter;

/**
 * Class | Interface Enter description here
 * 
 * Creation Date: 11-Jan-04
 * @author sbrewin
 * 
 * Copyright 2003, Synergy Systems Limited
 */
/**
 * Class Reject implements the Reject Command as defined in RFC 3028, section 4.1.
 */
public class Reject extends AbstractActionCommand
{

    /**
     * Constructor for Reject.
     */
    public Reject()
    {
        super();
    }

    /**
     * <p>Add an ActionReject to the List of Actions to be performed.</p>
     * <p>Also,
     * @see org.apache.jsieve.commands.AbstractCommand#executeBasic(MailAdapter, Arguments, Block, SieveContext)
     * </p>
     */  
    protected Object executeBasic(MailAdapter mail, Arguments arguments, Block block, SieveContext context)
        throws SieveException
    {
        String message =
            (String) ((StringListArgument) arguments.getArgumentList().get(0))
                .getList()
                .get(
                0);
                        
        mail.addAction(new ActionReject(message));
        return null;
    }
    
    /**
     * @see org.apache.jsieve.commands.AbstractCommand#validateState(SieveContext)
     */
    protected void validateState(SieveContext context) throws CommandException
    {
        super.validateState(context);

        if (CommandStateManager.getInstance().isHasActions())
            throw new CommandException("The \"reject\" command is not allowed with other Action Commands");
    }
    
    /**
     * @see org.apache.jsieve.commands.AbstractCommand#updateState()
     */
    protected void updateState()
    {
        super.updateState();        
        CommandStateManager.getInstance().setRejected(true);        
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

        if (1 != ((StringListArgument) argument).getList().size())
            throw context.getCoordinate().syntaxException("Expecting exactly one argument");
    }  

}
