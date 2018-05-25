package fr.itii.exam.gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class DocumentInputFilter extends DocumentFilter
{
    int maxCharacters;
    boolean DEBUG = false;

    public DocumentInputFilter(int maxChars)
    {
        maxCharacters = maxChars;
    }

    public void insertString( final FilterBypass  pFilterBypass,
                              final int           pOffs,
                              final String        pString,
                              final AttributeSet  pAttributeSet )
        throws BadLocationException
    {
        if (DEBUG)
        {
            System.out.println("in DocumentSizeFilter's insertString method");
        }
        if ( isValidInteger(pString))
        {
            if ( (pFilterBypass.getDocument().getLength() + pString.length()) <= maxCharacters )
            {
                super.insertString(pFilterBypass, pOffs, pString, pAttributeSet);
            }
        }
    }

    public void replace( final FilterBypass  pFilterBypass,
                         final int           pOffs,
                         final int           pLength,
                         final String        pString,
                         final AttributeSet  pAttributeSet)
        throws BadLocationException
    {
        if (DEBUG)
        {
            System.out.println("in DocumentSizeFilter's replace method");
        }

        if ( isValidInteger(pString))
        {
            int textLength= pFilterBypass.getDocument().getLength();
            
            if ( textLength <= maxCharacters)
            {
                if ( textLength + pString.length() - pLength > maxCharacters )
                {
                    super.replace(pFilterBypass, pOffs, pLength, pString.substring(0, maxCharacters - textLength + pLength), pAttributeSet);
                }
                else
                {
                    super.replace(pFilterBypass, pOffs, pLength, pString, pAttributeSet);
                }
            }
        }
    }
    
    
    
    private static boolean isValidInteger( String pString )
    {
        boolean result=  false;
        try
        {
            int value=  Integer.parseInt( pString );
            result=  true;
        }
        catch ( NumberFormatException e )
        {
            result=  false;
        }
        return result;
    }
}
