package io.daiwei.jdbc;

import com.sun.tools.javac.util.Assert;
import io.daiwei.jdbc.pojo.User;
import io.daiwei.jdbc.test.JdbcBasicTest;
import io.daiwei.jdbc.test.JdbcPreStateTest;
import io.daiwei.jdbc.util.JdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Daiwei on 2021/2/20
 */
public class JdbcTestMain {

    public static void main(String[] args) {
        User user = new User("daiwei666", 25, "Nanchang");
//        JdbcBasicTest.insertUser(user);
//        JdbcBasicTest.updateUser(user);
//        User res = JdbcBasicTest.selectUserById(1L);
//        System.out.println(res);
//        JdbcBasicTest.deleteUserById(1L);
        JdbcPreStateTest.insertUser(user);
//        JdbcPreStateTest.updateUser(user);
//        User res = JdbcPreStateTest.selectUserById(2L);
//        System.out.println(res);
//        JdbcPreStateTest.deleteUserById(2L);
    }
}
