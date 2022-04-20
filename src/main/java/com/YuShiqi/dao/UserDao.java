package com.YuShiqi.dao;

import com.YuShiqi.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class UserDao implements IUserDao{
    @Override
    public boolean saveUser(Connection con, User user) throws SQLException {
        //insert into Users
        String sql = "INSERT INTO Users VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,user.getId());
        ps.setString(2,user.getUsername());
        ps.setString(3,user.getPassword());
        ps.setString(4,user.getEmail());
        ps.setString(5,user.getGender());
        ps.setDate(6, (java.sql.Date) user.getBirthdate());
        ps.executeUpdate();
        return true;
    }

    @Override
    public int deleteUser(Connection con, User user) throws SQLException {
        //delete...where id=?
        return 0;
    }

    @Override
    public int updateUser(Connection con, User user) throws SQLException {
        //update...where id=?
        String sql = "UPDATE Users SET username=?,password=?,email=?,gender=?,birthdate=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3,user.getEmail());
        ps.setString(4,user.getGender());
        ps.setDate(5,(java.sql.Date) user.getBirthdate());
        ps.setInt(6,user.getId());
        return ps.executeUpdate();
    }

    @Override
    public User findById(Connection con, Integer id) throws SQLException {
        //select...from Users where id=?
        return null;
    }

    @Override
    public User findByUsernamePassword(Connection con, String username, String password) throws SQLException {
        //select...from Users where username=? and password=?
        //use for login
        String sql = "SELECT id,username,password,email,gender,birthdate FROM Users WHERE username=? and password=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,username);
        ps.setString(2,password);
        ResultSet rs = ps.executeQuery();
        User user = null;
        if(rs.next()){
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthdate(rs.getDate("birthdate"));
        }
        return user;
    }

    @Override
    public List<User> findByUsername(Connection con, String username) throws SQLException {
        return null;
    }

    @Override
    public List<User> findByPassword(Connection con, String password) throws SQLException {
        return null;
    }

    @Override
    public List<User> findByEmail(Connection con, String email) throws SQLException {
        return null;
    }

    @Override
    public List<User> findByGender(Connection con, String gender) throws SQLException {
        return null;
    }

    @Override
    public List<User> findByBirthdate(Connection con, Date birthDate) throws SQLException {
        return null;
    }

    @Override
    public List<User> findAllUser(Connection con) throws SQLException {
        return null;
    }
}