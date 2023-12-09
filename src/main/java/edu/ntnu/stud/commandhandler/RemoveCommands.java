package edu.ntnu.stud.commandhandler;

public enum RemoveCommands {

  UNKNOWN(0), TRAIN_NUMBER(1), DESTINATION(2),
  DEPARTURE_TIME(3), TRACK(4), LANE(5),
  BACK(2), EXIT(3);

  private final int removeCommandInput;

  private RemoveCommands(int removeCommandInput) {
	this.removeCommandInput = removeCommandInput;
  }

  public int getRemoveCommandInput() {
	return removeCommandInput;
  }
}
