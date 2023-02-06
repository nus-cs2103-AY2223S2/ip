package duke.functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import duke.tasks.TaskList;

/**
 * Main storage class that controls the initialisation and reading of database, and the population of TaskList.
 *
 * @author JamesLiuZX
 *     AY2223-S2 CS2103T
 */
public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads duke.txt and populates existing TaskList in the appropriate format.
     *
     * @param dl TaskList passed down from duke.functions.Duke main class.
     */
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

    /**
     * Creates duke.txt in filePath specified if not already existing.
     *
     */
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
