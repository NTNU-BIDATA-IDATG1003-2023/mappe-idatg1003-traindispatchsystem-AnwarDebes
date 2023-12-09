package edu.ntnu.stud.commandhandler;

/**
 *Representations for all the valid command words for the adjust menu
 *along with an int that shows command ordinal.
 *
 */
public enum EditCommands
{
    // A value for each command word along with its
    // corresponding user interface string.
    UNKNOWN(0), DESTINATION(1), DEPARTURE_TIME(2), TRACK(3)
    , DELAY(4), LANE(5), TRAIN_NUMBER(6),  BACK(7) , EXIT(8) ;

    // The command input of adjust menu.
    private final int adjustCommandInput;

    /**
     * Initialise with the corresponding adjustCommand input.
     *
     * @param adjustCommandInput The command input.
     */
    private EditCommands(int adjustCommandInput)
    {
        this.adjustCommandInput = adjustCommandInput;
    }

}
    

