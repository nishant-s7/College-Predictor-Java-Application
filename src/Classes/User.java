package Classes;

import CSV.*;
import java.sql.*;
import java.util.*;

public class User extends Person{
    private String gender;
    private String category;
    private int GeneralRank;
    private int CategoryRank;
    
    public User() {
    
    }
    
    public User(String userName, String password) {
        setUsername(userName);
        setPassword(password);
    }
    
    public User(String userName, String email, String password, String category, String gender, int categoryRank, int generalRank) {
        super.setUsername(userName);
        super.setEmail(email);
        super.setPassword(password);
        setCategory(category);
        setCategoryRank(categoryRank);
        setGender(gender);
        setGeneralRank(generalRank);
    }
    
    public User(String userName, String email, String category, String gender, int categoryRank, int generalRank) {
        super.setUsername(userName);
        super.setEmail(email);
        setCategory(category);
        setCategoryRank(categoryRank);
        setGender(gender);
        setGeneralRank(generalRank);
    }
    
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public void setGeneralRank(int GeneralRank) {
        this.GeneralRank = GeneralRank;
    }
    
    public void setCategoryRank(int categoryRank) {
        CategoryRank = categoryRank;
    }
    
    public String getCategory() {
        return category;
    }
    
    public String getGender() {
        return gender;
    }
    
    public int getGeneralRank() {
        return GeneralRank;
    }
    
    public int getCategoryRank() {
        return CategoryRank;
    }
    
    public boolean Register(Connection connection) {
        try {
            PreparedStatement statement1 = connection.prepareStatement("select count(username) from user_details where username = ?");
            statement1.setString(1,getUsername());
            ResultSet rs1 = statement1.executeQuery();
            int count_username =0, count_email =0;
            if(rs1.next())
            {
                count_username = rs1.getInt(1);
            }
            PreparedStatement statement2 = connection.prepareStatement("select count(email) from user_details where email = ?");
            statement2.setString(1,getEmail());
            ResultSet rs2 = statement2.executeQuery();
            if(rs2.next())
            {
                count_email = rs2.getInt(1);
            }
    
            if(count_username >= 1 || count_email >= 1)
            {
                if(count_username>=1 && count_email >=1)
                {
                    System.out.println("Above Username and e-mail ID is already Registered.\nPlease Login with your Username/email ID and Password!");
                }else if(count_email >= 1)
                {
                    System.out.println("Given E-mail is already registered.");
                }else {
                    System.out.println("Given Username is not available, try with Different Username.");
                }
                return false;
            }
            else
            {
                PreparedStatement statement = connection.prepareStatement("insert into user_details(username,email,password,gender,category,generalRank,categoryRank) values(?,?,?,?,?,?,?)");
                statement.setString(1, getUsername());
                statement.setString(2, getEmail());
                statement.setString(3, getPassword());
                statement.setString(4, getGender());
                statement.setString(5, getCategory());
                statement.setInt(6, getGeneralRank());
                statement.setInt(7, getCategoryRank());
                statement.execute();
                System.out.println("You have Registered Successfully ! ");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean Login(Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement("select count(*) from user_details where (username= ? or email= ?) and password = ? ");
            
            statement.setString(1, getUsername());
            statement.setString(2, getUsername());
            statement.setString(3, getPassword());
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next() && resultSet.getInt(1) == 1) {
                PreparedStatement retriveUser = connection.prepareStatement("select * from user_details where (username= ? or email= ?) and password = ? ");
                retriveUser.setString(1, getUsername());
                retriveUser.setString(2, getUsername());
                retriveUser.setString(3, getPassword());
                ResultSet resultSet1 = retriveUser.executeQuery();
                while (resultSet1.next()) {
                    setUsername(resultSet1.getString("username"));
                    setEmail(resultSet1.getString("email"));
                    setPassword(resultSet1.getString("password"));
                    setGender(resultSet1.getString("gender"));
                    setCategory(resultSet1.getString("category"));
                    setGeneralRank(resultSet1.getInt("generalRank"));
                    setCategoryRank(resultSet1.getInt("categoryRank"));
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Application error : Database connectivity Problem");
            return false;
        }
    }

    public void UpdateUserData(Connection connection) {

        CSVFileHandle.UpdateCategoryData_CSVtoDatabase(
            "C:\\Users\\nisha\\OneDrive\\Documents\\IIITS\\OOP\\SLIDES\\Project\\OOP Project\\src\\CSV\\user_updatedCategory.csv",
            connection
        );
        CSVFileHandle.UpdateGenderData_CSVtoDatabase(
            "C:\\Users\\nisha\\OneDrive\\Documents\\IIITS\\OOP\\SLIDES\\Project\\OOP Project\\src\\CSV\\user_updatedGender.csv",
            connection
        );
        CSVFileHandle.UpdateGeneralRankData_CSVtoDatabase(
            "C:\\Users\\nisha\\OneDrive\\Documents\\IIITS\\OOP\\SLIDES\\Project\\OOP Project\\src\\CSV\\user_updatedGeneralRank.csv",
            connection
        );
        CSVFileHandle.UpdateCategoryRankData_CSVtoDatabase(
            "C:\\Users\\nisha\\OneDrive\\Documents\\IIITS\\OOP\\SLIDES\\Project\\OOP Project\\src\\CSV\\user_updatedCategoryRank.csv",
            connection
        );

        try {
            PreparedStatement stmt = connection.prepareStatement("select * from user_details where username = ? and email = ?");
            stmt.setString(1, this.getUsername());
            stmt.setString(2, this.getEmail());
            ResultSet rs = stmt.executeQuery();
            ArrayList<User> UserList = new ArrayList<>();
            while (rs.next()) {
                User user = new User(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(5),
                    rs.getString(4),
                    rs.getInt(6),
                    rs.getInt(7)
                );
                UserList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    public boolean UpdateUserDetails(Connection connection, int select, String str) {

        try{
            switch (select) {
                case 1 -> {
                    // System.out.println("Enter New Gender");
                    // str = sc.next();
                    String[] data =new String[3];
                    data[0]=getUsername();
                    data[1]=getEmail();
                    data[2]=str;
                    this.setGender(str);
                    
                    CSVFileHandle.WritelineIntoCSV("C:\\Users\\nisha\\OneDrive\\Documents\\IIITS\\OOP\\SLIDES\\Project\\OOP Project\\src\\CSV\\user_updatedGender.csv", data);

                    UpdateUserData(connection);
                    return true;
                }

                case 2 -> {
                    System.out.println("Enter New Category");
                    // str = sc.next();
                    String[] data =new String[3];
                    data[0]=getUsername();
                    data[1]=getEmail();
                    data[2]=str;
                    setCategory(str);
                    CSVFileHandle.WritelineIntoCSV("C:\\Users\\nisha\\OneDrive\\Documents\\IIITS\\OOP\\SLIDES\\Project\\OOP Project\\src\\CSV\\user_updatedCategory.csv", data);

                    UpdateUserData(connection);
                    return true;
                }

                case 3 -> {
                    System.out.println("Enter New GeneralRank");
                    // rank = sc.nextInt();
                    String[] data =new String[3];
                    data[0]=getUsername();
                    data[1]=getEmail();
                    data[2]=str;
                    setGeneralRank(Integer.parseInt(str));
                    CSVFileHandle.WritelineIntoCSV("C:\\Users\\nisha\\OneDrive\\Documents\\IIITS\\OOP\\SLIDES\\Project\\OOP Project\\src\\CSV\\user_updatedGeneralRank.csv", data);
                    
                    UpdateUserData(connection);
                    return true;
                }

                case 4 -> {
                    System.out.println("Enter New CategoryRank");
                    // rank = sc.nextInt();
                    String[] data =new String[3];
                    data[0]=getUsername();
                    data[1]=getEmail();
                    data[2]=str;
                    setCategoryRank(Integer.parseInt(str));
                    CSVFileHandle.WritelineIntoCSV("C:\\Users\\nisha\\OneDrive\\Documents\\IIITS\\OOP\\SLIDES\\Project\\OOP Project\\src\\CSV\\user_updatedCategoryRank.csv", data);
                    
                    UpdateUserData(connection);
                    return true;
                }
            }

        }
        catch (Exception e) {   
            e.printStackTrace();
        }
        return false;

    }

    public boolean resetPassword(Connection connection) {
        Scanner sc = new Scanner(System.in);
        String oldPass, newPass;
        boolean checkStatus = false;
        System.out.println("Enter Old Password");
        oldPass = sc.nextLine();
        
        if (getPassword().compareTo(oldPass) == 0) {
            System.out.println("Enter New Password");
            newPass = sc.nextLine();
            
            try {
                PreparedStatement stmt = connection.prepareStatement("update user_details SET password= ? where password= ? and username= ? ");
                stmt.setString(1, newPass);
                stmt.setString(2, newPass);
                stmt.setString(3, getUsername());
                
                if (stmt.execute()) {
                    System.out.println("Password Updated");
                    checkStatus = true;
                }
                
            } catch (Exception e) {
                System.out.println("Application error : Database connectivity Problem");
            }
        }
        sc.close();
        return checkStatus;
    }
    
    // public boolean deleteAccount(Connection connection) {
    //     Scanner sc = new Scanner(System.in);
    //     String oldPass;
    //     String uname;
    //     boolean checkStatus = false;
    //     int checkVal;
        
    //     System.out.println("NOTE:After Deletion of Account Your Data will be Erased and you will be log out \n Press 1:Continue\n Press 2:Stop Deletion");
    //     checkVal = sc.nextInt();
    //     sc.nextLine();
    //     if (checkVal == 1) {
    //         System.out.println("Enter Username");
    //         uname = sc.nextLine();
    //         System.out.println("Enter Your Password");
    //         oldPass = sc.nextLine();
            
    //         if (oldPass.compareTo(getPassword()) == 0 && uname.compareTo(getUsername()) == 0) {
    //             try {
    //                 PreparedStatement stmt = connection.prepareStatement("delete from user_details where username = ? and password = ?");
    //                 stmt.setString(1, uname);
    //                 stmt.setString(2, oldPass);
    //                 int ct = stmt.executeUpdate();
    //                 if (ct != 0) {
    //                     checkStatus = true;
    //                     System.out.println("Account Deleted");
                        
    //                 } else {
    //                     System.out.println("Can't Delete Account");
                        
    //                 }
    //                 return checkStatus;
    //             } catch (Exception e) {
    //                 System.out.println("Application error : Database connectivity Problem");
    //             }
    //         }
    //     }
    //     return checkStatus;
    // }
    
}