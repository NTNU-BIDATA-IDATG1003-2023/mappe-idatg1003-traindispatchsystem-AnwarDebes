package edu.ntnu.stud.register;

import edu.ntnu.stud.utility.CheckValid;
import edu.ntnu.stud.entity.TrainDeparture;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Register {

  private HashMap<Integer, TrainDeparture> trainDepartures;


  public Register() {

	trainDepartures = new HashMap<>();
  }

  public HashMap<Integer, TrainDeparture> getTrainDepartures() {

	if(CheckValid.checkMAp(trainDepartures)) {
	  return trainDepartures;
	}
	return null;
  }

  public boolean addTrainDeparture(TrainDeparture trainDeparture) {
	if(trainDeparture == null) {
	  throw new NullPointerException("Train departure can not be null");
	}
	else if (trainDepartures.containsKey(trainDeparture.getTrainNumber())) {
	  throw new IllegalArgumentException("Train number already exists");
	} else {
	  trainDepartures.put(trainDeparture.getTrainNumber(), trainDeparture);
	  return true;
	}
  }

  public TrainDeparture getTrainDepartureWithNumber(int trainNumber) {

	if (CheckValid.checkValidInt(trainNumber ,"Train number should be positiv")
	&& trainDepartures.containsKey(trainNumber)) {
	  return trainDepartures.get(trainNumber);
	} else {
	  throw new IllegalArgumentException("Train number does not exist");
	}
  }

  public List<TrainDeparture> getTrainDepartureWithDestination(String destination) {
	  if(CheckValid.checkValidString(destination, "Destination needs to be a string")) {
		return  trainDepartures.values().stream()
			.filter(trainDeparture -> trainDeparture.getDestination().equals(destination)).toList();
	  }
	  throw new IllegalArgumentException("Destination does not exist");
  }

  public List<TrainDeparture> getTrainDepartureWithDepartureTime(String departureTime) {
	if(CheckValid.checkValidString(departureTime, "Departure time needs to be a string")) {
	  return trainDepartures.values().stream()
		  .filter(trainDeparture -> LocalTime.parse
			  (trainDeparture.getDepartureTime()).isBefore(LocalTime.parse(departureTime)))
		  .toList();
	}
	throw new IllegalArgumentException("Departure time does not exist");
  }

  public HashMap<Integer, TrainDeparture> updateListByTime(String departureTime) {
	if(CheckValid.checkValidString(departureTime, "Departure time needs to be a string")) {
	  trainDepartures.values().removeIf(trainDeparture -> LocalTime.parse
		  (trainDeparture.getDepartureTime()).isBefore(LocalTime.parse(departureTime)));
	}
	  return trainDepartures;
  }

  public List<TrainDeparture> getTrainDepartureWithinTime(String startTime, String endTime) {
	if(CheckValid.checkValidString(startTime, "Start time needs to be a string") &&
		CheckValid.checkValidString(endTime, "End time needs to be a string")) {
	  return trainDepartures.values().stream()
		  .filter(trainDeparture -> LocalTime.parse(trainDeparture.getDepartureTime())
			  .isAfter(LocalTime.parse(startTime)) &&
			  LocalTime.parse(trainDeparture.getDepartureTime())
				  .isBefore(LocalTime.parse(endTime)))
		  .toList();
	}
	return null;
  }


  public boolean removeTrainDepartures(String departureTime ) {

	if (CheckValid.checkValidString(departureTime, "Departure time needs to be a string")) {
	  trainDepartures.values().removeIf(trainDeparture -> LocalTime.parse(
		  trainDeparture.getDepartureTime()).isBefore(LocalTime.parse(departureTime)));
	  return true;
	}
	return false;
  }

  public boolean removeTrainDepartures(int trainNumber) {
	if (CheckValid.checkValidInt(trainNumber, "Train number needs to be a positive number")) {
	  trainDepartures.remove(trainNumber);
	  return true;
  }
	return false;
  }

  public boolean removeTrainDeparturesByDestination(String destination) {
	if(CheckValid.checkValidString(destination, "Destination needs to be a string")) {
	  trainDepartures.values()
		  .removeIf(trainDeparture -> trainDeparture.getDestination().equals(destination));
	}
	  return true;
  }

  public List<TrainDeparture> sortTrainDepartures() {
	if(trainDepartures.isEmpty()) {
	  throw new IllegalArgumentException("Train departure list is empty");
	}
	return trainDepartures.values().stream().sorted(
		Comparator.comparing(trainDeparture -> LocalTime.parse(trainDeparture.getDepartureTime()))).toList();
  }
  }

