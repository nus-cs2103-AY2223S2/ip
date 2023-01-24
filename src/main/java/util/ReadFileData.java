package util;

import task.Task;
import task.TaskManager;

import java.io.*;
import java.util.Scanner;;

public class ReadFileData {
    private static final String FILEPATH = "src/main/java/data/UserTasks.txt";

    private static TaskManager taskManager = new TaskManager();

    public static void printFileContents() throws IOException, ClassNotFoundException {
        File file = new File(FILEPATH);
        Scanner s = new Scanner(file); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
            taskManager.addTaskToList(new Task(s.nextLine()));
        }

    }

    public static void main(String[] args) throws DukeException {
        try {
            printFileContents();
            taskManager.displayList();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            throw new DukeException("IOException when printing file contents");
        } catch (ClassNotFoundException e) {
            throw new DukeException("ClassNotFoundException when printing file contents");
        }
    }

}
