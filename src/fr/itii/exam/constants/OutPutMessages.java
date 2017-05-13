package fr.itii.exam.constants;

/**
 * All output messages displayed in the OutPut Text Area.
 */
public enum OutPutMessages
{

    GAME_STARTED("Game Started"),
    INVALID_VALUE("Invalid value"),
    VALUE_CALCULATED("Find the value"),
    LESS("Less"),
    GREATER("Greater"),
    FOUND("Found !"),
    TRY("Number of tries : %1$2s");

    private String mMessage;

    OutPutMessages( final String pMessage )
    {
        mMessage=  pMessage;
    }

    public String getMessage()
    {
        return mMessage;
    }

    public String getOutPutMessage()
    {
        return  mMessage + "\n";
    }
    
    /**
     * Take a value and format it based on the mMessage format
     * @param pInPut is the string value to format
     * @return a formated string
     */
    public String getOutPutMessage( final String pInPut )
    {
        return String.format( mMessage, pInPut );
    }
}
