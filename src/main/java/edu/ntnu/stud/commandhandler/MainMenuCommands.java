package edu.ntnu.stud.commandhandler;

/**
 *Representations for all the valid command words for the main menu
 *along with an int that shows command ordinal.
 *
 */
public enum MainMenuCommands
{
    // A value for each command word along with its
    // corresponding user interface string.
    UNKNOWN(0), ADD(1), SEARCH(2),
    SHOW_ALL(3), EDIT(4),  REMOVE(5),TIME(6),
    HELP(7),
    QUIT(8);

    // The main menu command input.
    private final int commandInput;

    /**
     * Initialise with the corresponding command input.
     *
     * @param commandInput The command input.
     */
     MainMenuCommands(int commandInput)
    {
        this.commandInput = commandInput;
    }

}
    

