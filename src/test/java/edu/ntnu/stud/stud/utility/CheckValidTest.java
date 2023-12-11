package edu.ntnu.stud.stud.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.stud.utility.CheckValid;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckValidTest {
    @BeforeEach
    public void testCheckValid() {
        CheckValid checkValid;
    }
    @Test
    public void testCheckValidString() {
        CheckValid.checkValidString("string", "exceptionMessage");
    }
    @Test
    public void testCheckValidInt() {
        CheckValid.checkValidInt(0, "exceptionMessage");
    }
    @Test
    void testCheckValidTrackNumber () {
        CheckValid.checkValidTrackNumber(0, "exceptionMessage");
    }
    @Test
    void testCheckValidTime () {
        CheckValid.checkValidTime("10:10", "exceptionMessage");
    }
    @Test
    void testCheckValidIfThereIsDelay (){
        CheckValid.checkIfThereIsDelay(LocalTime.parse("00:00"));
    }
    @Test
    void testCheckValidMap (){
        try{
            CheckValid.checkMap(null);
        } catch (NullPointerException e){
            System.out.println("laneMap is null");
        }
    }
}
