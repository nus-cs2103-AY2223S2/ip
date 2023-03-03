package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StorageTest {

    private static final String FILENAME = "/dukeList.txt";
    private Storage storage;
    private File file;

    @BeforeEach
    public void setUp() {
        storage = new Storage();
        file = new File(System.getProperty("user.dir") + FILENAME);
    }

    @AfterEach
    public void tearDown() {
        file.delete();
    }

    @Test
    public void testIsDirectory() {
        storage.createDirectory();
        assertTrue(storage.isDirectory());
    }

    @Test
    public void testCreateDirectory() {
        storage.createDirectory();
        assertTrue(file.exists());
    }

    @Test
    public void testWriteToFile() throws IOException {
        storage.createDirectory();
        String listOfTasks = "task1, task2, task3";
        storage.writeToFile(listOfTasks);
        Scanner scanner = new Scanner(file);
        String content = scanner.useDelimiter("\\A").next();
        assertEquals(listOfTasks, content);
    }

    @Test
    public void testReadFromFile() throws IOException {
        storage.createDirectory();
        FileWriter fileWriter = new FileWriter(file, false);
        fileWriter.write("task1, task2, task3");
        fileWriter.close();
        storage.readFromFile();
    }


}
