/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.mapper;

/**
 *
 * @author dissirama
 */
import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.genieLogiciel.gestionAuto.model.UserModel;
import org.springframework.jdbc.core.RowMapper;
 
public class UserMapper implements RowMapper<UserModel> {
 
    public static final String BASE_SQL //
            = "Select u.USER_ID, u.USER_NAME, u.ENCRYTED_PASSWORD From APP_USER u ";
 
    @Override
    public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
 
        Long userId = rs.getLong("USER_ID");
        String userName = rs.getString("USER_NAME");
        String encrytedPassword = rs.getString("ENCRYTED_PASSWORD");
 
        return new UserModel(userId, userName, encrytedPassword);
    }
 
}