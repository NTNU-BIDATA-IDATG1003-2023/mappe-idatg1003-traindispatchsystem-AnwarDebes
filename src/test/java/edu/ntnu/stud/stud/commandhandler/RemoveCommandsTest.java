package edu.ntnu.stud.stud.commandhandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import edu.ntnu.stud.commandhandler.Command;
import edu.ntnu.stud.commandhandler.Parser;
import edu.ntnu.stud.commandhandler.RemoveCommands;
import edu.ntnu.stud.ui.UserInputHandler;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class RemoveCommandsTest
{
    UserInputHandler testInputHandler;
    Parser parser;
    Command command;

    @Test
    void testValidRemoveCommandForTrainNumber()
    {
        testInputHandler = new UserInputHandler(new Scanner("1"));
        parser = new Parser(testInputHandler);
        command = parser.getCommand();

        assertNotNull(command, "Command should not be null");
        assertEquals(RemoveCommands.TRAIN_NUMBER, command.getRemoveCommandInput(),
            "Command to remove train number is 1");
    }

    @Test
    void testValidRemoveCommandForDestination()
    {
        testInputHandler = new UserInputHandler(new Scanner("2"));
        parser = new Parser(testInputHandler);
        command = parser.getCommand();

        assertNotNull(command, "Command should not be null");
        assertEquals(RemoveCommands.DESTINATION, command.getRemoveCommandInput(),
            "Command to remove destination is 2");
    }

    @Test
    void testValidRemoveCommandForDepartureTime()
    {
        testInputHandler = new UserInputHandler(new Scanner("3"));
        parser = new Parser(testInputHandler);
        command = parser.getCommand();

        assertNotNull(command, "Command should not be null");
        assertEquals(RemoveCommands.DEPARTURE_TIME, command.getRemoveCommandInput(),
            "Command to remove departure time is 3");
    }

    @Test
    void testValidRemoveCommandForTrack()
    {
        testInputHandler = new UserInputHandler(new Scanner("4"));
        parser = new Parser(testInputHandler);
        command = parser.getCommand();

        assertNotNull(command, "Command should not be null");
        assertEquals(RemoveCommands.TRACK, command.getRemoveCommandInput(),
            "Command to remove track is 4");
    }

    @Test
    void testValidRemoveCommandForLane()
    {
        testInputHandler = new UserInputHandler(new Scanner("5"));
        parser = new Parser(testInputHandler);
        command = parser.getCommand();

        assertNotNull(command, "Command should not be null");
        assertEquals(RemoveCommands.LANE, command.getRemoveCommandInput(),
            "Command to remove lane is 5");
    }

    @Test
    void testValidRemoveCommandForBack()
    {
        testInputHandler = new UserInputHandler(new Scanner("6"));
        parser = new Parser(testInputHandler);
        command = parser.getCommand();

        assertNotNull(command, "Command should not be null");
        assertEquals(RemoveCommands.BACK, command.getRemoveCommandInput(),
            "Command to go back to the main menu is 6");
    }

    @Test
    void testValidRemoveCommandForExit()
    {
        testInputHandler = new UserInputHandler(new Scanner("7"));
        parser = new Parser(testInputHandler);
        command = parser.getCommand();

        assertNotNull(command, "Command should not be null");
        assertEquals(RemoveCommands.EXIT, command.getRemoveCommandInput(),
            "Command to exit the remove menu is 7");
    }

    @Test
    void testInvalidRemoveCommand()
    {
        try
        {
            testInputHandler = new UserInputHandler(new Scanner("8"));
            parser = new Parser(testInputHandler);
            command = parser.getCommand();

            assertNotNull(command, "Command should not be null");
            assertEquals(RemoveCommands.UNKNOWN, command.getRemoveCommandInput(),
                "Command is not found in the menu");
        } catch (Exception e)
        {
            System.out.println("Invalid input");
        }
    }

    @Test
    void testInvalidRemoveCommandForNegativeInput()
    {
        try
        {
            testInputHandler = new UserInputHandler(new Scanner("-1"));
            parser = new Parser(testInputHandler);
            command = parser.getCommand();

            assertNotNull(command, "Command should not be null");
            assertEquals(RemoveCommands.UNKNOWN, command.getRemoveCommandInput(),
                "Command is not found in the menu");
        } catch (Exception e)
        {
            System.out.println("Invalid input");
        }
    }

    @Test
    void testInvalidRemoveCommandForText()
    {
        try
        {
            testInputHandler = new UserInputHandler(new Scanner("HH!03j"));
            parser = new Parser(testInputHandler);
            command = parser.getCommand();

            assertNotNull(command, "Command should not be null");
            assertEquals(RemoveCommands.UNKNOWN, command.getRemoveCommandInput(),
                "Command is not found in the menu");
        }
        catch (Exception e)
        {
            System.out.println("Invalid input");
    }
    }
}
