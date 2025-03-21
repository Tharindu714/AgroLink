package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sensor_data")
public class SensorData implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float temperature;
    private float humidity;
    private float soil_moisture;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    // Getters and Setters
   public SensorData() {  //default constructor
        
    }

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

   
    public float getsoil_moisture() {
        return soil_moisture;
    }

   
    public void setsoil_moisture(float soil_moisture) {
        this.soil_moisture = soil_moisture;
    }

 
    public Date getTimestamp() {
        return timestamp;
    }

    
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
