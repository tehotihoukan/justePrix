package fr.itii.exam.utils;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

import fr.itii.exam.constants.GameColor;
import fr.itii.exam.constants.GameLabel;

/**
 * Verifier for value entered in the given Parameter T
 * @param <T> is a JTextCompnent
 */
public final class IntInputVerifier< T extends JTextComponent >
    extends InputVerifier
{
    private final int mMin;
    private final int mMax;

    
    public static boolean DEBUG = true;
    
    public IntInputVerifier ( final int pMin,
                              final int pMax)
    {
        super();
        mMin=  pMin;
        mMax=  pMax;
    }

    @Override
    public boolean verify ( final JComponent pInput )
    {
        String text= ((JTextComponent) pInput).getText();
        
        if (IntInputVerifier.DEBUG)
        {
            System.out.println("verify :" + text);
        }
        
        boolean accept=  false;
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

            if ( GameLabel.PROPOSAL_VALUE_FOUND.getLabel().equals( inputText ) )
            {
                found=  true;
            }

        }
        finally
        {
            pInput.setBackground( accept
                                    ? GameColor.DEFAULT_INPUT_BG.getColor()
                                    : found
                                        ? GameColor.FOUND_INPUT_BG.getColor()
                                        : GameColor.INVALID_INPUT_BG.getColor() );
        }
        return accept;
    }

}
