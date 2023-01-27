package duke.util;

import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private String filePath;
    private Ui ui;

    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
    }

    public void save(TaskList taskList) throws IOException {
        File file = new File(filePath);
        File parentFile = file.getParentFile();

        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file);

        for (Task task : taskList) {
            String str = task.encode();
            fw.write(str);
            fw.write(System.lineSeparator());
        }
        ui.printTaskSaved();
        fw.close();
    }

    public TaskList load(String filePath) throws FileNotFoundException {
        File file = new File(filePath); // create a File for the given file path
        TaskList taskList = new TaskList();

        Scanner sc = new Scanner(file); // create a Scanner using the File as the source

        ui.printTaskExistence();
        while (sc.hasNext()) {

            String[] inputs = sc.nextLine().trim().split(" \\| ");

            TaskType taskType = TaskType.valueOf(inputs[0].toUpperCase());

            String task = inputs[2];
            boolean status = Boolean. parseBoolean(inputs[1]);

            switch(taskType) {
                case TODO:
                    Task todo = new ToDo(task, status);
                    ui.printTask(todo);
                    taskList.add(todo);
                    break;
                case DEADLINE:
                    Task deadline = new Deadline(task, status, inputs[3]);
                    ui.printTask(deadline);
                    taskList.add(deadline);
                    break;
                case EVENT:
                    Task event = new Event(task, status, inputs[3], inputs[4]);
                    ui.printTask(event);
                    taskList.add(event);
                    break;
                default:
                    // Will not reach here
            }
        }

        sc.close();
        return taskList;
    }
}
