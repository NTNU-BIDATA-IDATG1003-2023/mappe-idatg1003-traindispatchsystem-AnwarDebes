package edu.ntnu.stud.stud.commandhandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import edu.ntnu.stud.commandhandler.MainMenuCommands;
import org.junit.jupiter.api.Test;

class MainMenuCommandsTest {

    @Test
    void testMainMenuCommandsValues() {
        assertEquals(0, MainMenuCommands.UNKNOWN.ordinal(), "Ordinal of UNKNOWN should be 0");
        assertEquals(1, MainMenuCommands.ADD.ordinal(), "Ordinal of ADD should be 1");
        assertEquals(2, MainMenuCommands.SEARCH.ordinal(), "Ordinal of SEARCH should be 2");
        assertEquals(3, MainMenuCommands.SHOW_ALL.ordinal(), "Ordinal of SHOW_ALL should be 3");
        assertEquals(4, MainMenuCommands.EDIT.ordinal(), "Ordinal of EDIT should be 4");
        assertEquals(5, MainMenuCommands.REMOVE.ordinal(), "Ordinal of REMOVE should be 5");
        assertEquals(6, MainMenuCommands.TIME.ordinal(), "Ordinal of TIME should be 6");
        assertEquals(7, MainMenuCommands.HELP.ordinal(), "Ordinal of HELP should be 7");
        assertEquals(8, MainMenuCommands.QUIT.ordinal(), "Ordinal of QUIT should be 8");
    }

    @Test
    void testMainMenuCommandsIncorrectValues() {
        assertNotEquals(1, MainMenuCommands.UNKNOWN.ordinal(), "Ordinal of UNKNOWN should not be 1");
        assertNotEquals(2, MainMenuCommands.ADD.ordinal(), "Ordinal of ADD should not be 2");
        assertNotEquals(3, MainMenuCommands.UNKNOWN.ordinal(), "Ordinal of UNKNOWN should not be 3");
        assertNotEquals(4, MainMenuCommands.ADD.ordinal(), "Ordinal of ADD should not be 4");
        assertNotEquals(5, MainMenuCommands.UNKNOWN.ordinal(), "Ordinal of UNKNOWN should not be 5");
        assertNotEquals(6, MainMenuCommands.ADD.ordinal(), "Ordinal of ADD should not be 6");
        assertNotEquals(7, MainMenuCommands.UNKNOWN.ordinal(), "Ordinal of UNKNOWN should not be 7");
        assertNotEquals(8, MainMenuCommands.ADD.ordinal(), "Ordinal of ADD should not be 8");
    }

    @Test
    void testMainMenuCommandsGetCommandInput() {
        assertEquals(0, MainMenuCommands.UNKNOWN.ordinal(), "Command input of UNKNOWN should be 0");
        assertEquals(1, MainMenuCommands.ADD.ordinal(), "Command input of ADD should be 1");
        assertEquals(2, MainMenuCommands.SEARCH.ordinal(), "Command input of SEARCH should be 2");
        assertEquals(3, MainMenuCommands.SHOW_ALL.ordinal(), "Command input of SHOW_ALL should be 3");
        assertEquals(4, MainMenuCommands.EDIT.ordinal(), "Command input of EDIT should be 4");
        assertEquals(5, MainMenuCommands.REMOVE.ordinal(), "Command input of REMOVE should be 5");
        assertEquals(6, MainMenuCommands.TIME.ordinal(), "Command input of TIME should be 6");
        assertEquals(7, MainMenuCommands.HELP.ordinal(), "Command input of HELP should be 7");
        assertEquals(8, MainMenuCommands.QUIT.ordinal(), "Command input of QUIT should be 8");
    }

    @Test
    void testMainMenuCommandsGetCommandInputIncorrect() {
        assertNotEquals(1, MainMenuCommands.UNKNOWN.ordinal(), "Command input of UNKNOWN should not be 1");
        assertNotEquals(2, MainMenuCommands.ADD.ordinal(), "Command input of ADD should not be 2");
        assertNotEquals(3, MainMenuCommands.ADD.ordinal(), "Command input of ADD should not be 3");
        assertNotEquals(4, MainMenuCommands.SEARCH.ordinal(), "Command input of SEARCH should not be 4");
        assertNotEquals(5, MainMenuCommands.SEARCH.ordinal(), "Command input of SEARCH should not be 5");
        assertNotEquals(6, MainMenuCommands.SHOW_ALL.ordinal(), "Command input of SHOW_ALL should not be 6");
        assertNotEquals(7, MainMenuCommands.SHOW_ALL.ordinal(), "Command input of SHOW_ALL should not be 7");
        assertNotEquals(8, MainMenuCommands.EDIT.ordinal(), "Command input of EDIT should not be 8");
        assertNotEquals(9, MainMenuCommands.EDIT.ordinal(), "Command input of EDIT should not be 9");
    }
}
