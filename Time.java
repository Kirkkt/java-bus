public class Time {
  private int minutes;

  public Time(String string) {
    int offset = 0;
    if (string.endsWith("PM")) {
      offset = 720;
    }
    if (string.startsWith("12")) {
      offset -= 720;
    }
    string = string.substring(0, string.length() - 2);
    minutes = offset +
      Integer.parseInt(string.split(":")[0]) * 60 +
      Integer.parseInt(string.split(":")[1]);
  }

  public int getMinutes() {
    return minutes;
  }

  @Override
  public String toString() {
    String postfix = "";
    if (minutes < 720) {
      postfix = "AM";
    } else {
      postfix = "PM";
    }
    String hour = "" + (minutes % 720) / 60;
    if (hour.equals("0")) {
      hour = "12";
    } else if (hour.length() == 1) {
      hour = "0" + hour;
    }
    String minute = "" + (minutes % 60);
    if (minute.length() == 1) {
      minute = "0" + minute;
    }
    return hour + ":" + minute + postfix;
  }
}
