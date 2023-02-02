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
//            File data = new File("data");
//            if (!data.exists()) {
//                data.mkdir();
//                System.out.printf("Create folder: %s\n", data.getName());
//            }
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
     * in task list to an ArrayList<Task></Task>
     * @return The task list in the form of an ArrayList<Task></Task>
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

    public void updateTaskList(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter(dukeFilePath);
        for (int i = 0; i < tasks.getTaskListSize(); i++) {
            Task currTask = tasks.getTaskByIndex(i);
            writer.write(currTask.formatDescription() + "\n");
        }
        writer.close();
    }
}
