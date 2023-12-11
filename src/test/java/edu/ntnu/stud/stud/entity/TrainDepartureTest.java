package edu.ntnu.stud.stud.entity;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.stud.entity.TrainDeparture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

class TrainDepartureTest {
  TrainDeparture trainDeparture;
  @BeforeEach
    void setUp() {
     trainDeparture = new TrainDeparture(LocalTime.parse("10:15"), "L1", 1, "Oslo", 1, LocalTime.parse("00:00"));
  }

  @Test
  void setDepartureTimePositive() {
    trainDeparture.setDepartureTime(LocalTime.parse("21:15"));
        assertEquals("21:15", trainDeparture.getDepartureTime().toString());

  }
  @Test
  void setDepartureTimeNegative() {
    try {
      trainDeparture.setDepartureTime( LocalTime.parse("25:15"));
      fail("Should throw exception");
      }catch (DateTimeParseException e) {
      assertEquals("Text '25:15' could not be parsed: Invalid value for HourOfDay (valid values 0 - 23): 25", e.getMessage());
    }

  }

  @Test
  void setLinePositive() {
    trainDeparture.setLane("L2");
    assertEquals("L2", trainDeparture.getLane());
  }
  @Test
  void setLineNegative() {
    try {
      trainDeparture.setLane("");
      fail("Should throw exception");
    }catch (IllegalArgumentException e) {
        assertEquals("Line needs to be a string", e.getMessage());
    }
  }

  @Test
  void setTrainNumberPositive() {
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
  void setDestinationPositive() {
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
  void setTrackPositive() {
    trainDeparture.setTrack(2);
    assertEquals(2, trainDeparture.getTrack());

  }
  @Test
  void setTrackNegative() {
   try {
     trainDeparture.setTrack(-500);
     fail("Should throw exception");
    }catch (IllegalArgumentException e) {
     assertEquals("Track needs to be a positive number", e.getMessage());
   }
  }

  @Test
  void setDelayPositive() {
    trainDeparture.setDelay( LocalTime.parse("23:59"));
    assertEquals("23:59", trainDeparture.getDelay().toString());
  }
  @Test
  void setDelayNegative() {
    try {
      trainDeparture.setDelay( LocalTime.parse("25:30"));
      fail("Should throw exception");
    }catch (DateTimeParseException e) {
        assertEquals("Text '25:30' could not be parsed: Invalid value for HourOfDay (valid values 0 - 23): 25", e.getMessage());
    }
  }
}