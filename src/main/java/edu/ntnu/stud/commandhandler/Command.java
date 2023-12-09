package edu.ntnu.stud.commandhandler;

/**
 * Holds information about a command input that was issued by the user and returns words from
 * their correspondent enum class
 *
 * @author Anwar
 * @version 0.0.1
 * @see MainMenuCommands
 * @see EditCommands
 * @see SearchCommands
 * @see RemoveCommands
 */

public class Command
{
    // main menu commands
    private final MainMenuCommands mainMenuCommands;
    // edit menu commands
    private final EditCommands editCommands;
    // search menu commands
    private final SearchCommands searchCommands;
    // remove menu commands
    private final RemoveCommands removeCommands;
    /**
     * initializes the command objects and objects of the enum classes holding commands
     *
     * @param mainMenuCommands the main menu mainMenuCommands
     * @param editCommands the editCommands menu
     * @param searchCommands  the searchCommands menu
     */
    public Command(MainMenuCommands mainMenuCommands, EditCommands editCommands,
                   SearchCommands searchCommands, RemoveCommands removeCommands)
    {
        this.mainMenuCommands = mainMenuCommands;
        this.editCommands = editCommands;

        this.searchCommands = searchCommands;
        this.removeCommands = removeCommands;

    }

    /**
     * Return the command words of the main menu.
     *
     * @return The command words.
     */
    public MainMenuCommands getCommandInput()
    {
        return mainMenuCommands;
    }

    /**
     * Return the command words of the adjust menu.
     *
     * @return The command word.
     */
    public EditCommands getEditCommandInput()
    {
        return editCommands;
    }


    /**
     * Return the command words of search menu.
     *
     * @return The command word.
     */
    public SearchCommands getSearchCommandInput()
    {
        return searchCommands;
    }

    public RemoveCommands getRemoveCommandInput()
    {
        return removeCommands;
    }



}




