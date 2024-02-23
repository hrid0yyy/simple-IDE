package sample.ide;
import sample.ide.Helper.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class dbOperations {
        static public Connection connection;

    static {
        try {
            connection = sample.ide.Helper.dbHandler.getDbConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


        static public PreparedStatement preparedStatement;
        public static void AddProblem(int num,String input,String output) throws SQLException, ClassNotFoundException {

                String insert = "INSERT INTO problems(problemID,input,output)"
                        +"VALUES(?,?,?)";

                preparedStatement = connection.prepareStatement(insert);
                preparedStatement.setInt(1,num);
                preparedStatement.setString(2,input);
                preparedStatement.setString(3,output);
                preparedStatement.executeUpdate();

        }

        public static ResultSet readFromProblem(int id) throws SQLException, ClassNotFoundException {

                String query = "Select * from problems where problemID = "+id;

                 preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
             resultSet.next();
             return  resultSet;
        }
        public static void updateProblem(int id,String input,String output) throws SQLException, ClassNotFoundException {


                String update = "UPDATE problems SET input = ? , output = ? where problemID = ?";

                preparedStatement = connection.prepareStatement(update );
                preparedStatement.setString(1,input);
                preparedStatement.setString(2,output);
                preparedStatement.setInt(3,id);
                preparedStatement.executeUpdate();



        }

}

