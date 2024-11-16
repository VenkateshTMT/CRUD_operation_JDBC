package BatchUpdate;

import PrepareOperations.Main;

import javax.xml.transform.Source;
import java.sql.*;
import java.util.Scanner;

public class Batchupdate {
    public static Connection connection=null;
    public static Statement statement=null;
    public static PreparedStatement pstmt=null;

    private static final Scanner input = new Scanner(System.in);


    public static void displayTableData(Connection connection) throws SQLException {
        statement =connection.createStatement();
        ResultSet res=statement.executeQuery("select * from employee");
        System.out.println("------------------------------------------------------------------");
        while(res.next()){
            //System.out.printf(""+res.getInt("id")+", name:"+res.getString("name")+", Department:"+res.getString("dept")+", salary"+res.getInt("salary"));
            System.out.printf("| %-5d | %-10s | %-20s | %-8s | %-7d |\n",res.getInt("id"),res.getString("name"),res.getString("email"),res.getString("dept"),res.getInt("salary"));

        }
        System.out.println("------------------------------------------------------------------");
    }


    public static void main(String[] args){
        String url="jdbc:mysql://localhost:3306/jdbcdata";
        String username="root";
        String password="@Venkat46";

        String sql="insert into `employee`(`id`,`name`,`email`,`dept`,`salary`)"
                +"values (?,?,?,?,?)";
        String choice=null;

        try {

            connection = DriverManager.getConnection(url, username, password);

            Batchupdate.displayTableData(connection);

            pstmt=connection.prepareStatement(sql);
            do{
                System.out.println("enter id:");
                pstmt.setInt(1,input.nextInt());

                System.out.println("enter department name:");
                pstmt.setString(2,input.next());

                System.out.println("enter email:");
                pstmt.setString(3,input.next());

                System.out.println("enter department:");
                pstmt.setString(4,input.next());

                System.out.println("enter salary:");
                pstmt.setInt(5,input.nextInt());

                pstmt.addBatch();
                System.out.println("do want to add more yes/no");
                choice=input.next();

            }while(choice.equals("yes"));



            int[] arr=pstmt.executeBatch();

            for(int i:arr){
                System.out.println(i);
            }

            Main.displayTableData(connection);

        }catch(SQLException e){
            e.printStackTrace();
        }


    }
}
