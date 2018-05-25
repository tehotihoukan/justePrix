package fr.itii.exam.toolkit;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

/**
 * Extended JTextField with business data
 *
 */
public final class KTextField extends JTextField
{
    
    public KTextField(final String initialText)
    {
        super(initialText);
        
        addFocusListener(new FocusListener()
        {

            @Override
            public void focusGained(FocusEvent e)
            {
                KTextField.this.select(0, getText().length());
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                KTextField.this.select(0, 0);
            }
        });
    }
}
