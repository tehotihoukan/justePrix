package fr.itii.exam.gui;

import java.awt.Color;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import fr.itii.exam.constants.GameState;

/**
 * Main fram containing the menu and the main panel itself
 *
 */
public class MainWindow
    extends JFrame
{

    private final static MainWindow INSTANCE=  new MainWindow();

    private MenuItem mQuitMenu;
    private MenuItem mNewGameMenu;

    private GamePanel mGamePanel;

    private MainWindow ()
    {
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        
        setIconImage( (new ImageIcon("images/icon.png")).getImage() );
        setTitle( "Less / Greater game" );
        setVisible( true );
        setSize( 400, 400 );
        setLocationRelativeTo( null );

        MenuBar menuBar=  new MenuBar();
        Menu menu=  new Menu("Menu");

        menu.add( getNewGameMenuItem() );
        menu.addSeparator();
        menu.add( getQuitMenuItem() );


        menuBar.add( menu );

        setMenuBar( menuBar );

        setContentPane( getGamePanel() );
        setBackground( Color.darkGray );

        revalidate();
    }

    private void close ()
    {
        Runtime.getRuntime().exit( 0 );
    }

    public static MainWindow getInstance ()
    {
        return INSTANCE;
    }

    public GamePanel getGamePanel ()
    {
        if ( mGamePanel == null )
        {
            mGamePanel=  new GamePanel();
        }
        return mGamePanel;
    }

    public MenuItem getNewGameMenuItem()
    {
        if ( mNewGameMenu == null )
        {
            mNewGameMenu=  new MenuItem( "New Game" );
            mNewGameMenu.addActionListener( new ActionListener()
            {

                @Override
                public void actionPerformed ( ActionEvent e )
                {
                    // A compléter : Initialisation du GamePanel.
                    //
                    getGamePanel().initialize( GameState.RUNNING );
                    
                    //////////////////////////////////
                }
            } );

        }
        return mNewGameMenu;
    }

    public MenuItem getQuitMenuItem()
    {
        if ( mQuitMenu == null )
        {
            mQuitMenu=  new MenuItem( "Quit" );
            mQuitMenu.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed ( ActionEvent arg0 )
                    {
                        MainWindow.getInstance().close();
                    }
                });
        }
        return mQuitMenu;
    }
}
