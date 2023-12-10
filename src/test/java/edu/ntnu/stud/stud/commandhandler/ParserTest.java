
package edu.ntnu.stud.stud.commandhandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import edu.ntnu.stud.commandhandler.Command;
import edu.ntnu.stud.commandhandler.MainMenuCommands;
import edu.ntnu.stud.commandhandler.Parser;
import edu.ntnu.stud.ui.UserInputHandler;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class ParserTest  {
    @Test
    void testValidCommandForAdd() {
        UserInputHandler testInputHandler = new UserInputHandler(new Scanner("1"));
        Parser parser = new Parser(testInputHandler);
        Command command = parser.getCommand();

        assertNotNull(command, "Command should not be null");
        assertEquals(MainMenuCommands.ADD, command.getMainCommandInput(), "Command should be ADD for input 1");
    }

    @Test
    void testValidCommandForSearch() {
        UserInputHandler testInputHandler = new UserInputHandler(new Scanner("2"));
        Parser parser = new Parser(testInputHandler);
        Command command = parser.getCommand();

        assertNotNull(command, "Command should not be null");
        assertEquals(MainMenuCommands.SEARCH, command.getMainCommandInput(), "Command should be ADD for input 1");
    }

    @Test
    void testValidCommandForDisplayTheTrainsDepartures() {
        UserInputHandler testInputHandler = new UserInputHandler(new Scanner("3"));
        Parser parser = new Parser(testInputHandler);
        Command command = parser.getCommand();

        assertNotNull(command, "Command should not be null");
        assertEquals(MainMenuCommands.SHOW_ALL, command.getMainCommandInput(), "Command should be ADD for input 1");
    }

    @Test
    void testValidCommandForEdit() {
        UserInputHandler testInputHandler = new UserInputHandler(new Scanner("4"));
        Parser parser = new Parser(testInputHandler);
        Command command = parser.getCommand();

        assertNotNull(command, "Command should not be null");
        assertEquals(MainMenuCommands.EDIT, command.getMainCommandInput(), "Command should be ADD for input 1");
    }

    @Test
    void testValidCommandForRemove() {
        UserInputHandler testInputHandler = new UserInputHandler(new Scanner("5"));
        Parser parser = new Parser(testInputHandler);
        Command command = parser.getCommand();

        assertNotNull(command, "Command should not be null");
        assertEquals(MainMenuCommands.REMOVE, command.getMainCommandInput(), "Command should be ADD for input 1");
    }
    @Test
    void testValidCommandForTime() {
        UserInputHandler testInputHandler = new UserInputHandler(new Scanner("6"));
        Parser parser = new Parser(testInputHandler);
        Command command = parser.getCommand();

        assertNotNull(command, "Command should not be null");
        assertEquals(MainMenuCommands.TIME, command.getMainCommandInput(), "Command should be ADD for input 1");
    }

    @Test
    void testValidCommandForHelp() {
        UserInputHandler testInputHandler = new UserInputHandler(new Scanner("7"));
        Parser parser = new Parser(testInputHandler);
        Command command = parser.getCommand();

        assertNotNull(command, "Command should not be null");
        assertEquals(MainMenuCommands.HELP, command.getMainCommandInput(), "Command should be ADD for input 1");
    }

    @Test
    void testValidCommandForQuit() {
        UserInputHandler testInputHandler = new UserInputHandler(new Scanner("8"));
        Parser parser = new Parser(testInputHandler);
        Command command = parser.getCommand();

        assertNotNull(command, "Command should not be null");
        assertEquals(MainMenuCommands.QUIT, command.getMainCommandInput(), "Command should be ADD for input 1");
    }

    @Test
    void testInvalidCommand() {
        try
        {
            UserInputHandler testInputHandler = new UserInputHandler(new Scanner("-1"));
            Parser parser = new Parser(testInputHandler);
            Command command = parser.getCommand();

            assertNotNull(command, "Command should not be null");
            assertEquals(MainMenuCommands.UNKNOWN, command.getMainCommandInput(),
                "Command should be UNKNOWN for invalid input");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    @Test
    void testInvalidCommandForString() {
        try
        {
            UserInputHandler testInputHandler = new UserInputHandler(new Scanner("Hei!"));
            Parser parser = new Parser(testInputHandler);
            Command command = parser.getCommand();

            assertNotNull(command, "Command should not be null");
            assertEquals(MainMenuCommands.UNKNOWN, command.getMainCommandInput(),
                "Command should be UNKNOWN for invalid input");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}


