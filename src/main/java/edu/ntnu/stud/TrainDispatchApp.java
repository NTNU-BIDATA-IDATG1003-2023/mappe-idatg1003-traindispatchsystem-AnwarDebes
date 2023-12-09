package edu.ntnu.stud;

import edu.ntnu.stud.ui.UI;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
    public static void main(String[] args) {
        UI ui = new UI();
        ui.init();
        ui.start();
    }
}
