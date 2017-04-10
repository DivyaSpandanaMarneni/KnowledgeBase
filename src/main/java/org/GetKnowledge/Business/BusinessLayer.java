package org.GetKnowledge.Business;

import org.GetKnowledge.Model.FileModel;
import org.GetKnowledge.Persistance.Database;

import java.io.File;
import java.sql.SQLException;

/**
 * Created by Divya on 4/8/2017.
 */
public class BusinessLayer {

    Database databaseObj = new Database();
    public FileModel uploadFile(FileModel fileModel) throws Exception{

        return databaseObj.uploadFileDB(fileModel);
    }
}
