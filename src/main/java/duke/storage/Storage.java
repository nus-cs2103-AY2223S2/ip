package duke.storage;

import duke.exception.InvalidInputException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final File storageFile;

    public Storage() {
        this.filePath = "data/duke.txt";
        this.storageFile = new File(filePath);
    }

    public Storage(String filePath) {
        this.filePath = filePath;
        this.storageFile = new File(filePath);
    }

    public TaskList load() throws FileNotFoundException, InvalidInputException {
        TaskList list = new TaskList();
        if (!storageFile.exists()) {
            return list;
        }

        Scanner sc = new Scanner(storageFile);
        while (sc.hasNextLine()) {
            String instruction = sc.nextLine().strip();
            String[] information = instruction.split("\\s\\|\\s");

            String taskTag = information[0];
            boolean isDone = information[1].equals("[X]");
            String description = information[2];

            if (taskTag.equals("[T]")) {
                TodoTask todo = new TodoTask(description);
                if (isDone) {
                    todo.markAsDone();
                }
                list.addTask(todo);
            } else if (taskTag.equals("[D]")) {
                String date = information[3];
                DeadlineTask deadline = new DeadlineTask(description, LocalDate.parse(date));
                if (isDone) {
                    deadline.markAsDone();
                }
                list.addTask(deadline);
            } else {
                String from = information[3];
                String to = information[4];
                EventTask event = new EventTask(description, LocalDate.parse(from), LocalDate.parse(to));
                if (isDone) {
                    event.markAsDone();
                }
                list.addTask(event);
            }
        }
        return list;
    }

    //@@author Yufannnn-reused
    //https://nus-cs2103-ay2223s2.github.io/website/schedule/week3/topics.html#W3-4c
    //with minor modification, nice and concise function to overwrite text to a given file.
    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
    //@@author

    public void save(TaskList list) throws IOException {
        StringBuilder record = new StringBuilder();
        for (int i = 0; i < list.remainingTasks(); i++) {
            DukeTask task = list.getTask(i);
            record.append(task.storageString()).append(System.lineSeparator());
        }
        writeToFile(filePath, record.toString());
    }
}
