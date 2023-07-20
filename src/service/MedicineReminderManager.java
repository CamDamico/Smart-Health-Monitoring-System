package service;

import model.MedicineReminder;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The MedicineReminderManager class should have methods to add reminders, get reminders
 *  1. for a specific user, and
 *  2. get reminders that are DUE for a specific user.
 *
 *  You'll need to integrate this class with your application and database logic to
 *  1. store,
 *  2. update, and
 *  3. delete reminders as needed.
 */

public class MedicineReminderManager {

    public MedicineReminderManager() {}

    public void addReminder(Connection conn, MedicineReminder reminder) {
        PreparedStatement statement = null;
         try {
             String query = "INSERT INTO medicine_reminders (user_id, medicine_name, dosage, schedule, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?)";
             statement = conn.prepareStatement(query);
             statement.setInt(1, reminder.getUserId());
             statement.setString(2, reminder.getMedicineName());
             statement.setString(3, reminder.getDosage());
             statement.setString(4, reminder.getSchedule());
             statement.setDate(5, reminder.getStartDate());
             statement.setDate(6, reminder.getEndDate());

             int rowsAffected = statement.executeUpdate();
             if (rowsAffected > 0) {
                 System.out.println("Reminder has been added for user: " + reminder.getUserId());
                 System.out.println();
             } else {
                 System.out.println("Reminder could not be added.");
                 System.out.println();
             }
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
    }

    public ResultSet getRemindersForUser(Connection conn, String first_name, String last_name) {
        ResultSet rs = null;
        int reminderCount = 1;

        try {
            String query = "SELECT Mr.*, u.first_name,u.last_name,u.is_doctor\n" +
                    "from medicine_reminders Mr\n" +
                    "INNER Join users u ON Mr.user_id = u.id\n" +
                    "WHERE u.first_name = ? and u.last_name = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            rs = preparedStatement.executeQuery();

            System.out.println("Medicine Reminders for Patient: " + first_name + " " + last_name);
            System.out.println("--------------------------------");

            while (rs.next()) {
                System.out.println("Reminder # " + reminderCount++);
                int medRemId = rs.getInt("id");
                int userId = rs.getInt("user_id");
                String MedName = rs.getString("medicine_name");
                String Dos = rs.getString("dosage");
                String Schedule = rs.getString("schedule");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");

                if(endDate.toLocalDate().isBefore(LocalDateTime.now().toLocalDate())){
                    System.out.println("Medicine Reminder Has Expired.");
                    System.out.println();
                } else {
                    System.out.println("Medicine Reminder for User: " + first_name + " " + last_name);
                    System.out.println("User Id: " + userId);
                    System.out.println("Medicine Name: " + MedName);
                    System.out.println("Dosage: " + Dos);
                    System.out.println("Schedule: " + Schedule);
                    System.out.println("Start Date: " + startDate);
                    System.out.println("End Date: " + endDate);
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
