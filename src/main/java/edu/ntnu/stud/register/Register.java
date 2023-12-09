package edu.ntnu.stud.register;

import edu.ntnu.stud.entity.TrainDeparture;
import edu.ntnu.stud.utility.CheckValid;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Register {

    private Set<TrainDeparture> trainDepartures;

    private List<TrainDeparture> results;


    public Register() {
        results = new ArrayList<>();
        trainDepartures = new HashSet<>();
    }

    public Set<TrainDeparture> getTrainDepartures() {
        try {
            CheckValid.checkSet(trainDepartures);
            return trainDepartures;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("List is empty");
        }
    }

    public boolean addTrainDeparture(TrainDeparture trainDeparture) {
        if (trainDeparture == null) {
            throw new NullPointerException("Train departure can not be null");
        }
        trainDepartures.forEach(trainDeparture1 -> {
            if (calculateTheTotalTimeToDeparture(trainDeparture1).equals(calculateTheTotalTimeToDeparture(trainDeparture))) {
                if (trainDeparture1.getTrainNumber() == trainDeparture.getTrainNumber()) {
                    throw new IllegalStateException("Train departure with the given time and number already exists");
                } else if (trainDeparture1.getTrack() == trainDeparture.getTrack() && trainDeparture.getTrack() != -1) {
                    throw new IllegalArgumentException("Train departure with the given time and track already exists");
                }
            }
        });
        trainDepartures.add(trainDeparture);
        return true;
    }

    public List<TrainDeparture> getTrainDepartureWithNumber(int trainNumber) {
        if (CheckValid.checkValidInt(trainNumber, "Train number should be positiv")) {
            results =  filterDeparturesByNumber(trainNumber);
            if(!results.isEmpty()) {
                return results;
            }
        }
        throw new IllegalArgumentException("Train number does not exist");
    }

    public List<TrainDeparture> getTrainDepartureWithDestination(String destination) {
        if (CheckValid.checkValidString(destination, "Destination needs to be a string")) {
            results = filterDeparturesByDestination(destination);
            if(!results.isEmpty()) {
                return results;
            }
        }
        throw new IllegalArgumentException("Train number does not exist");
    }


    public List<TrainDeparture> getTrainDepartureWithDepartureTime(String departureTime) {
        if (CheckValid.checkValidString(departureTime, "Departure time needs to be a string")) {
            results = filterDeparturesByTime(departureTime);
            if(!results.isEmpty()) {
                return results;
            }
        }
        throw new IllegalArgumentException("Train number does not exist");
    }

    public List<TrainDeparture> getTrainDepartureWithTrack(int track) {
        if (CheckValid.checkValidTrackNumber(track, "Track needs to be a positive number")) {
            results = filterDeparturesByTrack(track);
            if(!results.isEmpty()) {
                return results;
            }
        }
        throw new IllegalArgumentException("Train number does not exist");
    }


    public List<TrainDeparture> getTrainDepartureWithLane(String line) {
        if (CheckValid.checkValidString(line, "Line needs to be a string")) {
            results = filterDeparturesByLine(line);
            if(!results.isEmpty()) {
                return results;
            }
        }
        throw new IllegalArgumentException("Train number does not exist");
    }

    public List<TrainDeparture> getTrainDepartureWithDelay(String delay) {
        if (CheckValid.checkValidString(delay, "Delay needs to be a string")) {
            results = filterDeparturesByDelay(delay);
            if(!results.isEmpty()) {
                return results;
            }
        }
        throw new IllegalArgumentException("Train number does not exist");
    }


    public void removeTrainDepartures(String departureTime) {
        if (CheckValid.checkValidString(departureTime, "Departure time needs to be a string")) {
            trainDepartures.removeIf(trainDeparture -> {
                LocalTime departureTimeWithDelay = calculateTheTotalTimeToDeparture(trainDeparture);
                if (departureTimeWithDelay.isBefore(LocalTime.parse(departureTime))) {
                    return true;
                } else {
                    return false;
                }
            });
        }
    }

    private LocalTime calculateTheTotalTimeToDeparture(TrainDeparture trainDeparture) {
        String[] delay = trainDeparture.getDelay().split(":");
        int delayHours = Integer.parseInt(delay[0]);
        int delayMinutes = Integer.parseInt(delay[1]);
        return LocalTime.parse(trainDeparture.getDepartureTime()).plusHours(delayHours).plusMinutes(delayMinutes);
    }

    public boolean removeTrainDeparturesWithTime(String departureTime, int index) {
        if (CheckValid.checkValidString(departureTime, "Departure time needs to be a string")) {
         results = filterDeparturesByTime(departureTime);
            if(!results.isEmpty()) {
                trainDepartures.remove(results.get(index));
                return true;
            }

        }
        return false;
    }

    public boolean removeTrainDepartures(int trainNumber, int index) {
        if (CheckValid.checkValidInt(trainNumber, "Train number needs to be a positive number")) {
            results = filterDeparturesByNumber(trainNumber);
            if(!results.isEmpty()) {
                trainDepartures.remove(results.get(index));
                return true;
            }
        }
        return false;
    }

    public boolean removeTrainDeparturesByTrack(int track, int index) {
        if (CheckValid.checkValidTrackNumber(track, "Track needs to be a positive number")) {
            results = filterDeparturesByTrack(track);
            if(!results.isEmpty()) {
                trainDepartures.remove(results.get(index));
                return true;
            }
        }
        return false;

    }

    public boolean removeTrainDeparturesByLane(String line, int index) {
        if (CheckValid.checkValidString(line, "Line needs to be a string")) {
            results = filterDeparturesByLine(line);
            if(!results.isEmpty()) {
                trainDepartures.remove(results.get(index));
                return true;
            }
        }
        return false;
    }

    public boolean removeTrainDeparturesByDestination(String destination, int index) {
        if (CheckValid.checkValidString(destination, "Destination needs to be a string")) {
            results = filterDeparturesByDestination(destination);
            if(!results.isEmpty()) {
                trainDepartures.remove(results.get(index));
                return true;
            }

        }
        return false;
    }

    public List<TrainDeparture> sortTrainDepartures() {
        return  trainDepartures.stream().sorted(
                Comparator.comparing(trainDeparture -> LocalTime.parse(trainDeparture.getDepartureTime()))).collect(Collectors.toCollection(ArrayList::new));
    }


    public List<TrainDeparture> filterDeparturesByTime(String departureTime) {
        results.clear();
        if (CheckValid.checkValidString(departureTime, "Departure time needs to be a string")) {
            results = trainDepartures.stream()
                    .filter(trainDeparture -> LocalTime.parse
                            (trainDeparture.getDepartureTime()).equals(LocalTime.parse(departureTime)))
                    .collect(Collectors.toCollection(ArrayList::new));
            if(!results.isEmpty()) {
                return results;
            }
        }
        throw new IllegalArgumentException("Train number does not exist");
    }

    public List<TrainDeparture> filterDeparturesByTrack(int track) {
        results.clear();

        if (CheckValid.checkValidTrackNumber(track, "Track needs to be a positive number")) {
            results = trainDepartures.stream()
                    .filter(trainDeparture -> trainDeparture.getTrack() == track)
                    .collect(Collectors.toCollection(ArrayList::new));
            if(!results.isEmpty()) {
                return results;
            }
        }
        throw new IllegalArgumentException("Train number does not exist");
    }

    public List<TrainDeparture> filterDeparturesByLine(String line) {
        results.clear();
        if (CheckValid.checkValidString(line, "Line needs to be a string")) {
            results = trainDepartures.stream()
                    .filter(trainDeparture -> trainDeparture.getLine().equals(line))
                    .collect(Collectors.toCollection(ArrayList::new));
            if(!results.isEmpty()) {
                return results;
            }
        }
        throw new IllegalArgumentException("Train number does not exist");
    }

    public List<TrainDeparture> filterDeparturesByDestination(String destination) {
        results.clear();
        if (CheckValid.checkValidString(destination, "Destination needs to be a string")) {
            results = trainDepartures.stream()
                    .filter(trainDeparture -> trainDeparture.getDestination().equals(destination))
                    .collect(Collectors.toCollection(ArrayList::new));
            if(!results.isEmpty()) {
                return results;
            }
        }
        throw new IllegalArgumentException("Train number does not exist");
    }

    public List<TrainDeparture> filterDeparturesByDelay(String delay) {
        results.clear();
        if (CheckValid.checkValidString(delay, "Delay needs to be a string")) {
            results = trainDepartures.stream()
                    .filter(trainDeparture -> trainDeparture.getDelay().equals(delay))
                    .collect(Collectors.toCollection(ArrayList::new));
            if(!results.isEmpty()) {
                return results;
            }
        }
        throw new IllegalArgumentException("Train number does not exist");
    }

    public List<TrainDeparture> filterDeparturesByNumber(int trainNumber) {
        if (CheckValid.checkValidInt(trainNumber, "Train number should be positiv")) {
            results =  trainDepartures.stream().filter(trainDeparture -> trainDeparture.getTrainNumber() == trainNumber).collect(Collectors.toCollection(ArrayList::new));
            if(!results.isEmpty()) {
                return results;
            }
        }
        throw new IllegalArgumentException("Train number does not exist");
    }

    public TrainDeparture getTrainDeparture(int trainNumber,int index) {
        if (CheckValid.checkValidInt(index, "Index should be positiv")) {
            results = filterDeparturesByNumber(trainNumber);
            return results.get(index);
        }
        throw new IllegalArgumentException("Train number does not exist");
    }



}

