import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler {

    String filePath = "data/duke.txt";
    File file;

    FileHandler() throws DukeException {
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
                switch (task[0]) {
                case "T":
                    taskList.addTask(new ToDo(task[2]));
                    break;
                case "D":
                    taskList.addTask(new Deadline(task[2], task[3]));
                    break;
                case "E":
                    taskList.addTask(new Event(task[2], task[3], task[4]));
                    break;
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
