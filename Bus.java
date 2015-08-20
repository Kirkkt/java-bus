public class Bus {
  public Time startTime = null;
  public Time arriveTime = null;
  public Station startStation = null;
  public Station arriveStation = null;
  public int route = -1;

  public Bus init(Time startTime, Time arriveTime, Station startStation, Station arriveStation,
      int route) {
    this.startTime = startTime;
    this.arriveTime = arriveTime;
    this.startStation = startStation;
    this.arriveStation = arriveStation;
    this.route = route;
    return this;
  }

  public int getDuration() {
    int t = arriveTime.getMinutes() - startTime.getMinutes();
    if (t < - 22 * 60) {
      t += 24 * 60;
    }
    return t;
  }

  @Override
  public String toString() {
    return route + ": " + startTime.toString() +  " - " + arriveTime.toString();
  }
}
