import java.io.*;
import java.util.*;

public class Util {
  public static final int TRANSFER_MINUTES = 5;

  public static ArrayList<Bus> getBuses(String fileName) {
    ArrayList<Bus> buses = new ArrayList<Bus>();
    try {
      FileReader fr = new FileReader(fileName);
      BufferedReader br = new BufferedReader(fr);
      String line;
      line = br.readLine();
      Station startStation = new Station(line.split(",")[0].trim());
      Station arriveStation = new Station(line.split(",")[1].trim());
      while ((line = br.readLine()) != null) {
        Time startTime  = new Time(line.split(",")[0].trim());
        Time arriveTime  = new Time(line.split(",")[1].trim());
        buses.add(new Bus().init(startTime, arriveTime, startStation, arriveStation,
          Integer.parseInt(line.split(",")[2].trim())));
      }
    } catch (Exception e) {
      System.err.println(e);
    }
    return buses;
  }

  public static ArrayList<MergedBus> merge(ArrayList<Bus> firstRoute, ArrayList<Bus> secondRoute) {
    return merge(firstRoute, secondRoute, TRANSFER_MINUTES, new ArrayList<MergedBus>());
  }

  public static ArrayList<MergedBus> merge(ArrayList<Bus> firstRoute, ArrayList<Bus> secondRoute,
      int transferMinutes) {
    return merge(firstRoute, secondRoute, transferMinutes, new ArrayList<MergedBus>());
  }

  public static ArrayList<MergedBus> merge(ArrayList<Bus> firstRoute, ArrayList<Bus> secondRoute,
      ArrayList<MergedBus> mergedBuses) {
    return merge(firstRoute, secondRoute, TRANSFER_MINUTES, mergedBuses);
  }

  public static ArrayList<MergedBus> merge(ArrayList<Bus> firstRoute, ArrayList<Bus> secondRoute,
      int transferMinutes, ArrayList<MergedBus> mergedBuses) {
    String result = "";

    Bus[] firstRouteArray = (Bus[]) firstRoute.toArray(new Bus[firstRoute.size()]);
    Bus[] secondRouteArray = (Bus[]) secondRoute.toArray(new Bus[secondRoute.size()]);
    for (int i = 0, j = 0; i < firstRouteArray.length && j < secondRouteArray.length;) {
      Bus first = firstRouteArray[i];
      Bus second = secondRouteArray[j];
      int t1 = first.arriveTime.getMinutes() + transferMinutes;
      int t2 = second.startTime.getMinutes();
      if (t1 > t2) {
        j++;
        continue;
      }
      mergedBuses.add(new MergedBus().init(first, second));
      i++;
    }
    return mergedBuses;
  }

  public static ArrayList<MergedBus> sortByDurations(ArrayList<MergedBus> input) {
    MergedBus[] output = new MergedBus[input.size()];
    int ii = 0;
    for (MergedBus bus : input) {
      output[ii++] = bus;
    }
    for (int i = 0; i < output.length; i++) {
      for (int j = i + 1; j < output.length; j++) {
        if (output[i].getDuration() > output[j].getDuration()) {
          MergedBus temp = output[i];
          output[i] = output[j];
          output[j] = temp;
        }
      }
    }
    return new ArrayList<MergedBus>(Arrays.asList(output));
  }
}
