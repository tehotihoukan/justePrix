package fr.itii.exam.constants;

/**
 * All output messages displayed on the OutPut Text Area.
 * +
 * Some relevant methods to format the output messages
 */
public enum OutPutMessages
{
    
    GAME_STARTED     ( "Game Started"               ),
    INVALID_VALUE    ( "Invalid value"              ),
    VALUE_CALCULATED ( "Find the value"             ),
    LESS             ( "Less"                       ),
    GREATER          ( "Greater"                    ),
    FOUND            ( "Value : %1$2s"              ), // Message supporting formatter.
    TRY              ( "Number of tries : %1$2s"    ); // Message supporting formatter.

    
    /** The output message to display **/
    private String mMessage;

    /**
     * Constructor
     * @param pMessage the message to display
     */
    OutPutMessages( final String pMessage )
    {
        mMessage=  pMessage;
    }

    /**
     * @return the current message in raw format
     */
    public String getMessage()
    {
        return mMessage;
    }

    /**
     * @return the current message with a new line a the end
     */
    public String getOutPutMessage()
    {
        return  mMessage + "\n";
    }
    
    /**
     * Format the message accordingly to its content.
     * Message must have a format support.
     * 
     * @param pInPut is the string value to format
     * @return a formated string to replay
     */
    public String getOutPutMessage( final String pInPut )
    {
        return String.format( mMessage + "\n", pInPut );
    }
}
