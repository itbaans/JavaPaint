package ToolBars;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import LayerEngineRecources.LayersEngine;

public class FileWriting {
    
    public FileWriting(LayersEngine layers) {

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss_a").format(new Date());
        String fileName = "file_" + timeStamp + ".ser";       

        try {

            FileOutputStream fileOut = new FileOutputStream("src\\savedFiles\\"+fileName);           
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(layers);
            objectOut.close();
            System.out.println("File saved successfully with name: " + fileName);
        }
         catch (IOException e) {
            System.out.println("An error occurred while saving the file: " + e.getMessage());
        }

    }


}
