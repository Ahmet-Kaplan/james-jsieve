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
package org.apache.jsieve.mail;

import java.util.List;
import java.util.ListIterator;

import org.apache.jsieve.SieveException;

/**
 * <p>Interface <code>MailAdapter</code> defines the minimum functionality required of
 * of a class implementing a mail message. This is the functionality neccesary to
 * implement the Commands and Tests that RFC32028 mandates MUST be implemented.
 * </p>
 * 
 * <p>Typically, implementations will wrap an application's pre-existing mail 
 * message implementation. It is expected that implementations will extend the
 * minimum level of functionality to provide support for Command and Test 
 * extensions that exploit the capabilities of a particular application.</p>
 */
public interface MailAdapter
{
    /**
     * Method getActions answers the List of Actions accumulated by the receiver.
     * @return Action
     */
    public List getActions();
    
    /**
     * Method getActionIteraror answers an Iterator over the List of Actions
     * accumulated by the receiver.
     * @return Action
     */
    public ListIterator getActionsIterator();
    
    /**
     * Method getHeader answers a List of all of the headers in the receiver whose 
     * name is equal to the passed name. If no headers are found an empty List is 
     * returned.
     * 
     * @param name
     * @return List
     * @throws SieveMailException
     */
    public List getHeader(String name) throws SieveMailException;
    
    /**
     * <p>Method getMatchingHeader answers a List of all of the headers in the 
     * receiver with the passed name. If no headers are found an empty List is 
     * returned.
     * </p>
     * 
     * <p>This method differs from getHeader(String) in that it ignores case and the 
     * whitespace prefixes and suffixes of a header name when performing the
     * match, as required by RFC 3028. Thus "From", "from ", " From" and " from "
     * are considered equal.
     * </p>
     * 
     * @param name
     * @return List
     * @throws SieveMailException
     */
    public List getMatchingHeader(String name)
        throws SieveMailException;
        
    /**
     * Method getHeaderNames answers a List of all of the headers in the receiver.
     * No duplicates are allowed.
     * @return List
     * @throws SieveMailException
     */
    public List getHeaderNames() throws SieveMailException;       
    
    /**
     * Method addAction adds an Action to the List of Actions to be performed by the
     * receiver.
     * @param action
     */
    public void addAction(Action action); 
    
    /**
     * Method executeActions. Applies the Actions accumulated by the receiver.
     */
    public void executeActions() throws SieveException;            



    /**
     * Method getSize answers the receiver's message size in octets.
     * @return int
     * @throws SieveMailException
     */
    int getSize() throws SieveMailException;

}
