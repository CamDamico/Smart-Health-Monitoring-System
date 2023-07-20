package service;

import model.HealthData;
import model.Recommendation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * In this basic version of the
 * RecommendationSystem class, complete the generateRecommendations to take a
 * HealthData object as input and generates recommendations based on the user's heart rate and step count.
 * You can also expand this class to include more health data analysis and generate more specific
 * recommendations based on the user's unique health profile
 * NOTE:
 * To integrate this class into your application, you'll need to pass the HealthData object to the generateRecommendations method
 * and store the generated recommendations in the recommendations table in the database.
 */

public class RecommendationSystem {

    public void createRecommendations(Connection conn, Recommendation recommendation){
        PreparedStatement statement = null;

        try{
            String query = "INSERT INTO recommendations (user_id, recommendation_text, date) VALUES (?, ?, ?)";
            statement = conn.prepareStatement(query);
            statement.setInt(1, recommendation.getUser_id());
            statement.setString(2, recommendation.getRecommendation_text());
            statement.setDate(3, recommendation.getDate());

            int rowsAffected = statement.executeUpdate();
            if(rowsAffected > 0) {
                System.out.println("Recommendation added for User with Id: " + recommendation.getUser_id());
                System.out.println();
            }else {
                System.out.println("Recommendation could not be added.");
                System.out.println();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet getRecommendationByUser(Connection conn, String first_name, String last_name) {
        int recommendationCount = 1;
        ResultSet rs = null;
        try {
            String query = "SELECT R.*, u.first_name,u.last_name,u.is_doctor FROM recommendations R INNER Join users u ON R.user_id = u.id WHERE u.first_name = ? and u.last_name = ?";
            PreparedStatement prepareStatement = conn.prepareStatement(query);
            prepareStatement.setString(1, first_name);
            prepareStatement.setString(2, last_name);

            rs = prepareStatement.executeQuery();

            System.out.println("----------------------------------");
            System.out.println("Recommendations For Patient:" + first_name + " " + last_name);
            while (rs.next()){
                System.out.println("Recommendation # " + recommendationCount++ );
                System.out.println("----------------------------------");
                int userId = rs.getInt("user_id");
                String Rec = rs.getString("recommendation_text");
                Date date = rs.getDate("date");
                String fname = rs.getString("first_name");
                String lname = rs.getString("last_name");

                System.out.println("Recommendation: " + Rec);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
