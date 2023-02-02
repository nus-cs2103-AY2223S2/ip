package duke;

import duke.tasks.Deadline;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.ToDos;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage {

    public static String path = "DukeData/tasks.txt";
    public static String dirPath = "DukeData";

    /**
     * Creates Storage for Duke to load tasks from previous run
     * and save tasks in the current run.
     */
    public Storage() {}

    /**
     * Saves the task into a separate file after each update from the user.
     *
     * @param taskList the current tasklist.
     */
    public void saveTasks(TaskList taskList) {
        try {
            String input = "";
            FileWriter fw = new FileWriter(path);

            for (Task tasks: taskList.tasks) {
                input += (tasks.saveString() + "\n");
            }
            fw.write(input);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads all the task from the previous run.
     * Tasks are all saved in a separate file.
     *
     * @param taskList the current tasklist
     * @exception IOException catch for Input and Output inconsistencies.
     */
    public void loadTasks(TaskList taskList) throws IOException {
        File folder = new File(dirPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            String ref = sc.nextLine();
            String[] text = ref.split("\\|");
            Task curr = new Task("");

            switch (text[0]) {
                case ("T"):
                    curr = new ToDos(text[2]);
                    break;
                case ("D"):
                    curr = new Deadline(text[2], text[3]);
                    break;
                case ("E"):
                    curr = new Events(text[2], text[3], text[4]);
                    break;
                default:
                    System.out.println("Error while Loading up the file");
                    break;
            }

            if (!curr.emptyTask()) {
                if (text[1].equals("1")) {
                    curr.mark();
                }
                taskList.add(curr);
            }
        }
        sc.close();
    }
}
