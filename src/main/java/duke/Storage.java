package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;


/**
 * Handles loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final TaskList taskList;

    /**
     * Constructor for Storage class.
     *
     * @param list Task list to read/write from the current list of tasks.
     */
    public Storage(TaskList list) {
        this.taskList = list;
    }

    /**
     * Reads from file and store data (tasks) into tasks list.
     */
    public void initalizeList() {
        try {
            File yourFile = new File("duke.txt");
            yourFile.createNewFile();
            assert yourFile.exists();
            try (BufferedReader br = new BufferedReader(new FileReader("duke.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    char taskType = line.charAt(1);

                    boolean isMarked = false;
                    Character markedChar = line.charAt(4);

                    String description = line.substring(7);
                    if (markedChar.equals('X')) {
                        isMarked = true;
                    }
                    switch (taskType) {
                    case 'T':
                        Todo todoTask = new Todo(description, isMarked);
                        taskList.add(todoTask);
                        break;
                    case 'E':
                        String modifiedDescription = description.split(" \\(from: ")[1];
                        LocalDate fromDate = LocalDate.parse(modifiedDescription.split(" to: ")[0],
                                DateTimeFormatter.ofPattern("MMM d yyyy"));
                        LocalDate toDate = LocalDate.parse(modifiedDescription.split(" to: ")[1]
                                .split("\\)")[0],
                                        DateTimeFormatter.ofPattern("MMM d yyyy"));
                        Event eventTask = new Event(description.split(" \\(from: ")[0],
                                isMarked, fromDate, toDate);
                        taskList.add(eventTask);
                        break;
                    case 'D':
                        LocalDate byDate = LocalDate.parse(description.split(" \\(by: ")[1]
                                .split("\\)")[0], DateTimeFormatter.ofPattern("MMM d yyyy"));
                        Deadline deadlineTask = new Deadline(description.split(" \\(by: ")[0],
                                isMarked, byDate);
                        taskList.add(deadlineTask);
                        break;
                    default:
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets tasks from tasks list and write data (tasks) into file.
     */
    public void saveToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("duke.txt"));
            for (int i = 0; i < taskList.size(); i++) {
                writer.write(taskList.get(i) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
