package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * deals with loading and saving tasks to/from the tasklist in Duke
 */
public class Storage {

    private String filePath;

    /**
     * constructor for new Storage instance
     * 
     * @param filePath path to the file for read from/ write to
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * obtains the lists of tasks from the text file and copies it into an arraylist
     * 
     * @return new Arraylist of tasks
     * @throws DukeException error reading tasks from the file
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        if (f.exists()) {
            try {
                Scanner sn = new Scanner(f);
                while (sn.hasNext()) {
                   String data = sn.nextLine();
                   Task taskToAdd = createTask(data);
                   tasks.add(taskToAdd);
                }
                sn.close();
            } catch (FileNotFoundException e) {
                System.out.println("File could not be found!");
            }
        } else {
            createFile();
        }
        return tasks;
    }

    /**
     * writes the tasks from the current Duke program into the text file
     * 
     * @param tasks TaskList containing current list of tasks
     * @throws DukeException error writing tasks into the text file
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            String dataString = tasks.toStorageData();
            fw.write(dataString);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error writing data into file.");
        }
    }

    /**
     * creates a directory data containing a file if the directory and/or the file does not exist yet
     *
     * @throws DukeException error in creating file
     */
    private void createFile() throws DukeException {
        try {
            File data = new File("./data");
            File d = new File(filePath);
            data.mkdir();
            d.createNewFile();
        } catch (IOException d) {
            throw new DukeException("Error creating file!");
        }
    }

    /**
     * creates a Task instance based on data from Duke.txt
     *
     * @param data information of a Task stored in Duke.txt
     * @return Task instance
     * @throws DukeException error creating task instance from task in duke.txt
     */
    private Task createTask(String data) throws DukeException {
        String[] info = data.split(Task.DIVIDER);
        String type = info[0];
        String status = info[1];
        String description = info[2];
        String note = info[3];
        Task task;

        switch (type) {
        case "[T]":
            task = new Todo(description, note);
            break;
        case "[D]":
            String dateString = info[4];
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM u");
            LocalDate date = LocalDate.parse(dateString, dateFormatter);
            task = new Deadline(description, date, note);
            break;
        case "[E]":
            String from = info[4];
            String to = info[5];
            task = new Event(description, from, to, note);
            break;
        default:
            throw new DukeException("Unknown task type found in storage file");
        }
        if (status.equals("X")) {
            task.mark();
        }
        return task;
    }
}
