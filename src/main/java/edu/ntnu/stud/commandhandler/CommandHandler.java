package edu.ntnu.stud.commandhandler;

import java.util.HashMap;
import java.util.Map;

/**
 * This class holds an enumeration of all command inputs known to the application.
 * It is used to recognise commands as they are typed in.
 *
 * @author Anwar Debes
 * @version 0.0.1
 * @see MainMenuCommands
 * @see EditCommands
 * @see SearchCommands
 * @see RemoveCommands
 */

public class CommandHandler
{
    // A map to store valid main menu commands indexed by their ordinal values.
    private final Map<Integer, MainMenuCommands> mainCommands;
    // A map to store valid editing commands indexed by their ordinal values.
    private final Map<Integer, EditCommands> editCommands;
    // A map to store valid search commands indexed by their ordinal values.
    private final Map<Integer, SearchCommands> searchCommands;
    // A map to store valid remove commands indexed by their ordinal values.
    private final Map<Integer, RemoveCommands> removeCommands;

    /**
     * Constructor - initializes the command input maps for different categories of commands.
     * It populates each map with command enums, excluding the unknown command type for
     * each category
     */
    public CommandHandler()
    {
        mainCommands = new HashMap<>();
        editCommands = new HashMap<>();
        searchCommands = new HashMap<>();
        removeCommands = new HashMap<>();

        // Populate each map with respective command enums, excluding unknown.
        for (MainMenuCommands command : MainMenuCommands.values())
        {
            if (command != MainMenuCommands.UNKNOWN)
            {
                mainCommands.put(command.ordinal(), command);
            }
        }
        for (EditCommands adjustCommand : EditCommands.values())
        {
            if (adjustCommand != EditCommands.UNKNOWN)
            {
                editCommands.put(adjustCommand.ordinal(), adjustCommand);
            }
        }
        for (SearchCommands searchForItemCommand : SearchCommands.values())
        {
            if (searchForItemCommand != SearchCommands.UNKNOWN)
            {
                this.searchCommands.put(searchForItemCommand.ordinal(),
                    searchForItemCommand);
            }
        }
        for (RemoveCommands removeCommand : RemoveCommands.values())
        {
            if (removeCommand != RemoveCommands.UNKNOWN)
            {
                this.removeCommands.put(removeCommand.ordinal(), removeCommand);
            }
        }
    }

    /**
     * Find the command ordinal associated with a main menu enum command words.
     *
     * @param commandInput The integer to look up.
     * @return The CommandWord corresponding to Command input ordinal, or unknown
     *     if it is not a valid command input.
     */
    public MainMenuCommands getMainCommandInput(int commandInput)
    {
        MainMenuCommands command = mainCommands.get(commandInput);
        if (commandInput > 0 && commandInput <= mainCommands.size())
        {
            return command;
        } else
        {
            return MainMenuCommands.UNKNOWN;
        }
    }

    /**
     * Find the adjustCommand ordinal associated with adjust enum command words.
     *
     * @param commandInput The integer to look up.
     * @return The CommandWord corresponding to adjust command input ordinal, or UNKNOWN
     *        if it is not a valid command word.
     */
    public EditCommands getEditCommandInput(int commandInput)
    {
        EditCommands adjustCommand = editCommands.get(commandInput);
        if (commandInput > 0 && commandInput <= editCommands.size())
        {
            return adjustCommand;
        } else
        {
            return EditCommands.UNKNOWN;
        }
    }

    /**
     * Find the searchCommand ordinal associated with search enum command words.
     *
     * @param commandInput The integer to look up.
     * @return The CommandWord corresponding to searchCommand input ordinal, or UNKNOWN
     *        if it is not a valid command word.
     */
    public SearchCommands getSearchCommandInput(int commandInput)
    {
        SearchCommands command = searchCommands.get(commandInput);
        if (commandInput > 0 && commandInput <= searchCommands.size())
        {
            return command;
        } else
        {
            return SearchCommands.UNKNOWN;
        }
    }

    /**
     * Retrieves the remove command corresponding to the given ordinal.
     *
     * @param commandInput The integer ordinal of the remove command to retrieve.
     * @return The RemoveCommands enum corresponding to the input ordinal, or UNKNOWN if not valid.
     */

    public  RemoveCommands getRemoveCommandInput(int commandInput)
    {
        RemoveCommands command = removeCommands.get(commandInput);
        if (commandInput > 0 && commandInput <= removeCommands.size())
        {
            return command;
        } else
        {
            return RemoveCommands.UNKNOWN;
        }
    }
}
