package edu.ntnu.stud.commandhandler;

import edu.ntnu.stud.ui.UserInputHandler;

/**
 * This parser reads user input and tries to interpret it as a menu command.
 * The parser has a set of known command words. It checks user input against
 * the command word ordinal, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 *
 * @author Anwar Debes
 * @version 0.0.1
 */
public class Parser
{
    // holds all valid command inputs
    private final CommandHandler commands;
    // holds the user input
    private final UserInputHandler userInputHandler;

    /**
     * Create a parser to read from the terminal window.
     *
     * @param userInputHandler the userInputHandler from UserInterface
     */
    public Parser(UserInputHandler userInputHandler)
    {
        commands = new CommandHandler();
        this.userInputHandler = userInputHandler;
    }

    /**
     * Gets the command input from the user and returns the command word
     * associated with teh command word ordinal.
     *
     * @return The next command from the user
     */
    public Command getCommand()
    {
        int commandInput =
            userInputHandler.getIntInput(
                "Please type a number as shown in menu:" + "\n" + "> ",
                "Please enter a valid number from the menu");
        return new Command(commands.getMainCommandInput(commandInput),
            commands.getEditCommandInput(commandInput),
            commands.getSearchCommandInput(commandInput),
            commands.getRemoveCommandInput(commandInput));
    }

}
