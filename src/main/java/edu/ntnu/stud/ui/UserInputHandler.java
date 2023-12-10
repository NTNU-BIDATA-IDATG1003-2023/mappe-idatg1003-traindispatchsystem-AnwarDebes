package edu.ntnu.stud.ui;

import edu.ntnu.stud.utility.CheckValid;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * Checks for valid user input and throws exception if input is not valid.(some method are
 * overloaded to be used in different classes)
 *
 * @author Anwar Debs
 * @version 0.0.1
 * @see CheckValid
 */
public class UserInputHandler
{
    private final Scanner reader;

    /**
     * constructor to reuse objects of scanner and printer message class from smartHua class.
     *
     * @param reader gets scanner class from SmartHus class
     */
    public UserInputHandler(Scanner reader)
    {
        this.reader = reader;
    }

    /**
     * Reads user input and checks if it is a valid integer.
     *
     * @return the valid integer input from the user.
     */
    public int readAndCheckIfInputInt()
    {
        while (!reader.hasNextInt())
        {
            System.err.print(
                "Your input is not accepted, please enter a number greater than 0. " + "\n" + "> ");
            reader.next();
        }
        return reader.nextInt();
    }

    /**
     * Reads user input and checks if it is a valid string.
     *
     * @param message The message to be displayed to the user.
     * @param errormessage The error message to be displayed in case of invalid input.
     * @return the valid string input from the user.
     */
    public String getStringInput(String message, String errormessage)
    {
        String stringInput;
        boolean stringValid = false;
        do
        {
            System.out.print(message);

            stringInput = reader.next();
            if (Boolean.TRUE.equals(CheckValid.checkValidString(stringInput, ""))
                && stringInput.length() <= 20)
            {
                stringValid = true;

            } else
            {
                System.err.print(errormessage + "\n" + "> ");


            }

        } while (!stringValid);
        return stringInput;
    }

    /**
     * Reads user input and checks if it is a valid time format.
     *
     * @param message The message to be displayed to the user.
     * @param errormessage The error message to be displayed in case of invalid input.
     * @return the valid time input from the user.
     */
    public String getTimeInput(String message, String errormessage)
    {
        String stringInput;
        boolean stringValid = false;
        do
        {
            System.out.print(message);

            stringInput = reader.next();
            try
            {
                if (Boolean.TRUE.equals(CheckValid.checkValidTime(stringInput, ""))
                    && stringInput.length() <= 20)
                {
                    stringValid = true;

                }
            } catch (IllegalArgumentException e)
            {
                System.err.print(errormessage + "\n" + "> ");
            }

        } while (!stringValid);
        return stringInput;
    }

    /**
     * Reads user input and checks if it is a valid time format and is after the specified time.
     *
     * @param message The message to be displayed to the user.
     * @param errormessage The error message to be displayed in case of invalid input.
     * @param oldTime The time to compare the input against.
     * @return the valid time input from the user.
     */
    public String getClockInput(String message, String errormessage, String oldTime)
    {
        String stringInput;
        boolean stringValid = false;
        do
        {
            System.out.print(message);

            stringInput = reader.next();
            try
            {
                if (Boolean.TRUE.equals(CheckValid.checkValidTime(stringInput, ""))
                    && LocalTime.parse(stringInput).isAfter(LocalTime.parse(oldTime)))
                {
                    stringValid = true;
                } else
                {
                    System.err.println(
                        "New time cannot be before the current time, pleas try again");
                }
            } catch (IllegalArgumentException e)
            {
                System.err.print(errormessage + "\n" + "> ");
            }

        } while (!stringValid);
        return stringInput;
    }

    /**
     * Reads user input and checks if it is a valid integer.
     *
     * @param message The message to be displayed to the user.
     * @param errorMessage The error message to be displayed in case of invalid input.
     * @return the valid integer input from the user.
     */
    public int getIntInput(String message, String errorMessage)
    {
        int intInput;
        boolean intValid = false;
        do
        {
            System.out.print(message);

            intInput = readAndCheckIfInputInt();
            try
            {
                if (Boolean.TRUE.equals(CheckValid.checkValidInt(intInput)))
                {
                    intValid = true;
                }
            } catch (IllegalArgumentException e)
            {
                System.err.print(errorMessage + "\n" + "> ");
            }

        } while (!intValid);
        return intInput;
    }

    /**
     * Reads user input and checks if it is a valid track number.
     *
     * @param message The message to be displayed to the user.
     * @param errorMessage The error message to be displayed in case of invalid input.
     * @return the valid track number input from the user.
     */
    public int getTrackInput(String message, String errorMessage)
    {
        int intInput;
        boolean intValid = false;
        do
        {
            System.out.print(message);

            intInput = readAndCheckIfInputInt();
            try
            {
                if (Boolean.TRUE.equals(CheckValid.checkValidTrackNumber(intInput,
                    "Track needs to be a positive number")))
                {
                    intValid = true;
                }
            } catch (IllegalArgumentException e)
            {
                System.err.print(errorMessage + "\n" + "> ");
            }

        } while (!intValid);
        return intInput;
    }
    
    /**
     * Checks if the provided string input matches the unwanted input, and prompts the user until a valid input is given.
     *
     * @param wantedInput The input string to validate.
     * @param unwantedInput The string input that is not accepted.
     * @param message The message to be displayed to the user.
     * @param errorMessage The error message to be displayed in case of invalid input.
     * @return a valid string input that doesn't match the unwanted input.
     */
    public String checkStringInput(String wantedInput, String unwantedInput, String message,
                                   String errorMessage)
    {
        while (wantedInput.equalsIgnoreCase(unwantedInput))
        {
            wantedInput = getStringInput(message, errorMessage);
            if (wantedInput.equalsIgnoreCase(unwantedInput))
            {
                System.err.println(
                    "The name of the destination cannot be the same as the current station name");
            }
        }
        return wantedInput;
    }

    /**
     * Validates the user response against two acceptable responses.
     *
     * @param response1 The first acceptable response.
     * @param response2 The second acceptable response.
     * @param message The message to be displayed to the user.
     * @param errormessage The error message to be displayed in case of invalid input.
     * @return the valid response input from the user.
     */
    public String checkResponseInput(String response1, String response2, String message,
                                     String errormessage)
    {
        String response;
        boolean responseValid = false;
        do
        {
            response = getStringInput(message, errormessage);
            if (response.equalsIgnoreCase(response1) || response.equalsIgnoreCase(response2))
            {
                responseValid = true;
            } else
            {
                System.err.print(errormessage + "\n" + "> ");
            }
        } while (!responseValid);
        return response;
    }

}
