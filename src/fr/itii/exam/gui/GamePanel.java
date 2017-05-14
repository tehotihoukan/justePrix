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

import fr.itii.exam.constants.GameColor;
import fr.itii.exam.constants.GameState;
import fr.itii.exam.constants.OutPutMessages;
import fr.itii.exam.utils.IntInputVerifier;

/**
 * Main game panel with fields, labels and text area
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
        final GridBagLayout gbl=  new GridBagLayout();
        
        this.setLayout( gbl );

        int row=  0;

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth= 2;
        c.weightx = 1;
        c.weighty = 0.0;
        c.gridx = 0;
        c.gridy = row;
        mProposalLabel.setHorizontalAlignment( SwingConstants.RIGHT );
        add( mProposalLabel, c );

        c.gridx = 2;
        c.gridwidth= 1;
        c.weightx = 1;
        c.gridy = row;
        mMinLabel.setHorizontalAlignment( SwingConstants.RIGHT );
        add( mMinLabel, c );

        c.gridx = 3;
        c.gridwidth= 1;
        c.weightx = 1;
        c.gridy = row;
        add( getProposalTextField(), c );

        c.gridx = 4;
        c.gridwidth= 1;
        c.weightx = 1;
        c.gridy = row;
        mMaxLabel.setHorizontalAlignment( SwingConstants.LEFT );
        add( mMaxLabel , c );


        c.fill = GridBagConstraints.BOTH;
        c.gridwidth= 5;
        c.gridheight= 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = ++row;


        add( getResultTextArea(), c );

        setBackground( Color.gray );

        initialize( GameState.START );
        
        repaint();
        validate();
    }

    public JTextField getProposalTextField()
    {
        if ( mProposalTextField == null )
        {
            mProposalTextField=  new JTextField("");
            
            /** Verifier that ensures the value entered by user is correct **/
            final IntInputVerifier<JTextField> inputVerifier=  new IntInputVerifier<JTextField>( MIN_VALUE, MAX_VALUE );

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
                            ////////////////////////////////////////////////////
                            // TODO Question 2. A compléter et à reporter sur la copie :
                            // 
                            // écrire le code nécessaire afin d'afficher les messages suivants en cas de réussite
                            // (ici l'exemple est donné pour une réussite après 11 essais) :
                            //
                            //  Found !
                            //  Number of tries : 11
                            //

                            getResultTextArea().append( OutPutMessages.FOUND.getOutPutMessage() );
                            getResultTextArea().append( OutPutMessages.TRY.getOutPutMessage( Integer.toString( TRY ) ) );
                            
                            ////////////////////////////////////////////////////
                            

                            ////////////////////////////////////////////////////
                            // TODO Question 3. A compléter et à reporter sur la copie :
                            //
                            // De même, vous devez initialiser le Game Panel dans un l'état 
                            // correspond à une partie terminée
                            //

                            initialize( GameState.END );
                            Thread thd=  new Thread();
                            thd.start();
                            ////////////////////////////////////////////////////
                        }
                        TRY ++;
                    }
                    else
                    {
                        getResultTextArea().append( OutPutMessages.INVALID_VALUE.getOutPutMessage() );
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
     * This method enables / disables some components depending on 
     * the given game state (game is being started or ended).
     * @param gameState is a state of the current game
     */
    public void initialize ( GameState gameState )
    {
        // A compléter : initialization des composants, des valeurs etc...
        //
        mProposalTextField.setText("");

        switch( gameState )
        {
            case START :
                mProposalTextField.setText("enter a value");
            case RESTART :              
                mValueToFind=  generateValue();
                mResultTextArea.setText("");
                mProposalTextField.setBackground( GameColor.DEFAULT_INPUT_BG.getColor() );
                mProposalTextField.setEnabled( true );
                TRY=0;
                break;
            case END:
                mProposalTextField.setText("found");
                mProposalTextField.setEnabled( false );
                break;
        }
        //
        /////////////////////////////
    }

    /**
     * Method that calculates a random value between 0
     * and the MAX_VALUE allowed by the game
     * @return a new randomly generated value
     */
    private final int generateValue()
    {
        return (int) ( Math.random() * MAX_VALUE );
    }
}
