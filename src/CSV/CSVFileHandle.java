package CSV;

// import com.opencsv.CSVWriter;
import Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
// import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CSVFileHandle {

    // static public void WriteLineIntoCSVForDeletion(String filepath, String[] data) {
        
    //     File file = new File(filepath);
        
    //     try {
            
    //         FileWriter outputfile = new FileWriter(file, true);
    //         CSVWriter writer = new CSVWriter(outputfile, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
    //         writer.writeNext(data);
            
    //         writer.close();
    //     } catch (Exception e) {
    //         System.out.println("Application error : File handling problem.");
    //     }
    // }

    // static public void WritelineIntoCSV(String filepath, String[] data) {
        
    //     File file = new File(filepath);
        
    //     try {
    //         FileWriter outputfile = new FileWriter(file, false);
    //         CSVWriter writer = new CSVWriter(outputfile, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
    //         writer.writeNext(data);
            
    //         writer.close();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    // static public void addCSVtoDatabase(String filepath, Connection connection) {
    //     try {
    //         BufferedReader lineReader = new BufferedReader(new FileReader(filepath));
    //         String lineText;
    //         PreparedStatement preparedStatement = connection.prepareStatement("insert into user_details values (?,?,?,?,?,?,?)");
    //         while ((lineText = lineReader.readLine()) != null) {
    //             String[] data = lineText.split(",");
    //             String name = data[0];
    //             String email = data[1];
    //             String pass = data[2];
    //             String gender = data[3];
    //             String category = data[4];
    //             String generalRank = data[5];
    //             String categoryRank = data[6];
                
    //             preparedStatement.setString(1, name);
    //             preparedStatement.setString(2, email);
    //             preparedStatement.setString(3, pass);
    //             preparedStatement.setString(4, gender);
    //             preparedStatement.setString(5, category);
    //             preparedStatement.setInt(6, Integer.parseInt(generalRank));
    //             preparedStatement.setInt(7, Integer.parseInt(categoryRank));
    //             preparedStatement.addBatch();
    //         }
            
    //         lineReader.close();
    //         preparedStatement.executeBatch();
    //         System.out.println("User data uploaded");
            
    //     } catch (Exception e) {
    //         System.out.println("Application error : Database connectivity problem.");
    //     }
    // }
    
    // static public void addUser_deletedCsvToDatabasesUser_deleted(String filepath, Connection connection) {
    //     try {
    //         BufferedReader lineReader = new BufferedReader(new FileReader(filepath));
    //         String lineText;
    //         PreparedStatement preparedStatement = connection.prepareStatement("insert into user_deleted values (?,?,?,?,?,?,?)");
    //         while ((lineText = lineReader.readLine()) != null) {
    //             String[] data = lineText.split(",");
    //             String name = data[0];
    //             String email = data[1];
    //             String pass = data[2];
    //             String gender = data[3];
    //             String category = data[4];
    //             String generalRank = data[5];
    //             String categoryRank = data[6];
                
    //             preparedStatement.setString(1, name);
    //             preparedStatement.setString(2, email);
    //             preparedStatement.setString(3, pass);
    //             preparedStatement.setString(4, gender);
    //             preparedStatement.setString(5, category);
    //             preparedStatement.setInt(6, Integer.parseInt(generalRank));
    //             preparedStatement.setInt(7, Integer.parseInt(categoryRank));
    //             preparedStatement.addBatch();
    //         }
            
    //         lineReader.close();
    //         preparedStatement.executeBatch();
    //         System.out.println("Deleted User data uploaded to User_deleted table");
            
            
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    // static public void UpdateCategoryData_CSVtoDatabase(String filepath, Connection connection) {
    //     try {
    //         BufferedReader lineReader = new BufferedReader(new FileReader(filepath));
    //         String lineText;
    //         PreparedStatement preparedStatement = connection.prepareStatement("update user_details set Category = ? where username=? and email=?");
            
    //         while ((lineText = lineReader.readLine()) != null) {
    //             String[] data = lineText.split(",");
    //             String name = data[0];
    //             String email = data[1];
    //             String value = data[2];
    //             preparedStatement.setString(1, value);
    //             preparedStatement.setString(2, name);
    //             preparedStatement.setString(3, email);
    //             preparedStatement.addBatch();
    //         }
    //         lineReader.close();
    //         preparedStatement.executeBatch();
    //     } catch (Exception e) {
    //         System.out.println("caught");
    //         e.printStackTrace();
    //     }
    // }

    // static public void UpdateGenderData_CSVtoDatabase(String filepath, Connection connection) {
    //     try {
    //         BufferedReader lineReader = new BufferedReader(new FileReader(filepath));
    //         String lineText;
    //         PreparedStatement preparedStatement = connection.prepareStatement("update user_details set gender = ? where username=? and email=?");
            
    //         while ((lineText = lineReader.readLine()) != null) {
    //             String[] data = lineText.split(",");
    //             String name = data[0];
    //             String email = data[1];
    //             String value = data[2];
    //             preparedStatement.setString(1, value);
    //             preparedStatement.setString(2, name);
    //             preparedStatement.setString(3, email);
    //             preparedStatement.addBatch();
    //         }
    //         lineReader.close();
    //         preparedStatement.executeBatch();
    //     } catch (Exception e) {
    //         System.out.println("caught");
    //         e.printStackTrace();
    //     }
    // }

    // static public void UpdateGeneralRankData_CSVtoDatabase(String filepath, Connection connection) {
    //     try {
    //         BufferedReader lineReader = new BufferedReader(new FileReader(filepath));
    //         String lineText;
    //         PreparedStatement preparedStatement = connection.prepareStatement("update user_details set generalRank = ? where username=? and email=?");
            
    //         while ((lineText = lineReader.readLine()) != null) {
    //             String[] data = lineText.split(",");
    //             String name = data[0];
    //             String email = data[1];
    //             String value = data[2];
    //             preparedStatement.setString(1, value);
    //             preparedStatement.setString(2, name);
    //             preparedStatement.setString(3, email);
    //             preparedStatement.addBatch();
    //         }
    //         lineReader.close();
    //         preparedStatement.executeBatch();
    //     } catch (Exception e) {
    //         System.out.println("Application error : Database connectivity problem.");
    //         System.out.println(e);
    //     }
    // }

    // static public void UpdateCategoryRankData_CSVtoDatabase(String filepath, Connection connection) {
    //     try {
    //         BufferedReader lineReader = new BufferedReader(new FileReader(filepath));
    //         String lineText;
    //         PreparedStatement preparedStatement = connection.prepareStatement("update user_details set categoryRank = ? where username=? and email=?");
            
    //         while ((lineText = lineReader.readLine()) != null) {
    //             String[] data = lineText.split(",");
    //             String name = data[0];
    //             String email = data[1];
    //             String value = data[2];
    //             preparedStatement.setString(1, value);
    //             preparedStatement.setString(2, name);
    //             preparedStatement.setString(3, email);
    //             preparedStatement.addBatch();
    //         }
    //         lineReader.close();
    //         preparedStatement.executeBatch();
    //     } catch (Exception e) {
    //         System.out.println("Application error : Database connectivity problem.");
    //         System.out.println(e);
    //     }
    // }
    
    // static public void DeleteCSVFIle(String filepath) {
    //     try {
    //         File f = new File(filepath);
    //         if (f.delete())
    //         {
    //             System.out.println(f.getName() + " deleted after execution.");
    //         } else {
    //             System.out.println("failed");
    //         }
    //     } catch (Exception e) {
    //         System.out.println("Application error : File handling problem.");
    //     }
    // }
    
    static public void UploadJosaaRoundCutoffToDatabase(String filepath, Connection connection, int round) {
        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(filepath));
            String lineText;
            String roundNo = "round".concat(Integer.toString(round));
            lineReader.readLine();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into " + roundNo + " values (?,?,?,?,?,?,?)");
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String Institute = data[0];
                String Program = data[1];
                String quota = data[2];
                String category = data[3];
                String gender = data[4];
                String Opening_Rank = data[5];
                String Closing_Rank = data[6];
                
                preparedStatement.setString(1, Institute);
                preparedStatement.setString(2, Program);
                preparedStatement.setString(3, quota);
                preparedStatement.setString(4, category);
                preparedStatement.setString(5, gender);
                preparedStatement.setString(6, Opening_Rank);
                preparedStatement.setString(7, Closing_Rank);
                
                preparedStatement.addBatch();
            }
            
            lineReader.close();
            preparedStatement.executeBatch();
            System.out.println("User data uploaded");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public ObservableList<Institute> bulkUpdation(String filepath, Connection connection, int round){

        ObservableList<Institute> updatedInstitute = FXCollections.observableArrayList();
        try{
            BufferedReader lineReader = new BufferedReader(new FileReader(filepath));
            String lineText;
            String roundNo = "round".concat(Integer.toString(round));
            lineReader.readLine();

            PreparedStatement preparedStatement = connection.prepareStatement("update " + roundNo + " SET Opening_Rank=?, Closing_Rank=? where Institute= ? and Program= ? and Quota=? and Category= ? and Gender= ? ");
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String Institute = data[0];
                String Program = data[1];
                String quota = data[2];
                String category = data[3];
                String gender = data[4];
                String Opening_Rank = data[5];
                String Closing_Rank = data[6];

                preparedStatement.setString(1, Opening_Rank);
                preparedStatement.setString(2, Closing_Rank);
                preparedStatement.setString(3, Institute);
                preparedStatement.setString(4, Program);
                preparedStatement.setString(5, quota);
                preparedStatement.setString(6, category);
                preparedStatement.setString(7, gender);

                if(preparedStatement.executeUpdate() == 1)
                {
                    Institute inst = new Institute(1,Institute,Program,quota,category,gender,Integer.parseInt(Opening_Rank),Integer.parseInt(Closing_Rank));
                    updatedInstitute.add(inst);
                }
            }

            lineReader.close();
            return updatedInstitute;

        }
        catch (Exception e){
            e.printStackTrace();
            return updatedInstitute;
        }

    }

}
