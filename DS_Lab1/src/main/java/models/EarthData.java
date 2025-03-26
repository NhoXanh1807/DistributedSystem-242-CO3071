package models;

public class EarthData {
    private String time;
    private String station;
    private double moisture;
    private double temperature;
    private double salinity;
    private double pH;
    private double waterLeaf;
    private double waterLevel;
    private double waterRoot;
    private double voltage;

    // Constructor
    public EarthData(String time, String station, double moisture, double temperature, double salinity, double pH,
            double waterLeaf, double waterLevel, double waterRoot, double voltage) {
        this.time = time;
        this.station = station;
        this.moisture = moisture;
        this.temperature = temperature;
        this.salinity = salinity;
        this.pH = pH;
        this.waterLeaf = waterLeaf;
        this.waterLevel = waterLevel;
        this.waterRoot = waterRoot;
        this.voltage = voltage;
    }

    // Getters
    public String getTime() {
        return time;
    }

    public String getStation() {
        return station;
    }

    public double getMoisture() {
        return moisture;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getSalinity() {
        return salinity;
    }

    public double getpH() {
        return pH;
    }

    public double getWaterLeaf() {
        return waterLeaf;
    }

    public double getWaterLevel() {
        return waterLevel;
    }

    public double getWaterRoot() {
        return waterRoot;
    }

    public double getVoltage() {
        return voltage;
    }

    // toString()
    @Override
    public String toString() {
        return "EarthData{" +
                "time='" + time + '\'' +
                ", station='" + station + '\'' +
                ", moisture=" + moisture +
                ", temperature=" + temperature +
                ", salinity=" + salinity +
                ", pH=" + pH +
                ", waterLeaf=" + waterLeaf +
                ", waterLevel=" + waterLevel +
                ", waterRoot=" + waterRoot +
                ", voltage=" + voltage +
                '}';
    }
}
