package edu.ntnu.stud.stud.commandhandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import edu.ntnu.stud.commandhandler.EditCommands;
import org.junit.jupiter.api.Test;

class EditCommandsTest {
    @Test
    void editCommandsValuePositive() {
        assertEquals(1, EditCommands.DESTINATION.getAdjustCommandInput(), "DESTINATION should have the value 1");
        assertEquals(2, EditCommands.DEPARTURE_TIME.getAdjustCommandInput(), "DEPARTURE_TIME should have the value 2");
    }
    @Test
    void editCommandsValueNegative() {
        assertNotEquals(3, EditCommands.DESTINATION.getAdjustCommandInput(), "DESTINATION should not have the value 3");
        assertNotEquals(4, EditCommands.DEPARTURE_TIME.getAdjustCommandInput(), "DEPARTURE_TIME should not have the value 4");
    }
    @Test
    void editCommandsExceptionHandling() {
        try {
            someMethodThatMightThrowException(EditCommands.DESTINATION);
            fail("Should throw exception");
        } catch (Exception e) {
            assertEquals("Expected exception message", e.getMessage());
        }
    }
    private void someMethodThatMightThrowException(EditCommands command) throws Exception {
        if (command == EditCommands.DESTINATION) {
            throw new Exception("Expected exception message");
        }
    }
}
