public class MergedBus extends Bus {
  public Bus firstBus = null;
  public Bus secondBus = null;

  public MergedBus init(Bus firstBus, Bus secondBus) {
    this.firstBus = firstBus;
    this.secondBus = secondBus;

    this.startTime = firstBus.startTime;
    this.arriveTime = secondBus.arriveTime;

    this.startStation = firstBus.startStation;
    this.arriveStation = secondBus.arriveStation;

    this.route = -1;
    return this;
  }

  public int getTransitTime() {
    return secondBus.startTime.getMinutes() - firstBus.arriveTime.getMinutes();
  }

  @Override
  public String toString() {
    return "(" + getDuration() + "): " + firstBus.toString() + " ==(" + getTransitTime() + ")=> " + secondBus.toString();
  }
}
