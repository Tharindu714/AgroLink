package DAO;

import DTO.SensorDataDTO;
import entity.SensorData;
import model.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SensorDataDAO {

public void save(SensorDataDTO sensorDataDTO) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = null;

    try {
        transaction = session.beginTransaction();

        // Map DTO to Entity
        SensorData sensorData = new SensorData();
        sensorData.setTemperature(sensorDataDTO.getTemperature());
        sensorData.setHumidity(sensorDataDTO.getHumidity());
        sensorData.setsoil_moisture(sensorDataDTO.getSoil_moisture());
        
        // Handle null timestamps
        if (sensorDataDTO.getTimestamp() == null) {
            sensorData.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
        } else {
            sensorData.setTimestamp(java.sql.Timestamp.valueOf(sensorDataDTO.getTimestamp()));
        }

        session.save(sensorData); // Save entity to the database
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback(); // Rollback in case of failure
        }
        e.printStackTrace();
    } finally {
        session.close(); // Ensure session is closed
    }
}


    public SensorDataDTO getLatestData() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        SensorDataDTO latestData = (SensorDataDTO) session.createQuery("FROM SensorDataDTO ORDER BY id DESC")
                .setMaxResults(1)
                .uniqueResult();
        session.close();
        return latestData;
    }
}
