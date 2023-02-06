package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void loadTest_loadTextFileData_loadedDataMatchesTextFileData() {
        Storage storage = new Storage("data/duke.txt");
        try {
            TaskList tasks = new TaskList(storage.load());
            String tasksToTest = tasks.toStorageData();
            File f = new File("data/duke.txt");
            String storageTest = "";
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
