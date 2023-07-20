package model;

import java.sql.*;

public class HealthDataDao {
    public void createHealthData(Connection conn, HealthData healthData) {
        PreparedStatement statement = null;

        try {
            String query = "INSERT INTO health_data (user_id, weight, height, steps, heart_rate, date) VALUES (?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(query);
            statement.setInt(1, healthData.getUserId());
            statement.setDouble(2, healthData.getWeight());
            statement.setDouble(3, healthData.getHeight());
            statement.setInt(4, healthData.getSteps());
            statement.setInt(5, healthData.getHeartRate());
            statement.setDate(6, healthData.getDate());

            int rowsAffected = statement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Health Data has been created.");
                System.out.println();
            } else {
                System.out.println("Health Data could not be created.");
                System.out.println();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet getHealthDataById(Connection conn, int id) {
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM health_data WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int HealthDataId = rs.getInt("id");
                int userId = rs.getInt("user_id");
                double weight = rs.getDouble("weight");
                double height = rs.getDouble("height");
                int steps = rs.getInt("steps");
                int heartRate = rs.getInt("heart_rate");
                Date date = rs.getDate("date");

                System.out.println();
                System.out.println("Health Data Id: " + HealthDataId);
                System.out.println("User Id: " + userId);
                System.out.println("Weight: " + weight);
                System.out.println("Height: " + height);
                System.out.println("Steps: " + steps);
                System.out.println("Heart Rate: " + heartRate);
                System.out.println("Date: " + date);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    public ResultSet getHealthDataByUserId(Connection conn, int user_id) {
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM health_data WHERE user_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, user_id);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int HealthDataId = rs.getInt("id");
                int userId = rs.getInt("user_id");
                double weight = rs.getDouble("weight");
                double height = rs.getDouble("height");
                int steps = rs.getInt("steps");
                int heartRate = rs.getInt("heart_rate");
                Date date = rs.getDate("date");

                System.out.println();
                System.out.println("Health Data Id: " + HealthDataId);
                System.out.println("User Id: " + userId);
                System.out.println("Weight: " + weight);
                System.out.println("Height: " + height);
                System.out.println("Steps: " + steps);
                System.out.println("Heart Rate: " + heartRate);
                System.out.println("Date: " + date);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void updateHealthData(Connection conn, int id, int user_id, double weight, double height, int steps, int heart_rate, Date date) {
        PreparedStatement statement = null;

        try {
            String query = "update health_data set user_id = ?, weight = ?, height = ?, steps = ?, heart_rate = ?, date = ? WHERE id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, user_id);
            statement.setDouble(2, weight);
            statement.setDouble(3, height);
            statement.setInt(4, steps);
            statement.setInt(5, heart_rate);
            statement.setDate(6, date);
            statement.setInt(7, id);

            int rowsAffected = statement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Health Data has been updated!");
                System.out.println();
            } else {
                System.out.println("Health Data could not be updated.");
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void deleteHealthData(Connection conn, int health_dataId) {
        PreparedStatement statement = null;

        try {
            String query = "DELETE FROM users WHERE id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, health_dataId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Health Data has been deleted!");
                System.out.println();
            } else {
                System.out.println("Health Data could not be deleted.");
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}