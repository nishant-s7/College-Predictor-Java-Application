package Classes;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import CSV.*;

public class Admin extends Person{
    private String position;
    
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    public Admin()
    {
    
    }
    public Admin(String username,String password)
    {
        this();
        setUsername(username);
        setPassword(password);
    }
    public Admin(String username,String email, String password)
    {
        setUsername(username);
        setEmail(email);
        setPassword(password);
    }
    public Admin(String username,String email, String password, String position)
    {
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setPosition(position);
    }

    public boolean addNewAdmin(Connection connection, String adminID, String email, String password)
    {
        try {
            PreparedStatement statement1 = connection.prepareStatement("select count(adminID) from admin_details where adminID = ?");
            statement1.setString(1,adminID);
            ResultSet rs1 = statement1.executeQuery();

            int count_username =0, count_email =0;
            if(rs1.next()) {
                count_username = rs1.getInt(1);
            }
            PreparedStatement statement2 = connection.prepareStatement("select count(email) from admin_details where email = ?");
            
            statement2.setString(1,email);
            ResultSet rs2 = statement2.executeQuery();
            
            if(rs2.next())
            {
                count_email = rs2.getInt(1);
            }
            
            if(count_username >= 1 || count_email >= 1)
            {
                if(count_username>=1 && count_email >=1)
                {
                    System.out.println("Above AdminID and e-mail ID is already a administrator.\nPlease Login with your Admin ID / E-mail ID and Password!");
                }else if(count_email >= 1)
                {
                    System.out.println("Given E-mail is already registered.");
                }else {
                    System.out.println("Given Admin ID is not available, try with Different Admin ID.");
                }
                return false;
            }
            else {
                PreparedStatement statement = connection.prepareStatement("insert into admin_details(adminID,email,Password,position) values(?,?,?,'admin')");
                statement.setString(1, adminID);
                statement.setString(2, email);
                statement.setString(3, password);
                statement.execute();
    
                return true;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean removeAdmin(Connection connection, String adminID,String email){
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM admin_details WHERE adminID = ? and email = ? and position not like 'owner'");
            stmt.setString(1,adminID);
            stmt.setString(2,email);
            if(stmt.executeUpdate()==1)
            {
                System.out.println(" You removed Admin : '" + adminID + "' with E-mail ID : ''"+email+"', Successfully");
                return true;
            }else {
                System.out.println("Given Admin ID and E-mail not found in database");
                return false;
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean Login(Connection connection)
    {
        try {
            PreparedStatement statement = connection.prepareStatement("select count(*) from admin_details where (adminID= ? or email= ?) and password = ?");
            
            statement.setString(1,getUsername());
            statement.setString(2,getUsername());
            statement.setString(3,getPassword());
            
            ResultSet resultSet = statement.executeQuery();
            
            if(resultSet.next() && resultSet.getInt(1)==1)
            {
                
                PreparedStatement retriveUser = connection.prepareStatement("select * from admin_details where (adminID= ? or email= ?) and password = ?");
                retriveUser.setString(1,getUsername());
                retriveUser.setString(2,getUsername());
                retriveUser.setString(3,getPassword());
                ResultSet resultSet1 = retriveUser.executeQuery();
                while (resultSet1.next())
                {
                    setUsername(resultSet1.getString("adminID"));
                    setEmail(resultSet1.getString("email"));
                    setPassword(resultSet1.getString("password"));
                    setPosition(resultSet1.getString("position"));
                }
                
                return true;
            }else {
                
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean removeInstitute(Connection connection, String InstituteName){
        try {
            int count =0;
            for (int i = 1; i <= 6; i++) {
                PreparedStatement statement1 = connection.prepareStatement("DELETE FROM round"+i+" WHERE Institute = ?");
                statement1.setString(1,InstituteName);
                if(statement1.executeUpdate() >= 1)
                {
                    count++;
                }
            }
            return count == 6;
        }
        catch (Exception e)
        {
            System.out.println("Application error : Database connectivity Problem");
            return false;
        }
    }

    public ObservableList<Admin> getAdminList(Connection connection){

        ObservableList<Admin> adminList = FXCollections.observableArrayList();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM admin_details order by adminID");
            
            while(rs.next()){
                Admin admin = new Admin(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                adminList.add(admin);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return adminList;

    }
    
    public ObservableList<User> getUserList(Connection connection){

        ObservableList<User> UserList = FXCollections.observableArrayList();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username,email,category,gender,generalRank,categoryRank FROM user_details order by username");
            
            while(rs.next()){
                User user = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
                UserList.add(user);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return UserList;

    }
    
    public boolean removeUser(Connection connection,String username,String email){
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM user_details WHERE username = ? and email= ?");
            stmt.setString(1,username);
            stmt.setString(2,email);
            int count =stmt.executeUpdate();
            if(count ==1)
            {
                System.out.println("You removed User : '" + username + "' with E-mail ID : '"+email+"', Successfully");
                return true;
            }else {
                System.out.println("Given Username and E-mail not found in database, so '"+username+"' can't be removed");
                return false;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //Search via ID/Primary-Key => Single Record
    public ObservableList<User> getUser(Connection connection,String username, String email){
        ObservableList<User> UserList = FXCollections.observableArrayList();
        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT username,email,category,gender,generalRank,categoryRank ,count(*) FROM user_details where username = ? and email = ?",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1,username);
            stmt.setString(2,email);
            ResultSet rs = stmt.executeQuery();
            if(rs.next() && rs.getInt(7) ==0)
            {
                System.out.println("User not exist / Invalid Username or E-mail");
                UserList = null;
            }
            else {
            rs.previous();
                while(rs.next()){
                    User user = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
                    UserList.add(user);
                }
            }
            // User user1 = new User();
            // user1.printUserList(UserList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return UserList;
    }

    public void UploadDeletedUserCSV(Connection connection){
        
        try{
            CSVFileHandle.addUser_deletedCsvToDatabasesUser_deleted("C:\\Users\\nisha\\OneDrive\\Documents\\IIITS\\OOP\\SLIDES\\Project\\OOP Project\\src\\CSV\\user_deleted.csv", connection);

            CSVFileHandle.DeleteCSVFIle("C:\\Users\\nisha\\OneDrive\\Documents\\IIITS\\OOP\\SLIDES\\Project\\OOP Project\\src\\CSV\\user_deleted.csv");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    
    public static void UploadAllJosaaRoundCutoff(Connection connection) {

        try{
            CSVFileHandle.UploadJosaaRoundCutoffToDatabase("C:\\Users\\nisha\\OneDrive\\Documents\\IIITS\\OOP\\SLIDES\\Project\\OOP Project\\src\\CSV\\round1.csv",connection,1);
            PreparedStatement statement1 = connection.prepareStatement("select count(*) from round1");
            ResultSet rs1 = statement1.executeQuery();
            if(rs1.next())
            {
                System.out.println(rs1.getInt(1)+" rows Inserted successfully in java_proj_college_predictor.round1");
            }
            
            CSVFileHandle.UploadJosaaRoundCutoffToDatabase("C:\\Users\\nisha\\OneDrive\\Documents\\IIITS\\OOP\\SLIDES\\Project\\OOP Project\\src\\CSV\\round2.csv",connection,2);
            PreparedStatement statement2 = connection.prepareStatement("select count(*) from round2");
            ResultSet rs2 = statement2.executeQuery();
            if(rs2.next())
            {
                System.out.println(rs2.getInt(1)+" rows Inserted successfully in java_proj_college_predictor.round2");
            }
            
            CSVFileHandle.UploadJosaaRoundCutoffToDatabase("C:\\Users\\nisha\\OneDrive\\Documents\\IIITS\\OOP\\SLIDES\\Project\\OOP Project\\src\\CSV\\round3.csv",connection,3);
            PreparedStatement statement3 = connection.prepareStatement("select count(*) from round3");
            ResultSet rs3 = statement3.executeQuery();
            if(rs3.next())
            {
                System.out.println(rs3.getInt(1)+" rows Inserted successfully in java_proj_college_predictor.round3");
            }
            
            CSVFileHandle.UploadJosaaRoundCutoffToDatabase("C:\\Users\\nisha\\OneDrive\\Documents\\IIITS\\OOP\\SLIDES\\Project\\OOP Project\\src\\CSV\\round4.csv",connection,4);
            PreparedStatement statement4 = connection.prepareStatement("select count(*) from round4");
            ResultSet rs4 = statement4.executeQuery();
            if(rs4.next())
            {
                System.out.println(rs4.getInt(1)+" rows Inserted successfully in java_proj_college_predictor.round4");
            }
            
            CSVFileHandle.UploadJosaaRoundCutoffToDatabase("C:\\Users\\nisha\\OneDrive\\Documents\\IIITS\\OOP\\SLIDES\\Project\\OOP Project\\src\\CSV\\round5.csv",connection,5);
            PreparedStatement statement5 = connection.prepareStatement("select count(*) from round5");
            ResultSet rs5 = statement5.executeQuery();
            if(rs5.next())
            {
                System.out.println(rs5.getInt(1)+" rows Inserted successfully in java_proj_college_predictor.round5");
            }
            
            CSVFileHandle.UploadJosaaRoundCutoffToDatabase("C:\\Users\\nisha\\OneDrive\\Documents\\IIITS\\OOP\\SLIDES\\Project\\OOP Project\\src\\CSV\\round6.csv",connection,6);
            PreparedStatement statement6 = connection.prepareStatement("select count(*) from round6");
            ResultSet rs6 = statement6.executeQuery();
            if(rs6.next())
            {
                System.out.println(rs6.getInt(1)+" rows Inserted successfully in java_proj_college_predictor.round6");
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}