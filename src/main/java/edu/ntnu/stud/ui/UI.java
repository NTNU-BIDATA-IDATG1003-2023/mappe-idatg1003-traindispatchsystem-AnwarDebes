package edu.ntnu.stud.ui;

import com.github.lalyos.jfiglet.FigletFont;
import edu.ntnu.stud.commandhandler.*;
import edu.ntnu.stud.entity.TrainDeparture;
import edu.ntnu.stud.register.Register;

import java.io.IOException;
import java.util.Scanner;

public class UI {

    private Register register;

    private Scanner reader;


    private Parser parser;

    private String clock, stationName;

    private BoardsAndMenusHandler boardsAndMenusHandler;

    private UserInputHandler inputHandler;


    public UI() {


    }

    public void init() {

        clock = "00:00";
        register = new Register();
        reader = new Scanner(System.in);
        boardsAndMenusHandler = new BoardsAndMenusHandler();
        inputHandler = new UserInputHandler(reader);
        parser = new Parser(inputHandler);
        // remove this when done
        TrainDeparture t1 = new TrainDeparture("12:10", "L1", 1, "Oslo", 1, "00:00");
        TrainDeparture t2 = new TrainDeparture("12:20", "L2", 2, "Stavanger", 2, "00:00");
        TrainDeparture t3 = new TrainDeparture("12:30", "L4", 3, "Trondheim", 3, "00:00");
        register.addTrainDeparture(t1);
        register.addTrainDeparture(t2);
        register.addTrainDeparture(t3);

    }


    public void start() {
        stationName = inputHandler.getStringInput("Please enter the station name: ",
                "Station name cant be empty or blank, please try again :");
        getTrainStationName(true);
        boardsAndMenusHandler.printWelcomeMessage();
        mainMenu();

    }

    private void getTrainStationName(boolean isWelcomeMessage) {
        try {
            if (isWelcomeMessage) {
                System.out.println(FigletFont.convertOneLine("Welcome to " + stationName + " station"));
            } else {
                System.out.print("\t\t\t\t\t\t\t" + FigletFont.convertOneLine(stationName + " station"));
            }
        } catch (IOException e) {
            System.err.println("Name of the station could not be printed, try again");
        }
    }


    /**
     * closes the application if the user wants otherwise it keeps the application running.
     */
    private void mainMenu() {

        boardsAndMenusHandler.printMenu();
        boolean finished = false;
        while (!finished) {
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
    private boolean processMainMenuCommand(Command command) {

        boolean wantToQuit = false;

        MainMenuCommands commandInput = command.getCommandInput();

        switch (commandInput) {
            case UNKNOWN -> System.err.println("No command here, please try a higher number!");
            case ADD -> addTrainDeparture();
            case SEARCH -> searchMenu();
            case SHOW_ALL -> printAllTrainDepartures();
            case EDIT -> editMenu();
            case REMOVE -> removeMenu();
            case TIME -> handelTime();
            case HELP -> boardsAndMenusHandler.printMenu();
            case QUIT -> wantToQuit = true;
            default -> {
                System.err.println("Please enter a valid number from menu");
                mainMenu();
            }
        }
        return wantToQuit;
    }

    private void handelTime() {

        String wantsToChangeTime = inputHandler.getStringInput
                ("Do you want to change the time? (y/n):", "Please enter y or n");
        if (wantsToChangeTime.equals("y")) {
            String newTime = inputHandler.getClockInput
                    ("Enter new time: ", "Time needs to be in format hh:mm", clock);
            clock = newTime;
            register.removeTrainDepartures(clock);
            boardsAndMenusHandler.printClock(clock);
            boardsAndMenusHandler.printMenu();
        } else {
            boardsAndMenusHandler.printClock(clock);
            boardsAndMenusHandler.printMenu();
        }
    }


    public void searchMenu() {

        boardsAndMenusHandler.printSearchMenu();
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processSearchMenuCommand(command);
        }
        mainMenu();
    }

    public void editMenu() {
        int trainNumber = inputHandler.getIntInput
                ("Enter train number: ", "Train number needs to be a positive number");
        TrainDeparture trainDeparture = register.getTrainDepartureWithNumber(trainNumber);

        boardsAndMenusHandler.printEditMenu();
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processEditMenuCommand(command, trainDeparture);
        }
        mainMenu();
    }

    public void removeMenu() {
        boardsAndMenusHandler.printRemoveMenu();
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processRemoveMenuCommand(command);
        }
        mainMenu();
    }

    private boolean processRemoveMenuCommand(Command command) {

        boolean goBack = false;
        RemoveCommands commandInput = command.getRemoveCommandInput();
        switch (commandInput) {
            case UNKNOWN -> System.out.println("There is no command here, please try a higher number!");
            case TRAIN_NUMBER -> removeTrainDepartureWithNumber();
            case DESTINATION -> removeTrainDeparturesWithDestination();
            case DEPARTURE_TIME -> removeTrainDeparturesWithTime();
            case TRACK -> removeTrainDeparturesWithTrack();
            case LANE -> removeTrainDeparturesWithLane();
            case BACK -> goBack = true;
            case EXIT -> System.exit(0);
            default -> {
                System.err.println("Please enter a valid number from menu");
                removeMenu();
            }

        }
        return goBack;
    }

    private boolean processEditMenuCommand(Command command, TrainDeparture trainDeparture) {

        boolean goBack = false;
        EditCommands commandInput = command.getEditCommandInput();
        switch (commandInput) {
            case UNKNOWN -> System.out.println("There is no command here, please try a higher number!");
            case DESTINATION -> editDestination(trainDeparture);
            case DEPARTURE_TIME -> editDepartureTime(trainDeparture);
            case TRACK -> editTrack(trainDeparture);
            case LANE -> editLine(trainDeparture);
            case DELAY -> editDelay(trainDeparture);
            case BACK -> goBack = true;
            case EXIT -> System.exit(0);
            default -> {
                System.err.println("Please enter a valid number from menu");
                editMenu();
            }
        }
        return goBack;
    }

    private void editDelay(TrainDeparture trainDeparture) {
        String newDelay = inputHandler.getTimeInput
                ("Enter new delay: ", "Delay needs to be in format hh:mm");
        trainDeparture.setDelay(newDelay);
        System.out.println("Delay changed successfully ");
        boardsAndMenusHandler.printEditMenu();
    }

    private void editLine(TrainDeparture trainDeparture) {
        String newLine = inputHandler.getStringInput
                ("Enter new line: ", "Line cant be empty or blank");
        trainDeparture.setLine(newLine);
        System.out.println("Line changed successfully ");
        boardsAndMenusHandler.printEditMenu();
    }

    private void editTrack(TrainDeparture trainDeparture) {
        int newTrack = inputHandler.getTrackInput
                ("Enter new track: ", "Track needs to be a positive number");
        trainDeparture.setTrack(newTrack);
        System.out.println("Track changed successfully ");
        boardsAndMenusHandler.printEditMenu();
    }

    private void editDepartureTime(TrainDeparture trainDeparture) {
        String newDepartureTime = inputHandler.getTimeInput
                ("Enter new departure time: ", "Time needs to be in format hh:mm");
        trainDeparture.setDepartureTime(newDepartureTime);
        System.out.println("Departure time changed successfully ");
        boardsAndMenusHandler.printEditMenu();
    }

    private boolean processSearchMenuCommand(Command command) {

        boolean goBack = false;
        SearchCommands commandInput = command.getSearchCommandInput();
        switch (commandInput) {
            case UNKNOWN -> System.out.println("");
            case NUMBER -> getTrainDepartureWithNumber();
            case DEPARTURE -> getTrainDepartureWithDestination();
            case DEPARTURE_TIME -> getTrainDepartureWithDepartureTime();
            case TRACK -> getTrainDepartureWithTrack();
            case LANE -> getTrainDepartureWithLine();
            case DELAY -> getTrainDepartureWithDelay();
            case HELP -> boardsAndMenusHandler.printSearchMenu();
            case BACK -> goBack = true;
            case QUIT -> System.exit(0);
            default -> {
                System.err.println("Please enter a valid number from menu");
                searchMenu();
            }
        }
        return goBack;
    }

    private boolean checkIfTrainDepartureListIsEmpty() {
    	try{
            register.getTrainDepartures();
            return true;
        }catch (IllegalArgumentException e){
            System.err.println("List is empty, try adding some departures first");
        }
    	return false;
    }

    private void getTrainDepartureWithDepartureTime() {

        String departureTime = inputHandler.getTimeInput
                ("Enter departure time: ", "Time needs to be in format hh:mm");
      if(checkIfTrainDepartureListIsEmpty()) {
            try {
				getTrainStationName(false);
				boardsAndMenusHandler.printClock(clock);
				boardsAndMenusHandler.printTrainDepartureBoardHeader();
                register.getTrainDepartureWithDepartureTime(departureTime).forEach(trainDeparture ->
                        System.out.println(trainDeparture.display()));
            } catch (IllegalArgumentException e) {
                System.err.println("Train departure(s) was not found");
            }
        }
        boardsAndMenusHandler.printSearchMenu();
    }

    private void getTrainDepartureWithTrack() {

        int track = inputHandler.getTrackInput
                ("Enter track: ", "Track needs to be a positive number");
        if(checkIfTrainDepartureListIsEmpty()) {
            try {
				getTrainStationName(false);
				boardsAndMenusHandler.printClock(clock);
				boardsAndMenusHandler.printTrainDepartureBoardHeader();
                register.getTrainDepartureWithTrack(track).forEach(trainDeparture ->
                        System.out.println(trainDeparture.display()));
            } catch (IllegalArgumentException e) {
                System.err.println("Train departure(s) was not found");
            }
        }
        boardsAndMenusHandler.printSearchMenu();
    }

    private void getTrainDepartureWithLine() {

        String line = inputHandler.getStringInput
                ("Enter line: ", "Line cant be empty or blank");
        if(checkIfTrainDepartureListIsEmpty()) {
            try {
                getTrainStationName(false);
                boardsAndMenusHandler.printClock(clock);
                boardsAndMenusHandler.printTrainDepartureBoardHeader();
                register.getTrainDepartureWithLine(line)
                        .forEach(trainDeparture -> System.out.println(trainDeparture.display()));
            } catch (IllegalArgumentException e) {
                System.err.println("Train departure(s) was not found");
            }
        }
        boardsAndMenusHandler.printSearchMenu();
    }

    private void getTrainDepartureWithDelay() {

        String delay = inputHandler.getTimeInput
                ("Enter delay: ", "Delay needs to be in format hh:mm");
        if(checkIfTrainDepartureListIsEmpty()) {
            try {
				getTrainStationName(false);
				boardsAndMenusHandler.printClock(clock);
				boardsAndMenusHandler.printTrainDepartureBoardHeader();
                register.getTrainDepartureWithDelay(delay)
                        .forEach(trainDeparture -> System.out.println(trainDeparture.display()));
            } catch (IllegalArgumentException e) {
                System.err.println("Train departure(s) was not found");
            }
        }
        boardsAndMenusHandler.printSearchMenu();
    }

    private void addTrainDeparture() {

        boolean hasTrack = false;
        boolean hasDelay = false;
        String hasTrackResponse =
                inputHandler.checkResponseInput("y", "n", "Does the train have a track? (y/n): "
                        , "Please enter y or n");
        if (hasTrackResponse.equals("y")) {
            hasTrack = true;
        }
        String hasDelayResponse =
                inputHandler.checkResponseInput("y", "n", "Does the train have a delay? (y/n): "
                        , "Please enter y or n");
        if (hasDelayResponse.equals("y")) {
            hasDelay = true;
        }
        String destination = inputHandler.checkStringInput
                (stationName, stationName, "Enter destination: "
                        , "Destination needs to be a valid name and needs to be " +
                                "equal or less then 20 characters");
        String departureTime = inputHandler.getTimeInput
                ("Enter departure time: ", "Time needs to be in format hh:mm");
        int trainNumber = inputHandler.getIntInput
                ("Enter train number: ", "Train number needs to be a positive number"
                        + " and not already exist");

        String line = inputHandler.getStringInput
                ("Enter line: ", "Line cant be empty or blank and needs to be equal "
                        + "or less then 20 characters");
        int track = -1;
        if (hasTrack) {
            track = inputHandler.getIntInput
                    ("Enter track: ", "Track needs to be a positive number");
        }
        String delay = "00:00";
        if (hasDelay) {
            delay = inputHandler.getTimeInput
                    ("Enter delay: ", "Delay needs to be in format hh:mm");
        }
        TrainDeparture trainDeparture =
                new TrainDeparture(departureTime, line, trainNumber, destination, track, delay);
        if (register.addTrainDeparture(trainDeparture)) {
            System.out.println("Train departure added");
            getTrainStationName(false);
            boardsAndMenusHandler.printClock(clock);
            boardsAndMenusHandler.printTrainDepartureBoardHeader();
            System.out.println(trainDeparture.display());
        } else {
            System.err.println("Train departure was not added");
        }
        boardsAndMenusHandler.printMenu();
    }

    private void getTrainDepartureWithDestination() {

        String destination = inputHandler.getStringInput
                ("Enter destination: ", "Destination needs to be a valid name");
        if(checkIfTrainDepartureListIsEmpty()) {
            try{
            getTrainStationName(false);
            boardsAndMenusHandler.printClock(clock);
            boardsAndMenusHandler.printTrainDepartureBoardHeader();
            register.getTrainDepartureWithDestination(destination)
                    .forEach(trainDeparture -> System.out.println(trainDeparture.display()));
            } catch (IllegalArgumentException e) {
                System.err.println("Train departure(s) was not found");
            }
        }
        boardsAndMenusHandler.printSearchMenu();
    }

    private void printAllTrainDepartures() {
        if(checkIfTrainDepartureListIsEmpty()) {
            getTrainStationName(false);
            boardsAndMenusHandler.printClock(clock);
            boardsAndMenusHandler.printTrainDepartureBoardHeader();
            register.sortTrainDepartures();
            register.getTrainDepartures().values()
                    .forEach(trainDeparture -> System.out.println(trainDeparture.display()));
        }
        boardsAndMenusHandler.printMenu();
    }


    private void removeTrainDeparturesWithTime() {

        String departureTime = inputHandler.getStringInput
                ("Enter departure time: "
                        , "Time needs to be in format hh:mm");
        if(checkIfTrainDepartureListIsEmpty()) {
            try {
                register.removeTrainDeparturesWithTime(departureTime);
                System.out.println("Train departures removed");
            } catch (IllegalArgumentException e) {
                System.err.println("Train departures was not removed because time was not valid");
            }
        }
        boardsAndMenusHandler.printRemoveMenu();
    }

    private void removeTrainDeparturesWithTrack() {
        int track = inputHandler.getTrackInput
                ("Enter track: ", "Track needs to be a positive number");
        if(checkIfTrainDepartureListIsEmpty()) {
            try {
                register.removeTrainDeparturesByTrack(track);
                System.out.println("Train departures removed");
            } catch (IllegalArgumentException e) {
                System.err.println("Train departures was not removed because track was not valid");
            }
        }
        boardsAndMenusHandler.printRemoveMenu();
    }

    private void removeTrainDeparturesWithLane() {
        String line = inputHandler.getStringInput
                ("Enter line: ", "Line cant be empty or blank");
        if(checkIfTrainDepartureListIsEmpty()) {
            try {
                register.removeTrainDeparturesByLane(line);
                System.out.println("Train departures removed");
            } catch (IllegalArgumentException e) {
                System.err.println("Train departures was not removed because line was not valid");
            }
        }
        boardsAndMenusHandler.printRemoveMenu();
    }

    private void removeTrainDeparturesWithDestination() {
        String destination = inputHandler.getStringInput
                ("Enter destination: ", "Destination needs to be a valid name");
        if(checkIfTrainDepartureListIsEmpty()) {
            try {
                register.removeTrainDeparturesByDestination(destination);
                System.out.println("Train departures removed");
            } catch (IllegalArgumentException e) {
                System.err.println("Train departures was not removed because destination was not valid");
            }
        }
        boardsAndMenusHandler.printRemoveMenu();
    }

    private void removeTrainDepartureWithNumber() {
        int trainNumber = inputHandler.getIntInput

                ("Enter train number: ", "Train number needs to be a positive number");
        if(checkIfTrainDepartureListIsEmpty()) {
            try {
                register.removeTrainDepartures(trainNumber);
                System.out.println("Train departures removed");
            } catch (IllegalArgumentException e) {
                System.err.println("Train departures was not removed because train number was not valid");
            }
        }
        boardsAndMenusHandler.printRemoveMenu();
    }


    private void getTrainDepartureWithNumber() {

        int trainNumber = inputHandler.getIntInput("Enter train number: ",
                "Train number needs to be a positive number");
        if(checkIfTrainDepartureListIsEmpty()) {
            try {
				getTrainStationName(false);
				boardsAndMenusHandler.printClock(clock);
				boardsAndMenusHandler.printTrainDepartureBoardHeader();
                System.out.println(register.getTrainDepartureWithNumber(trainNumber).display());
            } catch (IllegalArgumentException e) {
                System.err.println("Train departure(s) was not found");
            }
        }
        boardsAndMenusHandler.printSearchMenu();
    }


    private void editDestination(TrainDeparture trainDeparture) {
        String newDestination = inputHandler.getStringInput
                ("Enter new destination: ", "Destination needs to be a valid name");
        trainDeparture.setDestination(newDestination);
        System.out.println("Destination changed successfully ");
        boardsAndMenusHandler.printEditMenu();
    }

}