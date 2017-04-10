package org.GetKnowledge.Presentation;

import org.GetKnowledge.Business.BusinessLayer;
import org.GetKnowledge.Model.FileModel;
import org.json.JSONObject;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

/**
 * Created by Divya on 4/3/2017.
 */
@WebServlet(name = "FileUploadServlet", urlPatterns = {"/fileUpload"})
@MultipartConfig
public class FileUploadServlet extends HttpServlet {

    BusinessLayer businessLayer = new BusinessLayer();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");
        File uploads = new File("./uploads");
        String pathNew = getServletContext().getRealPath("uploads");


        String jsonData = request.getParameter("data");
        if (jsonData != null) {
            System.out.println("jsong data from angulalr http is " + jsonData);
        }

        System.out.println("Inside servlet doPost FileUploadServlet");
        PrintWriter printWriter = response.getWriter();

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = request.getReader();
        String str = null;
        while ((str = bufferedReader.readLine()) != null){
            stringBuilder.append(str);
        }
         
        //// TODO: 4/8/2017 check why the json object and string are coming as null 
        JSONObject jsonObject = new JSONObject(stringBuilder.toString());
        Part filePart =  (Part)  jsonObject.get("file");

        final String fileNameAbs = getFileName(filePart);

        Path p = Paths.get(fileNameAbs);
        String fileName = p.getFileName().toString();


        System.out.println("The file name is "+fileName);

        OutputStream outputStream = null;
        InputStream fileContent = null;
        //final PrintWriter printWriter = response.getWriter();

        try{
            outputStream = new FileOutputStream(new File(pathNew+ File.separator + fileName));

            fileContent = filePart.getInputStream();
            System.out.println("File content is "+fileContent.toString());
            //add the code to store it in apache jena database and fire the queries
            //another database to store the details of files and the keywords associated and the tags
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = fileContent.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            FileModel fileModel = new FileModel();
            fileModel.setFileName(fileName);
            fileModel.setFileContent(fileContent.toString());
            FileModel fileModelResult = null;

            //logic for storing sending it to the jena connection layer and uploading it into database
            try {
                System.out.println("Sending file to database");
                fileModelResult = businessLayer.uploadFile(fileModel);
            }catch(Exception e){
                e.printStackTrace();
            }











            printWriter.println("New file " + fileName + " created at " + pathNew);

            //after the file is created store it in the database and pass the file to the database layer/ rest client layer
            //get the results and display them in the UI - keywords + tag word page



        }catch (FileNotFoundException f){
            printWriter.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            printWriter.println("<br/> ERROR: " + f.getMessage());
            f.printStackTrace();
        }finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (fileContent != null) {
                fileContent.close();
            }
            if (printWriter != null) {
                printWriter.close();
            }
        }

    }


    private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");

        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
