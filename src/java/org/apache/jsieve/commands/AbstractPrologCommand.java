/* ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2000-2004 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Apache", "Jakarta", "JAMES", "JSieve" and 
 *    "Apache Software Foundation" must not be used to endorse or promote
 *    products derived from this software without prior written permission.
 *    For written permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * Portions of this software are based upon public domain software
 * originally written at the National Center for Supercomputing Applications,
 * University of Illinois, Urbana-Champaign.
 */
package org.apache.jsieve.commands;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import org.apache.jsieve.Arguments;
import org.apache.jsieve.Block;
import org.apache.jsieve.Command;
import org.apache.jsieve.CommandException;
import org.apache.jsieve.CommandManager;
import org.apache.jsieve.FeatureException;
import org.apache.jsieve.LookupException;
import org.apache.jsieve.SieveException;
import org.apache.jsieve.StringListArgument;
import org.apache.jsieve.SyntaxException;
import org.apache.jsieve.TestManager;
import org.apache.jsieve.mail.MailAdapter;

/**
 * <p>Abstract class AbstractPrologCommand defines the common state validation behavior
 * for Prolog Commands. In RFC 3028 the only Prolog Command is 'requires', however,
 * the specification may evolve.</p>
 */
public abstract class AbstractPrologCommand extends AbstractCommand
{

    /**
     * Constructor for AbstractPrologCommand.
     */
    public AbstractPrologCommand()
    {
        super();
    }

    /**
     * <p>Method validateState() ensures, via the CommandStateManager, that a Prolog
     * Command is permissible.</p>
     * 
     * <p>Also, @see org.apache.jsieve.commands.AbstractCommand#validateState()</p>
     */
    protected void validateState()
            throws CommandException
    {
        super.validateState();
        
        if (!(CommandStateManager.getInstance().isInProlog()))
            throw new CommandException("Invalid state for a prolog command.");     
    }
    

    
}
