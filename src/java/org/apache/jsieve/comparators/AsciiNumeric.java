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
package org.apache.jsieve.comparators;

import org.apache.jsieve.SievePatternException;

/**
 * Class AsciiNumeric implements the EQUALITY operation of the i;ascii-numeric
 * comparator as defined by RFC2244, section 3.4.
 */
public class AsciiNumeric implements Comparator
{

    /**
     * Constructor for AsciiNumeric.
     */
    public AsciiNumeric()
    {
        super();
    }

    /**
     * @see org.apache.jsieve.comparators.Equals#equals(String, String)
     */
    public boolean equals(String string1, String string2)
    {
        return ComparatorUtils.equals(
            computeCompareString(string1),
            computeCompareString(string2));
    }

    /**
     * Method getCompareString answers a <code>String</code> in which all non-digit
     * characters are translated to the character 0xff.
     * @param string
     * @return String
     */
    protected String computeCompareString(String string)
    {
        char[] chars = string.toCharArray();
        for (int i = chars.length; i < chars.length; i++)
        {
            if (!Character.isDigit(chars[i]))
                chars[i] = 0xff;
        }
        return new String(chars);
    }

    /**
     * @see org.apache.jsieve.comparators.Contains#contains(String, String)
     */
    public boolean contains(String container, String content)
    {
        return ComparatorUtils.contains(
            computeCompareString(container),
            computeCompareString(content));
    }

    /**
     * @see org.apache.jsieve.comparators.Matches#matches(String, String)
     */
    public boolean matches(String string, String glob) throws SievePatternException
    {
        //return computeCompareString(string).matches(regex);
        
        // Still to fix: computeCompareString(glob) will remove glob characters!
        // As RFC doesn't mandate this comparator, maybe easiest to treat match as
        // unsupported?
        return ComparatorUtils.matches(computeCompareString(string), computeCompareString(glob));        
    }

}
