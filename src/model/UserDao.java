package model;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserDao {
    public void createUser(Connection conn, User user) {
        PreparedStatement statement = null;
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        try {
            String query = "INSERT INTO users (first_name, last_name, email, password, is_doctor) VALUES (?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(query);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, hashedPassword);
            statement.setBoolean(5, user.isDoctor());

            int rowsAffected = statement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("User has been created.");
                System.out.println();
            } else {
                System.out.println("User could not be added.");
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet getUserById(Connection conn, int id) {

        ResultSet rs = null;

        try {
            String query = "SELECT * FROM users WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            System.out.println("User by id: " + id);
            System.out.println("--------------------");

            while (rs.next()) {
                int userId = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                boolean isDoctor = rs.getBoolean("is_doctor");

                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("Email: " + email);
                System.out.println("Doctor: " + isDoctor);
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getUserByEmail(Connection conn, String usersEmail) {

        ResultSet rs = null;

        try {
            String query = "SELECT * FROM users WHERE email = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, usersEmail);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                boolean isDoctor = rs.getBoolean("is_doctor");

                System.out.println("User with email: " + email);
                System.out.println("------------------------------");
                System.out.println("ID: " + userId);
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("Doctor: " + isDoctor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void updateUser(Connection conn, User user) {

        PreparedStatement statement = null;
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        try {
            String query = "update users set first_name = ?, last_name = ?, password = ?, email= ? where id = ?";
            statement = conn.prepareStatement(query);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, hashedPassword);
            statement.setBoolean(5, user.isDoctor());

            int rowsAffected = statement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("User has been updated!");
            } else {
                System.out.println("User could not be updated.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(Connection conn, int usersId) {
        PreparedStatement statement = null;

        try {
            String query = "DELETE FROM users WHERE id = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, usersId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User has been deleted.");
            } else{
                System.out.println("User could not be deleted.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet verifyPassword (Connection conn, String usersEmail, String usersPassword){

        ResultSet rs = null;
        try {
            String query = "SELECT password FROM users WHERE email = ?";
            PreparedStatement prepareStatement = conn.prepareStatement(query);
            prepareStatement.setString(1, usersEmail);
            rs = prepareStatement.executeQuery();

            System.out.println();
            System.out.println("Verify Password for user with email: " + usersEmail);
            System.out.println("------------------------------");

            while (rs.next()) {
                String userPassword = rs.getString("password");

                boolean PasswordMatch = BCrypt.checkpw(usersPassword, userPassword);

                if(PasswordMatch){
                    System.out.println("Verified Password!");
                    System.out.println();
                } else {
                    System.out.println("Could not verify password.");
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

}
