package edu.ntnu.stud.commandhandler;

/**
 * Representations for all the valid command words for the search menu
 * along with an int that shows command ordinal.
 *
 * @author Anwar Debes
 * @version 0.0.1
 */
public enum SearchCommands
{
    // Command for an unknown action
    UNKNOWN(0),
    // Command to search by number
    NUMBER(1),
    // Command to search by departure location
    DEPARTURE(2),
    //  Command to search by departure time
    DEPARTURE_TIME(3),
    // Command to search by track
    TRACK(4),
    // Command to search by lane
    LANE(5),
    // Command to search by delay
    DELAY(6),

    // Command to go back to main menu
    BACK(7),
    // Command to exit the program
    QUIT(8);


    // The searchCommand for input of search menu
    private final int commandInput;

    /**
     * Initialise with the corresponding searchCommand input.
     *
     * @param commandInput The command input.
     */
    SearchCommands(int commandInput)
    {
        this.commandInput = commandInput;
    }


}
