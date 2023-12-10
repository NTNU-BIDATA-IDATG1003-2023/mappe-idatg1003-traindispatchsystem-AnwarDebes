package edu.ntnu.stud;

import edu.ntnu.stud.ui.UserInterface;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        userInterface.init();
        userInterface.start();
    }
}
