package com.wangxiaomeng.dao;

import com.wangxiaomeng.model.User;
import com.wangxiaomeng.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    // define sql statements
    private static final String INSERT_USERS_SQL="insert into users"+" (name, email, country) values "+" (?, ?, ?);";
    private static final String SELECT_USER_BY_ID="select id,name,email,country from users where id =?;";
    private static final String SELECT_ALL_USERS="select * from users;";
    private static final String DELETE_USERS_SQL="delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL="update users set name = ?,email= ?, country =? where id = ?;";

    // insert record int database
    public void insertUser(User user){
        try (Connection connection= JDBCUtils.getConnection();
             PreparedStatement preparedStatement=connection.prepareStatement(INSERT_USERS_SQL)){
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getCountry());
            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getLocalizedMessage());
        }
    }

    // select user from database
    public User selectUser(int id){
        User user=null;
        try (Connection connection=JDBCUtils.getConnection();
             PreparedStatement preparedStatement=connection.prepareStatement(SELECT_USER_BY_ID)){
            preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);

            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                String name=resultSet.getString("name");
                String email=resultSet.getString("email");
                String country=resultSet.getString("country");

                user=new User(id, name,email,country);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getLocalizedMessage());
        }
        return user;
    }

    // select all users from database
    public List<User> selectAllUser(){
        List<User> users=new ArrayList<>();

        try (Connection connection=JDBCUtils.getConnection();
             PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL_USERS)){
            System.out.println(preparedStatement);

            ResultSet rs=preparedStatement.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                String email=rs.getString("email");
                String country=rs.getString("country");
                users.add(new User(id,name,email,country));
            }
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getLocalizedMessage());
        }
        return users;
    }

    // delete user from database
    public boolean deleteUser(int id){
        boolean rowDeleted=false;
        try (Connection connection=JDBCUtils.getConnection();
             PreparedStatement preparedStatement=connection.prepareStatement(DELETE_USERS_SQL)){
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);

            rowDeleted=preparedStatement.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getLocalizedMessage());
        }
        return rowDeleted;
    }

    // update user in database
    public boolean updateUser(User user){
        boolean rowUpdated=false;
        try (Connection connection=JDBCUtils.getConnection();
             PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_USERS_SQL)){
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getCountry());
            preparedStatement.setInt(4,user.getId());
            System.out.println(preparedStatement);

            rowUpdated=preparedStatement.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getLocalizedMessage());
        }
        return rowUpdated;
    }
}
