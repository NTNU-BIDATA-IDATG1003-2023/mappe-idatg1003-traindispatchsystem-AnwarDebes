package edu.ntnu.stud;

import java.util.List;
import java.util.Map;

public  class CheckValid {




  public static Boolean checkValidString(String string, String exceptionMessage) {
	if 	(string == null || string.isEmpty() || string.isBlank()){
	  throw new IllegalArgumentException(exceptionMessage);
	}
	return true;
  }
  public static Boolean checkValidInt(int number, String exceptionMessage) {
	if(number < 0) {
	  throw new IllegalArgumentException(exceptionMessage);
	}
	return true;
  } public static Boolean checkValidInt(int number) {
	return (number> 0);
  }
  public static Boolean checkValidTrackNumber(int number, String exceptionMessage) {
	if(number < -1) {
	  throw new IllegalArgumentException(exceptionMessage);
	}
	return true;
  }

  public static Boolean checkValidTime(String time, String exceptionMessage) {
	if(!time.matches("(2[0-3]|[01][0-9]):[0-5][0-9]")){
	  throw new IllegalArgumentException(exceptionMessage);
	}
	return 	true;
  }
  public  static boolean checkMAp(Map<Integer,TrainDeparture> map) {

	if (map.isEmpty() || map == null) {
	  throw new IllegalArgumentException("List is empty");
	}
	return true;
  }

}
