package org.GetKnowledge.Persistance;

import com.mysql.jdbc.*;
import com.mysql.jdbc.Statement;
import org.GetKnowledge.Model.FileModel;

import java.net.URL;
import java.sql.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Divya on 4/7/2017.
 */
public class Database {

    public static final String URL = "jdbc:mysql://localhost:3306/KnowledgeDB";
    public static final String uname = "root";
    public static final String passwd = "Mdsp1891#";

    Connection connection = null;
    PreparedStatement preparedStatement = null;



    public FileModel uploadFileDB(FileModel fileModel) throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

        }catch (Exception e){
            e.printStackTrace();
        }
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        connection = DriverManager.getConnection(URL,uname,passwd);
        int fileId = -1;
        String fileContent = null;
        String fileName = null;
        String keyWords = null;
        String tagWord = null;
        String sql = null;

        FileModel fileModelResult = new FileModel();

        System.out.println("Connected and updating to database");

        sql = "Select * from File where fileName = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,fileModel.getFileName());

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            fileId = resultSet.getInt("fileId");
            fileName = resultSet.getString("fileName");
            fileContent = resultSet.getBlob("fileContent").toString();
            keyWords = resultSet.getString("keyWords");

        }

        if(fileId == -1){
            sql = "INSERT INTO File (fileName, fileContent) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,fileModel.getFileName());
            byte[] byteContent = fileModel.getFileContent().getBytes();
            com.mysql.jdbc.Blob blobData =(com.mysql.jdbc.Blob) connection.createBlob();
            blobData.setBytes(1,byteContent);
            preparedStatement.setBlob(2,blobData);
            int status = -1;
            try {
                status = preparedStatement.executeUpdate();
            }catch (SQLException sq){
                sq.printStackTrace();
            }
            System.out.println("Insert status "+status);
            if(status > 0){
                System.out.println("inside the inserting block");
                ResultSet resultSetInsert = preparedStatement.getGeneratedKeys();
                if(resultSetInsert.next()){
                    fileId = resultSetInsert.getInt(1);
                }

                fileModel.setFileId(fileId);
                System.out.println("The file id after inserting "+fileId);
            }
            return fileModel;
        }

        else{
            fileModelResult.setFileId(fileId);
            fileModelResult.setFileName(fileName);
            fileModelResult.setKeyWords(keyWords);
            return fileModelResult;
        }
        //return null;
    }
}
