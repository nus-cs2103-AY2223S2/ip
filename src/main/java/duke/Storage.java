package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String dukeFilePath;

    public Storage(String filePath) {
        try {
            this.dukeFilePath = filePath;
            File dukeTxt = new File(filePath);
            if (dukeTxt.createNewFile()) {
                System.out.printf("Create file: %s\n", dukeTxt.getName());
            }
        } catch (IOException e) {
            System.out.printf("Something went wrong ): %s", e);
        }
    }

    /**
     * Reads the task list and adds all tasks currently
     * in task list to an <code>ArrayList</code>
     * @return The task list in the form of an <code>ArrayList</code>
     * @throws DukeException if task list is empty
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File(dukeFilePath));
            while (scan.hasNext()) {
                String currentLine = scan.nextLine();
                Task newTask = Parser.convertTaskFromLineInTaskList(currentLine);
                taskList.add(newTask);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File is not found.");
        }
        if (taskList.isEmpty()) {
            throw new DukeException("No task list yet.");
        } else {
            return taskList;
        }
    }

    /**
     * Updates the task list.
     * @param tasks list of updated tasks
     * @throws IOException if something went wrong while updating
     */
    public void updateTaskList(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter(dukeFilePath);
        for (int i = 0; i < tasks.getTaskListSize(); i++) {
            Task currTask = tasks.getTaskByIndex(i);
            writer.write(currTask.formatDescription() + "\n");
        }
        writer.close();
    }
}
