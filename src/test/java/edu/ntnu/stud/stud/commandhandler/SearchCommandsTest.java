package edu.ntnu.stud.stud.commandhandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import edu.ntnu.stud.commandhandler.Command;
import edu.ntnu.stud.commandhandler.Parser;
import edu.ntnu.stud.commandhandler.SearchCommands;
import edu.ntnu.stud.ui.UserInputHandler;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class SearchCommandsTest {
    UserInputHandler testInputHandler;
    Parser parser;
    Command command;

    @Test
    void testValidSearchCommandForTrainNumber()
    {
        testInputHandler = new UserInputHandler(new Scanner("1"));
        parser = new Parser(testInputHandler);
        command = parser.getCommand();

        assertNotNull(command, "Command should not be null");
        assertEquals(SearchCommands.NUMBER, command.getSearchCommandInput(),
            "Command to search for a train number is 1");
    }

    @Test
    void testValidSearchCommandForDeparture()
    {
        testInputHandler = new UserInputHandler(new Scanner("2"));
        parser = new Parser(testInputHandler);
        command = parser.getCommand();

        assertNotNull(command, "Command should not be null");
        assertEquals(SearchCommands.DEPARTURE, command.getSearchCommandInput(),
            "Command to search for the departure is 2");
    }

    @Test
    void testValidSearchCommandForDepartureTime()
    {
        testInputHandler = new UserInputHandler(new Scanner("3"));
        parser = new Parser(testInputHandler);
        command = parser.getCommand();

        assertNotNull(command, "Command should not be null");
        assertEquals(SearchCommands.DEPARTURE_TIME, command.getSearchCommandInput(),
            "Command to search for the departure time is 3");
    }

    @Test
    void testValidSearchCommandForTrack()
    {
        testInputHandler = new UserInputHandler(new Scanner("4"));
        parser = new Parser(testInputHandler);
        command = parser.getCommand();

        assertNotNull(command, "Command should not be null");
        assertEquals(SearchCommands.TRACK, command.getSearchCommandInput(),
            "Command to search for the track is 4");
    }

    @Test
    void testValidSearchCommandForLane()
    {
        testInputHandler = new UserInputHandler(new Scanner("5"));
        parser = new Parser(testInputHandler);
        command = parser.getCommand();

        assertNotNull(command, "Command should not be null");
        assertEquals(SearchCommands.LANE, command.getSearchCommandInput(),
            "Command to search for the lane is 5");
    }

    @Test
    void testValidSearchCommandForDelay()
    {
        testInputHandler = new UserInputHandler(new Scanner("6"));
        parser = new Parser(testInputHandler);
        command = parser.getCommand();

        assertNotNull(command, "Command should not be null");
        assertEquals(SearchCommands.DELAY, command.getSearchCommandInput(),
            "Command to search for the delay is 6");
    }


    @Test
    void testValidSearchCommandForBack()
    {
        testInputHandler = new UserInputHandler(new Scanner("7"));
        parser = new Parser(testInputHandler);
        command = parser.getCommand();

        assertNotNull(command, "Command should not be null");
        assertEquals(SearchCommands.BACK, command.getSearchCommandInput(),
            "Command to go back is 7");
    }
    @Test
    void testValidSearchCommandForExit()
    {
        testInputHandler = new UserInputHandler(new Scanner("8"));
        parser = new Parser(testInputHandler);
        command = parser.getCommand();

        assertNotNull(command, "Command should not be null");
        assertEquals(SearchCommands.QUIT, command.getSearchCommandInput(),
            "Command to exit the search menu is 8");
    }

    @Test
    void testInvalidSearchCommand()
    {
        try
        {
            testInputHandler = new UserInputHandler(new Scanner("33"));
            parser = new Parser(testInputHandler);
            command = parser.getCommand();

            assertNotNull(command, "Command should not be null");
            assertEquals(SearchCommands.UNKNOWN, command.getSearchCommandInput(),
                "Command is not in the menu");
        } catch (Exception e)
        {
            System.out.println("Invalid input");
        }
    }

    @Test
    void testInvalidSearchCommandForNegativeInput()
    {
        try
        {
            testInputHandler = new UserInputHandler(new Scanner("-1"));
            parser = new Parser(testInputHandler);
            command = parser.getCommand();

            assertNotNull(command, "Command should not be null");
            assertEquals(SearchCommands.UNKNOWN, command.getSearchCommandInput(),
                "Command is not found in the menu");
        } catch (Exception e)
        {
            System.out.println("Invalid input");
        }
    }

    @Test
    void testInvalidSearchCommandForText()
    {
        try
        {
            testInputHandler = new UserInputHandler(new Scanner("HH!03j"));
            parser = new Parser(testInputHandler);
            command = parser.getCommand();

            assertNotNull(command, "Command should not be null");
            assertEquals(SearchCommands.UNKNOWN, command.getSearchCommandInput(),
                "Command is not found in the menu");
        }
        catch (Exception e)
        {
            System.out.println("Invalid input");
        }
    }
}
