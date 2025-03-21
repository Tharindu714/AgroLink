package DTO;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "sensor_data") // Table name in the database
public class SensorDataDTO implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float temperature;
    private float humidity;
    private float soil_moisture;
    private String timestamp;
    
    public SensorDataDTO(){
        
    }

    // Constructor
    public SensorDataDTO(float temperature, float humidity, float soil_moisture, String timestamp) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.soil_moisture = soil_moisture;
        this.timestamp = timestamp;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getSoil_moisture() {
        return soil_moisture;
    }

    public void setSoil_moisture(float soil_moisture) {
        this.soil_moisture = soil_moisture;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
