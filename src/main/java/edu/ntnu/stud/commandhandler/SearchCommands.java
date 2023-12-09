package edu.ntnu.stud.commandhandler;

/**
 *Representations for all the valid command words for the search menu
 *along with an int that shows command ordinal.
 *
 * @author Yasin Hessnawi
 * @version 0.0.1 (20.11.22)
 */
public enum SearchCommands
{
    UNKNOWN(0), NUMBER(1), DEPARTURE(2),
     DEPARTURE_TIME(3), TRACK(4),
    LANE(5),  DELAY(6),HELP(7), BACK(8),
    QUIT(9);




    // The searchCommand input.
    private int commandInput;

    /**
     * Initialise with the corresponding searchCommand input.
     *
     * @param commandInput The command input.
     */
   private SearchCommands(int commandInput)
    {
        this.commandInput = commandInput;
    }


}
