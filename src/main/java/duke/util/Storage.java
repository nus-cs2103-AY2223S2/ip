package duke.util;
import duke.util.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    public Storage(String filePath) {
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataArr = data.split("@");
                Parser.fileInpProcessor(dataArr);
            }
            System.out.println("loaded your past list *.*");
            Parser.list();
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Starting a fresh list :)");
            File dir = new File("data");
            boolean dirCreated = dir.mkdir();
            File file = new File("data/duke.txt");
            try {
                if (dir.createNewFile()) {
                    System.out.println("File created: " + dir.getName());
                }
            } catch (IOException err) {
                System.out.println("An error occurred.");
                err.printStackTrace();
            }
        }
    }

}
