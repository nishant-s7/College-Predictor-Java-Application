package Classes;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SearchInstitute extends User {
    private String instituteName;
    private String branch;
    private int round;
    private int select;
    
    public SearchInstitute() {

    }

    public SearchInstitute(String username, String password){
        super(username,password);
    }

    public SearchInstitute(String userName,String email, String password, String category,String gender,int categoryRank,int generalRank, int select){
        super(userName, email, category, gender, categoryRank, generalRank);
        setSelect(select);
    }

    public SearchInstitute(int round, String gender, String instituteName, String branchName, String category, int categoryRank) {
        setRound(round);
        setGender(gender);
        setInstituteName(instituteName);
        setBranch(branchName);
        setCategory(category);
        setCategoryRank(categoryRank);
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public int getSelect() {
        return select;
    }
    
    public void setRound(int round) {
        this.round = round;
    }
    
    public int getRound() {
        return round;
    }
    
    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }
    
    public String getInstituteName() {
        return instituteName;
    }
    
    public void setBranch(String branch) {
        this.branch = branch;
    }
    
    public String getBranch() {
        return branch;
    }
    
    public String defineGender(String str) {
        String gender;
        if (str.toLowerCase().contains("f") && str.toLowerCase().compareTo("female") == 0) {
            gender = "Female-only (including Supernumerary)";
        } else {
            gender = "Gender-Neutral";
        }
        return gender;
    }
    
    public ObservableList<Institute> IncognitoSearch(Connection connection, int round, String gender, String instituteName, String branchName, String category, int categoryRank) {

        String r;
        int rank;

        // System.out.println("Enter JOSAA Round i.e(1-6)");
        r = Integer.toString(round);
        r = "round".concat(r);

        // System.out.println("Enter Gender")
        setGender(gender);
        gender = defineGender(getGender());

        // System.out.println("Enter Institute Name");
        setInstituteName(instituteName);
        // System.out.println("Enter Branch Name");
        setBranch(branchName);
        // System.out.println("Enter Category");
        setCategory(category);
        // System.out.println("Enter Category Rank");
        rank= categoryRank;

        ObservableList<Institute> InstituteList1 = FXCollections.observableArrayList();
        try{

            PreparedStatement statement1= connection.prepareStatement("select * from "+r+" where Institute LIKE ? and Program LIKE ? and Category LIKE ? and gender= ? and Opening_Rank <= ? and Closing_Rank >= ?" );
            statement1.setString(1,"%"+ getInstituteName() +"%");
            statement1.setString(2,"%"+ getBranch()+"%");
            statement1.setString(3,"%"+ getCategory()+"%");
            statement1.setString(4,gender);
            statement1.setInt(5,rank);
            statement1.setInt(6, rank);

            ResultSet resultSet1 = statement1.executeQuery();
            while (resultSet1.next()) {
                Institute inst = new Institute(getRound(), resultSet1.getString("Institute"), resultSet1.getString("Program"), resultSet1.getString("Quota"), resultSet1.getString("Category"), resultSet1.getString("Gender"), resultSet1.getInt("Opening_rank"), resultSet1.getInt("Closing_rank"));
                InstituteList1.add(inst);
            }

        }
        catch(Exception e){
            System.out.println("Application error : Database connectivity Problem");
        }
        return InstituteList1;

    }

    
    public ObservableList<Institute> searchCollege(Connection connection) {

        String r;
        String gender = defineGender(super.getGender());

        try {
            switch (select) {
                case 1 -> {
                    ObservableList<Institute> InstituteList1 = FXCollections.observableArrayList();
                    r = Integer.toString(getRound());
                    r = "round".concat(r);

                    PreparedStatement statement1 = connection.prepareStatement("select * from " + r + " where gender= ? and category=? and Opening_Rank < ? and closing_rank > ?");
                    statement1.setString(1, gender);
                    statement1.setString(2, this.getCategory());
                    statement1.setInt(3, this.getCategoryRank());
                    statement1.setInt(4, this.getCategoryRank());

                    ResultSet resultSet = statement1.executeQuery();
                    while (resultSet.next()) {
                        Institute inst = new Institute(
                                getRound(),
                                resultSet.getString("Institute"),
                                resultSet.getString("Program"),
                                resultSet.getString("Quota"),
                                resultSet.getString("Category"),
                                resultSet.getString("Gender"),
                                resultSet.getInt("Opening_rank"),
                                resultSet.getInt("Closing_rank")
                            );
                        InstituteList1.add(inst);
                    }
                    return InstituteList1;
                }
                case 2 -> {
                    ObservableList<Institute> InstituteList2 = FXCollections.observableArrayList();

                    r = Integer.toString(getRound());
                    r = "round".concat(r);

                    PreparedStatement statement2 = connection.prepareStatement("select * from " + r + " where Institute LIKE ? and gender=? and category=? and Opening_Rank < ? and Closing_Rank > ?");
                    statement2.setString(1, "%" + getInstituteName() + "%");
                    statement2.setString(2, gender);
                    statement2.setString(3, this.getCategory());
                    statement2.setInt(4, this.getCategoryRank());
                    statement2.setInt(5, this.getCategoryRank());

                    ResultSet resultSet2 = statement2.executeQuery();
                    while (resultSet2.next()) {
                        Institute inst = new Institute(
                            getRound(),
                            resultSet2.getString("Institute"),
                            resultSet2.getString("Program"),
                            resultSet2.getString("Quota"),
                            resultSet2.getString("Category"),
                            resultSet2.getString("Gender"),
                            resultSet2.getInt("Opening_rank"),
                            resultSet2.getInt("Closing_rank")
                        );
                        InstituteList2.add(inst);
                    }
                    return InstituteList2;
                }
                case 3 -> {
                    ObservableList<Institute> InstituteList3 = FXCollections.observableArrayList();

                    r = Integer.toString(getRound());
                    r = "round".concat(r);

                    PreparedStatement statement3 = connection.prepareStatement("select * from " + r + " where Program LIKE ? and gender=? and category=? and Opening_Rank < ? and Closing_Rank > ?");
                    statement3.setString(1, "%" + getBranch() + "%");
                    statement3.setString(2, gender);
                    statement3.setString(3, this.getCategory());
                    statement3.setInt(4, this.getCategoryRank());
                    statement3.setInt(5, this.getCategoryRank());

                    ResultSet resultSet3 = statement3.executeQuery();
                    while (resultSet3.next()) {
                        Institute inst = new Institute(
                            getRound(),
                            resultSet3.getString("Institute"),
                            resultSet3.getString("Program"),
                            resultSet3.getString("Quota"),
                            resultSet3.getString("Category"),
                            resultSet3.getString("Gender"),
                            resultSet3.getInt("Opening_rank"),
                            resultSet3.getInt("Closing_rank")
                        );
                        InstituteList3.add(inst);
                    }
                    return InstituteList3;
                }
                case 4 -> {
                    ObservableList<Institute> InstituteList4 = FXCollections.observableArrayList();

                    r = Integer.toString(getRound());
                    r = "round".concat(r);

                    PreparedStatement statement4 = connection.prepareStatement("select * from " + r + " where Institute LIKE ? and  Program LIKE ? and gender=? and category=? and Opening_Rank < ? and Closing_Rank > ?");
                    statement4.setString(1, "%" + getInstituteName() + "%");
                    statement4.setString(2, "%" + getBranch() + "%");
                    statement4.setString(3, gender);
                    statement4.setString(4, this.getCategory());
                    statement4.setInt(5, this.getCategoryRank());
                    statement4.setInt(6, this.getCategoryRank());
                    ResultSet resultSet4 = statement4.executeQuery();

                    while (resultSet4.next()) {
                        Institute inst = new Institute(
                            getRound(),
                            resultSet4.getString("Institute"),
                            resultSet4.getString("Program"),
                            resultSet4.getString("Quota"),
                            resultSet4.getString("Category"),
                            resultSet4.getString("Gender"),
                            resultSet4.getInt("Opening_rank"),
                            resultSet4.getInt("Closing_rank")
                        );
                        InstituteList4.add(inst);
                    }
                    return InstituteList4;
                }
            }
        } catch (Exception e) {
            System.out.println("Application error : Database connectivity Problem");
        }
        return null;
    }
}
