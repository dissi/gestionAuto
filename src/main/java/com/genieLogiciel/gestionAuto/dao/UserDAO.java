/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genieLogiciel.gestionAuto.dao;

/**
 *
 * @author dissirama
 */
import javax.sql.DataSource;
 
import  com.genieLogiciel.gestionAuto.mapper.UserMapper;
import  com.genieLogiciel.gestionAuto.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
@Repository
@Transactional
public class UserDAO extends JdbcDaoSupport {
 
    @Autowired
    public UserDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
 
    public UserModel findUserAccount(String userName) {
        // Select .. from App_User u Where u.User_Name = ?
        String sql = UserMapper.BASE_SQL + " where u.USER_NAME = ? ";
 
        Object[] params = new Object[] { userName };
        UserMapper mapper = new UserMapper();
        try {
            UserModel userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
 
}