package fr.itii.exam.constants;

public enum GameLabel
{

    EMPTY_VALUE("     "),
    EMPTY(""),
    TITLE("Less / Greater game"),
    MENU_TITLE( "Menu"),
    
    MENU_ITEM_NEW_GAME( "New Game"),
    
    MENU_ITEM_QUIT( "Quit"),
    
    PROPOSAL_LABEL( "My proposal :" ),
    PROPOSAL_ENTER_A_VALUE( "???" ),
    PROPOSAL_VALUE_FOUND( "Found" ),

    ;
    
    String label;
    
    private GameLabel( final String label)
    {
        this.label=  label;
    }
    
    public String getLabel()
    {
        return label;
    }
}
