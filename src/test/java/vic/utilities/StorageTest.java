package vic.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import vic.exceptions.DukeException;
import vic.tasks.Deadline;
import vic.tasks.Event;
import vic.tasks.ITask;
import vic.tasks.Todo;

class StorageTest {

    @Test
    void load() throws DukeException {
        Storage storage = new Storage("data/jtest.txt");
        ArrayList<ITask> actual = storage.load();
        ArrayList<ITask> expected = new ArrayList<>();
        generateSampleTasks(expected);

        assertEquals(actual.toString(), expected.toString());

    }

    private void generateSampleTasks(ArrayList<ITask> expected) {
        ITask todo = new Todo("x", false);
        ITask deadline = new Deadline("return book",
                new Date(Long.parseLong("1575280800000")), true);
        ITask event = new Event("buy burger", new Date(Long.parseLong("1575280800000")),
                new Date(Long.parseLong("1575291600000")), false);
        expected.add(todo);
        expected.add(deadline);
        expected.add(event);
    }

    @Test
    void saveAll() throws DukeException, FileNotFoundException {
        ArrayList<ITask> tasks = new ArrayList<>();
        Storage storage = new Storage("data/jtestsave.txt");
        generateSampleTasks(tasks);
        storage.saveAll(tasks);
        File actualFile = new File("data/jtestsave.txt");
        Scanner fileReader = new Scanner(actualFile);
        String actual = null;
        while (fileReader.hasNextLine()) {
            actual = fileReader.nextLine();
        }
        fileReader.close();

        File expectFile = new File("data/jtestexpectsave.txt");
        fileReader = new Scanner(expectFile);
        String expected = null;
        while (fileReader.hasNextLine()) {
            expected = fileReader.nextLine();
        }
        fileReader.close();
        assertNotNull(actual);
        assertEquals(actual, expected);
    }
}
