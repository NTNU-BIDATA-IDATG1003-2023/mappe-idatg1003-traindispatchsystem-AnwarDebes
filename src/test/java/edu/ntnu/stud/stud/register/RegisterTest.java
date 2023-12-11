package edu.ntnu.stud.stud.register;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.stud.entity.TrainDeparture;
import edu.ntnu.stud.register.Register;
import java.time.format.DateTimeParseException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

class RegisterTest {
Register register;
  TrainDeparture trainDeparture;
  @BeforeEach
  void setUp() {
     register = new Register();
     trainDeparture = new TrainDeparture(LocalTime.parse("12:00"), "L1", 1, "Oslo", 1, LocalTime.parse("00:00"));
    TrainDeparture trainDeparture1 = new TrainDeparture(LocalTime.parse("15:00"), "L2", 2, "Oslo", 2, LocalTime.parse("00:10"));
    register.addTrainDeparture(trainDeparture);
    register.addTrainDeparture(trainDeparture1);
  }

  @Test
  void getTrainDeparturesPositive() {
    assertEquals(2, register.getTrainDepartures().size());
  }
  @Test
  void getTrainDeparturesNegativ() {
    register.removeTrainDepartureByTime("19:00");

    try{
      register.getTrainDepartures();
      fail("Should throw exception");

    } catch (IllegalArgumentException e) {
      assertEquals("Set is empty", e.getMessage());
    }
  }


  @Test
  void addTrainDeparturePositive() {
    register.addTrainDeparture(new TrainDeparture(LocalTime.parse("18:00"), "L3", 3, "Oslo", 5, LocalTime.parse("01:00")));
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
    assertEquals(  trainDeparture , register.getTrainDepartureWithNumber(1).get(0));
  }
  @Test
  void getTrainDepartureWithNumberNegative() {
   try{
     register.getTrainDepartureWithNumber(-1);
     fail("Should throw exception");
   } catch (IllegalArgumentException e) {
     assertEquals("Train number should be positive", e.getMessage());
   }
  }

  @Test
  void getTrainDepartureWithDestinationPositive() {
    List<TrainDeparture> departures = register.getTrainDepartureWithDestination("Oslo");
    assertFalse(departures.isEmpty());
    assertEquals("Oslo", departures.get(0).getDestination());
  }
  @Test
  void getTrainDepartureWithDestinationNegative() {
    try {
      register.getTrainDepartureWithDestination("Unknown");
      fail("Should throw exception for unknown destination");
    } catch (IllegalArgumentException e) {
      assertEquals("Destination does not exist", e.getMessage());
    }
  }
  @Test
  void getTrainDepartureWithDepartureTimePositive() {
    List<TrainDeparture> departures = register.getTrainDepartureWithDepartureTime("12:00");
    assertFalse(departures.isEmpty());
    assertEquals(LocalTime.parse("12:00"), departures.get(0).getDepartureTime());
  }
  @Test
  void getTrainDepartureWithDepartureTimeNegative() {
    try {
      register.getTrainDepartureWithDepartureTime("25:00");
      fail("Should throw exception for invalid time");
    } catch (IllegalArgumentException | DateTimeParseException e) {
      assertEquals("Text '25:00' could not be parsed: Invalid value for HourOfDay (valid values 0 - 23): 25", e.getMessage());
    }
  }

  @Test
  void removeTrainDeparturesPositive() {
    assertEquals(2, register.getTrainDepartures().size());
    register.removeTrainDepartureByTime("14:00");
    // Expecting that after removal, only one train departure remains
    assertEquals(1, register.getTrainDepartures().size());
  }

  @Test
  void testRemoveTrainDeparturesNegative() {
    register.removeTrainDepartureByTime("19:00"); // This should remove all departures as they are before 19:00
    try {
      register.getTrainDepartures();
      fail("Should throw exception because the set is now empty");
    } catch (IllegalArgumentException e) {
      assertEquals("Set is empty", e.getMessage());
    }
  }

  @Test
  void removeTrainDeparturesByDestinationPositive() {
    assertEquals(2, register.getTrainDepartures().size());
    register.removeTrainDeparturesByDestination("Oslo", 0);
    // Expecting that after removal, only one train departure remains
    assertEquals(1, register.getTrainDepartures().size());
  }
  @Test
  void removeTrainDeparturesByDestinationNegative() {
    try {
      register.removeTrainDeparturesByDestination("Unknown", 0);
      fail("Should throw exception because the destination is unknown");
    } catch (IllegalArgumentException e) {
      assertEquals("Destination does not exist", e.getMessage());
    }
  }
  @Test
  void sortTrainDepartures() {
    List<TrainDeparture> sortedDepartures = register.sortTrainDepartures();
    assertFalse(sortedDepartures.isEmpty());
    assertTrue(sortedDepartures.get(0).getDepartureTime().isBefore(sortedDepartures.get(1).getDepartureTime()));
  }





  @Test
  void addDuplicateTrainDepartureSameTimeAndTrack() {
    assertThrows(IllegalArgumentException.class, () ->
        register.addTrainDeparture(new TrainDeparture(LocalTime.parse("12:00"), "L1", 3, "Bergen", 1, LocalTime.parse("00:00"))));
  }

  @Test
  void addDuplicateTrainDepartureSameTimeAndNumber() {
    assertThrows(IllegalStateException.class, () ->
        register.addTrainDeparture(new TrainDeparture(LocalTime.parse("12:00"), "L3", 1, "Bergen", 4, LocalTime.parse("00:00"))));
  }

  @Test
  void getTrainDepartureWithInvalidNumber() {
    assertThrows(IllegalArgumentException.class, () -> register.getTrainDepartureWithNumber(-1));
  }

  @Test
  void getTrainDepartureWithInvalidDestination() {
    assertThrows(IllegalArgumentException.class, () -> register.getTrainDepartureWithDestination("NonExistent"));
  }

  @Test
  void getTrainDepartureWithInvalidTime() {
    assertThrows(DateTimeParseException.class, () -> register.getTrainDepartureWithDepartureTime("24:00"));
  }

  @Test
  void getTrainDepartureWithInvalidTrack() {
    assertThrows(IllegalArgumentException.class, () -> register.getTrainDepartureWithTrack(-1));
  }

  @Test
  void getTrainDepartureWithInvalidLane() {
    assertThrows(IllegalArgumentException.class, () -> register.getTrainDepartureWithLane("NonExistentLane"));
  }

  @Test
  void getTrainDepartureWithInvalidDelay() {
    assertThrows(IllegalArgumentException.class, () -> register.getTrainDepartureWithDelay("99:99"));
  }

  @Test
  void removeTrainDepartureByInvalidNumber() {
    try
    {
        register.removeTrainDepartureByNumber(-1, 0);
        fail("Should throw exception");
        } catch (IllegalArgumentException e) {
        assertEquals("Train number needs to be a positive number", e.getMessage());
    }
  }

  @Test
  void removeTrainDepartureByInvalidTrack() {
    try
    {
        register.removeTrainDeparturesByTrack(-1, 0);
        fail("Should throw exception");
        } catch (IllegalArgumentException e) {
        assertEquals("Track number does not exist", e.getMessage());
    }
  }

  @Test
  void removeTrainDepartureByInvalidLane() {
    try
    {
        register.removeTrainDeparturesByLane("Lane does not exist", 0);
        fail("Should throw exception");
        } catch (IllegalArgumentException e) {
        assertEquals("Lane does not exist", e.getMessage());
    }
  }

  @Test
  void removeTrainDepartureByInvalidDestination() {
    try
    {
        register.removeTrainDeparturesByDestination("Destination does not exist", 0);
        fail("Should throw exception");
        } catch (IllegalArgumentException e) {
        assertEquals("Destination does not exist", e.getMessage());
    }
  }

  @Test
  void sortEmptyTrainDepartures() {
    register = new Register(); // Resetting the register
    List<TrainDeparture> sortedDepartures = register.sortTrainDepartures();
    assertTrue(sortedDepartures.isEmpty());
  }



  @Test
  void filterDeparturesByInvalidTime() {
    try{
      register.filterDeparturesByTime("25:00");
      fail("Should throw exception for invalid time");
    } catch (IllegalArgumentException | DateTimeParseException e) {
      assertEquals("Text '25:00' could not be parsed: Invalid value for HourOfDay (valid values 0 - 23): 25", e.getMessage());
    }
  }

  @Test
  void filterDeparturesByInvalidTrack() {
    assertThrows(IllegalArgumentException.class, () -> register.filterDeparturesByTrack(-1));
  }

  @Test
  void filterDeparturesByInvalidLine() {
    assertThrows(IllegalArgumentException.class, () -> register.filterDeparturesByLine("NonExistentLine"));
  }

  @Test
  void filterDeparturesByInvalidDestination() {
    assertThrows(IllegalArgumentException.class, () -> register.filterDeparturesByDestination("NonExistentDestination"));
  }

  @Test
  void filterDeparturesByInvalidDelay() {
    assertThrows(IllegalArgumentException.class, () -> register.filterDeparturesByDelay("99:99"));
  }

  @Test
  void getLaneMapEmpty() {
    register = new Register(); // Resetting the register
    assertThrows(IllegalArgumentException.class, register::getLaneMap);
  }

  @Test
  void getLaneListEmpty() {
    register = new Register(); // Resetting the register
    assertThrows(NullPointerException.class, register::getLaneList);
  }
}
