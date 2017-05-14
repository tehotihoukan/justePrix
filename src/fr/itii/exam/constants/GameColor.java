package fr.itii.exam.constants;

import java.awt.Color;

/**
 * List of colors used by the game
 */
public enum GameColor
{
    /**
     *     name          red       green       blue
     *      V             V          V           V
     **/
    INVALID_INPUT_BG   ( 200,       40,         40  ),
    DEFAULT_INPUT_BG   ( 255,       255,        255 ),
    FOUND_INPUT_BG     ( 40,        40,         150 ),
    MAIN_WINDOW_BG     ( 50,        50,         50  ),
    ;
    
    /** Current color for the given GameColor **/
    Color color;
    
    /**
     * Constructor
     * @param r is the Red color (0-255)
     * @param v is the Green color (0-255)
     * @param b is the Blue color (0-255)
     */
    GameColor( int r, int g, int b)
    {
        color=  new Color(r, g, b);
    }
    
    /**
     * @return the current {@link Color} associated to the {@link GameColor}
     */
    public Color getColor()
    {
        return color;
    }
}
