package fr.itii.exam.gui;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import fr.itii.exam.constants.GameColor;
import fr.itii.exam.constants.GameLabel;
import fr.itii.exam.constants.GameState;

/**
 * Main frame providing a menu and containing the {@link GamePanel}
 * /!\ This MainWindow is a singleting /!\
 */
public class MainWindow
    extends JFrame
{

    /** Used for serializing the current class. **/
    private static final long serialVersionUID = -8550809543988259909L;

    /** Unique instance of the current MainWindo (Singleton) **/
    private final static MainWindow INSTANCE=  new MainWindow();

    /** Menu item for quitting the game **/
    private MenuItem mQuitMenu;

    /** Menu item for starting a new the game **/
    private MenuItem mNewGameMenu;

    /** Main panel contained by the ContentPane of the current MainWindow **/
    private GamePanel mGamePanel;

    /**
     * Getter on the MainWindow Singleton
     * @return the MainWindow Singleton
     */
    public static MainWindow getInstance ()
    {
        return INSTANCE;
    }

    /**
     * Constructor
     */
    private MainWindow ()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setIconImage( (new ImageIcon("images/icon.png")).getImage() );
        setTitle( GameLabel.TITLE.getLabel() );
        setVisible( true );
        setSize( 400, 400 );
        setLocationRelativeTo( null );

        MenuBar menuBar=  new MenuBar();
        Menu menu=  new Menu( GameLabel.MENU_TITLE.getLabel() );

        menu.add( getNewGameMenuItem() );
        menu.addSeparator();
        menu.add( getQuitMenuItem() );

        menuBar.add( menu );

        setMenuBar( menuBar );

        ////////////////////////////////////////////////////
        // TODO Question 4. A compléter et à reporter sur la copie :
        //
        // ajouter le Game Panel au Content Pane.
        //

        setContentPane( getGamePanel() );

        ////////////////////////////////////////////////////


        setBackground( GameColor.MAIN_WINDOW_BG.getColor() );

        revalidate();
    }

    private void close ()
    {
        Runtime.getRuntime().exit( 0 );
    }

    public GamePanel getGamePanel ()
    {
        if ( mGamePanel == null )
        {
            mGamePanel=  new GamePanel();
        }
        return mGamePanel;
    }

    /**
     * @return the "New Game" Menu Item
     */
    public MenuItem getNewGameMenuItem()
    {
        if ( mNewGameMenu == null )
        {
            mNewGameMenu=  new MenuItem( GameLabel.MENU_ITEM_NEW_GAME.getLabel() );
            mNewGameMenu.addActionListener( new ActionListener()
            {

                @Override
                public void actionPerformed ( ActionEvent e )
                {
                    getGamePanel().initialize( GameState.RESTART );
                }
            } );

        }
        return mNewGameMenu;
    }

    /**
     * @return the "Quit" Menu Item
     */
    public MenuItem getQuitMenuItem()
    {
        if ( mQuitMenu == null )
        {
            mQuitMenu=  new MenuItem( GameLabel.MENU_ITEM_QUIT.getLabel() );
            mQuitMenu.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed ( ActionEvent actionEvent )
                    {
                        MainWindow.getInstance().close();
                    }
                });
        }
        return mQuitMenu;
    }
}
