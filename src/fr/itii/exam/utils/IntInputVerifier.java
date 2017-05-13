package fr.itii.exam.utils;

import java.awt.Color;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

/**
 * Verifier for value entered in the given Parameter T
 * @param <T> is a JTextCompnent
 */
public final class IntInputVerifier< T extends JTextComponent >
    extends InputVerifier
{

    private final int mMin;
    private final int mMax;

    public IntInputVerifier ( final int pMin,
                              final int pMax )
    {
        super();
        mMin=  pMin;
        mMax=  pMax;
    }

    @Override
    public boolean verify ( final JComponent pInput )
    {
        boolean accept=  ( (JTextComponent) pInput).getText().isEmpty() ;
        boolean found=  false;
        try
        {
            int value=  Integer.parseInt( ( (JTextComponent) pInput).getText() );

            accept=  (    mMin < value
                      &&  value < mMax );
        }
        catch ( NumberFormatException e )
        {
            accept=  false;
            
            // Check if "found"
            String inputText=( (JTextComponent) pInput).getText();
            
            if (inputText.equals("found"))
            {
                found=  true;
            }
               
        }
        finally
        {
            pInput.setBackground( accept
                                    ? Color.white
                                    : found
                                        ? Color.blue
                                        : Color.green );
        }
        return accept;
    }

}
