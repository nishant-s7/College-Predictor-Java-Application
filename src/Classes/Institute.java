package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Institute{
    
    private int round;
    private String InstituteName;
    private String program;
    private String Quota;
    private String category;
    private String Gender;
    private int OpeningRank;
    private int ClosingRank;
    
    public Institute() {
    
    }
    
    public Institute(int round, String instituteName, String program, String quota, String category, String gender, int openingRank, int closingRank) {
        setRound(round);
        setInstituteName(instituteName);
        setProgram(program);
        setQuota(quota);
        setCategory(category);
        setGender(gender);
        setOpeningRank(openingRank);
        setClosingRank(closingRank);
    }
    
    public int getRound() {
        return round;
    }
    
    public void setRound(int round) {
        this.round = round;
    }
    
    public String getInstituteName() {
        return InstituteName;
    }
    
    public void setInstituteName(String instituteType) {
        InstituteName = instituteType;
    }
    
    public String getProgram() {
        return program;
    }
    
    public void setProgram(String program) {
        this.program = program;
    }
    
    public String getQuota() {
        return Quota;
    }
    
    public void setQuota(String quota) {
        Quota = quota;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getGender() {
        return Gender;
    }
    
    public void setGender(String gender) {
        Gender = gender;
    }
    
    public int getOpeningRank() {
        return OpeningRank;
    }
    
    public void setOpeningRank(int openingRank) {
        OpeningRank = openingRank;
    }
    
    public int getClosingRank() {
        return ClosingRank;
    }
    
    public void setClosingRank(int closingRank) {
        ClosingRank = closingRank;
    }
    
    public ObservableList<String> getAllInstitute(Connection connection) {

        ObservableList<String> InstituteList = FXCollections.observableArrayList();
        try {
            PreparedStatement statement2 = connection.prepareStatement("select distinct institute from round1 order by Institute");
            ResultSet resultSet = statement2.executeQuery();

            while (resultSet.next()) {
                Institute inst = new Institute(1, resultSet.getString("Institute"), null, null, null, null, 0, 0);
                InstituteList.add(inst.getInstituteName());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return InstituteList;

    }

    public ObservableList<String> getAllBranch(Connection connection) {

        ObservableList<String> instituteList = FXCollections.observableArrayList();
        try {
            PreparedStatement statement2 = connection.prepareStatement("select distinct program from round1 order by program");
            ResultSet resultSet = statement2.executeQuery();

            while (resultSet.next()) {
                Institute branch = new Institute(1,null, resultSet.getString("Program"), null, null, null, 0, 0);
                instituteList.add(branch.getProgram());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instituteList;

    }
    
}