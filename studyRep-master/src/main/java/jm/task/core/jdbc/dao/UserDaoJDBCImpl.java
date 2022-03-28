package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection connection =  Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users" +
                    "(id mediumint not null auto_increment," +
                    "name VARCHAR(50)," +
                    "lastname VARCHAR(50)," +
                    "age tinyint," +
                    "PRIMARY KEY (id))");

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO katadb.users(name, lastname, age) VALUES(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }




    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){

                String name = rs.getString(2);
                String lastName = rs.getString(3);
                byte age = (byte)rs.getInt(4);
                User user = new User(name,lastName,age);
                userList.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
