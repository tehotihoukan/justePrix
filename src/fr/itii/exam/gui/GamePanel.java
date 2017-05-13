package fr.itii.exam.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fr.itii.exam.constants.GameState;
import fr.itii.exam.constants.OutPutMessages;
import fr.itii.exam.utils.IntInputVerifier;

/**
 * Main game panel with fields, labels and text area
 *
 */
public final class GamePanel
    extends JPanel
{

    /**
     * Unic ID for serialization
     */
    private static final long serialVersionUID = -129016078586290502L;
    
    
    private JTextField mProposalTextField;
    private JTextArea mResultTextArea;

    private final JLabel mProposalLabel=  new JLabel( "My proposal :");
    private final JLabel mMinLabel=  new JLabel( MIN_VALUE + " <");
    private final JLabel mMaxLabel=  new JLabel( "< " + MAX_VALUE );

    static final int MAX_VALUE=  1000;
    static final int MIN_VALUE=  0;

    static int TRY= 0;

    private int mValueToFind;

    public GamePanel ()
    {
        /////
//        setVisible( true );
        /////
        
        GridBagLayout gbl=  new GridBagLayout();
        
        this.setLayout( gbl );

        int row=  0;

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth= 1;
        c.weightx = 0.4;
        c.weighty = 0.0;
        c.gridx = 0;
        c.gridy = row++;
        mProposalLabel.setHorizontalAlignment( SwingConstants.RIGHT );
        add( mProposalLabel, c );

        c.gridx = 1;
        mMinLabel.setHorizontalAlignment( SwingConstants.RIGHT );
        add( mMinLabel, c );

        c.gridx = 2;
        add( getProposalTextField(), c );

        c.gridx = 3;
        mMaxLabel.setHorizontalAlignment( SwingConstants.LEFT );
        add( mMaxLabel , c );


        c.fill = GridBagConstraints.BOTH;
        c.gridwidth= 0;
        c.gridheight= 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = row++;


        add( getResultTextArea(), c );

        setBackground( Color.gray );

        initialize( GameState.STARTING );
    }

    public JTextField getProposalTextField()
    {
        if ( mProposalTextField == null )
        {
            mProposalTextField=  new JTextField("");

//            mProposalTextField.setEditable(false);
            IntInputVerifier<JTextField> inputVerifier = new IntInputVerifier<JTextField>( MIN_VALUE, MAX_VALUE );

            mProposalTextField.setInputVerifier( inputVerifier );

            mProposalTextField.addKeyListener( new KeyListener()
            {

                @Override
                public void keyTyped ( final KeyEvent pKey )
                {
                    if (    ( pKey.getKeyChar() < KeyEvent.VK_0 )
                         || ( pKey.getKeyChar() > KeyEvent.VK_9   ) )

                    {
                        pKey.consume();
                    }
                }

                @Override
                public void keyReleased ( final KeyEvent pKey )
                {
                    // Nothing
                }

                @Override
                public void keyPressed ( final KeyEvent pKey )
                {
                   // Nothing
                }
            });

            mProposalTextField.addActionListener( new ActionListener()
            {
                @Override
                public void actionPerformed ( final ActionEvent pActionEvent )
                {

                    if ( inputVerifier.verify(mProposalTextField) )
                    {
                        if ( Integer.parseInt( mProposalTextField.getText() ) > mValueToFind )
                        {
                            getResultTextArea().append( OutPutMessages.LESS.getOutPutMessage() );
                        }
                        else if ( Integer.parseInt( mProposalTextField.getText() ) < mValueToFind )
                        {
                            getResultTextArea().append( OutPutMessages.GREATER.getOutPutMessage() );
                        }
                        else
                        {
                            // A compléter : ni inférieur, ni supérieur ?
                            //
                            getResultTextArea().append( OutPutMessages.FOUND.getOutPutMessage() );
                            getResultTextArea().append( OutPutMessages.TRY.getOutPutMessage( Integer.toString(TRY) ) );
                            
                            
                            // Erase the field and disable it
//                            mProposalTextField.setText("found");
//                            mProposalTextField.setEnabled(false);
                            
                            initialize( GameState.ENDED );
                            ///////
                        }
                        TRY ++;
                    }
                    else
                    {
                        mResultTextArea.append( OutPutMessages.INVALID_VALUE.getOutPutMessage() );
                    }
                }
            } );
        }
        return mProposalTextField;
    }

    public JTextArea getResultTextArea()
    {
        // A compléter : accesseur sur mResultTextArea
        //
        if ( mResultTextArea == null )
        {
            mResultTextArea= new JTextArea(); 
            mResultTextArea.setEditable(false);
        }
        return mResultTextArea;
        //////////////////////////////////////////////
    }

    /**
     * This method enable / disable some components
     * depending if the game is being started or ended.
     * @param pGameStarted is set to true to reinitialize the game,
     * else false to end the game
     */
    public void initialize ( GameState gameState )
    {
        // A compléter : initialization des composants, des valeurs etc...
        //
        switch( gameState )
        {
            case STARTING :
                mProposalTextField.setText("enter a value");

            case RUNNING :
                
                mValueToFind=  generateValue();
                mResultTextArea.setText("");
                mProposalTextField.setText("");
                mProposalTextField.setEnabled( true );
                TRY=0;
                break;
            case ENDED:
                mProposalTextField.setText("found");
                mProposalTextField.setEnabled( false );
                break;
        }

        /////////////////////////////
    }

    /**
     * Method that calculate a random value between 0
     * and the MAX_VALUE
     * @return
     */
    private final int generateValue()
    {
        return (int) ( Math.random() * MAX_VALUE );
    }
}
