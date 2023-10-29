package edu.ntnu.stud.entity;

import edu.ntnu.stud.utility.CheckValid;

/**
 * The TrainDeparture class represents the departure details of a train. It includes information such as
 * the departure time, line, train number, destination, track, and delay.
 *
 * @author Anwar Debes
 * @see CheckValid
 * @version 27.10.2023
 */
public class TrainDeparture {

  private String departureTime;

  private String line;

  private int trainNumber;

  private String destination;

  private int track;

  private String delay;

	/**
	 * Constructs a new TrainDeparture instance with the specified details.
	 *
	 * @param departureTime The time of departure in the format hh:mm.
	 * @param line          The line on which the train operates.
	 * @param trainNumber   The number assigned to the train.
	 * @param destination   The destination of the train.
	 * @param track         The track from which the train departs.
	 * @param delay         The delay of the train in the format hh:mm.
	 */
  public TrainDeparture(String departureTime, String line, int trainNumber, String destination, int track,
						String delay) {
	setDepartureTime(departureTime);
	setLine(line);
	setTrainNumber(trainNumber);
	setDestination(destination);
	setTrack(track);
	setDelay(delay);
  }

	/**
	 * Returns the departure time of the train.
	 *
	 * @return The departure time in the format hh:mm.
	 */
  public String getDepartureTime() {

	return departureTime;
  }

	/**
	 * Sets the departure time of the train after validating the input.
	 *
	 * @param departureTime The departure time in the format hh:mm.
	 */
  public void setDepartureTime(String departureTime) {

	if (CheckValid.checkValidString(departureTime, "Time needs to be a string") &&
		CheckValid.checkValidTime
			(departureTime, "Times needs to be in the form of hh:mm")) {
	  this.departureTime = departureTime;
	}
  }

	/**
	 * Returns the line on which the train operates.
	 *
	 * @return The line of the train.
	 */
  public String getLine() {

	return line;
  }

	/**
	 * Sets the line of the train after validating the input.
	 *
	 * @param line The line on which the train operates. It must be a valid string.
	 */
  public void setLine(String line) {

	if (CheckValid.checkValidString(line, "Line needs to be a string")) {
	  this.line = line;
	}
  }

	/**
	 * Returns the number assigned to the train.
	 *
	 * @return The train number.
	 */
  public int getTrainNumber() {

	return trainNumber;
  }

	/**
	 * Sets the train number after validating the input.
	 *
	 * @param trainNumber The number assigned to the train. It needs to be a positive number.
	 */
  public void setTrainNumber(int trainNumber) {

	if (CheckValid.checkValidInt
		(trainNumber, "TrainDeparture number needs to be a positive number")) {
	  this.trainNumber = trainNumber;
	}
  }

	/**
	 * Returns the destination of the train.
	 *
	 * @return The destination of the train.
	 */
  public String getDestination() {

	return destination;
  }

	/**
	 * Sets the destination of the train after validating the input.
	 *
	 * @param destination The destination of the train. It must be a valid string.
	 */
  public void setDestination(String destination) {

	if (CheckValid.checkValidString(destination, "Destination needs to be a string")) {
	  this.destination = destination;
	}
  }

	/**
	 * Returns the track from which the train departs.
	 *
	 * @return The track number.
	 */
  public int getTrack() {

	return track;
  }

	/**
	 * Sets the track of the train after validating the input.
	 *
	 * @param track The track from which the train departs. It needs to be a positive number.
	 */
  public void setTrack(int track) {

	if (CheckValid.checkValidTrackNumber(track, "Track needs to be a positive number")) {
	  this.track = track;
	}
  }

	/**
	 * Returns the delay of the train.
	 *
	 * @return The delay in the format hh:mm.
	 */
  public String getDelay() {

	return delay;
  }

	/**
	 * Sets the delay of the train after validating the input.
	 *
	 * @param delay The delay of the train. It must be a valid string in the format hh:mm.
	 */
  public void setDelay(String delay) {

	if (CheckValid.checkValidString(delay, "delay needs to be a valid string") &&
		CheckValid.checkValidTime(delay, "Times needs to be in the form of hh:mm")) {
	  this.delay = delay;
	}
  }

	/**
	 * Returns a string representation of the TrainDeparture instance, displaying all the details.
	 *
	 * @return A string representation of the train departure details.
	 */
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
