package PrepareOperations;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static Connection connection=null;
    public static Statement statement=null;
    public static PreparedStatement pstmt=null;

    private static final Scanner input = new Scanner(System.in);


    public static void displayTableData(Connection connection) throws SQLException{
        statement =connection.createStatement();
        ResultSet res=statement.executeQuery("select * from employee");
        while(res.next()){
            System.out.println("id:"+res.getInt("id")+", name:"+res.getString("name")+", Department:"+res.getString("dept")+", salary"+res.getInt("salary"));
        }
        System.out.println("--------------------------");
    }


    public static void main(String[] args){
        String url="jdbc:mysql://localhost:3306/jdbcdata";
        String username="root";
        String password="@Venkat46";

        String sql="update `employee` set `salary`=`salary`+? where `dept`=?";

        try {

            connection = DriverManager.getConnection(url, username, password);

            Main.displayTableData(connection);

            pstmt=connection.prepareStatement(sql);

            System.out.println("enter department name:");
            String department =input.next();
            System.out.println("enter salary hike:");
            int salary=input.nextInt();

            pstmt.setInt(1,salary);
            pstmt.setString(2,department);



            int i=pstmt.executeUpdate();

            Main.displayTableData(connection);

        }catch(SQLException e){
            e.printStackTrace(); 
        }


    }
}
