package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Storage {

    private String filePath = "data/duke.txt";
    private File file;

    Storage() throws DukeException {
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException("File creation failed.");
        }
    }

    public TaskList readFromFile() throws DukeException {
        TaskList taskList = new TaskList();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] task = sc.nextLine().split(" \\| ");
                if ("T".equals(task[0])) {
                    taskList.addTask(new ToDo(task[2]));
                } else if ("D".equals(task[0])) {
                    taskList.addTask(new Deadline(task[2], task[3]));
                } else if ("E".equals(task[0])) {
                    taskList.addTask(new Event(task[2], task[3], task[4]));
                }
                if (task[1].equals("1")) {
                    taskList.markTask(taskList.getTaskList().size() - 1);
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException("File reading failed.");
        }
        return taskList;
    }

    public void writeToFile(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.getSize(); i++) {
                Task task = taskList.getTask(i);
                fw.write(task.toFileString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException("File writing failed.");
        }
    }

    public void clearFile() throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException("File clearing failed.");
        }
    }

}
