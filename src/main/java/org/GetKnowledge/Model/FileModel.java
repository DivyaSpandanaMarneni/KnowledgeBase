package org.GetKnowledge.Model;

import com.mysql.jdbc.Blob;
import com.sun.jmx.snmp.SnmpString;

/**
 * Created by Divya on 4/3/2017.
 */
public class FileModel {

    private Integer fileId;
    private String fileName;
    private String fileContent;
    private String keyWords;
    private String tagWord;

    public FileModel(){
        this.fileId = -1;
        this.fileName = null;
        this.fileContent = null;
        this.keyWords = null;
        this.tagWord = null;

    }

    public FileModel(String fileName, String fileContent, String keyWords, String tagWord){
        this.fileName = fileName;
        this.fileContent = fileContent;
        this.keyWords = keyWords;
        this.tagWord = tagWord;

    }



    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getTagWord() {
        return tagWord;
    }

    public void setTagWord(String tagWord) {
        this.tagWord = tagWord;
    }



}
