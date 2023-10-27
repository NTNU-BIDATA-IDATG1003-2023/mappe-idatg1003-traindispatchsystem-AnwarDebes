package edu.ntnu.stud;


public class TrainDeparture {


  private String departureTime;

  private String line;

  private int trainNumber;

  private String destination;

  private int track;

  private String delay;

  public TrainDeparture(String departureTime, String line, int trainNumber, String destination, int track,
						String delay) {
	setDepartureTime(departureTime);
	setLine(line);
	setTrainNumber(trainNumber);
	setDestination(destination);
	setTrack(track);
	setDelay(delay);
  }


  public String getDepartureTime() {

	return departureTime;
  }

  public void setDepartureTime(String departureTime) {

	if (CheckValid.checkValidString(departureTime, "Time needs to be a string") &&
		CheckValid.checkValidTime
			(departureTime, "Times needs to be in the form of hh:mm")) {
	  this.departureTime = departureTime;
	}
  }

  public String getLine() {

	return line;
  }

  public void setLine(String line) {

	if (CheckValid.checkValidString(line, "Line needs to be a string")) {
	  this.line = line;
	}
  }

  public int getTrainNumber() {

	return trainNumber;
  }

  public void setTrainNumber(int trainNumber) {

	if (CheckValid.checkValidInt
		(trainNumber, "TrainDeparture number needs to be a positive number")) {
	  this.trainNumber = trainNumber;
	}
  }


  public String getDestination() {

	return destination;
  }

  public void setDestination(String destination) {

	if (CheckValid.checkValidString(destination, "Destination needs to be a string")) {
	  this.destination = destination;
	}
  }

  public int getTrack() {

	return track;
  }

  public void setTrack(int track) {

	if (CheckValid.checkValidTrackNumber(track, "Track needs to be a positive number")) {
	  this.track = track;
	}
  }

  public String getDelay() {

	return delay;
  }

  public void setDelay(String delay) {

	if (CheckValid.checkValidString(delay, "delay needs to be a valid string") &&
		CheckValid.checkValidTime(delay, "Times needs to be in the form of hh:mm")) {
	  this.delay = delay;
	}
  }

  public String display() {
	StringBuilder sb = new StringBuilder();
	sb.append("Train number: ").append(trainNumber).append("\t");
	sb.append("Departure time: ").append(departureTime).append("\t");
	sb.append("Line: ").append(line).append("\t");
	sb.append("Destination: ").append(destination).append("\t");
	sb.append("Track: ").append(track).append("\t");
	sb.append("Delay: ").append(delay).append("\t");
	return sb.toString();
  }
}
