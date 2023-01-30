package duke;

import duke.exceptions.DukeExceptions;
import duke.exceptions.InvalidFilePathException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks(Parser parser) throws DukeExceptions {
        File file = new File(this.filePath);
        ArrayList<Task> resultingList = new ArrayList<>();

        if(!file.exists() || file.length() == 0) {
            return resultingList; // make a new arraylist for the tasks in this new file
        } else {
            try {
                List<String> content = Files.readAllLines(Paths.get(this.filePath));
                for(String line : content) {
                    Task thisTask = parser.translateFileToTaskList(line);
                    resultingList.add(thisTask);
                }
                return resultingList;
            } catch(IOException e) {
                throw new InvalidFilePathException();
            }
        }
    }

    public void updateTasks(TaskList tasks) throws DukeExceptions {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < tasks.getSize(); i++) {
                Task thisTask = tasks.getTasks().get(i);
                sb.append(thisTask.getDataString()).append(System.lineSeparator());
            }
            fw.write(sb.toString());
            fw.close();
        } catch(IOException e) {
            throw new InvalidFilePathException();
        }
    }
}