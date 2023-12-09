package edu.ntnu.stud.register;

import edu.ntnu.stud.utility.CheckValid;
import edu.ntnu.stud.entity.TrainDeparture;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Register {

  private HashMap<Integer, TrainDeparture> trainDepartures;

  private List<TrainDeparture> results;


  public Register() {
	results = new ArrayList<>();
	trainDepartures = new HashMap<>();
  }

  public HashMap<Integer, TrainDeparture> getTrainDepartures() {
	try{
	CheckValid.checkMAp(trainDepartures);
	return trainDepartures;
	}catch (IllegalArgumentException e) {
	  throw new IllegalArgumentException("List is empty");
	}
  }

  public boolean addTrainDeparture(TrainDeparture trainDeparture) {
	if(trainDeparture == null) {
	  throw new NullPointerException("Train departure can not be null");
	}
	else if (trainDepartures.containsKey(trainDeparture.getTrainNumber())) {
	  throw new IllegalArgumentException("Train number already exists");
	  // legge til for å skjekke om linjer og track er ikke like for det samme tidspunkt.
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
	  results.clear();
	  if(CheckValid.checkValidString(destination, "Destination needs to be a string")) {
		  results = trainDepartures.values().stream()
				  .filter(trainDeparture -> trainDeparture.getDestination().equals(destination)).collect(Collectors.toCollection(ArrayList::new));
		  return results;
	  } else{
			  throw new IllegalArgumentException("Destination does not exist");
		  }
	  }
  

  public List<TrainDeparture> getTrainDepartureWithDepartureTime(String departureTime) {
	  results.clear();
	if(CheckValid.checkValidString(departureTime, "Departure time needs to be a string")) {
	   results =  trainDepartures.values().stream()
		  .filter(trainDeparture -> LocalTime.parse
			  (trainDeparture.getDepartureTime()).equals(LocalTime.parse(departureTime)))
		  .collect(Collectors.toCollection(ArrayList::new));
	   return results;
	} else{
		throw new IllegalArgumentException("Departure time does not exist");
	}
  }

  public List<TrainDeparture> getTrainDepartureWithTrack(int track) {
	  results.clear();

	  if(CheckValid.checkValidTrackNumber(track, "Track needs to be a positive number")) {
	  results = trainDepartures.values().stream()
		  .filter(trainDeparture -> trainDeparture.getTrack() == track)
		  .collect(Collectors.toCollection(ArrayList::new));
		return results;
	} else{
		throw new IllegalArgumentException("Track does not exist");
	}
  }

  public List<TrainDeparture> getTrainDepartureWithLine(String line) {
	  results.clear();
	if(CheckValid.checkValidString(line, "Line needs to be a string")) {
	  results = trainDepartures.values().stream()
		  .filter(trainDeparture -> trainDeparture.getLine().equals(line))
		  .collect(Collectors.toCollection(ArrayList::new));
		return results;
	} else{
		throw new IllegalArgumentException("Line does not exist");
	}
  }

  public List<TrainDeparture> getTrainDepartureWithDelay(String delay) {
	if(CheckValid.checkValidString(delay, "Delay needs to be a string")) {
	  results = trainDepartures.values().stream()
		  .filter(trainDeparture -> trainDeparture.getDelay().equals(delay))
		  .collect(Collectors.toCollection(ArrayList::new));
		return results;
	} else{
		throw new IllegalArgumentException("delay does not exist");
	}
  }



  public void removeTrainDepartures(String departureTime ) {

	  if (CheckValid.checkValidString(departureTime, "Departure time needs to be a string")) {
		  trainDepartures.values().removeIf(trainDeparture -> {
			  String[] delay = trainDeparture.getDelay().split(":");
			  int delayHours = Integer.parseInt(delay[0]);
			  int delayMinutes = Integer.parseInt(delay[1]);
			  LocalTime departureTimeWithDelay = LocalTime.parse(trainDeparture.getDepartureTime()).plusHours(delayHours).plusMinutes(delayMinutes);
			  if (departureTimeWithDelay.isBefore(LocalTime.parse(departureTime))) {
				  return true;
			  } else {
				  return false;
			  }
		  });
	  }
  }
  public boolean removeTrainDeparturesWithTime(String departureTime ) {
	  int listSize = trainDepartures.size();
	  if (CheckValid.checkValidString(departureTime, "Departure time needs to be a string")) {
		  trainDepartures.values().removeIf(trainDeparture -> {
			  String[] delay = trainDeparture.getDelay().split(":");
			  int delayHours = Integer.parseInt(delay[0]);
			  int delayMinutes = Integer.parseInt(delay[1]);
			  LocalTime departureTimeWithDelay = LocalTime.parse(departureTime).plusHours(delayHours).plusMinutes(delayMinutes);
			  if (departureTimeWithDelay.equals(LocalTime.parse(departureTime))) {
				  return true;
			  } else {
				  return false;
			  }
		  });
	  }
	  if (listSize == trainDepartures.size()) {
		  throw new IllegalArgumentException("Departure time does not exist");
	  }else{
		  return true;
	  }
  }

  public boolean removeTrainDepartures(int trainNumber) {
	if (CheckValid.checkValidInt(trainNumber, "Train number needs to be a positive number")) {
	  trainDepartures.remove(trainNumber);
	  return true;
  }
	return false;
  }

  public boolean removeTrainDeparturesByTrack(int track) {
	if(CheckValid.checkValidTrackNumber(track, "Track needs to be a positive number")) {
	  trainDepartures.values().removeIf(trainDeparture -> trainDeparture.getTrack() == track);
	  return true;
	}
	return false;

  }
  public boolean removeTrainDeparturesByLane(String line) {
	if(CheckValid.checkValidString(line, "Line needs to be a string")) {
	  trainDepartures.values().removeIf(trainDeparture -> trainDeparture.getLine().equals(line));
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
	  results.clear();
	results = trainDepartures.values().stream().sorted(
		Comparator.comparing(trainDeparture -> LocalTime.parse(trainDeparture.getDepartureTime()))).collect(Collectors.toCollection(ArrayList::new));
	  return results;
  }
  }

