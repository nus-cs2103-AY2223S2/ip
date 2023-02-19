package treebot.utils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import treebot.tasks.Deadline;
import treebot.tasks.Event;
import treebot.tasks.Task;
import treebot.tasks.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    void loadTask_loadsSuccessfullyFromDataTextFile() {
        Storage testStorage = new Storage("src/test/data/testData.txt");
        try {
            ArrayList<Task> loadedTasks = testStorage.loadTasks();

            assertEquals(loadedTasks.get(0).toString(), "[T][] do homework");
            assertEquals(loadedTasks.get(1).toString(), "[T][X] read book");
            assertEquals(loadedTasks.get(2).toString(), "[D][X] hand in assignment (by: Dec 2 2019 1822)");
            assertEquals(loadedTasks.get(3).toString(), "[E][] project meeting (from: May 4 2022 1900 to: May 4 2022 2000)");


        } catch (FileNotFoundException e) {
            // file exists since this is test data
        }

    }
    @Test
    void saveTask_saveSuccessfullyToDataTextFile() {

        final String testFilePath = "src/test/data/testData-save.txt";
        clearTextFile(testFilePath);
        File file = new File(testFilePath);
        assert file.length() == 0 : "File is cleared before testing";
        Storage testSaveStorage =  new Storage(testFilePath);

        try {
            testSaveStorage.saveTasks(getDummyTaskListState());

        } catch (IOException e) {
            // file exists
        }

        try {
            Scanner sc = new Scanner(new File(testFilePath));
            assertEquals(sc.nextLine(), "T|0|do homework");
            assertEquals(sc.nextLine(), "T|1|read book");
            assertEquals(sc.nextLine(), "D|1|hand in assignment|2/12/2019 1822");
            assertEquals(sc.nextLine(), "E|0|project meeting|4/5/2022 1900|4/5/2022 1200");

        } catch (IOException e) {
            // file exists
        }

    }

    ArrayList<Task> getDummyTaskListState() {
        Todo todoTask = new Todo("do homework");
        Todo todoTask2 = new Todo("read book");
        todoTask2.markAsDone();

        LocalDateTime deadline = LocalDateTime.parse("2/12/2019 1822",
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm")
        );

        LocalDateTime eventStart = LocalDateTime.parse("4/5/2022 1900",
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm")
        );

        LocalDateTime eventEnd = LocalDateTime.parse("4/5/2022 1200",
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm")
        );

        Deadline deadlineTask = new Deadline("hand in assignment", deadline);
        deadlineTask.markAsDone();
        Event eventTask = new Event("project meeting", eventStart, eventEnd);

        ArrayList<Task> taskListState = new ArrayList<>();
        taskListState.add(todoTask);
        taskListState.add(todoTask2);
        taskListState.add(deadlineTask);
        taskListState.add(eventTask);

        return taskListState;
    }

    void clearTextFile(String filePath) {
        try {
            new PrintWriter(filePath).close();
        } catch (FileNotFoundException e) {
            // file exists
        }
    }


}
