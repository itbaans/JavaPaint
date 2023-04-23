package ToolBars;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileWriting {
    
    public FileWriting() {

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss_a").format(new Date());
        String fileName = "file_" + timeStamp + ".txt";
        String fileContent = "This is the content of the file.";

        try {

            File myObj = new File("src\\savedFiles\\"+fileName);
            if (myObj.createNewFile()) {
              System.out.println("File created: " + myObj.getName());
            } 
            else {
              System.out.println("File already exists.");
            } 
            FileWriter fileWriter = new FileWriter(myObj);
            fileWriter.write(fileContent);
            fileWriter.close();
            System.out.println("File saved successfully with name: " + fileName);
        }
         catch (IOException e) {
            System.out.println("An error occurred while saving the file: " + e.getMessage());
        }

    }


}
