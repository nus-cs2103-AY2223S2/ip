package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class StorageTest {
    @Test
    public void testLoad() {
        File testFile = new File("test.txt");
        if (testFile.exists()) {
            testFile.delete();
        }
        try {
            testFile.createNewFile();
            FileWriter fw = new FileWriter(testFile);
            fw.write("T | 0 | test" + System.lineSeparator());
            fw.write("D | 1 | test | 2020-01-01" + System.lineSeparator());
            fw.write("E | 0 | test | 2020-01-01 | 2020-01-02" + System.lineSeparator());
            fw.close();
            Storage storage = new Storage("test.txt");
            ArrayList<Task> list = storage.load();
            assertEquals(3, list.size());
            assertEquals("T | 0 | test", list.get(0).toFile());
            assertEquals("D | 1 | test | 2020-01-01", list.get(1).toFile());
            assertEquals("E | 0 | test | 2020-01-01 | 2020-01-02", list.get(2).toFile());
        } catch (IOException e) {
            fail("IOException thrown");
        } catch (DukeException e) {
            fail("DukeException thrown");
        } finally {
            testFile.delete();
        }
    }

    @Test
    public void testSave() throws IOException{
        File testFile = new File("test.txt");
        if (testFile.exists()) {
            testFile.delete();
        }
        try {
            testFile.createNewFile();
            Storage storage = new Storage("test.txt");
            TaskList list = new TaskList(new ArrayList<Task>());
            list.add(new Todo("test", false));
            list.add(new Deadline("test", LocalDate.parse("2020-01-01"), true));
            list.add(new Event("test", LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-02"), false));
            storage.save(list);
            Scanner s = new Scanner(testFile);
            assertEquals("T | 0 | test", s.nextLine());
            assertEquals("D | 1 | test | 2020-01-01", s.nextLine());
            assertEquals("E | 0 | test | 2020-01-01 | 2020-01-02", s.nextLine());
            s.close();
        // } catch (IOException e) {
        //     fail("IOException thrown");
        } catch (DukeException e) {
            fail("DukeException thrown");
        } finally {
            testFile.delete();
        }
    }
}