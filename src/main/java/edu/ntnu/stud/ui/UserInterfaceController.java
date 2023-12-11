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

/**
 * The UserInterface class manages the user interface for a train dispatch application.
 * It handles various user interactions such as displaying menus, adding or removing train
 * departures and handling user inputs.
 *
 * @author Anwar Debes
 * @version 0.0.1
 * @see UserInputHandler
 * @see UserInterfaceView
 * @see TrainDeparture
 * @see Register
 * @see Command
 * @see MainMenuCommands
 * @see EditCommands
 * @see SearchCommands
 * @see RemoveCommands
 * @see LocalTime
 */
public class UserInterfaceController
{
    // boolean to check if the train has a track
    boolean hasTrack;
    // boolean to check if the train has a delay
    boolean hasDelay;
    // The register that holds all train departures
    private Register register;
    // The scanner that reads user input
    private Scanner reader;
    // The parser that parses user input
    private Parser parser;
    // The clock that displays the current time
    private String clock;
    // The name of the station
    private String stationName;
    // The handler that handles the display of various user interface components
    private UserInterfaceView userInterfaceView;
    // The handler that handles user input
    private UserInputHandler inputHandler;

    /**
     * Empty constructor for the user interface class.
     */
    public UserInterfaceController()
    {
        // empty constructor, no need to initialize anything

    }

    /**
     * Initializes the user interface by creating a new register, scanner, parser,
     *     and boardsAndMenusHandler.
     */
    public void init()
    {

        clock = "00:00";
        register = new Register();
        reader = new Scanner(System.in);
        userInterfaceView = new UserInterfaceView();
        inputHandler = new UserInputHandler(reader);
        parser = new Parser(inputHandler);

    }

    /**
     * Starts the application by displaying a welcome message, printing the main menu, and
     * handling user input.
     */

    public void start()
    {
        stationName = inputHandler.getStringInput("Please enter the station name: ",
            "Station name cant be empty or blank, please try again :");
        getTrainStationName(true);
        userInterfaceView.printWelcomeMessage();
        mainMenu();

    }

    /**
     * Displays the name of the train station. If the parameter isWelcomeMessage is true,
     * the method displays a welcome message. Otherwise, it only displays the name of the station.
     *
     * @param isWelcomeMessage Indicates whether to display the welcome message.
     */
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

        userInterfaceView.printMenu();
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

        MainMenuCommands commandInput = command.getMainCommandInput();

        switch (commandInput)
        {
            case UNKNOWN -> System.err.println("No command here, please try a higher number!");
            case ADD -> addTrainDeparture();
            case SEARCH -> searchMenu();
            case SHOW_ALL -> printAllTrainDepartures();
            case EDIT -> editMenu();
            case REMOVE -> removeMenu();
            case TIME -> handelTime();
            case HELP -> userInterfaceView.printWelcomeMessage();
            case QUIT -> wantToQuit = true;
            default ->
            {
                System.err.println("Please enter a valid number as shown in the menu");
                mainMenu();
            }
        }
        return wantToQuit;
    }

    /**
     * Handles the time input from the user and displays the clock and the main menu.
     */
    private void handelTime()
    {

        String wantsToChangeTime =
            inputHandler.checkResponseInput("y", "n",
                "Do you want to change the time? (y/n):",
                "Please enter y(yes) or n(no)");
        if (wantsToChangeTime.equals("y"))
        {
            clock =
                inputHandler.getClockInput("Enter new time: ",
                    "Time needs to be in format HH:MM",
                    clock);
            register.removeTrainDepartureByTime(clock);
            userInterfaceView.printClock(clock);
            userInterfaceView.printMenu();
        } else
        {
            userInterfaceView.printClock(clock);
            userInterfaceView.printMenu();
        }
    }

    /**
     * Removes train departures using various criteria selected from the remove menu.
     */
    public void removeMenu()
    {
        userInterfaceView.printRemoveMenu();
        boolean finished = false;
        while (!finished)
        {
            Command command = parser.getCommand();
            finished = processRemoveMenuCommand(command);
        }
        mainMenu();
    }

    /**
     * Processes the command selected from the remove menu.
     *
     * @param command The command to be processed
     * @return true if the user chooses to go back, otherwise false
     */
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
                System.err.println("Please enter a valid number as shown in the  menu");
                removeMenu();
            }

        }
        return goBack;
    }

    /**
     * Displays and handles the edit menu for modifying train departure details.
     */
    public void editMenu()
    {
        int trainNumber = inputHandler.getIntInput(
            "Enter train number for the wanted departure(s): ",
            "Train number needs to be a positive number and not already exist");
        List<TrainDeparture> trainDepartures = register.getTrainDepartureWithNumber(trainNumber);

        TrainDeparture trainDeparture =
            register.getTrainDeparture(trainNumber, getChoiceFromResults(trainDepartures) - 1);
        userInterfaceView.printEditMenu();
        boolean finished = false;
        while (!finished)
        {
            Command command = parser.getCommand();
            finished = processEditMenuCommand(command, trainDeparture);
        }
        mainMenu();
    }

    /**
     * Processes the command selected from the edit menu.
     *
     * @param command        The command to be processed
     * @param trainDeparture The train departure object to be edited
     * @return true if the user chooses to go back, otherwise false
     */
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

    /**
     * Edits the delay of a specific train departure.
     *
     * @param trainDeparture The train departure object to be edited.
     */
    private void editDelay(TrainDeparture trainDeparture)
    {
        String newDelay =
            inputHandler.getTimeInput("Enter new delay: ",
                "Delay time needs to be in format hh:mm");
        register.getTrainDepartures().remove(trainDeparture);
        trainDeparture.setDelay(LocalTime.parse(newDelay));
        try
        {
            if (register.addTrainDeparture(trainDeparture))
            {
                System.out.println("Delay changed successfully ");
            }
        } catch (IllegalArgumentException | IllegalStateException | NullPointerException f)
        {
            System.err.println(
                "Editing the delay was not successful, because the changes you made "
                    + "resulted in a train departure that is not valid");
            register.addTrainDeparture(trainDeparture);
        }
        userInterfaceView.printEditMenu();
    }

    /**
     * Edits the line (lane) of a specific train departure.
     *
     * @param trainDeparture The train departure object to be edited
     */
    private void editLane(TrainDeparture trainDeparture)
    {

        String newLine =
            inputHandler.getStringInput("Enter new line: ", "Lane cant be empty or blank");
        register.getTrainDepartures().remove(trainDeparture);
        trainDeparture.setLane(newLine);
        try
        {
            register.addTrainDeparture(trainDeparture);
            System.out.println("Line changed successfully ");
        } catch (IllegalArgumentException | IllegalStateException | NullPointerException f)
        {
            System.err.println(
                "Editing the Lane was not successful, because the changes you made "
                    + "resulted in a train departure that wasn't valid");
            register.addTrainDeparture(trainDeparture);
        }
        userInterfaceView.printEditMenu();
    }

    /**
     * Edits the track of a specific train departure.
     *
     * @param trainDeparture The train departure object to be edited
     */
    private void editTrack(TrainDeparture trainDeparture)
    {

        int newTrack =
            inputHandler.getTrackInput("Enter new track: ",
                "Track number needs to be a positive number");
        register.getTrainDepartures().remove(trainDeparture);
        trainDeparture.setTrack(newTrack);
        try
        {
            register.addTrainDeparture(trainDeparture);
            System.out.println("Track changed successfully ");
        } catch (IllegalArgumentException | IllegalStateException | NullPointerException f)
        {
            System.err.println(
                "Editing the track was not successful, because the changes you made "
                    + "resulted in a train departure that isn't valid");
            register.addTrainDeparture(trainDeparture);
        }
        userInterfaceView.printEditMenu();
    }

    /**
     * Edits the departure time of a specific train departure.
     *
     * @param trainDeparture The train departure object to be edited.
     */
    private void editDepartureTime(TrainDeparture trainDeparture)
    {
        String newDepartureTime = inputHandler.getTimeInput("Enter new departure time: ",
            "Time needs to be in format HH:MM");
        register.getTrainDepartures().remove(trainDeparture);
        trainDeparture.setDepartureTime(LocalTime.parse(newDepartureTime));
        try
        {
            register.addTrainDeparture(trainDeparture);
            System.out.println("Departure time changed successfully ");
        } catch (IllegalArgumentException | IllegalStateException | NullPointerException f)
        {
            System.err.println(
                "Editing the departure time was not successful, because the changes you made "
                    + "resulted in a train departure that wasn't valid");
            register.addTrainDeparture(trainDeparture);
        }
        userInterfaceView.printEditMenu();
    }

    /**
     * Edits the destination of a specific train departure.
     *
     * @param trainDeparture The train departure object to be edited.
     */
    private void editDestination(TrainDeparture trainDeparture)
    {
        String newDestination = inputHandler.getStringInput("Enter new destination: ",
            "Destination needs to be a valid name and not empty or blank");
        register.getTrainDepartures().remove(trainDeparture);
        trainDeparture.setDestination(newDestination);
        try
        {
            register.addTrainDeparture(trainDeparture);
            System.out.println("Destination changed successfully ");
        } catch (IllegalArgumentException | IllegalStateException | NullPointerException f)
        {
            System.err.println(
                "Editing the destination was not successful, because the changes you made "
                    + "resulted in a train departure that was not valid");
            register.addTrainDeparture(trainDeparture);
        }
        userInterfaceView.printEditMenu();
    }

    /**
     * Edits the train number of a specific train departure.
     *
     * @param trainDeparture The train departure object to be edited.
     */
    private void editTrainNumber(TrainDeparture trainDeparture)
    {
        int newTrainNumber = inputHandler.getIntInput("Enter new train number: ",
            "Train number needs to be a positive number and not already exist");
        register.getTrainDepartures().remove(trainDeparture);
        trainDeparture.setTrainNumber(newTrainNumber);
        try
        {
            register.addTrainDeparture(trainDeparture);
            System.out.println("Train number changed successfully ");
        } catch (IllegalArgumentException | IllegalStateException | NullPointerException f)
        {
            System.err.println(
                "Editing the train number was not successful, because the changes you made "
                    + "resulted in a train departure that was not valid");
            register.addTrainDeparture(trainDeparture);
        }
        userInterfaceView.printEditMenu();
    }

    /**
     * Displays and handles the search menu for finding train departures.
     */
    public void searchMenu()
    {

        userInterfaceView.printSearchMenu();
        boolean finished = false;
        while (!finished)
        {
            Command command = parser.getCommand();
            finished = processSearchMenuCommand(command);
        }
        mainMenu();
    }

    /**
     * Processes the command selected from the search menu.
     *
     * @param command The command to be processed.
     * @return true if the user chooses to go back, otherwise false.
     */
    private boolean processSearchMenuCommand(Command command)
    {

        boolean goBack = false;
        SearchCommands commandInput = command.getSearchCommandInput();
        switch (commandInput)
        {
            case UNKNOWN -> System.err.println("No command here, please try a higher number!");
            case NUMBER -> getTrainDepartureWithNumber();
            case DEPARTURE -> getTrainDepartureWithDestination();
            case DEPARTURE_TIME -> getTrainDepartureWithDepartureTime();
            case TRACK -> getTrainDepartureWithTrack();
            case LANE -> getTrainDepartureWithLine();
            case DELAY -> getTrainDepartureWithDelay();
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

    /**
     * Checks if the list of train departures is empty.
     *
     * @return true if the list is not empty, false otherwise
     */
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

    /**
     * Retrieves and displays train departures based on departure time selected by the user
     * from a list of train departures with the same departure time. If the list is empty,
     * the user is returned to the search menu. If the list is not empty, the user is
     * prompted to choose a train departure from the list. If the user chooses a valid
     * train departure it is displayed.
     */
    private void getTrainDepartureWithDepartureTime()
    {

        String departureTime =
            inputHandler.getTimeInput("Enter departure time:  ",
                "Departure time needs to be in format hh:mm");
        if (checkIfTrainDepartureListIsEmpty())
        {
            try
            {
                getTrainStationName(false);
                userInterfaceView.printClock(clock);
                userInterfaceView.printTrainDepartureBoardHeader();
                register.getTrainDepartureWithDepartureTime(departureTime)
                    .forEach(trainDeparture -> System.out.println(trainDeparture.display()));
            } catch (IllegalArgumentException e)
            {
                System.err.println("Train departure(s) was not found, try again");
            }
        }
        userInterfaceView.printSearchMenu();
    }

    /**
     * Retrieves and displays train departures based on track number selected by the user
     * from a list of train departures with the same track number. If the list is empty,
     * the user is returned to the search menu. If the list is not empty, the user is
     * prompted to choose a train departure from the list. If the user chooses a valid
     * train departure it is displayed.
     */
    private void getTrainDepartureWithTrack()
    {

        int track =
            inputHandler.getTrackInput("Enter track number: ",
                "Track needs to be a positive number, please try again");
        if (checkIfTrainDepartureListIsEmpty())
        {
            try
            {
                getTrainStationName(false);
                userInterfaceView.printClock(clock);
                userInterfaceView.printTrainDepartureBoardHeader();
                register.getTrainDepartureWithTrack(track)
                    .forEach(trainDeparture -> System.out.println(trainDeparture.display()));
            } catch (IllegalArgumentException e)
            {
                System.err.println("Train departure(s) was not found, please try again");
            }
        }
        userInterfaceView.printSearchMenu();
    }

    /**
     * Retrieves and displays train departures based on the line selected by the user
     * from a list of train departures with the same line. If the list is empty, the user is
     * returned to the search menu. If the list is not empty, the user is prompted to choose a
     * train departure from the list. If the user chooses a valid train departure it is displayed.
     */
    private void getTrainDepartureWithLine()
    {

        String line = inputHandler.getStringInput("Enter line: ", "Line cant be empty or blank");
        if (checkIfTrainDepartureListIsEmpty())
        {
            try
            {
                getTrainStationName(false);
                userInterfaceView.printClock(clock);
                userInterfaceView.printTrainDepartureBoardHeader();
                register.getTrainDepartureWithLane(line)
                    .forEach(trainDeparture -> System.out.println(trainDeparture.display()));
            } catch (IllegalArgumentException e)
            {
                System.err.println("Train departure(s) was not found, try again");
            }
        }
        userInterfaceView.printSearchMenu();
    }

    /**
     * Retrieves and displays train departures based on delay selected by the user
     * from a list of train departures with the same delay. If the list is empty,
     * the user is returned to the search menu. If the list is not empty, the user is
     * prompted to choose a train departure from the list. If the user chooses a valid
     * train departure it is displayed.
     */
    private void getTrainDepartureWithDelay()
    {

        String delay =
            inputHandler.getTimeInput("Enter delay: ", "Delay needs to be in format hh:mm");
        if (checkIfTrainDepartureListIsEmpty())
        {
            try
            {
                getTrainStationName(false);
                userInterfaceView.printClock(clock);
                userInterfaceView.printTrainDepartureBoardHeader();
                register.getTrainDepartureWithDelay(delay)
                    .forEach(trainDeparture -> System.out.println(trainDeparture.display()));
            } catch (IllegalArgumentException e)
            {
                System.err.println("Train departure(s) was not found, try again with different "
                    + "input");
            }
        }
        userInterfaceView.printSearchMenu();
    }

    /**
     * Retrieves and displays train departures based on train number selected by the user
     * from a list of train departures with the same train number. If the list is empty,
     * the user is returned to the search menu. If the list is not empty, the user is
     * prompted to choose a train departure from the list. If the user chooses a valid
     * train departure it is displayed.
     */
    private void getTrainDepartureWithNumber()
    {

        int trainNumber = inputHandler.getIntInput("Enter train(s) number(s): ",
            "Train number needs to be a positive number, please try again");
        if (checkIfTrainDepartureListIsEmpty())
        {
            try
            {
                getTrainStationName(false);
                userInterfaceView.printClock(clock);
                userInterfaceView.printTrainDepartureBoardHeader();
                register.getTrainDepartureWithNumber(trainNumber)
                    .forEach(trainDeparture -> System.out.println(trainDeparture.display()));
            } catch (IllegalArgumentException e)
            {
                System.err.println("Train departure(s) was not found");
            }
        }
        userInterfaceView.printSearchMenu();
    }

    /**
     * Handles user responses for track and delay while adding a train departure.
     */
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

    /**
     * Adds a new train departure based on user input and displays the train departure board
     * with the new train departure added to it. If the train departure is not valid, it is not
     * added. If the train departure already exists, it is not added. If the train departure is
     * added, the train departure board is displayed.
     */
    private void addTrainDeparture()
    {
        hasTrack = false;
        hasDelay = false;
        handelTrackAndDelayResponse();
        String destination =
            inputHandler.checkStringInput(stationName, stationName, "Enter destination name: ",
                "Destination needs to be a valid name and needs to be "
                    + "equal or less then 20 characters");
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
            userInterfaceView.printClock(clock);
            userInterfaceView.printTrainDepartureBoardHeader();
            System.out.println(trainDeparture.display());
        } catch (IllegalArgumentException e)
        {
            System.err.println("Train departure was not added because a train "
                + "departure with the given time and track already exists");
        } catch (IllegalStateException e)
        {
            System.err.println("Train departure was not added because a train "
                + "departure with the given time and train number already exists");
        } catch (NullPointerException e)
        {
            System.err.println("Train departure was not added because it was not valid");
        }
        userInterfaceView.printMenu();
    }

    /**
     * Handles the input for delay while adding a train departure.
     *
     * @param hasDelay A boolean indicating if the train has a delay.
     * @return The delay time as a string.
     */
    private String handelDelayInput(boolean hasDelay)
    {
        String delay = "00:00";
        if (hasDelay)
        {
            delay = inputHandler.getTimeInput("Enter delay: ", "Delay needs to be in format hh:mm");
        }
        return delay;
    }

    /**
     * Handles the input for track while adding a train departure.
     *
     * @param hasTrack A boolean indicating if the train has a specific track.
     * @return The track number.
     */
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

    /**
     * Handles the input for lane while adding a train departure.
     *
     * @param destination The destination of the train departure.
     * @return The lane as a string.
     */
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
                lane = inputHandler.getStringInput("Enter departure lane: ",
                    "Line cant be empty or blank and needs to be equal "
                        + "or less then 20 characters");
            }
        } catch (NullPointerException e)
        {
            lane = inputHandler.getStringInput("Enter lane: ",
                "Line cant be empty or blank and needs to be equal "
                    + "or less then 20 characters");
        }
        return lane;
    }

    /**
     * Retrieves and displays train departures based on destination selected by the user
     * from a list of train departures with the same destination. If the list is empty,
     * the user is returned to the search menu. If the list is not empty, the user is
     * prompted to choose a train departure from the list. If the user chooses a valid
     * train departure it is displayed.
     */
    private void getTrainDepartureWithDestination()
    {

        String destination = inputHandler.getStringInput("Enter destination: ",
            "Destination needs to be a valid name");
        if (checkIfTrainDepartureListIsEmpty())
        {
            try
            {
                getTrainStationName(false);
                userInterfaceView.printClock(clock);
                userInterfaceView.printTrainDepartureBoardHeader();
                register.getTrainDepartureWithDestination(destination)
                    .forEach(trainDeparture -> System.out.println(trainDeparture.display()));
            } catch (IllegalArgumentException e)
            {
                System.err.println("Train departure(s) was not found");
            }
        }
        userInterfaceView.printSearchMenu();
    }

    /**
     * Allows the user to choose from a list of train departures.
     *
     * @param trainDepartures A list of TrainDeparture objects
     * @return The user's choice as an integer
     */
    private int getChoiceFromResults(List<TrainDeparture> trainDepartures)
    {
        System.out.println("Please choose which train departure you want to work on: ");
        userInterfaceView.printTrainDepartureBoardHeader();
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
        boolean finished = false;
        int choice = 0;
        while (!finished)
        {
            choice = inputHandler.getIntInput("Enter number: ", "Please enter a valid number");
            if (choice > 0 && choice <= i)
            {
                finished = true;

            } else
            {
                System.err.println("Please enter a valid number");
            }
        }
        return choice;
    }

    /**
     * Removes train departures based on departure time selected by the user from a list
     * of train departures with the same departure time. If the list is empty, the user is
     * returned to the remove menu. If the list is not empty, the user is prompted to choose
     * a train departure from the list. If the user chooses a valid train departure,
     * it is removed from the register.
     */
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
                System.out.println("Train departures was successfully removed");
            } catch (IllegalArgumentException e)
            {
                System.err.println("Train departures was not removed because time was not valid");
            }
        }
        userInterfaceView.printRemoveMenu();
    }

    /**
     * Removes train departures based on track number selected by the user from a list of train
     * departures with the same track number. If the list is empty, the user is returned to the
     * remove menu. If the list is not empty, the user is prompted to choose a train departure
     * from the list. If the user chooses a valid train departure, it is removed from the register.
     */
    private void removeTrainDeparturesWithTrack()
    {
        int track =
            inputHandler.getTrackInput("Enter track: ", "Track needs to be a positive number");
        if (checkIfTrainDepartureListIsEmpty())
        {
            List<TrainDeparture> trainDepartures = register.getTrainDepartureWithTrack(track);
            try
            {
                if (register.removeTrainDeparturesByTrack(track,
                    getChoiceFromResults(trainDepartures) - 1))
                {
                    System.out.println("Train departures was successfully removed");
                }
            } catch (IllegalArgumentException e)
            {
                System.err.println("Train departures was not removed because track was not valid");
            }
        }
        userInterfaceView.printRemoveMenu();
    }

    /**
     * Removes train departures based on lane selected by the user from a list of train departures
     * with the same lane. If the list is empty, the user is returned to the remove menu. If the
     * list is not empty, the user is prompted to choose a train departure from the list.If the user
     * chooses a valid train departure, it is removed from the register.
     */
    private void removeTrainDeparturesWithLane()
    {
        String lane = inputHandler.getStringInput("Enter lane: ", "Line cant be empty or blank");
        if (checkIfTrainDepartureListIsEmpty())
        {
            List<TrainDeparture> trainDepartures = register.getTrainDepartureWithLane(lane);
            try
            {
                if (register.removeTrainDeparturesByLane(lane,
                    getChoiceFromResults(trainDepartures) - 1))
                {
                    System.out.println("Train departure is removed");
                }
            } catch (IllegalArgumentException e)
            {
                System.err.println("Train departures was not removed because lane was not valid");
            }
        }
        userInterfaceView.printRemoveMenu();
    }

    /**
     * Removes train departures based on destination selected by the user from a list of train
     * departures with the same destination. If the list is empty, the user is returned to the
     * remove menu. If the list is not empty, the user is prompted to choose a train departure
     * from the list. If the user chooses a valid train departure, it is removed from the register.
     */
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
                if (register.removeTrainDeparturesByDestination(destination,
                    getChoiceFromResults(trainDepartures) - 1))
                {
                    System.out.println("Train departures removed");
                }
            } catch (IllegalArgumentException e)
            {
                System.err.println(
                    "Train departures was not removed because destination was not valid");
            }
        }
        userInterfaceView.printRemoveMenu();
    }

    /**
     * Removes train departures based on train number selected by the user from a list of train
     * departures with the same train number. If the list is empty, the user is returned to the
     * remove menu. If the list is not empty, the user is prompted to choose a train departure
     * from the list. If the user chooses a valid train departure, it is removed from the register.
     */
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
                if (register.removeTrainDepartureByNumber(trainNumber,
                    getChoiceFromResults(trainDepartures) - 1))
                {
                    System.out.println("Train departures removed");
                }
            } catch (IllegalArgumentException e)
            {
                System.err.println(
                    "Train departures was not removed because train number was not valid");
            }
        }
        userInterfaceView.printRemoveMenu();
    }

    /**
     * Prints all registered train departures in the register sorted by departure time and displays
     * the train departure board. If the list is empty, the user is returned to the main menu.
     * If the list is not empty, the train departure board is displayed.
     */
    private void printAllTrainDepartures()
    {
        if (checkIfTrainDepartureListIsEmpty())
        {
            getTrainStationName(false);
            userInterfaceView.printClock(clock);
            userInterfaceView.printTrainDepartureBoardHeader();
            register.sortTrainDepartures()
                .forEach(trainDeparture -> System.out.println(trainDeparture.display()));
        }
        userInterfaceView.printMenu();
    }
}
