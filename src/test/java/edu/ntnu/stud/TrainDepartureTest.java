package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrainDepartureTest {
  TrainDeparture trainDeparture;
  @BeforeEach
    void setUp() {
     trainDeparture = new TrainDeparture("10:15", "L1", 1, "Oslo", 1, "00:00");
  }

  @Test
  void setDepartureTimePositiv() {
    trainDeparture.setDepartureTime("21:15");
        assertEquals("21:15", trainDeparture.getDepartureTime());

  }
  @Test
  void setDepartureTimeNegativ() {
    try {
      trainDeparture.setDepartureTime("21:15:00");
      fail("Should throw exception");
      }catch (IllegalArgumentException e) {
      assertEquals("Times needs to be in the form of hh:mm", e.getMessage());
    }

  }

  @Test
  void setLinePositive() {
    trainDeparture.setLine("L2");
    assertEquals("L2", trainDeparture.getLine());
  }
  @Test
  void setLineNegative() {
    try {
      trainDeparture.setLine("");
      fail("Should throw exception");
    }catch (IllegalArgumentException e) {
        assertEquals("Line needs to be a string", e.getMessage());
    }
  }

  @Test
  void setTrainNumberPositiv() {
    trainDeparture.setTrainNumber(54);
    assertEquals(54, trainDeparture.getTrainNumber());
  }
  @Test
  void setTrainNumberNegative() {
    try {
      trainDeparture.setTrainNumber(-1);
      fail("Should throw exception");
    }catch (IllegalArgumentException e) {
      assertEquals("TrainDeparture number needs to be a positive number", e.getMessage());
    }
  }

  @Test
  void setDestinationPositiv() {
    trainDeparture.setDestination("Trondheim");
    assertEquals("Trondheim", trainDeparture.getDestination());
  }
  @Test
  void setDestinationNegative() {
    try{
        trainDeparture.setDestination("");
        fail("Should throw exception");
    }catch (IllegalArgumentException e) {
        assertEquals("Destination needs to be a string", e.getMessage());
    }
  }

  @Test
  void setTrackPositiv() {
    trainDeparture.setTrack(2);
    assertEquals(2, trainDeparture.getTrack());

  }
  @Test
  void setTrackNegativ() {
   try {
     trainDeparture.setTrack(-500);
     fail("Should throw exception");
    }catch (IllegalArgumentException e) {
     assertEquals("Track needs to be a positive number", e.getMessage());
   }
  }

  @Test
  void setDelayPositiv() {
    trainDeparture.setDelay("23:59");
    assertEquals("23:59", trainDeparture.getDelay());
  }
  @Test
  void setDelayNegative() {
    try {
      trainDeparture.setDelay("25:30");
      fail("Should throw exception");
    }catch (IllegalArgumentException e) {
        assertEquals("Times needs to be in the form of hh:mm", e.getMessage());
    }
  }
  @Test
  void setDelayNegative2() {
    try {
      trainDeparture.setDelay("dhfjgdf");
      fail("Should throw exception");
    }catch (IllegalArgumentException e) {
        assertEquals("Times needs to be in the form of hh:mm", e.getMessage());
    }
  }
}