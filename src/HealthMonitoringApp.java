import client.DatabaseConnection;
import model.*;
import service.MedicineReminderManager;
import service.RecommendationSystem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class HealthMonitoringApp {

    public static void main(String[] args) throws SQLException {

        DatabaseConnection client = new DatabaseConnection();
        Connection conn = client.connect_to_db("Java_Sprint-2", "postgres", "Keyin2023");

        UserDao UD = new UserDao();
        HealthDataDao HDA = new HealthDataDao();
        DoctorPortalDao DPD = new DoctorPortalDao();
        MedicineReminderManager MedReminder = new MedicineReminderManager();
        RecommendationSystem RecSystem = new RecommendationSystem();


        // Create a User
        User user1 = new User(1, "Cameron", "D'Amico", "cameron@email.com", "cam123", false);
        User user2 = new User(5, "Nathan", "Greene", "nathan@email.com", "nathan123", false);
        User user3 = new User(6, "Kennedy", "Azupwah", "kennedy@email.com", "kennedy123", false);
        User user4 = new User(7, "Jon", "Snow", "jon@email.com", "jon123", false);
//        UD.createUser(conn, user4);

        // Logging In
        login("cameron@email.com", "cam123");
        LoggingIn();

        // Get user by id
        UD.getUserById(conn, 6);

        // Create Health Data
        HealthData HD1 = new HealthData(1, 1, 200, 67.2, 2013, 98, Date.valueOf("2023-04-10"));
        HealthData HD2 = new HealthData(2, 7, 175, 65, 3230, 88, Date.valueOf("2023-04-10"));
        HealthData HD3 = new HealthData(3, 6, 175, 66, 1765, 73, Date.valueOf("2023-04-10"));
        HealthData HD4 = new HealthData(4, 5, 205, 63, 2430, 92, Date.valueOf("2023-04-10"));
//        HDA.createHealthData(conn, HD4);

        // Get health data by patient id

        DPD.getHealthDataByPatientId(conn, 1);
        DPD.getHealthDataByPatientId(conn, 5);

        // Get patient by doctor id

        DPD.getPatientsByDoctorId(conn, 3);
        DPD.getPatientsByDoctorId(conn, 4);

        // Create a Doctor
        Doctor doctor1 = new Doctor(1, "Gnome", "Johnson", "gnome@email.com", "gnome123", true, "226734", "Family");
        Doctor doctor2 = new Doctor(2, "Jordan", "Kelloway", "jordan@email.com", "jordan123", true, "226735", "Surgeon");
//        DPD.createDoctor(conn, doctor2);

        // Get Doctor by Id
        DPD.getDoctorById(conn, 4);
        DPD.getDoctorById(conn, 3);

        // Book Appointment
//        DPD.bookAppointment(conn, 3, 1, Date.valueOf("2023-05-23"));
//        DPD.bookAppointment(conn, 4, 5, Date.valueOf("2023-06-03"));
//        DPD.bookAppointment(conn, 4, 6, Date.valueOf("2023-04-28"));
//        DPD.bookAppointment(conn, 3, 7, Date.valueOf("2023-07-21"));

        // Get appointment by doctor id
        DPD.getAppointmentByDoctorName(conn, "Jordan", "Kelloway");
        DPD.getAppointmentByDoctorName(conn, "Gnome", "Johnson");

        // Adding medicine reminder
        MedicineReminder MedRem1 = new MedicineReminder(1, 1, "Atorvastatin", "10mg", "1 Tablet every night before bed.", Date.valueOf("2023-04-25"), Date.valueOf("2023-06-25"));
        MedicineReminder MedRem2 = new MedicineReminder(2, 6, "Cetirizine", "15mg", "1 Tablet every morning.", Date.valueOf("2023-05-04"), Date.valueOf("2023-07-04"));
        MedicineReminder MedRem3 = new MedicineReminder(3, 1, "Desloratadine", "10mg", "1 Tablet every morning.", Date.valueOf("2023-04-24"), Date.valueOf("2023-06-24"));
        MedicineReminder MedRem4 = new MedicineReminder(4, 5, "Penicillin", "250mg", "1 Tablet every 8-12 Hours!", Date.valueOf("2023-02-12"), Date.valueOf("2023-03-02"));
//        MedReminder.addReminder(conn, MedRem4);

        // Get reminder by user
        MedReminder.getRemindersForUser(conn, "Cameron", "D'Amico");
        MedReminder.getRemindersForUser(conn, "Kennedy", "Azupwah");
        MedReminder.getRemindersForUser(conn, "Nathan", "Greene");

        // Create Recommendation
        Recommendation Rec1 = new Recommendation(1, 1, "Make sure to stretch before physical activity! This will help decrease risk of injury.", Date.valueOf("2023-04-20"));
        Recommendation Rec2 = new Recommendation(2, 1, "If your heart rate goes above 100, try to relax with deep breathing.", Date.valueOf("2023-04-21"));
        Recommendation Rec3 = new Recommendation(3, 6, "To maintain a healthy amount of physical activity, try and walk at least 1500 steps per day!", Date.valueOf("2023-04-22"));
        Recommendation Rec4 = new Recommendation(4, 7, "If your heart rate goes below 60, try to up the amount of physical actiity you do in a day!", Date.valueOf("2023-04-23"));
//        RecSystem.createRecommendations(conn, Rec4);

        // Get Recommendation by User
        RecSystem.getRecommendationByUser(conn, "Cameron", "D'Amico");
        RecSystem.getRecommendationByUser(conn, "Kennedy", "Azupwah");
        RecSystem.getRecommendationByUser(conn, "Jon", "Snow");
    }

    public static boolean login(String email, String password){
        UserDao UD = new UserDao();
        DatabaseConnection client = new DatabaseConnection();
        Connection conn = client.connect_to_db("Java_Sprint-2", "postgres", "Keyin2023");

        UD.getUserByEmail(conn, email);
        if (email != null){
            UD.verifyPassword(conn, email, password);
            return true;
        }
        return false;
    }

    public static void LoggingIn(){

        String email = "Kennedy@email.com";
        String password = "Kennedy123";

        boolean loggedIn = login(email, password);
        if (loggedIn){
            System.out.println();
            System.out.println("Successfully Logged In!");
            System.out.println();
        } else {
            System.out.println();
            System.out.println("Incorrect Email and/or Password. Please try again.");
            System.out.println();
        }
    }

}