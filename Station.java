public class Station {
  public static final int HOME = 0;
  public static final int ECR_WOLFE = 1;
  public static final int SC_TRANSIT_CENTER = 2;
  public static final int WORK = 3;

  public int station;

  public Station(String string) {
    if (string.contains("Camp")) {
      station =  HOME;
    } else if (string.contains("Wolfe")) {
      station = ECR_WOLFE;
    } else if (string.contains("Cali")) {
      station = WORK;
    } else if (string.contains("Transit Center")) {
      station = SC_TRANSIT_CENTER;
    } else {
      System.err.println("uncognizable station string: " + string);
      station = -1;
    }
  }

  @Override
  public String toString() {
    switch(station) {
      case HOME:
        return "Home";
      case WORK:
        return "Work";
      case ECR_WOLFE:
        return "ECR & Wolfe";
      case SC_TRANSIT_CENTER:
        return "SC Transit Center";
      default:
        return "";
    }
  }
}
