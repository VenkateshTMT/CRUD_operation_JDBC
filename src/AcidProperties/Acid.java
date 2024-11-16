package AcidProperties;
import BatchUpdate.Batchupdate;

import java.sql.*;

import java.util.*;
public class Acid {

    private static  Connection connection=null;
    private static  PreparedStatement statement=null;

    static final Scanner input = new Scanner(System.in);

    private static final String url = "jdbc:mysql://localhost:3306/jdbcdata";
    private static final String username = "root";
    private static final String password = "@Venkat46";


    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            Batchupdate.displayTableData(connection);
            transaction();
            Batchupdate.displayTableData(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void transaction() throws SQLException{
        System.out.println("Enter sender name:");
        String sender =input.next();
        System.out.println("Enter reciver name:");
        String receiver=input.next();
        System.out.println("Enter sending amount");
        int amount =input.nextInt();
        int x=updateBalance(sender,-amount);
        int y=updateBalance(receiver,amount);

        if(isConform(x,y)){
            System.out.println("Transaction is successful");
            connection.commit();
        }else{
            System.out.println("Transaction is failed");
            connection.rollback();
        }
    }

    private static int updateBalance(String user,int amount) throws SQLException{
        String sql="update `employee` set `salary`=`salary`+? where `name`=?";
        statement= connection.prepareStatement(sql);
        statement.setInt(1,amount);
        statement.setString(2,user);
        int i=statement.executeUpdate();
        return i;
    }

    private static boolean isConform(int x, int y) {
        System.out.println("enter conformation yes/no");
        return input.next().equalsIgnoreCase("yes") && x==1 && y==1;
    }
}
