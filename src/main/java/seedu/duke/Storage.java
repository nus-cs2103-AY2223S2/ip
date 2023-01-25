package seedu.duke;

import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws DukeException{
        try {
            return readAddFileContents(filePath);
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found");
        }
    }

    public void write(TaskList tasks) throws DukeException {
        try {
            writeToFile(tasks.tasksList, "data/duke.txt");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void writeToFile(List<Task> storage, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task element : storage) {
            String mark = "0";
            if (element.getStatusIcon().equals("X")) mark = "1";
            if (element instanceof Todo) {
                fw.write("T | " + mark + " | " + element.getDescription());
            }
            if (element instanceof Deadline) {
                fw.write("D | " + mark + " | " + element.getDescription() + " | " + ((Deadline) element).getBy());
            }
            if (element instanceof Event) {
                fw.write("E | " + mark + " | " + element.getDescription() + " | " + ((Event) element).getFrom() + " | " + ((Event) element).getTo());
            }
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    public List<Task> readAddFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        List<Task> storage = new ArrayList<>();
        Scanner fileScanner = new Scanner(f);
        while (fileScanner.hasNext()) {
            String[] currArray = fileScanner.nextLine().split("\\|");
            boolean mark = false;
            if (currArray[1].trim().equals("1")) mark = true;
            if (currArray[0].trim().equals("T")) {
                Todo t = new Todo(currArray[2].trim());
                if (mark) {
                    t.mark();
                }
                storage.add(t);
            } else if (currArray[0].trim().equals("D")) {
                Deadline d = new Deadline(currArray[2], currArray[3]);
                if (mark) {
                    d.mark();
                }
                storage.add(d);
            } else {
                Event e = new Event(currArray[2], currArray[3], currArray[4]);
                if (mark) {
                    e.mark();
                }
                storage.add(e);
            }
        }
        return storage;
    }
}
