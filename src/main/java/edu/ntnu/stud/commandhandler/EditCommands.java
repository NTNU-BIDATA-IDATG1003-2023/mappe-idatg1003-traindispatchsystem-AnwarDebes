package edu.ntnu.stud.commandhandler;

/**
 * Representations for all the valid command words for the adjust menu
 * along with an int that shows command ordinal.
 *
 * @author Anwar Debes
 * @version 0.0.1
 */
public enum EditCommands
{
    // A value for each command word along with its
    // corresponding user interface string.
    UNKNOWN(0), DESTINATION(1),
    DEPARTURE_TIME(2), TRACK(3),
    DELAY(4), LANE(5), TRAIN_NUMBER(6),
    BACK(7), EXIT(8);

    // The command input of adjust menu.
    private final int adjustCommandInput;

    /**
     * Initialise with the corresponding adjustCommand input.
     *
     * @param adjustCommandInput The command input.
     */
    EditCommands(int adjustCommandInput)
    {
        this.adjustCommandInput = adjustCommandInput;
    }

    /**
     * Returns the integer value associated with the command.
     *
     * @return the integer value of the command.
     */
    public int getAdjustCommandInput()
    {
        return adjustCommandInput;
    }

}
    

