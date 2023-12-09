package edu.ntnu.stud.ui;

import edu.ntnu.stud.utility.CheckValid;

import java.time.LocalTime;
import java.util.Scanner;

/**
 * Checks for valid user input and throws exception if input is not valid.(some method are
 * overloaded to be used in different classes)
 *
 * @author Anwar Debs
 * @see CheckValid
 */
public class UserInputHandler
{
    private final Scanner reader;

    /**
     * constructor to reuse objects of scanner and printer massage class from smartHua class.
     *
     * @param reader       gets scanner class from SmartHus class
     */
    public UserInputHandler(Scanner reader )
    {
        this.reader = reader;
    }

    /**
     * Checks if integer input is valid.
     *
     * @return if input is valid
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




    public String getStringInput(String massage, String errorMassage)
    {
        String stringInput;
        boolean stringValid = false;
        do
        {
            System.out.print(massage);

            stringInput = reader.next();
            if (CheckValid.checkValidString(stringInput,"") && stringInput.length() <= 20)
            {
                stringValid = true;

            } else
            {
                System.err.print(errorMassage  + "\n" + "> ");


            }

        } while (!stringValid);
        return stringInput;
    }
    public String getTimeInput(String massage, String errorMassage) {
        String stringInput;
        boolean stringValid = false;
        do
        {
            System.out.print(massage);

            stringInput = reader.next();
            try {
                if (CheckValid.checkValidTime(stringInput, "") && stringInput.length() <= 20) {
                    stringValid = true;

                }
            } catch (IllegalArgumentException e){
                    System.err.print(errorMassage  + "\n" + "> ");
                }

        } while (!stringValid);
        return stringInput;
    }
  public String getClockInput(String massage, String errorMassage, String oldTime) {
        String stringInput;
        boolean stringValid = false;
        do
        {
            System.out.print(massage);

            stringInput = reader.next();
            try {
                if (CheckValid.checkValidTime(stringInput, "")
                        && LocalTime.parse(stringInput).isAfter(LocalTime.parse(oldTime))) {
                    stringValid = true;
                }else {
                    System.err.println("New time cannot be before the current time, pleas try again");
                }
            } catch (IllegalArgumentException e){
                    System.err.print(errorMassage  + "\n" + "> ");
                }

        } while (!stringValid);
        return stringInput;
    }

    public int getIntInput(String massage, String errorMassage)
    {
        int intInput;
        boolean intValid = false;
        do
        {
            System.out.print(massage);

            intInput = readAndCheckIfInputInt();
            if (CheckValid.checkValidInt(intInput))
            {
                intValid = true;
            }

        } while (!intValid);
        return intInput;
    }
    public int getTrackInput(String massage, String errorMassage)
    {
        int intInput;
        boolean intValid = false;
        do
        {
            System.out.print(massage);

            intInput = readAndCheckIfInputInt();
            if (CheckValid.checkValidTrackNumber(intInput, "Track needs to be a positive number"))
            {
                intValid = true;
            }

        } while (!intValid);
        return intInput;
    }



    public int getCommandInput(String massage)
    {
        int intInput;
        boolean intValid = false;
        do
        {
            System.out.print(massage);

            intInput = readAndCheckIfInputInt();
            if (CheckValid.checkValidInt(intInput))
            {
                intValid = true;
            }

        } while (!intValid);
        return intInput;
    }

    public String checkStringInput(String wantedInput, String unwantedInput, String massage, String errorMassage) {
        while (wantedInput.equalsIgnoreCase(unwantedInput)) {
            wantedInput = getStringInput(massage, errorMassage);
            if(wantedInput.equalsIgnoreCase(unwantedInput)){
                System.err.println("The name of the destination cannot be the same as the current station name");
            }
        }
        return wantedInput;
    }

    public String checkResponseInput(String response1, String response2, String massage, String errorMassage) {
        String response;
        boolean responseValid = false;
        do {
            response = getStringInput(massage, errorMassage);
            if (response.equalsIgnoreCase(response1) || response.equalsIgnoreCase(response2)) {
                responseValid = true;
            }else{
                System.err.print(errorMassage  + "\n" + "> ");
            }
        } while (!responseValid);
        return response;
    }

}
