package com.example.indian_geography_trivia;

import java.sql.*;

public class DataSource {

    public static final String DB_NAME = "Indian Geography Trivia.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Jaskaran\\IdeaProjects\\Indian Geography Trivia\\database\\" + DB_NAME;

    public static final String TABLE_USER = "user";
    public static final String COLUMN_USER_USERNAME = "username";
    public static final String COLUMN_USER_PASSWORD = "password";

    public static final String TABLE_GUEST = "guest";
    public static final String COLUMN_GUEST_GUESTNUMBER = "guest_number";
    public static final String COLUMN_GUEST_GUESTNAME = "guest_name";

    public static final String TABLE_FACTS = "facts";
    public static final String COLUMN_FACTS_FACTNUMBER = "fact_number";
    public static final String COLUMN_FACTS_FACT = "fact_text";

    public static final String TABLE_QNA = "QnA";
    public static final String COLUMN_Q_NO = "Q_no";
    public static final String COLUMN_Q_DIFFICULTY = "Q_difficulty";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_OPTION_A = "option_A";
    public static final String COLUMN_OPTION_B = "option_B";
    public static final String COLUMN_OPTION_C = "option_C";
    public static final String COLUMN_OPTION_D = "option_D";
    public static final String COLUMN_ANSWER = "answer";

    public static final String TABLE_SCORE = "score";
    public static final String COLUMN_SCORE_USERNAME = "username";
    public static final String COLUMN_SCORE_GAME_MODE = "game_mode";
    public static final String COLUMN_SCORE_SCORE = "score";

    public static final String TABLE_READ = "read";
    public static final String COLUMN_READ_SRNO = "srno";
    public static final String COLUMN_READ_TOPIC = "topic";
    public static final String COLUMN_READ_EXPLANATION = "explanation";

    private static final DataSource instance = new DataSource();

    public static DataSource getInstance(){
        return instance;
    }

    public static final String INSERT_USER =
            "INSERT INTO " + TABLE_USER + '(' + COLUMN_USER_USERNAME + ", " + COLUMN_USER_PASSWORD + " ) VALUES (?,?)";

    public static final String INSERT_GUEST =
            "INSERT INTO " + TABLE_GUEST + '(' + COLUMN_GUEST_GUESTNUMBER + ", " + COLUMN_GUEST_GUESTNAME + ") VALUES (?,?)";

    public static final String INSERT_SCORE =
            "INSERT INTO " + TABLE_SCORE + '(' + COLUMN_SCORE_USERNAME + ", " + COLUMN_SCORE_GAME_MODE + ", " + COLUMN_SCORE_SCORE + ") VALUES (?,?,?)";

    public static final String QUERY_USER_LOGIN =
            "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USER_USERNAME + " = ? and " + COLUMN_USER_PASSWORD + " = ?";

    public static final String CHANGE_PASSWORD =
            "UPDATE " + TABLE_USER + " SET " + COLUMN_USER_PASSWORD + " = ? WHERE " + COLUMN_USER_USERNAME + " = ?";

    public static final String DELETE_ACCOUNT =
            "DELETE FROM " + TABLE_USER + " WHERE " + COLUMN_USER_USERNAME + " = ? and " + COLUMN_USER_PASSWORD + " = ?";

    public static final String FETCH_USERNAME =
            "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USER_USERNAME + " = ?";

    public static final String FETCH_GUEST_NUMBER =
            "SELECT * FROM " + TABLE_GUEST;

    public static final String FETCH_FACT =
            "SELECT " + COLUMN_FACTS_FACT + " FROM " + TABLE_FACTS + " WHERE " + COLUMN_FACTS_FACTNUMBER + " = ?";

    public static final String GET_QNA =
            "SELECT * FROM " + TABLE_QNA + " WHERE " + COLUMN_Q_NO + " = ?";

    public static final String GET_DIFFICULTY =
            "SELECT " + COLUMN_Q_DIFFICULTY + " FROM " + TABLE_QNA + " WHERE " + COLUMN_Q_NO + " = ?";

    public static final String GET_HIGH_SCORE =
            "SELECT * FROM " + TABLE_SCORE + " WHERE " + COLUMN_SCORE_GAME_MODE + " = ? ORDER BY " + COLUMN_SCORE_SCORE + " DESC";

    public static final String GET_TOPIC =
            "SELECT " + COLUMN_READ_TOPIC + " FROM " + TABLE_READ + " WHERE " + COLUMN_READ_SRNO + " = ?";

    public static final String GET_EXPLANATION =
            "SELECT " + COLUMN_READ_EXPLANATION + " FROM " + TABLE_READ + " WHERE " + COLUMN_READ_TOPIC + " = ?";

    private Connection conn;

    private PreparedStatement insertUser;
    private PreparedStatement insertGuest;
    private PreparedStatement insertScore;
    private PreparedStatement queryUserLogin;
    private PreparedStatement changePassword;
    private PreparedStatement deleteAccount;
    private PreparedStatement fetchUsername;
    private PreparedStatement fetchGuestNumber;
    private PreparedStatement fetchFact;
    private PreparedStatement getQnA;
    private PreparedStatement getDifficulty;
    private PreparedStatement getHighScore;
    private PreparedStatement getTopic;
    private PreparedStatement getExplanation;

    public boolean open(){
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
            insertUser = conn.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            insertGuest = conn.prepareStatement(INSERT_GUEST, Statement.RETURN_GENERATED_KEYS);
            insertScore = conn.prepareStatement(INSERT_SCORE, Statement.RETURN_GENERATED_KEYS);
            queryUserLogin = conn.prepareStatement(QUERY_USER_LOGIN,Statement.RETURN_GENERATED_KEYS);
            changePassword = conn.prepareStatement(CHANGE_PASSWORD,Statement.RETURN_GENERATED_KEYS);
            deleteAccount = conn.prepareStatement(DELETE_ACCOUNT,Statement.RETURN_GENERATED_KEYS);
            fetchUsername = conn.prepareStatement(FETCH_USERNAME,Statement.RETURN_GENERATED_KEYS);
            fetchGuestNumber = conn.prepareStatement(FETCH_GUEST_NUMBER,Statement.RETURN_GENERATED_KEYS);
            fetchFact = conn.prepareStatement(FETCH_FACT,Statement.RETURN_GENERATED_KEYS);
            getQnA = conn.prepareStatement(GET_QNA,Statement.RETURN_GENERATED_KEYS);
            getDifficulty = conn.prepareStatement(GET_DIFFICULTY,Statement.RETURN_GENERATED_KEYS);
            getHighScore = conn.prepareStatement(GET_HIGH_SCORE,Statement.RETURN_GENERATED_KEYS);
            getTopic = conn.prepareStatement(GET_TOPIC,Statement.RETURN_GENERATED_KEYS);
            getExplanation = conn.prepareStatement(GET_EXPLANATION,Statement.RETURN_GENERATED_KEYS);
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Couldn't connect to database at open" + e.getMessage());
            return false;
        }
    }

    public void close(){
        try{
            if (insertUser!=null){
                insertUser.close();
            }
            if (insertGuest!=null){
                insertGuest.close();
            }
            if (insertScore!=null) {
                insertScore.close();
            }
            if (queryUserLogin!=null){
                queryUserLogin.close();
            }
            if (changePassword!=null) {
                changePassword.close();
            }
            if (deleteAccount!=null) {
                deleteAccount.close();
            }
            if (fetchUsername!=null){
                fetchUsername.close();
            }
            if (fetchGuestNumber!=null) {
                fetchGuestNumber.close();
            }
            if (fetchFact!=null) {
                fetchFact.close();
            }
            if (getQnA!=null) {
                getQnA.close();
            }
            if (getDifficulty!=null) {
                getDifficulty.close();
            }
            if (getHighScore!=null) {
                getHighScore.close();
            }
            if (getTopic!=null) {
                getTopic.close();
            }
            if (getExplanation!=null) {
                getExplanation.close();
            }
        }catch(SQLException e){
            System.out.println("Couldn't close the connection" + e.getMessage());
        }
    }

    public boolean loginValidation(String username, String password) throws SQLException{

        queryUserLogin.setString(1,username);
        queryUserLogin.setString(2,password);

        try {
            ResultSet result = queryUserLogin.executeQuery();

            if (result.next()){
                return true;
            }else {
                return false;
            }
        } catch (Exception e){
            return false;
        }
    }

    public boolean insertUser (String username, String password) throws SQLException{
        insertUser.setString(1,username);
        insertUser.setString(2,password);
        int result = insertUser.executeUpdate();

        if (result!=1){
            throw new SQLException("Couldn't insert User!!");
        }else {
            return true;
        }
    }

    public boolean changePassword (String username, String password) throws SQLException {
        changePassword.setString(1,password);
        changePassword.setString(2,username);

        int result = changePassword.executeUpdate();

        if (result!=1) {
            throw new SQLException("Couldn't change password!!");
        }else {
            return true;
        }
    }

    public boolean deleteAccount (String username, String password) throws SQLException {
        deleteAccount.setString(1,username);
        deleteAccount.setString(2,password);

        int result = deleteAccount.executeUpdate();

        if (result!=1) {
            throw new SQLException("Couldn't delete Account!!");
        }else {
            return true;
        }
    }

    public void insertGuest (int guest_number, String guest_name) throws SQLException{
        insertGuest.setInt(1,guest_number);
        insertGuest.setString(2,guest_name);
        insertGuest.executeUpdate();
    }

    public boolean usernameValidation (String username) throws SQLException{
        fetchUsername.setString(1,username);

        try{
            ResultSet result = fetchUsername.executeQuery();

            if (result.next()){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    public int guestNumber() {
        int guest_number = 0;

        try {
            ResultSet result = fetchGuestNumber.executeQuery();

            while (result.next()){
                guest_number++;
            }
            return guest_number;
        } catch (Exception e) {
            return -1;
        }
    }

    public String getFact(int factNumber) throws SQLException {
        fetchFact.setInt(1,factNumber);

        try {
            ResultSet result = fetchFact.executeQuery();

            if (result.next()) {
                return result.getString("fact_text");
            } else {
                return "Couldn't fetch fact";
            }
        } catch (Exception e) {
            return "Couldn't fetch fact";
        }
    }

    public String[] getQnA(int QNumber) throws SQLException {
        String[] QnA = new String[6];

        getQnA.setInt(1,QNumber);

        try {
            ResultSet result = getQnA.executeQuery();

            if (result.next()) {
                QnA[0] = result.getString(COLUMN_QUESTION);
                QnA[1] = result.getString(COLUMN_OPTION_A);
                QnA[2] = result.getString(COLUMN_OPTION_B);
                QnA[3] = result.getString(COLUMN_OPTION_C);
                QnA[4] = result.getString(COLUMN_OPTION_D);
                QnA[5] = result.getString(COLUMN_ANSWER);

                return QnA;
            } else {
                System.out.println("Couldn't get QnA at try");
            }
        } catch (Exception e) {
            System.out.println("Couldn't get QnA at catch");
        }
        return null;
    }

    public String getDifficulty(int QNumber) throws SQLException {
        getDifficulty.setInt(1,QNumber);

        try {
            ResultSet result = getDifficulty.executeQuery();

            if (result.next()) {
                return result.getString(COLUMN_Q_DIFFICULTY);
            } else {
                System.out.println("Couldn't get Difficulty at try");
            }
        } catch (Exception e) {
            System.out.println("Couldn't get Difficulty at catch");
        }
        return null;
    }

    public String[] getHighScoreUsername(String game_mode) throws SQLException {
        int x=0;
        String[] usernames = new String[10];

        getHighScore.setString(1,game_mode);

        try {
            ResultSet result = getHighScore.executeQuery();

            while(result.next() && x<10) {
                usernames[x++] = result.getString(COLUMN_SCORE_USERNAME);
            }

            while(x<10) {
                usernames[x++] = "-";
            }

            return usernames;
        } catch (Exception e) {
            System.out.println("Couldn't get usernames for high score quiz");
        }
        return null;
    }

    public int[] getHighScoreScore(String game_mode) throws SQLException {
        int x=0;
        int[] scores = new int[10];

        getHighScore.setString(1,game_mode);

        try {
            ResultSet result = getHighScore.executeQuery();

            while(result.next() && x<10) {
                scores[x++] = result.getInt(COLUMN_SCORE_SCORE);
            }

            return scores;
        } catch (Exception e) {
            System.out.println("Couldn't get high scores");
        }
        return null;
    }

    public void insertScore(String user_name, String game_mode, int score) throws SQLException {
        insertScore.setString(1,user_name);
        insertScore.setString(2,game_mode);
        insertScore.setInt(3,score);
        insertScore.executeUpdate();
    }

    public String getTopic(int sr_no) throws SQLException {
        getTopic.setInt(1,sr_no);

        try {
            ResultSet result = getTopic.executeQuery();

            if(result.next()) {
                return result.getString(COLUMN_READ_TOPIC);
            }
            return "-";
        } catch (Exception e) {
            System.out.println("Couldn't get topic");
        }
        return "-";
    }

    public String getExplanation(String topic) throws SQLException {
        getExplanation.setString(1,topic);

        try {
            ResultSet result = getExplanation.executeQuery();

            if(result.next()) {
                return result.getString(COLUMN_READ_EXPLANATION);
            }
            return "-";
        } catch (Exception e) {
            System.out.println("Couldn't get topic");
        }
        return "-";
    }
}