package DmlOperations;

import java.sql.*;
import java.util.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static Connection connection=null;
    private static Statement statement=null;


    public static void main(String[] args) {
        ResultSet res=null;
        String url="jdbc:mysql://localhost:3306/jdbcdata";
        String username="root";
        String password="@Venkat46";
        Scanner input=new Scanner(System.in);
        String sql="delete from `employee` where `dept`='bpo'";

        try{
            connection = DriverManager.getConnection(url,username,password);
            statement =connection.createStatement();
            int i=statement.executeUpdate(sql);
            System.out.println(i);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}