
package models;

public class WaterData {
    private String time;
    private String station;
    private double pH;
    private double dissolvedOxygen; // DO (Dissolved Oxygen)
    private double temperature;
    private double salinity;

    // Constructor
    public WaterData(String time, String station, double pH, double dissolvedOxygen, double temperature,
            double salinity) {
        this.time = time;
        this.station = station;
        this.pH = pH;
        this.dissolvedOxygen = dissolvedOxygen;
        this.temperature = temperature;
        this.salinity = salinity;
    }

    // Getters
    public String getTime() {
        return time;
    }

    public String getStation() {
        return station;
    }

    public double getpH() {
        return pH;
    }

    public double getDissolvedOxygen() {
        return dissolvedOxygen;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getSalinity() {
        return salinity;
    }

    // toString()
    @Override
    public String toString() {
        return "WaterData{" +
                "time='" + time + '\'' +
                ", station='" + station + '\'' +
                ", pH=" + pH +
                ", dissolvedOxygen=" + dissolvedOxygen +
                ", temperature=" + temperature +
                ", salinity=" + salinity +
                '}';
    }
}
