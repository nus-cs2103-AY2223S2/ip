package duke.functions;

import duke.tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void readDatabase(TaskList dl) {
        File f = new File(filePath);
        try {
            Scanner fileReader = new Scanner(f);
            Parser p = new Parser(dl);
            p.parseDatabase(fileReader, dl);
            fileReader.close();
            dl.toString();
        } catch (FileNotFoundException e) {
            this.initialiseDatabase();
        }
    }


    private void initialiseDatabase() {
        File f = new File(filePath);
        File dir = new File("duke.txt");
        dir.mkdir();
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating database.");
        }
    }

}
