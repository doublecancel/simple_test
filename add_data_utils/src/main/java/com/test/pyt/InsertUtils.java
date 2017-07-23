package com.test.pyt;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Administrator on 2017/7/23 0023.
 */
public class InsertUtils {

    private static final String driverName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT&useServerPrepStmts=false&rewriteBatchedStatements=true";
    private static final String username = "root";
    private static final String password = "root";
    private static final String table = "User";

    private static final String[] strs = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
    "u", "v", "w", "x", "y", "z"};//26
    private static final String[] emails = {"sina", "qq", "163", "126", "google", "178", "hls"};//7
    private static final String[] nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};//10
    private static final String[] all = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};//36
    private static final String[] genders = {"N", "Y"};

    private static final String[] familyNames = getFNames();
    private static final String[] fullNames = getNames();//100

    static String[]  getNames() {
        InputStream is = InsertUtils.class.getResourceAsStream("/aaa.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = null;
        try {
            line = reader.readLine();
            is.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] string = line.split(" ");
        Arrays.stream(string).filter((a) -> a.isEmpty());
        return string;
    }
    static String[]  getFNames() {
        InputStream is = InsertUtils.class.getResourceAsStream("/bbb.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = null;
        StringBuffer sb = new StringBuffer();
        String str = "";
        try {
            while ((line = reader.readLine()) != null){
                sb.append(line.trim());
            }
            str = sb.toString().replaceAll("\r|\n", "");
            System.out.println();
            is.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        char[] cs = str.toCharArray();
        String[] strs = new String[cs.length];
        for(int a = 0; a < cs.length; a++){
            strs[a] = String.valueOf(cs[a]);
        }
        return strs;
    }

    static {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    static class MyConn{
        Connection connection;
        PreparedStatement pst;
        public MyConn(){
            try {
                this.connection = DriverManager.getConnection(url, username, password);
                this.connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public Connection getConnection() {
            return this.connection;
        }

        void close(){
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private static void create(Connection connection , int count) {
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement("insert into User (name, age, gender, email, phone, create_date, " +
                    "create_user, modify_date, modify_user, status, version) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            User user = null;
            for(int a = 0; a < count; a++){
                user = User.create();
                pst.setString(1, user.getName());
                pst.setInt(2, user.getAge());
                pst.setString(3, user.getGender());
                pst.setString(4, user.getEmail());
                pst.setString(5, user.getPhone());
                pst.setString(6, user.getCreate_date());
                pst.setString(7, user.getCreate_user());
                pst.setString(8, user.getModify_date());
                pst.setString(9, user.getModify_user());
                pst.setInt(10, user.getStatus());
                pst.setInt(11, user.getVersion());
                pst.addBatch();
                if(a % 1000 == 0){
                    pst.executeBatch();
                    connection.commit();
                    System.out.println(a);
                }
            }
            pst.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    static String generateStr(String type){

        if(type.equals("name")){
            String name = "";
            name += familyNames[ThreadLocalRandom.current().nextInt(16)];
            name += fullNames[ThreadLocalRandom.current().nextInt(99)];
            return name;
        }
        if(type.equals("email")){
            String emial = "";
            for(int b = 0; b < 10; b++){
                emial += all[ThreadLocalRandom.current().nextInt(36)];
            }
            emial += "@";
            emial += emails[ThreadLocalRandom.current().nextInt(7)];
            emial += ".com";
            return emial;
        }
        if(type.equals("num")){
            String num = "";
            for(int b = 0; b < 10; b++){
                num += nums[ThreadLocalRandom.current().nextInt(10)];
            }
            return num;
        }
        return "";
    }


    public static void main(String[] args) throws  Exception {
        MyConn con = new MyConn();
//        for(int a = 0; a < 100; a++){
            create(con.getConnection(), 1000000);
//            System.out.println(a * 1000);
//        }
        con.close();

    }


    static class User{
        private String name;
        private int age;
        private String gender;
        private String email;
        private String phone;
        private String create_date;
        private String create_user;
        private String modify_date;
        private String modify_user;
        private int status;
        private int version;

        public static User create(){
            User user = new User();
            user.setAge(ThreadLocalRandom.current().nextInt(100));
            user.setName(generateStr("name"));
            user.setEmail(generateStr("email"));
            user.setCreate_date(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
            user.setModify_date(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
            user.setGender(genders[ThreadLocalRandom.current().nextInt(2)]);
            user.setCreate_user(generateStr("num"));
            user.setModify_user(generateStr("num"));
            user.setPhone(generateStr("num"));
            user.setStatus(ThreadLocalRandom.current().nextInt(2));
            user.setVersion(0);
            return user;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getCreate_user() {
            return create_user;
        }

        public void setCreate_user(String create_user) {
            this.create_user = create_user;
        }

        public String getModify_date() {
            return modify_date;
        }

        public void setModify_date(String modify_date) {
            this.modify_date = modify_date;
        }

        public String getModify_user() {
            return modify_user;
        }

        public void setModify_user(String modify_user) {
            this.modify_user = modify_user;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", gender='" + gender + '\'' +
                    ", email='" + email + '\'' +
                    ", phone='" + phone + '\'' +
                    ", create_date='" + create_date + '\'' +
                    ", create_user='" + create_user + '\'' +
                    ", modify_date='" + modify_date + '\'' +
                    ", modify_user='" + modify_user + '\'' +
                    ", status=" + status +
                    ", version=" + version +
                    '}';
        }
    }
}
