package edu.ntnu.stud.stud.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.stud.ui.UserInputHandler;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserInputHandlerTest {
    private UserInputHandler userInputHandler;
    private Scanner scanner;

    @BeforeEach
    void setUp() {
        userInputHandler = new UserInputHandler(scanner);
    }

    @Test
    void readAndCheckIfInputIntPositive() {
        try
        {
            userInputHandler.readAndCheckIfInputInt();
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
    @Test
    void readAndCheckIfInputIntNegative() {
        try
        {
            userInputHandler.readAndCheckIfInputInt();
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
    @Test
    void getStringInputPositive() {
        try
        {
            userInputHandler.getStringInput("message", "errormessage");
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
    @Test
    void getStringInputNegative() {
        try
        {
            userInputHandler.getStringInput("message", "errormessage");
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
    @Test
    void getIntInputPositive() {
        try
        {
            userInputHandler.getIntInput("message", "errormessage");
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
    @Test
    void getIntInputNegative() {
        try
        {
            userInputHandler.getIntInput("message", "errormessage");
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
    @Test
    void getTrackInputPositive() {
        try
        {
            userInputHandler.getTrackInput("message", "errormessage");
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
    @Test
    void getTrackInputNegative() {
        try
        {
            userInputHandler.getTrackInput("message", "errormessage");
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
