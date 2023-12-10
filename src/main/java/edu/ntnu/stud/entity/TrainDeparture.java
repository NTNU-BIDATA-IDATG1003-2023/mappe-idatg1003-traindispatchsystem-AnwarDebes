package edu.ntnu.stud.entity;

import edu.ntnu.stud.utility.CheckValid;
import java.time.LocalTime;

/**
 * The TrainDeparture class represents the departure details of a train.
 * It includes information such as the departure time, line, train number, destination,
 * track, and delay.
 *
 * @author Anwar Debes
 * @version 27.10.2023
 * @see CheckValid
 */
public class TrainDeparture
{

    // The departure time of the train
    private LocalTime departureTime;
    // The line on which the train operates
    private String lane;
    // The number assigned to the train
    private int trainNumber;
    // The destination of the train
    private String destination;
    // The track from which the train departs
    private int track;
    // The delay of the train
    private LocalTime delay;

    /**
     * Constructs a new TrainDeparture instance with the specified details.
     *
     * @param departureTime The time of departure in the format hh:mm.
     * @param lane          The line on which the train operates.
     * @param trainNumber   The number assigned to the train.
     * @param destination   The destination of the train.
     * @param track         The track from which the train departs.
     * @param delay         The delay of the train in the format hh:mm.
     */
    public TrainDeparture(LocalTime departureTime, String lane, int trainNumber, String destination,
                          int track, LocalTime delay)
    {
        setDepartureTime(departureTime);
        setLane(lane);
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
    public LocalTime getDepartureTime()
    {

        return departureTime;
    }

    /**
     * Sets the departure time of the train after validating the input.
     *
     * @param departureTime The departure time in the format hh:mm.
     */
    public void setDepartureTime(LocalTime departureTime)
    {

        if (CheckValid.checkValidString(String.valueOf(departureTime),
            "Time needs to be a string") && CheckValid.checkValidTime(String.valueOf(departureTime),
            "Times needs to be in the form of hh:mm"))
        {
            this.departureTime = departureTime;
        }
    }

    /**
     * Returns the line on which the train operates.
     *
     * @return The line of the train.
     */
    public String getLane()
    {

        return lane;
    }

    /**
     * Sets the line of the train after validating the input.
     *
     * @param lane The line on which the train operates. It must be a valid string.
     */
    public void setLane(String lane)
    {

        if (Boolean.TRUE.equals(CheckValid.checkValidString(lane, "Line needs to be a string")))
        {
            this.lane = lane;
        }
    }

    /**
     * Returns the number assigned to the train.
     *
     * @return The train number.
     */
    public int getTrainNumber()
    {

        return trainNumber;
    }

    /**
     * Sets the train number after validating the input.
     *
     * @param trainNumber The number assigned to the train. It needs to be a positive number.
     */
    public void setTrainNumber(int trainNumber)
    {

        if (Boolean.TRUE.equals(CheckValid.checkValidInt(trainNumber,
            "TrainDeparture number needs to be a positive number")))
        {
            this.trainNumber = trainNumber;
        }
    }

    /**
     * Returns the destination of the train.
     *
     * @return The destination of the train.
     */
    public String getDestination()
    {

        return destination;
    }

    /**
     * Sets the destination of the train after validating the input.
     *
     * @param destination The destination of the train. It must be a valid string.
     */
    public void setDestination(String destination)
    {

        if (Boolean.TRUE.equals(Boolean.TRUE.equals(
            CheckValid.checkValidString(destination, "Destination needs to be a string"))))
        {
            this.destination = destination;
        }
    }

    /**
     * Returns the track from which the train departs.
     *
     * @return The track number.
     */
    public int getTrack()
    {

        return track;
    }

    /**
     * Sets the track of the train after validating the input.
     *
     * @param track The track from which the train departs. It needs to be a positive number.
     */
    public void setTrack(int track)
    {

        if (Boolean.TRUE.equals(
            CheckValid.checkValidTrackNumber(track, "Track needs to be a positive number")))
        {
            this.track = track;
        }
    }

    /**
     * Returns the delay of the train.
     *
     * @return The delay in the format hh:mm.
     */
    public LocalTime getDelay()
    {

        return delay;
    }

    /**
     * Sets the delay of the train after validating the input.
     *
     * @param delay The delay of the train. It must be a valid string in the format hh:mm.
     */
    public void setDelay(LocalTime delay)
    {

        if (CheckValid.checkValidString(String.valueOf(delay),
            "delay needs to be a valid string") && CheckValid.checkValidTime(String.valueOf(delay),
            "Times needs to be in the form of hh:mm"))
        {
            this.delay = delay;
        }
    }

    /**
     * Returns a string representation of the TrainDeparture instance, displaying all the details.
     *
     * @return A string representation of the train departure details.
     */
    public String display()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("| ").append(departureTime)
            .append(" ".repeat(15 - String.valueOf(departureTime).length())).append("|");
        sb.append(" ").append(lane).append(" ".repeat(21 - lane.length())).append("|");
        sb.append(" ").append(trainNumber)
            .append(" ".repeat(13 - String.valueOf(trainNumber).length())).append("|");
        sb.append(" ").append(destination).append(" ".repeat(21 - destination.length()))
            .append("|");
        if (CheckValid.checkIfThereIsDelay(delay))
        {
            sb.append(" ").append(delay).append(" ".repeat(6 - String.valueOf(delay).length()))
                .append("|");
        } else
        {
            sb.append(" ").append(" ".repeat(6)).append("|");
        }
        if (CheckValid.checkIfThereIsTrack(track))
        {
            sb.append(" ").append(track).append(" ".repeat(9 - String.valueOf(track).length()))
                .append("|");
        } else
        {
            sb.append(" ").append(" ".repeat(9)).append("|");
        }
        sb.append("\n").append("|").append("-".repeat(96)).append("|");
        return sb.toString();
    }
}
