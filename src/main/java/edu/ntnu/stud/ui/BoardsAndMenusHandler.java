package edu.ntnu.stud.ui;

public class BoardsAndMenusHandler {

  public BoardsAndMenusHandler( ) {

  }


  public void printTrainDepartureBoardHeader() {

	  System.out.print("""
		|----------------|----------------------|--------------|----------------------|-------|----------|
		| Departure time |       Line           | Train number | Destination          | Delay | Track    |          
		|----------------|----------------------|--------------|----------------------|-------|----------|
		""");
  }

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

  public void printSearchMenu() {

	System.out.println("""
 +--Search menu-----------------------------------------------------------------------------------------------------+
	|	1. search by train number                                                                                      |
	|	2. Search by destination                                                                                       |
	|	3. Search by departure time                                                                                    |
	|	4. Search by track                                                                                             |
	|	5. Search by line                                                                                              | 
	|	6. Search by delay                                                                                         	   |
	|	7. Help                                                                                                        |
	|	8. Back                                                                                                        |
	|	9. Exit                                                                                                        |
	+------------------------------------------------------------------------------------------------------------------+
		  """);

  }

  public void printEditMenu() {
	  System.out.println("""
 +--Edit menu-------------------------------------------------------------------------------------------------------+
	|	1. Edit Destination                                                                                            |
	|	2. Edit Departure time                                                                                         |
	|	3. Edit Track                                                                                                  |
	|	4. Edit Delay                                                                                                  |
	|	5. Edit Line                                                                                                   | 
	|	6. Back                                                                                                 	   |
	|	7. Exit                                                                                                        |
	+------------------------------------------------------------------------------------------------------------------+
		  """);

  }

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
	*	iv) when you are done with the application you can exit by using command 7.                                    *
	*	v) if you need help you can printer this message again by using command 6.                                     *
	********************************************************************************************************************
		""");

  }

}
