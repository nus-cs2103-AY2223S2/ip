package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.ToDos;

public class Storage {

    private static String path = "DukeData/tasks.txt";
    private static String dirPath = "DukeData";

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
     * Asserts that the file must exist when you are loading tasks in.
     *
     * @param taskList the current tasklist
     * @throws IOException catch for Input and Output inconsistencies.
     */
    public void loadTasks(TaskList taskList) throws IOException, DukeException {
        File folder = new File(dirPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }

        assert file.exists() : "File to save tasks does not exist!";

        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            String ref = sc.nextLine();
            String[] text = ref.split("\\|");
            Task curr = new Task("");

            switch (text[0]) {
            case "T":
                curr = new ToDos(text[2], text[3]);
                break;
            case "D":
                curr = new Deadline(text[2], text[3], text[4]);
                break;
            case "E":
                curr = new Events(text[2], text[3], text[4], text[5]);
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
