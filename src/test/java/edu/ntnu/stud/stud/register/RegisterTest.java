package edu.ntnu.stud.stud.register;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.stud.entity.TrainDeparture;
import edu.ntnu.stud.register.Register;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegisterTest {
Register register;
  TrainDeparture trainDeparture;
  @BeforeEach
  void setUp() {
     register = new Register();
     trainDeparture = new TrainDeparture("12:00", "L1", 1, "Oslo", 1, "00:00");
    TrainDeparture trainDeparture1 = new TrainDeparture("15:00", "L2", 2, "Oslo", 2, "10:00");
    register.addTrainDeparture(trainDeparture);
    register.addTrainDeparture(trainDeparture1);
  }

  @Test
  void getTrainDeparturesPositive() {
    assertEquals(2, register.getTrainDepartures().size());
  }
  @Test
  void getTrainDeparturesNegativ() {
    register.removeTrainDepartures(1);
    register.removeTrainDepartures(2);
    try{
      register.getTrainDepartures();
      fail("Should throw exception");

    } catch (IllegalArgumentException e) {
      assertEquals("List is empty", e.getMessage());
    }
  }


  @Test
  void addTrainDeparturePositive() {
    register.addTrainDeparture(new TrainDeparture("18:00", "L3", 3, "Oslo", 5, "01:00"));
    assertEquals(3, register.getTrainDepartures().size());
  }
  @Test
  void addTrainDepartureNegative() {
    try{
      register.addTrainDeparture(null);
      fail("Should throw exception");
    } catch (NullPointerException e) {
      assertEquals("Train departure can not be null", e.getMessage());
    }
  }

  @Test
  void getTrainDepartureWithNumberPositive() {
    assertEquals(trainDeparture, register.getTrainDepartureWithNumber(1));
  }
  @Test
  void getTrainDepartureWithNumberNegative() {
   try{
     register.getTrainDepartureWithNumber(-1);
     fail("Should throw exception");
   } catch (IllegalArgumentException e) {
     assertEquals("Train number should be positiv", e.getMessage());
   }
  }

  @Test
  void getTrainDepartureWithDestination() {

  }

  @Test
  void getTrainDepartureWithDepartureTime() {

  }

  @Test
  void getTrainDepartureWithinTime() {

  }

  @Test
  void removeTrainDepartures() {

  }

  @Test
  void testRemoveTrainDepartures() {

  }

  @Test
  void removeTrainDeparturesByDestination() {

  }

  @Test
  void sortTrainDepartures() {

  }
}