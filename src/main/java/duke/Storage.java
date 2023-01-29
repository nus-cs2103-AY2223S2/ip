package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String FILE_PATH;

    public Storage(String filePath) {
        FILE_PATH = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(this.FILE_PATH);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                Task task = fromData(data);
                if (!task.isEmpty()) {
                    tasks.add(task);
                }
            }

            scanner.close();
        } catch (IOException e) {
            if (!(e instanceof FileNotFoundException)) {
                throw new DukeException("Unexpected error when getting data.");
            }
            File file = new File(this.FILE_PATH);
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException ex) {
                throw new DukeException("Error when trying to create new file.");
            }
        }
        return tasks;
    }

    public void updateData(TaskList taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(this.FILE_PATH);
            String data = toData(taskList);
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Error has occurred when saving data.");
        }
    }

    private String toData(TaskList taskList) {
        String output = "";
        for (Task task : taskList.getTasks()) {
            output += task.toData();
            output += "\n";
        }
        return output;
    }

    private Task fromData(String data) throws DukeException {
        String[] typeSplit = data.split(" \\| ", 2);
        switch (typeSplit[0]) {
        case "Todo":
            return Todo.fromData(typeSplit[1]);
        case "Deadline":
            return Deadline.fromData(typeSplit[1]);
        case "Event":
            return Event.fromData(typeSplit[1]);
        default:
            throw new DukeException("Unknown entry in data file");
        }
    }
}
