package org.codehaus.modello.model;

/*
 * Copyright (c) 2004, Codehaus.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**
 * Default values for a model, that can be overrided with <code>defaults</code> element of the model descriptor.
 *
 * <table>
 * <tr><th>key</th><th>since</th><th>default value</th><th>usage</th></tr>
 * <tr><td><code>package</code></td><td></td>
 *   <td><code>model</code></td>
 *   <td>The package where java classes are generated by default if no <code>packageName</code> defined
 *     in class or interface model</td>
 * </tr>
 * <tr><td><code>java.util.List</code></td><td></td>
 *   <td><code>new java.util.ArrayList&lt;?&gt;()</code></td>
 *   <td>The default code generated for <code>List</code> fields initialization</td>
 * </tr>
 * <tr><td><code>java.util.Set</code></td><td></td>
 *   <td><code>new java.util.HashSet&lt;?&gt;()</code></td>
 *   <td>The default code generated for <code>Set</code> fields initialization</td>
 * </tr>
 * <tr><td><code>java.util.Map</code></td><td></td>
 *   <td><code>new java.util.HashMap()</code></td>
 *   <td>The default code generated for <code>Map</code> fields initialization</td>
 * </tr>
 * <tr><td><code>java.util.Properties</code></td><td></td>
 *   <td><code>new java.util.Properties()</code></td>
 *   <td>The default code generated for <code>Properties</code> fields initialization</td>
 * </tr>
 * <tr><td><code>strictXmlAttributes</code></td><td>1.2</td>
 *   <td><code>true</code></td>
 *   <td>If set to <code>true</code>, reading an XML document with strict parsing enabled not only checks
 *     elements but attributes too (new in Modello 1.2). Setting this property to <code>false</code>
 *     makes strict parsing behave like previously, ie ignoring attributes.</td>
 * </tr>
 * <tr><td><code>checkDeprecation</code></td><td></td>
 *   <td><code>false</code></td>
 *   <td>If set to <code>true</code>, checks that if a class has a version range with an specified upper version,
 *     its <code>deprecatedVersion</code> is not null</td>
 * </tr>
 * </table>
 *
 * @author <a href="mailto:evenisse@codehaus.org">Emmanuel Venisse</a>
 *
 * @version $Id$
 */
public class ModelDefault
{
    public static final String CHECK_DEPRECATION = "checkDeprecation";

    public static final String CHECK_DEPRECATION_VALUE = "false";

    public static final String PACKAGE = "package";

    public static final String PACKAGE_VALUE = "model";

    public static final String LIST = "java.util.List";

    public static final String LIST_VALUE = "new java.util.ArrayList<?>()";

    public static final String MAP = "java.util.Map";

    public static final String MAP_VALUE = "new java.util.HashMap()";

    public static final String PROPERTIES = "java.util.Properties";

    public static final String PROPERTIES_VALUE = "new java.util.Properties()";

    public static final String SET = "java.util.Set";

    public static final String SET_VALUE = "new java.util.HashSet<?>()";

    public static final String STRICT_XML_ATTRIBUTES = "strictXmlAttributes";

    public static final String STRICT_XML_ATTRIBUTES_VALUE = "true";

    private String key;

    private String value;

    public static ModelDefault getDefault( String key )
        throws ModelValidationException
    {
        validateKey( key );

        ModelDefault modelDefault = new ModelDefault();

        modelDefault.setKey( key );

        if ( CHECK_DEPRECATION.equalsIgnoreCase( key ) )
        {
            modelDefault.setValue( CHECK_DEPRECATION_VALUE );
        }
        else if ( PACKAGE.equalsIgnoreCase( key ) )
        {
            modelDefault.setValue( PACKAGE_VALUE );
        }
        else if ( LIST.equalsIgnoreCase( key ) )
        {
            modelDefault.setValue( LIST_VALUE );
        }
        else if ( MAP.equalsIgnoreCase( key ) )
        {
            modelDefault.setValue( MAP_VALUE );
        }
        else if ( PROPERTIES.equalsIgnoreCase( key ) )
        {
            modelDefault.setValue( PROPERTIES_VALUE );
        }
        else if ( SET.equalsIgnoreCase( key ) )
        {
            modelDefault.setValue( SET_VALUE );
        }
        else if ( STRICT_XML_ATTRIBUTES.equalsIgnoreCase( key ) )
        {
            modelDefault.setValue( STRICT_XML_ATTRIBUTES_VALUE );
        }

        return modelDefault;
    }

    public void setKey( String key )
    {
        this.key = key;
    }

    public String getKey()
    {
        return key;
    }

    public void setValue( String value )
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    public boolean getBoolean()
    {
        return Boolean.valueOf( value ).booleanValue();
    }

    public void validateElement()
        throws ModelValidationException
    {
        if ( isEmpty( key ) )
        {
            throw new ModelValidationException( "You must define the key of default element." );
        }

        if ( isEmpty( value ) )
        {
            throw new ModelValidationException( "You must define the value of default element." );
        }

        validateKey( key );
    }

    private static void validateKey( String key )
        throws ModelValidationException
    {
        if ( ! SET.equalsIgnoreCase( key )
             && ! LIST.equalsIgnoreCase( key )
             && ! MAP.equalsIgnoreCase( key )
             && ! PROPERTIES.equalsIgnoreCase( key )
             && ! CHECK_DEPRECATION.equalsIgnoreCase( key )
             && ! PACKAGE.equalsIgnoreCase( key )
             && ! STRICT_XML_ATTRIBUTES.equalsIgnoreCase( key ) )
        {
            throw new ModelValidationException( "The key of default element must be ' "+ SET +"', '" + LIST + "', '"
                + MAP + "', '" + PROPERTIES + "', '" + CHECK_DEPRECATION + "', '" + PACKAGE + "' or '"
                + STRICT_XML_ATTRIBUTES + "', was '" + key + "'." );
        }
    }

    protected boolean isEmpty( String string )
    {
        return string == null || string.trim().length() == 0;
    }
}
