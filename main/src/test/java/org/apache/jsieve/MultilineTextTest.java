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

import junit.framework.TestCase;

import org.apache.jsieve.exception.SieveException;
import org.apache.jsieve.mail.ActionReject;
import org.apache.jsieve.mail.MailAdapter;
import org.apache.jsieve.parser.generated.ParseException;
import org.apache.jsieve.utils.JUnitUtils;

/**
 * Class RejectTest
 */
public class MultilineTextTest extends TestCase {
    
    /**
     * Tests that a multiline message is correctly passed
     */
    public void testRejectMultilineMessage() throws Exception {
        String message = "This is not a love song";
        String script = "reject text:\n" + message + "\n.\n;";
        ActionReject rejection = runRejectScript(script);        
        assertEquals(message, rejection.getMessage());
    }
    
    /**
     * Tests that a multiline message is correctly passed when whitespace is inserted
     * between the command and the content.
     */
    public void testRejectMultilineMessageWithWhitespace() throws Exception {
        String message = "This is not a love song";
        String script = "reject text: \t \t \n" + message + "\n.\n;";
        ActionReject rejection = runRejectScript(script);        
        assertEquals(message, rejection.getMessage());
    }    

    private ActionReject runRejectScript(String script) throws SieveException, ParseException {
        MailAdapter mail = JUnitUtils.createMail();
        JUnitUtils.interpret(mail, script);
        assertTrue(mail.getActions().size() == 1);
        Object action = mail.getActions().get(0);
        assertTrue(action instanceof ActionReject);
        ActionReject rejection = (ActionReject) action;
        return rejection;
    }
}
