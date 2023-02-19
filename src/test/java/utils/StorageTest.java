package utils;

import org.junit.jupiter.api.Test;
import treebot.tasks.Task;
import treebot.utils.Storage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    @Test
    void loadTask() {
        try {
            String testDataFilePath = "src/test/data/testData.txt";
            ArrayList<Task> testTasks = new Storage(testDataFilePath).loadTasks();
            assertEquals(testTasks.get(0).toString(), "[T][] do homework");
            assertEquals(testTasks.get(1).toString(), "[T][X] read book");
            assertEquals(testTasks.get(2).toString(), "[D][X] hand in assignment (by: Dec 2 2019 1822)");
            assertEquals(testTasks.get(3).toString(), "[E][] project meeting (from: May 4 2022 1900 to: May 4 2022 2000)");
        } catch (FileNotFoundException e) {
            fail();
        }
    }




}
