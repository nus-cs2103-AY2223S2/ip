package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void loadTest() {
        Storage storage = new Storage("data/duke.txt");
        try {
            TaskList tasks = new TaskList(storage.load());
            String tasksToTest = tasks.toStorageData();
            File f = new File("data/duke.txt");
            String storageTest = "";
            //storageTest is in // // // format 
            //tasksToTest is in [][] xxx format 
            try {
                Scanner scn = new Scanner(f);
                while (scn.hasNext()) {
                    storageTest += scn.nextLine();
                    storageTest += "\n";
                }
                scn.close();
            assertEquals(storageTest.trim(), tasksToTest);
            } catch (FileNotFoundException e) {
                System.out.println("File does not exist");
            }
        } catch (DukeException d) {
            System.out.println(d.toString());
        }

    }
}
