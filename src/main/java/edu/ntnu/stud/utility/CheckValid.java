package edu.ntnu.stud.utility;

import edu.ntnu.stud.entity.TrainDeparture;

import java.time.LocalTime;
import java.util.Set;

/**
 * The CheckValid class provides static methods to perform various validation checks.
 */
public  class CheckValid {
	/**
	 * Checks if the provided string is not null, empty, or blank.
	 *
	 * @param string            The string to be checked.
	 * @param exceptionMessage  The message to be used in the exception if the check fails.
	 * @return True if the string is valid.
	 * @throws IllegalArgumentException If the string is null, empty, or blank.
	 */
  public static Boolean checkValidString(String string, String exceptionMessage) {
	if 	(string == null || string.isEmpty() || string.isBlank()){
	  throw new IllegalArgumentException(exceptionMessage);
	}
	return true;
  }
	/**
	 * Checks if the provided integer is a positive number.
	 *
	 * @param number            The number to be checked.
	 * @param exceptionMessage  The message to be used in the exception if the check fails.
	 * @return True if the number is positive.
	 * @throws IllegalArgumentException If the number is negative.
	 */
  public static Boolean checkValidInt(int number, String exceptionMessage) {
	if(number < 0) {
	  throw new IllegalArgumentException(exceptionMessage);
	}
	return true;
  }
	/**
	 * Checks if the provided integer is a positive number.
	 *
	 * @param number  The number to be checked.
	 * @return True if the number is positive.
	 */
  public static Boolean checkValidInt(int number) {
	return (number> 0);
  }
	/**
	 * Checks if the provided track number is not less than -1.
	 *
	 * @param number            The track number to be checked.
	 * @param exceptionMessage  The message to be used in the exception if the check fails.
	 * @return True if the track number is valid.
	 * @throws IllegalArgumentException If the track number is less than -1.
	 */
  public static Boolean checkValidTrackNumber(int number, String exceptionMessage) {
	if(number < -1) {
	  throw new IllegalArgumentException(exceptionMessage);
	}
	return true;
  }
	/**
	 * Checks if the provided time is in the format hh:mm.
	 *
	 * @param time              The time to be checked.
	 * @param exceptionMessage  The message to be used in the exception if the check fails.
	 * @return True if the time is in the correct format.
	 * @throws IllegalArgumentException If the time is not in the format hh:mm.
	 */
  public static Boolean checkValidTime(String time, String exceptionMessage) {
	if(!time.matches("(2[0-3]|[01][0-9]):[0-5][0-9]")){
	  throw new IllegalArgumentException(exceptionMessage);
	}
	return 	true;
  }
	/**
	 * Checks if the provided map of TrainDepartures is not empty.
	 *
	 * @param set  The map to be checked.
	 * @return True if the map is not empty.
	 * @throws IllegalArgumentException If the map is empty or null.
	 */
  public  static boolean checkSet(Set<TrainDeparture> set) {

	if (set.isEmpty() || set == null) {
	  throw new IllegalArgumentException("List is empty");
	}
	return true;
  }

	public static boolean checkIfThereIsDelay(LocalTime delay) {
	  return delay.isAfter(LocalTime.parse("00:00"));
	}
	public static boolean checkIfThereIsTrack(int track) {
	  return track != -1;
	}
}
