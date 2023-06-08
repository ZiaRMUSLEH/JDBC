import java.sql.*;

public class ExecuteQuery02 {

    public static void main (String[] args) throws SQLException {


        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db","dev_user","password");
        Statement statement = con.createStatement();

        System.out.println("************** Task-1 *************");
        //Task-1: Print department name and grade of department which has second highest pass_grade
        String query1 = "SELECT department, pass_grade FROM departments ORDER BY pass_grade DESC OFFSET 1 LIMIT 1 ";
        ResultSet resultSet1 = statement.executeQuery(query1);
        while (resultSet1.next()){
            System.out.println("Department: " + resultSet1.getString("department")+ " -- "+resultSet1.getInt("pass_grade"));
        }

        System.out.println("************** Task-2 *************");
        //Task-2: Print department name and pass_grade of department which has second highest pass_grade using sub-query
        String query2 = "SELECT department, pass_grade FROM departments WHERE pass_grade = (SELECT MAX(pass_grade) " +
                "FROM departments WHERE pass_grade < (SELECT MAX(pass_grade) FROM departments))";
        ResultSet resultSet2 = statement.executeQuery(query2);
        while (resultSet2.next()){
            System.out.println("Department: " + resultSet2.getString("department")+ " -- "+resultSet2.getInt("pass_grade"));
        }

        System.out.println("************** Task-3 *************");
        //Task-3: List department name, campus and highest grades of students from every department
        String query3 = "SELECT department, campus, (SELECT MAX(grade) FROM students s WHERE d.department=s.department)  max_grade  FROM departments d";
        //String query3 = "SELECT department, campus, (SELECT max(grade) FROM students s WHERE d.department=s.department) max_grade FROM departments d";
        ResultSet resultSet3 = statement.executeQuery(query3);
        while (resultSet3.next()){
            System.out.println("Department: " + resultSet3.getString("department")+  " -- "
                    +resultSet3.getString("campus")+ " -- "+resultSet3.getInt("max_grade"));
        }



        statement.close();
        con.close();




    }

}
