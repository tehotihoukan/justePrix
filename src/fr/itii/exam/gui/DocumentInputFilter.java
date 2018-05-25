package fr.itii.exam.gui;

import javax.swing.InputVerifier;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import fr.itii.exam.constants.GameLabel;
import fr.itii.exam.utils.IntInputVerifier;

public class DocumentInputFilter extends DocumentFilter
{
    int maxCharacters;
    

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
        if (IntInputVerifier.DEBUG)
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
        if (IntInputVerifier.DEBUG)
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
        
        // Check if the value is one of the allowed Game Labels
        if ( GameLabel.EMPTY_VALUE.getLabel().equals(pString))
        {
            result = true;
        }
        else if (GameLabel.PROPOSAL_ENTER_A_VALUE.getLabel().equals(pString))
        {
            result = true;
        }
        else if ( GameLabel.PROPOSAL_VALUE_FOUND.getLabel().equals(pString))
        {
            result = true;
        }
        else
        {
            // Check if the value is a number
            try
            {
                int value=  Integer.parseInt( pString );
                result=  true;
            }
            catch ( NumberFormatException e )
            {
                result=  false;
            }
        }
        return result;
    }
}
