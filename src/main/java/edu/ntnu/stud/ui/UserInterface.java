package edu.ntnu.stud.ui;

import com.github.lalyos.jfiglet.FigletFont;
import edu.ntnu.stud.commandhandler.Command;
import edu.ntnu.stud.commandhandler.EditCommands;
import edu.ntnu.stud.commandhandler.MainMenuCommands;
import edu.ntnu.stud.commandhandler.Parser;
import edu.ntnu.stud.commandhandler.RemoveCommands;
import edu.ntnu.stud.commandhandler.SearchCommands;
import edu.ntnu.stud.entity.TrainDeparture;
import edu.ntnu.stud.register.Register;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class UserInterface
{

    boolean hasTrack;
    boolean hasDelay;
    private Register register;
    private Scanner reader;
    private Parser parser;
    private String clock;
    private String  stationName;
    private BoardsAndMenusHandler boardsAndMenusHandler;
    private UserInputHandler inputHandler;


    public UserInterface()
    {


    }

    public void init()
    {

        clock = "00:00";
        register = new Register();
        reader = new Scanner(System.in);
        boardsAndMenusHandler = new BoardsAndMenusHandler();
        inputHandler = new UserInputHandler(reader);
        parser = new Parser(inputHandler);
        // remove this when done
        TrainDeparture t1 = new TrainDeparture(LocalTime.parse("12:10"), "L1", 1, "Oslo", 1,
            LocalTime.parse("00:00"));
        TrainDeparture t2 = new TrainDeparture(LocalTime.parse("12:20"), "L2", 2, "Stavanger", 2,
            LocalTime.parse("00:00"));
        TrainDeparture t3 = new TrainDeparture(LocalTime.parse("12:30"), "L4", 3, "Trondheim", 3,
            LocalTime.parse("00:00"));
        register.addTrainDeparture(t1);
        register.addTrainDeparture(t2);
        register.addTrainDeparture(t3);

    }


    public void start()
    {
        stationName = inputHandler.getStringInput("Please enter the station name: ",
            "Station name cant be empty or blank, please try again :");
        getTrainStationName(true);
        boardsAndMenusHandler.printWelcomeMessage();
        mainMenu();

    }

    private void getTrainStationName(boolean isWelcomeMessage)
    {
        try
        {
            if (isWelcomeMessage)
            {
                System.out.println(
                    FigletFont.convertOneLine("Welcome to " + stationName + " station"));
            } else
            {
                System.out.print(
                    "\t\t\t\t\t\t\t" + FigletFont.convertOneLine(stationName + " station"));
            }
        } catch (IOException e)
        {
            System.err.println("Name of the station could not be printed, try again");
        }
    }


    /**
     * closes the application if the user wants otherwise it keeps the application running.
     */
    private void mainMenu()
    {

        boardsAndMenusHandler.printMenu();
        boolean finished = false;
        while (!finished)
        {
            Command command = parser.getCommand();
            finished = processMainMenuCommand(command);
        }
        reader.close();
    }

    /**
     * Uses other methods to provide functions that can be used of the user.
     *
     * @param command the command Integer to perform to match with the functions
     * @return if the user wants to quite the application
     */
    private boolean processMainMenuCommand(Command command)
    {

        boolean wantToQuit = false;

        MainMenuCommands commandInput = command.getCommandInput();

        switch (commandInput)
        {
            case UNKNOWN -> System.err.println("No command here, please try a higher number!");
            case ADD -> addTrainDeparture();
            case SEARCH -> searchMenu();
            case SHOW_ALL -> printAllTrainDepartures();
            case EDIT -> editMenu();
            case REMOVE -> removeMenu();
            case TIME -> handelTime();
            case HELP -> boardsAndMenusHandler.printMenu();
            case QUIT -> wantToQuit = true;
            default ->
            {
                System.err.println("Please enter a valid number from menu");
                mainMenu();
            }
        }
        return wantToQuit;
    }

    private void handelTime()
    {

        String wantsToChangeTime =
            inputHandler.getStringInput("Do you want to change the time? (y/n):",
                "Please enter y or n");
        if (wantsToChangeTime.equals("y"))
        {
            clock =
                inputHandler.getClockInput("Enter new time: ", "Time needs to be in format hh:mm",
                    clock);
            register.removeTrainDepartures(clock);
            boardsAndMenusHandler.printClock(clock);
            boardsAndMenusHandler.printMenu();
        } else
        {
            boardsAndMenusHandler.printClock(clock);
            boardsAndMenusHandler.printMenu();
        }
    }


    public void removeMenu()
    {
        boardsAndMenusHandler.printRemoveMenu();
        boolean finished = false;
        while (!finished)
        {
            Command command = parser.getCommand();
            finished = processRemoveMenuCommand(command);
        }
        mainMenu();
    }

    private boolean processRemoveMenuCommand(Command command)
    {

        boolean goBack = false;
        RemoveCommands commandInput = command.getRemoveCommandInput();
        switch (commandInput)
        {
            case UNKNOWN ->
                System.out.println("There is no command here, please try a higher number!");
            case TRAIN_NUMBER -> removeTrainDepartureWithNumber();
            case DESTINATION -> removeTrainDeparturesWithDestination();
            case DEPARTURE_TIME -> removeTrainDeparturesWithTime();
            case TRACK -> removeTrainDeparturesWithTrack();
            case LANE -> removeTrainDeparturesWithLane();
            case BACK -> goBack = true;
            case EXIT -> System.exit(0);
            default ->
            {
                System.err.println("Please enter a valid number from menu");
                removeMenu();
            }

        }
        return goBack;
    }


    public void editMenu()
    {
        int trainNumber = inputHandler.getIntInput("Enter train number: ",
            "Train number needs to be a positive number");
        List<TrainDeparture> trainDepartures = register.getTrainDepartureWithNumber(trainNumber);

        TrainDeparture trainDeparture =
            register.getTrainDeparture(trainNumber, getChoiceFromResults(trainDepartures) - 1);
        boardsAndMenusHandler.printEditMenu();
        boolean finished = false;
        while (!finished)
        {
            Command command = parser.getCommand();
            finished = processEditMenuCommand(command, trainDeparture);
        }
        mainMenu();
    }

    private boolean processEditMenuCommand(Command command, TrainDeparture trainDeparture)
    {

        boolean goBack = false;
        EditCommands commandInput = command.getEditCommandInput();
        switch (commandInput)
        {
            case UNKNOWN ->
                System.out.println("There is no command here, please try a higher number!");
            case DESTINATION -> editDestination(trainDeparture);
            case DEPARTURE_TIME -> editDepartureTime(trainDeparture);
            case TRACK -> editTrack(trainDeparture);
            case LANE -> editLane(trainDeparture);
            case DELAY -> editDelay(trainDeparture);
            case TRAIN_NUMBER -> editTrainNumber(trainDeparture);
            case BACK -> goBack = true;
            case EXIT -> System.exit(0);
            default ->
            {
                System.err.println("Please enter a valid number from menu");
                editMenu();
            }
        }
        return goBack;
    }

    private void editDelay(TrainDeparture trainDeparture)
    {
        String newDelay =
            inputHandler.getTimeInput("Enter new delay: ", "Delay needs to be in format hh:mm");
        register.getTrainDepartures().remove(trainDeparture);
        trainDeparture.setDelay(LocalTime.parse(newDelay));
        try
        {
            register.addTrainDeparture(trainDeparture);
            System.out.println("Delay changed successfully ");
        } catch (IllegalArgumentException | IllegalStateException | NullPointerException f)
        {
            System.err.println(
                "Editing the delay was not successful, because the changes you made "
                    + "resulted in a train departure that was not valid");
            register.addTrainDeparture(trainDeparture);
        }
        boardsAndMenusHandler.printEditMenu();
    }

    private void editLane(TrainDeparture trainDeparture)
    {

        String newLine =
            inputHandler.getStringInput("Enter new line: ", "Line cant be empty or blank");
        register.getTrainDepartures().remove(trainDeparture);
        trainDeparture.setLine(newLine);
        try
        {
            register.addTrainDeparture(trainDeparture);
            System.out.println("Line changed successfully ");
        } catch (IllegalArgumentException | IllegalStateException | NullPointerException f)
        {
            System.err.println(
                "Editing the Lane was not successful, because the changes you made " +
                    "resulted in a train departure that was not valid");
            register.addTrainDeparture(trainDeparture);
        }
        boardsAndMenusHandler.printEditMenu();
    }

    private void editTrack(TrainDeparture trainDeparture)
    {

        int newTrack =
            inputHandler.getTrackInput("Enter new track: ", "Track needs to be a positive number");
        register.getTrainDepartures().remove(trainDeparture);
        trainDeparture.setTrack(newTrack);
        try
        {
            register.addTrainDeparture(trainDeparture);
            System.out.println("Track changed successfully ");
        } catch (IllegalArgumentException | IllegalStateException | NullPointerException f)
        {
            System.err.println(
                "Editing the track was not successful, because the changes you made " +
                    "resulted in a train departure that was not valid");
            register.addTrainDeparture(trainDeparture);
        }
        boardsAndMenusHandler.printEditMenu();
    }

    private void editDepartureTime(TrainDeparture trainDeparture)
    {
        String newDepartureTime = inputHandler.getTimeInput("Enter new departure time: ",
            "Time needs to be in format hh:mm");
        register.getTrainDepartures().remove(trainDeparture);
        trainDeparture.setDepartureTime(LocalTime.parse(newDepartureTime));
        try
        {
            register.addTrainDeparture(trainDeparture);
            System.out.println("Departure time changed successfully ");
        } catch (IllegalArgumentException | IllegalStateException | NullPointerException f)
        {
            System.err.println(
                "Editing the departure time was not successful, because the changes you made " +
                    "resulted in a train departure that was not valid");
            register.addTrainDeparture(trainDeparture);
        }
        boardsAndMenusHandler.printEditMenu();
    }

    private void editDestination(TrainDeparture trainDeparture)
    {
        String newDestination = inputHandler.getStringInput("Enter new destination: ",
            "Destination needs to be a valid name");
        register.getTrainDepartures().remove(trainDeparture);
        trainDeparture.setDestination(newDestination);
        try
        {
            register.addTrainDeparture(trainDeparture);
            System.out.println("Destination changed successfully ");
        } catch (IllegalArgumentException | IllegalStateException | NullPointerException f)
        {
            System.err.println(
                "Editing the destination was not successful, because the changes you made " +
                    "resulted in a train departure that was not valid");
            register.addTrainDeparture(trainDeparture);
        }
        boardsAndMenusHandler.printEditMenu();
    }

    private void editTrainNumber(TrainDeparture trainDeparture)
    {
        int newTrainNumber = inputHandler.getIntInput("Enter new train number: ",
            "Train number needs to be a positive number");
        register.getTrainDepartures().remove(trainDeparture);
        trainDeparture.setTrainNumber(newTrainNumber);
        try
        {
            register.addTrainDeparture(trainDeparture);
            System.out.println("Train number changed successfully ");
        } catch (IllegalArgumentException | IllegalStateException | NullPointerException f)
        {
            System.err.println(
                "Editing the train number was not successful, because the changes you made " +
                    "resulted in a train departure that was not valid");
            register.addTrainDeparture(trainDeparture);
        }
        boardsAndMenusHandler.printEditMenu();
    }

    public void searchMenu()
    {

        boardsAndMenusHandler.printSearchMenu();
        boolean finished = false;
        while (!finished)
        {
            Command command = parser.getCommand();
            finished = processSearchMenuCommand(command);
        }
        mainMenu();
    }

    private boolean processSearchMenuCommand(Command command)
    {

        boolean goBack = false;
        SearchCommands commandInput = command.getSearchCommandInput();
        switch (commandInput)
        {
            case UNKNOWN -> System.out.println();
            case NUMBER -> getTrainDepartureWithNumber();
            case DEPARTURE -> getTrainDepartureWithDestination();
            case DEPARTURE_TIME -> getTrainDepartureWithDepartureTime();
            case TRACK -> getTrainDepartureWithTrack();
            case LANE -> getTrainDepartureWithLine();
            case DELAY -> getTrainDepartureWithDelay();
            case HELP -> boardsAndMenusHandler.printSearchMenu();
            case BACK -> goBack = true;
            case QUIT -> System.exit(0);
            default ->
            {
                System.err.println("Please enter a valid number from menu");
                searchMenu();
            }
        }
        return goBack;
    }

    private boolean checkIfTrainDepartureListIsEmpty()
    {
        try
        {
            register.getTrainDepartures();
            return true;
        } catch (IllegalArgumentException e)
        {
            System.err.println("List is empty, try adding some departures first");
        }
        return false;
    }

    private void getTrainDepartureWithDepartureTime()
    {

        String departureTime =
            inputHandler.getTimeInput("Enter departure time: ", "Time needs to be in format hh:mm");
        if (checkIfTrainDepartureListIsEmpty())
        {
            try
            {
                getTrainStationName(false);
                boardsAndMenusHandler.printClock(clock);
                boardsAndMenusHandler.printTrainDepartureBoardHeader();
                register.getTrainDepartureWithDepartureTime(departureTime)
                    .forEach(trainDeparture -> System.out.println(trainDeparture.display()));
            } catch (IllegalArgumentException e)
            {
                System.err.println("Train departure(s) was not found");
            }
        }
        boardsAndMenusHandler.printSearchMenu();
    }

    private void getTrainDepartureWithTrack()
    {

        int track =
            inputHandler.getTrackInput("Enter track: ", "Track needs to be a positive number");
        if (checkIfTrainDepartureListIsEmpty())
        {
            try
            {
                getTrainStationName(false);
                boardsAndMenusHandler.printClock(clock);
                boardsAndMenusHandler.printTrainDepartureBoardHeader();
                register.getTrainDepartureWithTrack(track)
                    .forEach(trainDeparture -> System.out.println(trainDeparture.display()));
            } catch (IllegalArgumentException e)
            {
                System.err.println("Train departure(s) was not found");
            }
        }
        boardsAndMenusHandler.printSearchMenu();
    }

    private void getTrainDepartureWithLine()
    {

        String line = inputHandler.getStringInput("Enter line: ", "Line cant be empty or blank");
        if (checkIfTrainDepartureListIsEmpty())
        {
            try
            {
                getTrainStationName(false);
                boardsAndMenusHandler.printClock(clock);
                boardsAndMenusHandler.printTrainDepartureBoardHeader();
                register.getTrainDepartureWithLane(line)
                    .forEach(trainDeparture -> System.out.println(trainDeparture.display()));
            } catch (IllegalArgumentException e)
            {
                System.err.println("Train departure(s) was not found");
            }
        }
        boardsAndMenusHandler.printSearchMenu();
    }

    private void getTrainDepartureWithDelay()
    {

        String delay =
            inputHandler.getTimeInput("Enter delay: ", "Delay needs to be in format hh:mm");
        if (checkIfTrainDepartureListIsEmpty())
        {
            try
            {
                getTrainStationName(false);
                boardsAndMenusHandler.printClock(clock);
                boardsAndMenusHandler.printTrainDepartureBoardHeader();
                register.getTrainDepartureWithDelay(delay)
                    .forEach(trainDeparture -> System.out.println(trainDeparture.display()));
            } catch (IllegalArgumentException e)
            {
                System.err.println("Train departure(s) was not found");
            }
        }
        boardsAndMenusHandler.printSearchMenu();
    }

    private void getTrainDepartureWithNumber()
    {

        int trainNumber = inputHandler.getIntInput("Enter train number: ",
            "Train number needs to be a positive number");
        if (checkIfTrainDepartureListIsEmpty())
        {
            try
            {
                getTrainStationName(false);
                boardsAndMenusHandler.printClock(clock);
                boardsAndMenusHandler.printTrainDepartureBoardHeader();
                register.getTrainDepartureWithNumber(trainNumber)
                    .forEach(trainDeparture -> System.out.println(trainDeparture.display()));
            } catch (IllegalArgumentException e)
            {
                System.err.println("Train departure(s) was not found");
            }
        }
        boardsAndMenusHandler.printSearchMenu();
    }

    private void handelTrackAndDelayResponse()
    {
        String hasTrackResponse =
            inputHandler.checkResponseInput("y", "n", "Does the train have a track? (y/n): ",
                "Please enter y or n");
        if (hasTrackResponse.equals("y"))
        {
            hasTrack = true;
        }
        String hasDelayResponse =
            inputHandler.checkResponseInput("y", "n", "Does the train have a delay? (y/n): ",
                "Please enter y or n");
        if (hasDelayResponse.equals("y"))
        {
            hasDelay = true;
        }
    }

    private void addTrainDeparture()
    {
        hasTrack = false;
        hasDelay = false;
        handelTrackAndDelayResponse();
        String destination =
            inputHandler.checkStringInput(stationName, stationName, "Enter destination: ",
                "Destination needs to be a valid name and needs to be " +
                    "equal or less then 20 characters");
        String departureTime =
            inputHandler.getTimeInput("Enter departure time: ", "Time needs to be in format hh:mm");
        int trainNumber = inputHandler.getIntInput("Enter train number: ",
            "Train number needs to be a positive number" + " and not already exist");
        String lane = handleLaneInput(destination);
        int track = handelTrackInput(hasTrack);
        String delay = handelDelayInput(hasDelay);
        TrainDeparture trainDeparture =
            new TrainDeparture(LocalTime.parse(departureTime), lane, trainNumber, destination,
                track, LocalTime.parse(delay));
        try
        {
            register.addTrainDeparture(trainDeparture);
            System.out.println("Train departure added");
            getTrainStationName(false);
            boardsAndMenusHandler.printClock(clock);
            boardsAndMenusHandler.printTrainDepartureBoardHeader();
            System.out.println(trainDeparture.display());
        } catch (IllegalArgumentException e)
        {
            System.err.println("Train departure was not added because a train " +
                "departure with the given time and track already exists");
        } catch (IllegalStateException e)
        {
            System.err.println("Train departure was not added because a train " +
                "departure with the given time and train number already exists");
        } catch (NullPointerException e)
        {
            System.err.println("Train departure was not added because it was not valid");
        }
        boardsAndMenusHandler.printMenu();
    }

    private String handelDelayInput(boolean hasDelay)
    {
        String delay = "00:00";
        if (hasDelay)
        {
            delay = inputHandler.getTimeInput("Enter delay: ", "Delay needs to be in format hh:mm");
        }
        return delay;
    }

    private int handelTrackInput(boolean hasTrack)
    {
        int track = -1;
        if (hasTrack)
        {
            track =
                inputHandler.getIntInput("Enter track: ", "Track needs to be a positive number");
        }
        return track;
    }

    private String handleLaneInput(String destination)
    {
        String lane;
        try
        {
            Set<String> departuresWithLanes = register.getLaneList();
            if (departuresWithLanes.contains(destination.toLowerCase()))
            {
                lane = register.getLaneMap().get(destination.toLowerCase()).toUpperCase();
            } else
            {
                lane = inputHandler.getStringInput("Enter lane: ",
                    "Line cant be empty or blank and needs to be equal " +
                        "or less then 20 characters");
            }
        } catch (NullPointerException e)
        {
            lane = inputHandler.getStringInput("Enter lane: ",
                "Line cant be empty or blank and needs to be equal " +
                    "or less then 20 characters");
        }
        return lane;
    }

    private void getTrainDepartureWithDestination()
    {

        String destination = inputHandler.getStringInput("Enter destination: ",
            "Destination needs to be a valid name");
        if (checkIfTrainDepartureListIsEmpty())
        {
            try
            {
                getTrainStationName(false);
                boardsAndMenusHandler.printClock(clock);
                boardsAndMenusHandler.printTrainDepartureBoardHeader();
                register.getTrainDepartureWithDestination(destination)
                    .forEach(trainDeparture -> System.out.println(trainDeparture.display()));
            } catch (IllegalArgumentException e)
            {
                System.err.println("Train departure(s) was not found");
            }
        }
        boardsAndMenusHandler.printSearchMenu();
    }


    private int getChoiceFromResults(List<TrainDeparture> trainDepartures)
    {
        System.out.println("Please choose which train departure you want to work on: ");
        boardsAndMenusHandler.printTrainDepartureBoardHeader();
        int i;
        StringBuilder result = new StringBuilder();
        for (i = 0; i < trainDepartures.size(); i++)
        {
            result.delete(0, result.length());
            result.append(trainDepartures.get(i).display());
            result.append("\n \t\t\t\t\t\tTo choose the train departure above enter: ")
                .append(i + 1);
            result.append("\n" + "|").append("-".repeat(96)).append("|");
            System.out.println(result);
        }
        return inputHandler.getIntInput("Enter number: ", "Please enter a valid number");
    }

    private void removeTrainDeparturesWithTime()
    {

        String departureTime = inputHandler.getStringInput("Enter departure time: ",
            "Time needs to be in format hh:mm");
        if (checkIfTrainDepartureListIsEmpty())
        {
            List<TrainDeparture> trainDepartures =
                register.getTrainDepartureWithDepartureTime(departureTime);
            try
            {
                register.removeTrainDeparturesWithTime(departureTime,
                    getChoiceFromResults(trainDepartures) - 1);
                System.out.println("Train departures removed");
            } catch (IllegalArgumentException e)
            {
                System.err.println("Train departures was not removed because time was not valid");
            }
        }
        boardsAndMenusHandler.printRemoveMenu();
    }

    private void removeTrainDeparturesWithTrack()
    {
        int track =
            inputHandler.getTrackInput("Enter track: ", "Track needs to be a positive number");
        if (checkIfTrainDepartureListIsEmpty())
        {
            List<TrainDeparture> trainDepartures = register.getTrainDepartureWithTrack(track);
            try
            {
                register.removeTrainDeparturesByTrack(track,
                    getChoiceFromResults(trainDepartures) - 1);
                System.out.println("Train departures removed");
            } catch (IllegalArgumentException e)
            {
                System.err.println("Train departures was not removed because track was not valid");
            }
        }
        boardsAndMenusHandler.printRemoveMenu();
    }

    private void removeTrainDeparturesWithLane()
    {
        String lane = inputHandler.getStringInput("Enter lane: ", "Line cant be empty or blank");
        if (checkIfTrainDepartureListIsEmpty())
        {
            List<TrainDeparture> trainDepartures = register.getTrainDepartureWithLane(lane);
            try
            {
                register.removeTrainDeparturesByLane(lane,
                    getChoiceFromResults(trainDepartures) - 1);
                System.out.println("Train departures removed");
            } catch (IllegalArgumentException e)
            {
                System.err.println("Train departures was not removed because lane was not valid");
            }
        }
        boardsAndMenusHandler.printRemoveMenu();
    }

    private void removeTrainDeparturesWithDestination()
    {
        String destination = inputHandler.getStringInput("Enter destination: ",
            "Destination needs to be a valid name");
        if (checkIfTrainDepartureListIsEmpty())
        {
            List<TrainDeparture> trainDepartures =
                register.getTrainDepartureWithDestination(destination);
            try
            {
                register.removeTrainDeparturesByDestination(destination,
                    getChoiceFromResults(trainDepartures) - 1);
                System.out.println("Train departures removed");
            } catch (IllegalArgumentException e)
            {
                System.err.println(
                    "Train departures was not removed because destination was not valid");
            }
        }
        boardsAndMenusHandler.printRemoveMenu();
    }

    private void removeTrainDepartureWithNumber()
    {
        int trainNumber = inputHandler.getIntInput("Enter train number: ",
            "Train number needs to be a positive number");
        if (checkIfTrainDepartureListIsEmpty())
        {
            List<TrainDeparture> trainDepartures =
                register.getTrainDepartureWithNumber(trainNumber);
            try
            {
                register.removeTrainDepartures(trainNumber,
                    getChoiceFromResults(trainDepartures) - 1);
                System.out.println("Train departures removed");
            } catch (IllegalArgumentException e)
            {
                System.err.println(
                    "Train departures was not removed because train number was not valid");
            }
        }
        boardsAndMenusHandler.printRemoveMenu();
    }


    private void printAllTrainDepartures()
    {
        if (checkIfTrainDepartureListIsEmpty())
        {
            getTrainStationName(false);
            boardsAndMenusHandler.printClock(clock);
            boardsAndMenusHandler.printTrainDepartureBoardHeader();
            register.sortTrainDepartures()
                .forEach(trainDeparture -> System.out.println(trainDeparture.display()));
        }
        boardsAndMenusHandler.printMenu();
    }


}