package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class that handles loading tasks from the file and saving tasks in the file
 */
public class Storage {

    /**
     * Constructor for a Storage object
     */
    public Storage() {
        File folder = new File("./data/");
        File dataFile = new File("./data/duke.txt");
        try {
            if (!folder.exists()) {
                folder.mkdir();
            }
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException  e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Writes tasks to a specified file for it to be saved
     *
     * @param taskList TaskList object from which the tasks to be written are taken
     */
    public void writeToFile(TaskList taskList) {
        try {
            FileWriter myWriter = new FileWriter("./data/duke.txt");
            for (int pos = 0; pos < taskList.size(); pos++) {
                Task task = taskList.getTask(pos);
                myWriter.write(task.encode() + "\n");
            }
                myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Decodes String version of tasks from text file into corresponding Task objects
     *
     * @param data String version of a task object
     * @return A task object created with the attributes specified in the data argument
     */
    public Task decode(String data) {
        // Use examples in qn page to decode into tasks
        char taskType = data.charAt(0);
        Task currentTask = null;
        switch (taskType) {
        case 'T':
            if (data.charAt(4) == 'X') {
                currentTask = new Todo(true, data.substring(8));
            } else {
                currentTask = new Todo(false,data.substring(8));
            }
            break;
        case 'D':
            String tempString = data.substring(8);
            String taskName = tempString.substring(0, tempString.indexOf(" | "));
            String deadline = tempString.substring(tempString.indexOf(" | ") + 3);

            String reformattedInput = taskName + " /by " + deadline;

            if (data.charAt(4) == 'X') {
                currentTask = new Deadline(true, reformattedInput);
            } else {
                currentTask = new Deadline(false, reformattedInput);
            }
            break;
        case 'E':
            String temp = data.substring(8);
            String taskTitle = temp.substring(0, temp.indexOf(" | "));
            String datesString = temp.substring(temp.indexOf(" | ") + 3);
            String fromDate = datesString.substring(0, datesString.indexOf(" | "));
            String toDate = datesString.substring(datesString.indexOf(" | ") + 3);

            String newInput = taskTitle + " /from " + fromDate + " /to " + toDate;

            if (data.charAt(4) == 'X') {
                currentTask = new Event(true, newInput);
            } else {
                currentTask = new Event(false, newInput);
            }
            break;
        }
        return currentTask;
    }

    /**
     * Reads tasks from text file and stored them in a TaskList object
     *
     * @return TaskList object that contains the tasks read from specified text file
     */
    public TaskList read() {
        TaskList retrievedList = new TaskList();
        try {
            File myObj = new File("./data/duke.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                retrievedList.addTask(this.decode(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return retrievedList;
    }
}
