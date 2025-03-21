package controller;

import DTO.SensorDataDTO;
import DAO.SensorDataDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.google.gson.Gson; // You'll need Gson to convert data to JSON
import java.io.BufferedReader;

@WebServlet("/api/sensors/data")
public class SensorController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Handle GET request to fetch sensor data
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SensorDataDAO sensorDataDAO = new SensorDataDAO();
        SensorDataDTO latestData = sensorDataDAO.getLatestData(); // Fetch latest data from DB

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(latestData);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse); // Send JSON data to the frontend
        // Set the response content type and send the JSON data

    }

    // Handle POST request to add new sensor data
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        String jsonData = stringBuilder.toString();
        Gson gson = new Gson();
        try {
            SensorDataDTO sensorData = gson.fromJson(jsonData, SensorDataDTO.class);

            // Handle missing timestamp
            if (sensorData.getTimestamp() == null) {
                sensorData.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()).toString());
            }

            // Save the data to the database
            SensorDataDAO sensorDataDAO = new SensorDataDAO();
            sensorDataDAO.save(sensorData);

            System.out.println("Received JSON: " + jsonData);

            // Respond to ESP32
            response.setContentType("application/json");
            response.getWriter().write("{\"status\": \"success\"}");
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

}
