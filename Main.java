import java.util.*;

public class Main {
  public static void main(String[] args) {
    getBestRoutes("data/weekdays/", true);
    getBestRoutes("data/weekdays/", false);
    getBestRoutes("data/saturdays/", true);
    getBestRoutes("data/saturdays/", false);
    getBestRoutes("data/sundays/", true);
    getBestRoutes("data/sundays/", false);
  }

  private static void getBestRoutes(String prefix, boolean fromHomeToWork) {
    prefix += fromHomeToWork ? "from-home/" : "from-work/";
    System.out.println(prefix);
    ArrayList<Bus> b22w26 = Util.getBuses(
      prefix + "22-and-522-" + (fromHomeToWork ? "to-work" : "from-work") + "-with-26.csv"
    );
    ArrayList<Bus> b22w60 = Util.getBuses(
      prefix + "22-and-522-" + (fromHomeToWork ? "to-work" : "from-work") + "-with-60.csv"
    );
    ArrayList<Bus> b26 = Util.getBuses(
      prefix + "26-" + (fromHomeToWork ? "from-home" : "to-home") + ".csv"
    );
    ArrayList<Bus> b60 = Util.getBuses(
      prefix + "60-" + (fromHomeToWork ? "from-home" : "to-home") + ".csv"
    );
    ArrayList<MergedBus> mergedBuses;
    if (fromHomeToWork) {
      mergedBuses = Util.merge(b26, b22w26, 7);
      Util.merge(b60, b22w60, mergedBuses);
    } else {
      mergedBuses = Util.merge(b22w26, b26, 7);
      Util.merge(b22w60, b60, mergedBuses);
    }
    ArrayList<MergedBus> sortedMergedBuses = Util.sortByDurations(mergedBuses);
    for (MergedBus bus : sortedMergedBuses) {
      System.out.println(bus);
    }
    System.out.println("");
  }
}
