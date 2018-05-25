package fr.itii.exam.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;

import fr.itii.exam.constants.GameColor;
import fr.itii.exam.constants.GameLabel;
import fr.itii.exam.constants.GameState;
import fr.itii.exam.constants.OutPutMessages;
import fr.itii.exam.toolkit.KTextField;
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


    private KTextField mProposalTextField;
    private JTextArea mResultTextArea;

    private JLabel mProposalLabel;
    private JLabel mMinLabel;
    private JLabel mMaxLabel;

    static final int MAX_VALUE=  1000;
    static final int MIN_VALUE=  0;

    
    static final int PROPOSAL_MIN_SIZE= 20;
    static final int PROPOSAL_PREFERRED_SIZE= 30;
    static final int PROPOSAL_HEIGHT_SIZE= 40;
    
    static int TRY= 0;

    private int mValueToFind;

    public GamePanel ()
    {
        final GridBagLayout gbl=  new GridBagLayout();

        this.setLayout( gbl );

        int row=  0;

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = row;
        c.gridwidth= 1;
        c.weightx = 1;
        add( getProposalLabel(), c );

        c.gridx = 1;
        c.gridy = row;
        c.gridwidth= 1;
        c.weightx = 1;
        add( getMinLabel(), c );

        c.gridx = 2;
        c.gridy = row;
        c.gridwidth= 1;
        c.weightx = 1;
        add( getProposalTextField(), c );

        c.gridx = 3;
        c.gridy = row;
        c.gridwidth= 1;
        c.weightx = 1;
        add( getMaxLabel() , c );

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = ++row;
        c.gridwidth= 4;
        c.gridheight= 0;
        c.weightx = 1;
        c.weighty = 1;

        add( new JScrollPane( getResultTextArea() ), c );

        setBackground( Color.gray );

        initialize( GameState.START );

        repaint();
        validate();
    }
    
    public final JLabel  getProposalLabel()
    {
        if ( mProposalLabel  == null )
        {
            mProposalLabel =  new JLabel( GameLabel.PROPOSAL_LABEL.getLabel() );            
            mProposalLabel.setPreferredSize( new Dimension(PROPOSAL_PREFERRED_SIZE, PROPOSAL_HEIGHT_SIZE));
            mProposalLabel.setMinimumSize( new Dimension(PROPOSAL_MIN_SIZE, PROPOSAL_HEIGHT_SIZE));
            mProposalLabel.setHorizontalAlignment( SwingConstants.RIGHT );
        }
        return mProposalLabel;
    }
    
    public final JLabel getMinLabel()
    {
        if ( mMinLabel == null )
        {
            mMinLabel=  new JLabel( MIN_VALUE + " <" );
            mMinLabel.setPreferredSize( new Dimension(PROPOSAL_PREFERRED_SIZE, PROPOSAL_HEIGHT_SIZE));
            mMinLabel.setMinimumSize( new Dimension(PROPOSAL_MIN_SIZE, PROPOSAL_HEIGHT_SIZE));
            mMinLabel.setHorizontalAlignment( SwingConstants.RIGHT );
        }
        return mMinLabel;
    }
    
    public final JLabel getMaxLabel()
    {
        if ( mMaxLabel == null )
        {
            mMaxLabel=  new JLabel( "< " + MAX_VALUE );
            mMaxLabel.setPreferredSize( new Dimension(PROPOSAL_PREFERRED_SIZE, PROPOSAL_HEIGHT_SIZE));
            mMaxLabel.setMinimumSize(new Dimension(PROPOSAL_MIN_SIZE, PROPOSAL_HEIGHT_SIZE));     
            mMaxLabel.setHorizontalAlignment( SwingConstants.LEFT );
        }
        return mMaxLabel;
    }
    
    
    public KTextField getProposalTextField()
    {
        
        
        int i=0;
        i += ++i;
        
        
        System.out.println(i);
        
        if ( mProposalTextField == null )
        {
            mProposalTextField=  new KTextField(GameLabel.EMPTY_VALUE.getLabel());

            /** Verifier that ensures the value entered by user is correct **/
            final IntInputVerifier<JTextField> inputVerifier=  new IntInputVerifier<JTextField>( MIN_VALUE, MAX_VALUE );

            mProposalTextField.setInputVerifier( inputVerifier );

            final Document doc=  mProposalTextField.getDocument();
            if (doc instanceof AbstractDocument) 
            {
                AbstractDocument abstractDoc = (AbstractDocument) doc;
                abstractDoc.setDocumentFilter( new DocumentInputFilter(5) );
            }

            mProposalTextField.addActionListener( new ActionListener()
            {
                @Override
                public void actionPerformed ( final ActionEvent pActionEvent )
                {

                    if ( inputVerifier.verify(mProposalTextField) )
                    {
                        TRY ++;

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

                            ////////////////////////////////////////////////////
                        }
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

        switch( gameState )
        {
            case START :
            case RESTART :
                getProposalTextField().setText( GameLabel.PROPOSAL_ENTER_A_VALUE.getLabel() );
                getResultTextArea().setText( GameLabel.EMPTY.getLabel() );
                mValueToFind=  generateValue();
                if (IntInputVerifier.DEBUG)
                {
                    System.out.println("mValueToFind : " + mValueToFind);
                }
                getProposalTextField().setBackground( GameColor.DEFAULT_INPUT_BG.getColor() );
                getProposalTextField().setEnabled( true );
                TRY=0;
                break;
            case END:
                getProposalTextField().setBackground( GameColor.DEFAULT_INPUT_BG.getColor() );
                getProposalTextField().setEnabled( false );
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
