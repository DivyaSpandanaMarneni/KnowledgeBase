package org.GetKnowledge.Presentation;

import freemarker.ext.util.IdentityHashMap;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.GetKnowledge.Business.BusinessLayer;
import org.GetKnowledge.Jena.JenaCrud;
import org.GetKnowledge.Model.FileModel;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Created by Divya on 4/3/2017.
 */
@WebServlet(name = "FileUploadServletBkp", urlPatterns = {"/upload"})
@MultipartConfig
public class FileUploadServletBkp extends HttpServlet{
    JenaCrud jenaCrud = new JenaCrud();
    BusinessLayer businessLayer = new BusinessLayer();
    private static final long serialVersionUID = 1L;
    static String templateDir = "/";
    static String resultTemplateName = "Results.ftl";
    Template resultTemplate = null;

    private Configuration configuration;

    public void init(){
        try{
            configuration = new Configuration();
            configuration.setServletContextForTemplateLoading(getServletContext(),"/");
            resultTemplate = configuration.getTemplate(resultTemplateName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        response.setContentType("text/html");
        File uploads = new File("./uploads");
        Map<String,Object> resultsMap = new IdentityHashMap();
        resultTemplate = configuration.getTemplate(resultTemplateName);
        String pathNew = getServletContext().getRealPath("uploads");
        System.out.println("New path is "+pathNew);
        //final String path = new File(request.getParameter("destination")).getAbsolutePath();
        final Part filePart = request.getPart("file");
        final String fileNameAbs = getFileName(filePart);

        java.nio.file.Path p = Paths.get(fileNameAbs);
        String fileName = p.getFileName().toString();

        System.out.println("The file name is "+fileName);

        OutputStream outputStream = null;
        InputStream fileContent = null;
        BufferedWriter toClient = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
        //final PrintWriter printWriter = response.;

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
            //upload data to jena fuseki
            File fileObj = new File(pathNew+File.separator+fileName);
            //String uploadDataSet = FilenameUtils.removeExtension(fileName);
            String uploadDataSet = fileName.substring(0,fileName.lastIndexOf('.'));
            //JenaCrud.uploadRDF(fileObj,"http://localhost:3030/"+uploadDataSet);
            //JenaCrud.getJsonFile("http://localhost:3030/"+uploadDataSet+"/query","SELECT * WHERE { ?subject ?predicate ?object}",pathNew,uploadDataSet);

            FileModel fileModel = new FileModel();
            fileModel.setFileName(fileName);
            fileModel.setFileContent(fileContent.toString());
            FileModel fileModelResult = null;

            //logic for storing sending it to the jena connection layer and uploading it into database
            try {
                System.out.println("Sending file to database");
                fileModelResult = businessLayer.uploadFile(fileModel);
                System.out.println("The  file name after uploading "+fileModelResult.getFileName());
            }catch(Exception e){
                e.printStackTrace();
            }

            int fileId = fileModelResult.getFileId();
            System.out.println("The file id result is "+fileId);

            fileId = fileModelResult.getFileId();
            System.out.println("The file id result is "+fileId);

            //Entire content along with keywords is present in the fileModelResult
            //Details from fileModel result are to displayed in the reuslts page and prompt the user to enter tagWord
                resultsMap.put("fileName",fileModelResult.getFileName());
                resultsMap.put("keyWords","abc def ghi jkl mno");


            try {
                resultTemplate.process(resultsMap, toClient);
                toClient.flush();
            }catch (TemplateException te){
                te.printStackTrace();
            }

            //printWriter.println("New file " + fileName + " created at " + pathNew);
        }catch (FileNotFoundException f){
//            printWriter.println("You either did not specify a file to upload or are "
//                    + "trying to upload a file to a protected or nonexistent "
//                    + "location.");
//            printWriter.println("<br/> ERROR: " + f.getMessage());
            System.out.println("Issue while uploading the file");
            f.printStackTrace();
        }finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (fileContent != null) {
                fileContent.close();
            }
//            if (printWriter != null) {
//                printWriter.close();
//            }
            if(toClient != null)
                toClient.close();
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
