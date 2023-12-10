package edu.ntnu.stud.stud.commandhandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.stud.commandhandler.Command;
import edu.ntnu.stud.commandhandler.EditCommands;
import edu.ntnu.stud.commandhandler.MainMenuCommands;
import edu.ntnu.stud.commandhandler.RemoveCommands;
import edu.ntnu.stud.commandhandler.SearchCommands;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandTest {
    Command command;
    @BeforeEach
    void setUp() {
        command = new Command(MainMenuCommands.ADD, EditCommands.DESTINATION,
            SearchCommands.DEPARTURE, RemoveCommands.TRAIN_NUMBER);
    }
    @Test
    void testConstructorAndMainCommandGetter() {
        assertEquals(MainMenuCommands.ADD, command.getMainCommandInput(), "Main menu command should match");
    }
    @Test
    void testEditCommandGetter() {
        assertEquals(EditCommands.DESTINATION, command.getEditCommandInput(), "Edit command should match");
    }
    @Test
    void testSearchCommandGetter() {
        assertEquals(SearchCommands.DEPARTURE, command.getSearchCommandInput(), "Search command should match");
    }
    @Test
    void testRemoveCommandGetter() {
        assertEquals(RemoveCommands.TRAIN_NUMBER, command.getRemoveCommandInput(), "Remove command should match");
    }

}
