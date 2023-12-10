package edu.ntnu.stud.commandhandler;

/**
 * RemoveCommands is an enum class which represents a set of commands used for removing
 * various entities or attributes in a train system.
 * This enum encapsulates different types of removal commands that can be issued, including operations like removing a train number,
 * destination, departure time.
 * Each enum constant is associated with an integer value that represents the specific removal command input.
 * @author Anwar Debes
 * @version 0.0.1
 */

public enum RemoveCommands {

     //Represents an unknown command
     UNKNOWN(0),
    // Command to remove a train number
    TRAIN_NUMBER(1),
    // Command to remove a destination
    DESTINATION(2),
    // Command to remove a departure time
    DEPARTURE_TIME(3),
    // Command to remove a track
    TRACK(4),
    // Command to remove a delay
    LANE(5),
    // Command to remove a lane
    BACK(6),
    // Command to exit the remove menu
    EXIT(7);

     // The command input of the remove menu.
     private final int removeCommandInput;

    /**
     * Constructs a {@code RemoveCommands} enum constant with the specified input value.
     *
     * @param removeCommandInput the integer value representing the command.
     */
  private RemoveCommands(int removeCommandInput) {
	this.removeCommandInput = removeCommandInput;
  }
    /**
     * Returns the integer value associated with the command.
     *
     * @return the integer value of the command.
     */
  public int getRemoveCommandInput() {
	return removeCommandInput;
  }
}
