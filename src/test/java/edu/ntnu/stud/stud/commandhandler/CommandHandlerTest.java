package edu.ntnu.stud.stud.commandhandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import edu.ntnu.stud.commandhandler.CommandHandler;
import edu.ntnu.stud.commandhandler.EditCommands;
import edu.ntnu.stud.commandhandler.MainMenuCommands;
import edu.ntnu.stud.commandhandler.RemoveCommands;
import edu.ntnu.stud.commandhandler.SearchCommands;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 class CommandHandlerTest {
    CommandHandler commandHandler;

    @BeforeEach
    void setUp() {
        commandHandler = new CommandHandler();
    }
    @Test
    void testMainMenuCommandWithInvalidOrdinal() {
        assertEquals(MainMenuCommands.UNKNOWN, commandHandler.getMainCommandInput(1000), "Should return UNKNOWN for invalid ordinal");
    }

    @Test
    void testEditCommandWithInvalidOrdinal() {
        assertEquals(EditCommands.UNKNOWN, commandHandler.getEditCommandInput(1000), "Should return UNKNOWN for invalid ordinal");
    }

    @Test
    void testSearchCommandWithInvalidOrdinal() {
        assertEquals(SearchCommands.UNKNOWN, commandHandler.getSearchCommandInput(1000), "Should return UNKNOWN for invalid ordinal");
    }

    @Test
    void testRemoveCommandWithInvalidOrdinal() {
        assertEquals(RemoveCommands.UNKNOWN, commandHandler.getRemoveCommandInput(1000), "Should return UNKNOWN for invalid ordinal");
    }
    @Test
    void testMainMenuCommandWithValidOrdinal() {
        // Assuming 'ADD' is a valid command and its ordinal is not 0
        assertNotEquals(MainMenuCommands.UNKNOWN, commandHandler.getMainCommandInput(1),
            "Valid command should not return UNKNOWN");
    }

    @Test
    void testEditCommandWithValidOrdinal() {
        assertNotEquals(EditCommands.UNKNOWN, commandHandler.getEditCommandInput(1),
            "Valid command should not return UNKNOWN");
    }

    @Test
    void testSearchCommandWithValidOrdinal() {
        assertNotEquals(SearchCommands.UNKNOWN, commandHandler.getSearchCommandInput(1),
            "Valid command should not return UNKNOWN");
    }

    @Test
    void testRemoveCommandWithValidOrdinal() {
        assertNotEquals(RemoveCommands.UNKNOWN, commandHandler.getRemoveCommandInput(1),
            "Valid command should not return UNKNOWN");
    }

}
