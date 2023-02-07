package duke;

import tasks.Task;
import tasks.Todo;
import tasks.Deadline;
import tasks.Event;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadFile() throws Exception {
        List<Task> initTasks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine().trim();
        while (line != null) {
            String[] strArr = line.split(" \\| ");
            String type = strArr[0];
            boolean isCompleted = Integer.parseInt(strArr[1]) == 1;
            String taskDesc = strArr[2];

            switch (type) {
            case "T":
                initTasks.add(new Todo(taskDesc, isCompleted));
                break;
            case "D":
                initTasks.add(new Deadline(taskDesc, isCompleted, strArr[3]));
                break;
            case "E":
                initTasks.add(new Event(taskDesc, isCompleted, strArr[3], strArr[4]));
                break;
            }
            line = reader.readLine();
        }
        return initTasks;
    }

    public void saveListToFile(TaskList taskList, Ui ui) {
        try {
            FileWriter writer = new FileWriter(filePath, false);
            List<Task> list = taskList.getTaskList();
            for (Task t : list) {
                writer.write(t.parseToSave() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            ui.showSavingError();
        }
    }

}
