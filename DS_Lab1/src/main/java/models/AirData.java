package models;

public class AirData {
    private String time;
    private String station;
    private double temperature;
    private double moisture;
    private double light;
    private double totalRainfall;
    private double rainfall;
    private double windDirection; // Đổi từ String → double
    private double pm25;
    private double pm10;
    private double co;
    private double nox;
    private double so2;

    // Constructor
    public AirData(String time, String station,double temperature, double moisture, double light, double totalRainfall, double rainfall,
            double windDirection, double pm25, double pm10, double co, double nox, double so2) {
        this.time = time;
        this.station = station;
        this.temperature = temperature ;
        this.moisture = moisture;
        this.light = light;
        this.totalRainfall = totalRainfall;
        this.rainfall = rainfall;
        this.windDirection = windDirection;
        this.pm25 = pm25;
        this.pm10 = pm10;
        this.co = co;
        this.nox = nox;
        this.so2 = so2;
    }

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


    public double getLight() {
        return light;
    }

    public double getTotalRainfall() {
        return totalRainfall;
    }

    public double getRainfall() {
        return rainfall;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public double getPm25() {
        return pm25;
    }

    public double getPm10() {
        return pm10;
    }

    public double getCo() {
        return co;
    }

    public double getNox() {
        return nox;
    }

    public double getSo2() {
        return so2;
    }
    
    // toString()
    @Override
    public String toString() {
        return "AirData{" +
                "time='" + time + '\'' +
                ", station='" + station + '\'' +
                ", temperature'" + temperature + '\'' +
                ", moisture=" + moisture +
                ", light=" + light +
                ", totalRainfall=" + totalRainfall +
                ", rainfall=" + rainfall +
                ", windDirection=" + windDirection +
                ", pm25=" + pm25 +
                ", pm10=" + pm10 +
                ", co=" + co +
                ", nox=" + nox +
                ", so2=" + so2 +
                '}';
    }
}
