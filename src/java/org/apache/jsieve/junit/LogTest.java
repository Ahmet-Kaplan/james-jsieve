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
package org.apache.jsieve.junit;

import org.apache.jsieve.CommandManager;
import org.apache.jsieve.SieveException;
import org.apache.jsieve.TestManager;
import org.apache.jsieve.junit.utils.TestUtils;
import org.apache.jsieve.parser.generated.ParseException;

import junit.framework.TestCase;

/**
 * Class LogTest
 */
public class LogTest extends TestCase
{

    /**
     * Constructor for LogTest.
     * @param arg0
     */
    public LogTest(String arg0)
    {
        super(arg0);
    }

    public static void main(String[] args)
    {
        junit.swingui.TestRunner.run(LogTest.class);
    }

    /**
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        CommandManager.resetInstance();
        TestManager.resetInstance();         
    }

    /**
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    /**
     * Test for Command 'log'.
     */
    public void testLogDebug()
    {
        boolean isTestPassed = false;
        String script = "log :debug \"Log a debug message.\";";

        try
        {
            TestUtils.interpret(TestUtils.createMail(), script);
            isTestPassed = true;            
        }       
        catch (ParseException e)
        {
        }
        catch (SieveException e)
        {
        }
        assertTrue(isTestPassed);        
    }    
    
    /**
     * Test for Command 'log'.
     */
    public void testLogError()
    {
        boolean isTestPassed = false;
        String script = "log :error \"Log an error message.\";";

        try
        {
            TestUtils.interpret(TestUtils.createMail(), script);
            isTestPassed = true;            
        }       
        catch (ParseException e)
        {
        }
        catch (SieveException e)
        {
        }
        assertTrue(isTestPassed);        
    }
    
    /**
     * Test for Command 'log'.
     */
    public void testLogFatal()
    {
        boolean isTestPassed = false;
        String script = "log :fatal \"Log a fatal message.\";";

        try
        {
            TestUtils.interpret(TestUtils.createMail(), script);
            isTestPassed = true;            
        }       
        catch (ParseException e)
        {
        }
        catch (SieveException e)
        {
        }
        assertTrue(isTestPassed);        
    }
    
    /**
     * Test for Command 'log'.
     */
    public void testLogInfo()
    {
        boolean isTestPassed = false;
        String script = "log :info \"Log an info message.\";";

        try
        {
            TestUtils.interpret(TestUtils.createMail(), script);
            isTestPassed = true;            
        }       
        catch (ParseException e)
        {
        }
        catch (SieveException e)
        {
        }
        assertTrue(isTestPassed);        
    }
    
    /**
     * Test for Command 'log'.
     */
    public void testLogTrace()
    {
        boolean isTestPassed = false;
        String script = "log :trace \"Log a trace message.\";";

        try
        {
            TestUtils.interpret(TestUtils.createMail(), script);
            isTestPassed = true;            
        }       
        catch (ParseException e)
        {
        }
        catch (SieveException e)
        {
        }
        assertTrue(isTestPassed);        
    }
    
    /**
     * Test for Command 'log'.
     */
    public void testLogWarn()
    {
        boolean isTestPassed = false;
        String script = "log :warn \"Log a warning message.\";";

        try
        {
            TestUtils.interpret(TestUtils.createMail(), script);
            isTestPassed = true;            
        }       
        catch (ParseException e)
        {
        }
        catch (SieveException e)
        {
        }
        assertTrue(isTestPassed);        
    }
    
    /**
     * Test for Command 'log'.
     */
    public void testLogDefault()
    {
        boolean isTestPassed = false;
        String script = "log \"Log a default message.\";";

        try
        {
            TestUtils.interpret(TestUtils.createMail(), script);
            isTestPassed = true;            
        }       
        catch (ParseException e)
        {
        }
        catch (SieveException e)
        {
        }
        assertTrue(isTestPassed);        
    }        


        


        


        


        

}
