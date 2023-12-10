package edu.ntnu.stud;

import edu.ntnu.stud.ui.UserInterfaceController;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp
{
    /**
     * The main method for the train dispatch application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args)
    {
        // Create and start the user interface controller
        UserInterfaceController userInterfaceController = new UserInterfaceController();
        userInterfaceController.init();
        userInterfaceController.start();
    }
}
