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
package org.apache.jsieve;
import java.util.HashMap;
import java.util.Map;

import org.apache.jsieve.comparators.Comparator;

/**
 * Singleton class <code>ComparatorManager</code> maps Comparator names to configured
 * Comparator implementation classes.
 */
public class ComparatorManager
{
    /**
     * The sole instance of the receiver.
     */ 
    static private ComparatorManager fieldInstance;

    /**
     * Constructor for ComparatorManager.
     */
    private ComparatorManager()
    {
        super();
    }

    /**
     * Returns the sole instance of the receiver, lazily initialised if required.
     * @return ComparatorManager
     */
    public static synchronized ComparatorManager getInstance()
    {
        ComparatorManager current = null;
        if (null == (current = getInstanceBasic()))
        {
            updateInstance();
            return getInstance();
        }    
        return current;
    }
    
    /**
     * Returns the sole instance of the receiver.
     * @return ComparatorManager
     */
    private static ComparatorManager getInstanceBasic()
    {
        return fieldInstance;
    }
    
    /**
     * Computes a new instance of the receiver.
     * @return ComparatorManager
     */
    protected static ComparatorManager computeInstance()
    {
        return new ComparatorManager();
    }        

    /**
     * Sets the sole instance.
     * @param instance The current instance to set
     */
    protected static void setInstance(ComparatorManager instance)
    {
        fieldInstance = instance;
    }
    
    /**
     * Resets the sole instance.
     */
    public static void resetInstance()
    {
        setInstance(null);
    }    
    
    /**
     * Updates the sole instance.
     */
    protected static void updateInstance()
    {
        setInstance(computeInstance());
    }
    
    /**
     * <p>Method lookup answers the class to which a Comparator name is mapped.</p>
     * 
     * @param name - The name of the Comparator
     * @return Class - The class of the Comparator
     * @throws LookupException
     */
    public Class lookup(String name) throws LookupException
    {
        Class comparatorClass = null;
        try
        {
            comparatorClass =
                getClass().getClassLoader().loadClass(getClassName(name));
        }
        catch (ClassNotFoundException e)
        {
            throw new LookupException(
                "Comparator named '" + name + "' not found.");
        }
        if (!Comparator.class.isAssignableFrom(comparatorClass))
            throw new LookupException(
                "Class "
                    + comparatorClass.getName()
                    + " must implement "
                    + Comparator.class.getName());
        return comparatorClass;
    }
    
    /**
     * <p>Method newInstance answers an instance of the class to which a Comparator
     * name is mapped.</p>
     * 
     * @param name - The name of the Comparator
     * @return Class - The class of the Comparator
     * @throws LookupException
     */
    public Comparator newInstance(String name) throws LookupException
    {
        try
        {
            return (Comparator) lookup(name).newInstance();
        }
        catch (InstantiationException e)
        {
            throw new LookupException(e.getMessage());
        }
        catch (IllegalAccessException e)
        {
            throw new LookupException(e.getMessage());
        }
    }    


    /**
     * Method isSupported answers a boolean indicating if a Comparator name is 
     * configured.
     * @param name - The Comparator name
     * @return boolean - True if the Comparator name is configured.
     */
    public boolean isSupported(String name)
    {
        boolean isSupported = true;
        try
        {
            lookup(name);
        }
        catch (LookupException e)
        {
            isSupported = false;
        }
        return isSupported;
    }
    
    /**
     * <p>Method getClassName answers the name of the class to which a Comparator
     * name is mapped.</p>
     * 
     * @param name - The name of the Comparator
     * @return String - The name of the class
     * @throws LookupException
     */
    protected String getClassName(String name) throws LookupException
    {
        String className;
        try
        {
            className = (String) getClassNameMap().get(name.toLowerCase());
        }
        catch (SieveConfigurationException e)
        {
            throw new LookupException(
                "Lookup failed due to a Configuration Exception: "
                    + e.getMessage());
        }
        if (null == className)
            throw new LookupException(
                "Command named '" + name + "' not mapped.");
        return className;
    }
    
    /**
     * Method getClassNameMap answers a Map of Comparator names and their class 
     * names.
     * @return Map
     * @throws SieveConfigurationException
     */
    protected Map getClassNameMap() throws SieveConfigurationException
    {   
        return ConfigurationManager.getInstance().getComparatorMap();        
    }    
    
        

}
