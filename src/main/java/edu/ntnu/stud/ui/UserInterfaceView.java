package edu.ntnu.stud.ui;


/**
 * The BoardsAndMenusHandler class handles the display of various user interface components
 * for a train dispatch application. It includes methods for printing different types of menus,
 * boards, and messages to the console.
 *
 * @author Anwar Debes
 * @version 0.0.1
 */
public class UserInterfaceView
{

    /**
     * Empty constructs BoardsAndMenusHandler class
     */
    public UserInterfaceView() {

    }

    /**
     * Prints the header for the train departure board
     */
    public void printTrainDepartureBoardHeader() {

        System.out.print("""
                |----------------|----------------------|--------------|----------------------|-------|----------|
                | Departure time |       Line           | Train number | Destination          | Delay | Track    |
                |----------------|----------------------|--------------|----------------------|-------|----------|
                """);
    }

    /**
     * Prints the current time on the clock board.
     *
     * @param clock The current time to be displayed.
     */
    public void printClock(String clock) {

        StringBuilder clockBoard = new StringBuilder();
        clockBoard.append("|");
        clockBoard.append("--------".repeat(12));
        clockBoard.append("|");
        clockBoard.append("\n\t\t\t\t\t\t\t\t\t");
        clockBoard.append("Clock: ");
        clockBoard.append(clock);
        clockBoard.append("\n");
        clockBoard.append("|");
        clockBoard.append("--------".repeat(12));
        clockBoard.append("|");
        System.out.println(clockBoard);
    }

    /**
     * Prints the search menu with options to search for train departures based on various criteria
     */
    public void printSearchMenu() {

        System.out.println("""
                +--Search menu-----------------------------------------------------------------------------------------------------+
                |	1. search by train number                                                                                      |
                |	2. Search by destination                                                                                       |
                |	3. Search by departure time                                                                                    |
                |	4. Search by track                                                                                             |
                |	5. Search by line                                                                                              |\s
                |	6. Search by delay                                                                                         	   |
                |	7. Help                                                                                                        |
                |	8. Back                                                                                                        |
                |	9. Exit                                                                                                        |
                +------------------------------------------------------------------------------------------------------------------+
                	  """);

    }

    /**
     * Prints the edit menu with options to edit different attributes of a train departure
     */
    public void printEditMenu() {
        System.out.println("""
                +--Edit menu-------------------------------------------------------------------------------------------------------+
                |	1. Edit Destination                                                                                            |
                |	2. Edit Departure time                                                                                         |
                |	3. Edit Track                                                                                                  |
                |	4. Edit Delay                                                                                                  |
                |	5. Edit Line                                                                                                   |
                |	6. Edit Train number                                                                                           |\s
                |	7. Back                                                                                                 	   |
                |	8. Exit                                                                                                        |
                +------------------------------------------------------------------------------------------------------------------+
                	  """);

    }

    /**
     * Prints the remove menu with options to remove train departures based on different criteria
     */
    public void printRemoveMenu() {
        System.out.println("""
                +--Remove menu-----------------------------------------------------------------------------------------------------+
                |	1. Remove by train number                                                                                      |
                |	2. Remove by destination                                                                                       |
                |	3. Remove by departure time                                                                                    |
                |	4. Remove by track                                                                                             |
                |	5. Remove by line                                                                                              |
                |	6. Back                                                                                                 	   |
                |	7. Exit                                                                                                        |
                +------------------------------------------------------------------------------------------------------------------+
                	  """);
    }

    /**
     * Prints the main menu with options for various operations like adding, searching, editing,
     * and removing train departures, as well as viewing the time board and seeking help.
     */
    public void printMenu() {
        System.out.println("""
                +--Main menu-------------------------------------------------------------------------------------------------------+
                |	1. Add train departure                                                                                         |
                |	2. Search train departure(s)                                                                                   |
                |	3. Show departure board                                                                                        |
                |	4. Edit train departure(s)                                                                                     |
                |	5. Remove train departure(s)                                                                                   |
                |	6. Time board                                                                                           	   |
                |	7. Help                                                                                                        |
                |	8. Exit                                                                                                        |
                +------------------------------------------------------------------------------------------------------------------+
                	  """);
    }

    /**
     * Prints a welcome message with instructions and guidelines for using the train dispatch application
     */
    public void printWelcomeMessage() {

        System.out.println("""
                ********************************************************************************************************************
                *	Welcome to the train dispatch application!                                                                     *
                *	this application will help you keep track of train departures.                                                 *
                *	to use this application effectively you need to know the following mainMenuCommands:						   *
                *	i) Before using any mainMenuCommands (from 2-7) you need to add a train departure(s).                          *
                *	ii) To add a train departure you need to use command 1.                                                        *
                *	iii) when being asked for input you need to follow                                                             *
                *	the instructions otherwise you will get errors.                                                                *
                *	iv) when you are done with the application you can exit by using command 8.                                    *
                *	v) if you need help you can printer this message again by using command 7.                                     *
                ********************************************************************************************************************
                	""");

    }
}
