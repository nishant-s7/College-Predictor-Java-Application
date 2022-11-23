package Classes;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    // //Search via ID/Primary-Key => Single Record
    // public void getUser(Connection connection,String username, String email){
    //     try{
    //         PreparedStatement stmt = connection.prepareStatement("SELECT username,email,category,gender,generalRank,categoryRank ,count(*) FROM user_details where username = ? and email = ?",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
    //         stmt.setString(1,username);
    //         stmt.setString(2,email);
    //         ResultSet rs = stmt.executeQuery();
    //         if(rs.next() && rs.getInt(7) ==0)
    //         {
    //             System.out.println("User not exist / Invalid Username or E-mail");
    //         }
    //         rs.previous();
    //         ArrayList<User> UserList = new ArrayList<>();
    //         while(rs.next()){
    //             User user = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
    //             UserList.add(user);
    //         }
    //         User user1 = new User();
    //         user1.printUserList(UserList);
    //     }
    //     catch(Exception e){
    //         System.out.println("Application error : Database connectivity Problem");
    //     }
    // }
    
    // public void sortUserList(ArrayList<User> arrayList)
    // {
    //     Scanner scanner = new Scanner(System.in);
    //     System.out.println("Do you want to Sort User List\n1.Yes     2.No    (Select option number 1 or 2");
    //     int opt = scanner.nextInt();
    //     while (opt == 1) {
    //         System.out.println("Select proper number (1-5) for Parameter by which you want to sort User List\n1.Username     2.Category      3.Gender      4.Category Rank     5.General Rank");
    //         int para = scanner.nextInt();
    //         switch (para) {
    //             case 1: {
    //                 arrayList.sort(User::compareTo);
    //                 System.out.println("User List is Sorted by Username");
    //                 printUserList(arrayList);
    //                 break;
    //             }
    //             case 2: {
    //                 arrayList.sort(User::compareTo1);
    //                 System.out.println("User List is Sorted by Category");
    //                 printUserList(arrayList);
    //                 break;
    //             }
    //             case 3: {
    //                 arrayList.sort(User::compareTo2);
    //                 System.out.println("User List is Sorted by Gender");
    //                 printUserList(arrayList);
    //                 break;
    //             }
    //             case 4: {
    //                 arrayList.sort(User::compareTo3);
    //                 System.out.println("User List is Sorted by General Rank");
    //                 printUserList(arrayList);
    //                 break;
    //             }
    //             case 5: {
    //                 arrayList.sort(User::compareTo4);
    //                 System.out.println("User List is Sorted by Category Rank");
    //                 printUserList(arrayList);
    //                 break;
    //             }
    //             default: {
    //                 System.out.println("Error : Invalid option is Selected !");
    //                 break;
    //             }
    //         }
    //         System.out.println("Do you want to Sort it again\n1.Yes     2.No    (Select option number 1 or 2)");
    //         opt = scanner.nextInt();
    //     }
    // }
}