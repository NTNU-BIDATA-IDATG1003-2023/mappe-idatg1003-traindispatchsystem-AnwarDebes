package edu.ntnu.stud.register;

import edu.ntnu.stud.entity.TrainDeparture;
import edu.ntnu.stud.utility.CheckValid;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The Register class manages the registration and retrieval of train departure information.
 * It provides functionalities for adding, removing, and searching for train departures based on
 * various attributes like train number, departure time, track, lane, and delay.
 * It also allows sorting of train departures and manages a mapping of lanes to destinations.
 *
 * @author Anwar Debes
 * @version 0.0.1
 * @see TrainDeparture
 * @see CheckValid
 */
public class Register
{
    // A set to store all train departures
    private final Set<TrainDeparture> trainDepartures;
    // A map to store the lane and destination of each train departure
    private final Map<String, String> laneMap;
    // A list to store the results of a search
    private List<TrainDeparture> results;

    /**
     * Constructs a new Register instance.
     * Initializes the sets and maps used for storing train departures and lane information.
     */
    public Register()
    {
        results = new ArrayList<>();
        trainDepartures = new HashSet<>();
        laneMap = new HashMap<>();
    }

    /**
     * Retrieves the set of train departures.
     *
     * @return a set of {@link TrainDeparture} objects.
     * @throws IllegalArgumentException if the set is empty.
     */
    public Set<TrainDeparture> getTrainDepartures()
    {
        try
        {
            CheckValid.checkSet(trainDepartures);
            return trainDepartures;
        } catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException("Set is empty");
        }
    }

    /**
     * Retrieves the lane map which maps destinations to their respective lanes.
     *
     * @return a map of destination-lane pairs.
     * @throws IllegalArgumentException if the map is empty.
     */
    public Map<String, String> getLaneMap()
    {
        try
        {
            CheckValid.checkMap(laneMap);
            return laneMap;
        } catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException("List is empty");
        }
    }

    /**
     * Retrieves a set of lane names.
     *
     * @return a set of lane names.
     * @throws NullPointerException if the lane map is empty.
     */
    public Set<String> getLaneList()
    {
        try
        {
            CheckValid.checkMap(laneMap);
            return laneMap.keySet();
        } catch (IllegalArgumentException e)
        {
            throw new NullPointerException("List is empty");
        }
    }

    /**
     * Adds a new train departure to the register.
     *
     * @param trainDeparture The {@link TrainDeparture} object to add.
     * @return true if the train departure is successfully added.
     * @throws NullPointerException     if the train departure object is null.
     * @throws IllegalStateException    if a train departure with the same time and number already
     *     exists.
     * @throws IllegalArgumentException if a train departure with the same time and track already
     *     exists.
     */
    public boolean addTrainDeparture(TrainDeparture trainDeparture)
    {
        if (trainDeparture == null)
        {
            throw new NullPointerException("Train departure can not be null");
        }
        trainDepartures.forEach(trainDeparture1 ->
        {
            if (calculateTheTotalTimeToDeparture(trainDeparture1).equals(
                calculateTheTotalTimeToDeparture(trainDeparture)))
            {
                if (trainDeparture1.getTrainNumber() == trainDeparture.getTrainNumber())
                {
                    throw new IllegalStateException(
                        "Train departure with the given time and number already exists");
                } else if (trainDeparture1.getTrack() == trainDeparture.getTrack()
                    && trainDeparture.getTrack() != -1)
                {
                    throw new IllegalArgumentException(
                        "Train departure with the given time and track already exists");
                }
            }
        });
        trainDepartures.add(trainDeparture);
        laneMap.put(trainDeparture.getDestination().toLowerCase(),
            trainDeparture.getLane().toLowerCase());
        return true;
    }

    /**
     * Retrieves a list of train departures with a specific train number.
     *
     * @param trainNumber The train number to search for.
     * @return a list of {@link TrainDeparture} objects matching the train number.
     * @throws IllegalArgumentException if no train departures match the given number.
     */
    public List<TrainDeparture> getTrainDepartureWithNumber(int trainNumber)
    {
        if (Boolean.TRUE.equals(
            CheckValid.checkValidInt(trainNumber, "Train number should be positive")))
        {
            results = filterDeparturesByNumber(trainNumber);
            if (!results.isEmpty())
            {
                return results;
            }
        }
        throw new IllegalArgumentException("Provided train number does not exist");
    }

    /**
     * Retrieves a list of train departures to a specific destination.
     *
     * @param destination The destination to search for.
     * @return a list of {@link TrainDeparture} objects heading to the given destination.
     * @throws IllegalArgumentException if no train departures match the given destination.
     */
    public List<TrainDeparture> getTrainDepartureWithDestination(String destination)
    {
        if (Boolean.TRUE.equals(
            CheckValid.checkValidString(destination,
                "Destination needs to be a valid string")))
        {
            results = filterDeparturesByDestination(destination);
            if (!results.isEmpty())
            {
                return results;
            }
        }
        throw new IllegalArgumentException("Destination does not exist");
    }

    /**
     * Retrieves a list of train departures at a specific departure time.
     *
     * @param departureTime The departure time to search for.
     * @return a list of {@link TrainDeparture} objects with the given departure time.
     * @throws IllegalArgumentException if no train departures match the given departure time.
     */
    public List<TrainDeparture> getTrainDepartureWithDepartureTime(String departureTime)
    {
        if (Boolean.TRUE.equals(
            CheckValid.checkValidString(departureTime,
                "Departure time needs to be a string in format HH:MM")))
        {
            results = filterDeparturesByTime(departureTime);
            if (!results.isEmpty())
            {
                return results;
            }
        }
        throw new IllegalArgumentException("Departure time does not exist");
    }

    /**
     * Retrieves a list of train departures from a specific track.
     *
     * @param track The track number to search for.
     * @return a list of {@link TrainDeparture} objects departing from the given track.
     * @throws IllegalArgumentException if no train departures match the given track number.
     */
    public List<TrainDeparture> getTrainDepartureWithTrack(int track)
    {
        if (Boolean.TRUE.equals(Boolean.TRUE.equals(
            CheckValid.checkValidTrackNumber(track,
                "Track needs to be a positive number greater than 0"))))
        {
            results = filterDeparturesByTrack(track);
            if (!results.isEmpty())
            {
                return results;
            }
        }
        throw new IllegalArgumentException("Track number does not exist");
    }

    /**
     * Retrieves a list of train departures for a specific lane.
     *
     * @param line The lane to search for.
     * @return a list of {@link TrainDeparture} objects associated with the given lane.
     * @throws IllegalArgumentException if no train departures match the given lane.
     */
    public List<TrainDeparture> getTrainDepartureWithLane(String line)
    {
        if (Boolean.TRUE.equals(
            Boolean.TRUE.equals(CheckValid.checkValidString(line,
                "Lane needs to be a valid string"))))
        {
            results = filterDeparturesByLine(line);
            if (!results.isEmpty())
            {
                return results;
            }
        }
        throw new IllegalArgumentException("Lane does not exist");
    }

    /**
     * Retrieves a list of train departures that have a specific delay.
     *
     * @param delay The delay to search for.
     * @return a list of {@link TrainDeparture} objects with the specified delay.
     * @throws IllegalArgumentException if no train departures match the given delay.
     */
    public List<TrainDeparture> getTrainDepartureWithDelay(String delay)
    {
        if (Boolean.TRUE.equals(CheckValid.checkValidString(delay, "Delay needs to be a string")))
        {
            results = filterDeparturesByDelay(delay);
            if (!results.isEmpty())
            {
                return results;
            }
        }
        throw new IllegalArgumentException("Delay does not exist");
    }

    /**
     * Removes train departures up to a specified departure time.
     *
     * @param departureTime The cutoff departure time for removal.
     */
    public void removeTrainDepartureByTime(String departureTime)
    {
        if (Boolean.TRUE.equals(
            CheckValid.checkValidString(departureTime,
                "Departure time needs to be a valid string")))
        {
            trainDepartures.removeIf(trainDeparture ->
            {
                LocalTime departureTimeWithDelay = calculateTheTotalTimeToDeparture(trainDeparture);
                return departureTimeWithDelay.isBefore(LocalTime.parse(departureTime));
            });
        }
    }

    /**
     * Calculates the total time to departure for a given train departure.
     *
     * @param trainDeparture The train departure to calculate the time to departure for.
     * @return the total time to departure.
     */
    private LocalTime calculateTheTotalTimeToDeparture(TrainDeparture trainDeparture)
    {
        String[] delay = trainDeparture.getDelay().toString().split(":");
        int delayHours = Integer.parseInt(delay[0]);
        int delayMinutes = Integer.parseInt(delay[1]);
        return trainDeparture.getDepartureTime().plusHours(delayHours).plusMinutes(delayMinutes);
    }

    /**
     * Removes a specific train departure at a given index and time.
     *
     * @param departureTime The departure time to filter the departures.
     * @param index         The index of the departure to be removed from the filtered list.
     */
    public void removeTrainDeparturesWithTime(String departureTime, int index)
    {
        if (Boolean.TRUE.equals(
            CheckValid.checkValidString(departureTime, "Departure time needs to be a string")))
        {
            results = filterDeparturesByTime(departureTime);
            if (!results.isEmpty())
            {
                trainDepartures.remove(results.get(index));
            }

        }
    }

    /**
     * Removes a specific train departure based on train number and index.
     *
     * @param trainNumber The train number to filter the departures.
     * @param index       The index of the departure to be removed from the filtered list.
     * @return true if the removal was successful.
     */
    public boolean removeTrainDepartureByNumber(int trainNumber, int index)
    {
        if (Boolean.TRUE.equals(
            CheckValid.checkValidInt(trainNumber, "Train number needs to be a positive number")))
        {
            results = filterDeparturesByNumber(trainNumber);
            if (!results.isEmpty())
            {
                trainDepartures.remove(results.get(index));
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a specific train departure based on track and index.
     *
     * @param track The track to filter the departures
     * @param index The index of the departure to be removed from the filtered list
     * @return true if the removal was successful
     */
    public boolean removeTrainDeparturesByTrack(int track, int index)
    {
        if (Boolean.TRUE.equals(
            CheckValid.checkValidTrackNumber(track, "Track needs to be a positive number")))
        {
            results = filterDeparturesByTrack(track);
            if (!results.isEmpty())
            {
                trainDepartures.remove(results.get(index));
                return true;
            }
        }
        return false;

    }

    /**
     * Removes a specific train departure based on lane and index.
     *
     * @param line  The lane to filter the departures
     * @param index The index of the departure to be removed from the filtered list
     * @return true if the removal was successful
     */
    public boolean removeTrainDeparturesByLane(String line, int index)
    {
        if (Boolean.TRUE.equals(CheckValid.checkValidString(line, "Line needs to be a string")))
        {
            results = filterDeparturesByLine(line);
            if (!results.isEmpty())
            {
                trainDepartures.remove(results.get(index));
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a specific train departure based on the destination and its index in the
     * filtered list.
     *
     * @param destination The destination to filter the departures.
     * @param index       The index of the departure to be removed from the filtered list.
     * @return true if the removal was successful.
     */
    public boolean removeTrainDeparturesByDestination(String destination, int index)
    {
        if (Boolean.TRUE.equals(
            CheckValid.checkValidString(destination, "Destination needs to be a string")))
        {
            results = filterDeparturesByDestination(destination);
            if (!results.isEmpty())
            {
                trainDepartures.remove(results.get(index));
                return true;
            }

        }
        return false;
    }

    /**
     * Sorts and returns the list of all train departures based on their departure time.
     *
     * @return a sorted list of {@link TrainDeparture} objects.
     */
    public List<TrainDeparture> sortTrainDepartures()
    {
        return trainDepartures.stream()
            .sorted(Comparator.comparing(TrainDeparture::getDepartureTime))
            .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Filters and returns a list of train departures by a specific departure time.
     *
     * @param departureTime The departure time to filter the departures.
     * @return a list of {@link TrainDeparture} objects matching the specified departure time.
     * @throws IllegalArgumentException if no train departures match the given departure time.
     */
    public List<TrainDeparture> filterDeparturesByTime(String departureTime)
    {
        results.clear();
        if (Boolean.TRUE.equals(
            CheckValid.checkValidString(departureTime, "Departure time needs to be a string")))
        {
            results = trainDepartures.stream().filter(
                    trainDeparture -> trainDeparture.getDepartureTime()
                        .equals(LocalTime.parse(departureTime)))
                .collect(Collectors.toCollection(ArrayList::new));
            if (!results.isEmpty())
            {
                return results;
            }
        }
        throw new IllegalArgumentException("Departure time does not exist");
    }

    /**
     * Filters and returns a list of train departures by a specific track number.
     *
     * @param track The track number to filter the departures.
     * @return a list of {@link TrainDeparture} objects matching the specified track number.
     * @throws IllegalArgumentException if no train departures match the given track number.
     */
    public List<TrainDeparture> filterDeparturesByTrack(int track)
    {
        results.clear();

        if (Boolean.TRUE.equals(
            CheckValid.checkValidTrackNumber(track, "Track needs to be a positive number")))
        {
            results = trainDepartures.stream()
                .filter(trainDeparture -> trainDeparture.getTrack() == track)
                .collect(Collectors.toCollection(ArrayList::new));
            if (!results.isEmpty())
            {
                return results;
            }
        }
        throw new IllegalArgumentException("Track number does not exist");
    }

    /**
     * Filters and returns a list of train departures by a specific lane.
     *
     * @param line The lane to filter the departures.
     * @return a list of {@link TrainDeparture} objects matching the specified lane.
     * @throws IllegalArgumentException if no train departures match the given lane.
     */
    public List<TrainDeparture> filterDeparturesByLine(String line)
    {
        results.clear();
        if (Boolean.TRUE.equals(CheckValid.checkValidString(line, "Line needs to be a string")))
        {
            results = trainDepartures.stream()
                .filter(trainDeparture -> trainDeparture.getLane().equals(line))
                .collect(Collectors.toCollection(ArrayList::new));
            if (!results.isEmpty())
            {
                return results;
            }
        }
        throw new IllegalArgumentException("Lane does not exist");
    }

    /**
     * Filters and returns a list of train departures by a specific destination.
     *
     * @param destination The destination to filter the departures.
     * @return a list of {@link TrainDeparture} objects matching the specified destination.
     * @throws IllegalArgumentException if no train departures match the given destination.
     */
    public List<TrainDeparture> filterDeparturesByDestination(String destination)
    {
        results.clear();
        if (Boolean.TRUE.equals(
            CheckValid.checkValidString(destination, "Destination needs to be a string")))
        {
            results = trainDepartures.stream()
                .filter(trainDeparture -> trainDeparture.getDestination().equals(destination))
                .collect(Collectors.toCollection(ArrayList::new));
            if (!results.isEmpty())
            {
                return results;
            }
        }
        throw new IllegalArgumentException("Destination does not exist");
    }

    /**
     * Filters and returns a list of train departures by a specific delay.
     *
     * @param delay The delay to filter the departures.
     * @return a list of {@link TrainDeparture} objects matching the specified delay.
     * @throws IllegalArgumentException if no train departures match the given delay.
     */
    public List<TrainDeparture> filterDeparturesByDelay(String delay)
    {
        results.clear();
        if (Boolean.TRUE.equals(CheckValid.checkValidString(delay, "Delay needs to be a string")))
        {
            results = trainDepartures.stream()
                .filter(trainDeparture -> trainDeparture.getDelay().toString().equals(delay))
                .collect(Collectors.toCollection(ArrayList::new));
            if (!results.isEmpty())
            {
                return results;
            }
        }
        throw new IllegalArgumentException("Delay does not exist");
    }

    /**
     * Filters and returns a list of train departures by a specific train number.
     *
     * @param trainNumber The train number to filter the departures.
     * @return a list of {@link TrainDeparture} objects matching the specified train number.
     * @throws IllegalArgumentException if no train departures match the given train number.
     */
    public List<TrainDeparture> filterDeparturesByNumber(int trainNumber)
    {
        if (Boolean.TRUE.equals(
            CheckValid.checkValidInt(trainNumber, "Train number should be positive")))
        {
            results = trainDepartures.stream()
                .filter(trainDeparture -> trainDeparture.getTrainNumber() == trainNumber)
                .collect(Collectors.toCollection(ArrayList::new));
            if (!results.isEmpty())
            {
                return results;
            }
        }
        throw new IllegalArgumentException("Train number does not exist");
    }

    /**
     * Retrieves a specific train departure based on train number and its index in the
     * filtered list.
     *
     * @param trainNumber The train number to filter the departures.
     * @param index       The index in the filtered list of departures.
     * @return the {@link TrainDeparture} object at the specified index.
     * @throws IllegalArgumentException if the index is out of bounds or the train number
     *      does not exist.
     */
    public TrainDeparture getTrainDeparture(int trainNumber, int index)
    {
        if (Boolean.TRUE.equals(CheckValid.checkValidInt(index, "Index should be positive")))
        {
            results = filterDeparturesByNumber(trainNumber);
            return results.get(index);
        }
        throw new IllegalArgumentException("Train number does not exist");
    }
}

