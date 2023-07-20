package model;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class DoctorPortalDao {
    private UserDao userDao;
    private HealthDataDao healthDataDao;

    public DoctorPortalDao() {
        userDao = new UserDao();
        healthDataDao = new HealthDataDao();
    }

    public void createDoctor(Connection conn, Doctor doctor){
        PreparedStatement statement = null;
        String hashedPassword = BCrypt.hashpw(doctor.getPassword(), BCrypt.gensalt());

        try {
            String query = "INSERT INTO users (first_name, last_name, email, password, is_doctor, medicalLicenseNumber, specialization) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(query);
            statement = conn.prepareStatement(query);
            statement.setString(1, doctor.getFirstName());
            statement.setString(2, doctor.getLastName());
            statement.setString(3, doctor.getEmail());
            statement.setString(4, hashedPassword);
            statement.setBoolean(5, doctor.isDoctor());
            statement.setString(6, doctor.getMedicalLicenseNumber());
            statement.setString(7, doctor.getSpecialization());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Doctor has been created!");
                System.out.println();
            } else{
                System.out.println("Doctor can not be created.");
                System.out.println();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateDoctor(Connection conn, String last_name, String specialization, String password, int id) {
        PreparedStatement statement = null;
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        try {
            String query = "update users set last_name = ?, specialization = ?, password= ? where id = ? ";
            statement = conn.prepareStatement(query);
            statement.setString(1, last_name);
            statement.setString(2, specialization);
            statement.setString(3,hashedPassword);
            statement.setInt(4,id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Doctor has been updated!");
                System.out.println();
            } else {
                System.out.println("Doctor could not be updated.");
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getDoctorById(Connection conn, int doctorId) {
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM users WHERE is_doctor = true and id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);
            rs = preparedStatement.executeQuery();

            System.out.println("Doctor By ID");
            System.out.println("--------------------");

            while (rs.next()) {
                int userId = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                boolean isDoctor = rs.getBoolean("is_doctor");
                String med_lic_num = rs.getString("medicallicensenumber");
                String specialization = rs.getString("specialization");

                System.out.println("Id: " + userId);
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("Specialization: " + specialization);
                System.out.println("Medical License Number: " + med_lic_num);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getPatientsByDoctorId(Connection conn, int doctorId) {
        ResultSet rs = null;
        int patient = 1;

        try {
            String query = "SELECT * FROM users JOIN doctor_patient ON users.id = doctor_patient.patient_id WHERE doctor_patient.doctor_id =?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);
            rs = preparedStatement.executeQuery();

            System.out.println("List of patients for Doctor with Id: " + doctorId);
            System.out.println("-------------------------------------");

            while (rs.next()) {
                int userId = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                boolean isDoctor = rs.getBoolean("is_doctor");
                String doctor_id = rs.getString("doctor_id");
                String patient_id = rs.getString("patient_id");

                System.out.println();
                System.out.println("Patient # " + patient++);
                System.out.println();
                System.out.println("ID:  " + userId);
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("Email: " + email);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getHealthDataByPatientId(Connection conn, int patientId) {
        ResultSet rs = null;

        try {
            String query = "SELECT hd.*, u.first_name, u.last_name FROM health_data hd INNER JOIN users u ON hd.user_id = u.id WHERE u.id = ?;";
            PreparedStatement prepareStatement = conn.prepareStatement(query);
            prepareStatement.setInt(1, patientId);
            rs = prepareStatement.executeQuery();

            System.out.println("Health Data for patient: " + patientId);
            System.out.println("------------------------------");

            while (rs.next()) {
                int Id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                double weight = rs.getInt("weight");
                double height = rs.getInt("height");
                int steps = rs.getInt("steps");
                int heart_rate = rs.getInt("heart_rate");
                Date date = rs.getDate("date");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");

                System.out.println("Health Data Id: " + Id);
                System.out.println("User Id: " + user_id);
                System.out.println("First Name: " + first_name);
                System.out.println("Last Name: " + last_name);
                System.out.println("Weight: " + weight);
                System.out.println("Height: " + height);
                System.out.println("Steps: " + steps);
                System.out.println("Heart Rate: " + heart_rate);
                System.out.println("Date: " + date);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

   public void bookAppointment(Connection conn, int doctor_id, int patient_id, Date date){
        PreparedStatement statement = null;

        try {
            String query = "INSERT INTO appointments (doctor_id, patient_id, date) VALUES (?, ?, ?)";
            statement = conn.prepareStatement(query);
            statement.setInt(1, doctor_id);
            statement.setInt(2, patient_id);
            statement.setDate(3, date);

            int rowsAffected = statement.executeUpdate();
            if(rowsAffected > 0) {
                System.out.println("Appointment has been booked!");
                System.out.println();
            } else {
                System.out.println("Appointment could not be booked.");
                System.out.println();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getAppointmentByDoctorName(Connection conn, String first_name, String last_name){
        ResultSet rs = null;

        try {
            String query =  "SELECT a.*, p.first_name AS patient_first_name, p.last_name AS patient_last_name, d.first_name AS doctor_first_name, d.last_name AS doctor_last_name\n" +
                    "FROM appointments a\n" +
                    "INNER JOIN users p ON a.patient_id = p.id\n" +
                    "INNER JOIN users d ON a.doctor_id = d.id\n" +
                    "WHERE d.is_doctor = true\n" +
                    "and d.first_name = ? and d.last_name = ?";
            PreparedStatement prepareStatement = conn.prepareStatement(query);
            prepareStatement.setString(1, first_name);
            prepareStatement.setString(2, last_name);
            rs = prepareStatement.executeQuery();

            System.out.println("Appointments by Doctor: " + first_name + " " + last_name);
            System.out.println("------------------------------");

            while (rs.next()) {
                Date date = rs.getDate("date");
                String firstName = rs.getString("patient_first_name");
                String lastName = rs.getString("patient_last_name");

                System.out.println();
                System.out.println("Appointment Date: " + date);
                System.out.println("Patient Name: " + firstName + " " + lastName);
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}


