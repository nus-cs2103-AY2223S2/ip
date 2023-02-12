package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exceptions.InvalidCommandException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

public class Storage {
    private File file;
    private Path path;

    public Storage() {
        path = Paths.get(System.getProperty("user.home"), "CS2103T", "tasks.txt");
        file = new File(path.toUri());

        assert path != null;
        assert file != null;

        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Unable to create .txt file.");
        }
    }

    public TaskList loadFile() throws InvalidCommandException {
        TaskList listOfTasks = new TaskList();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                loadLine(sc, listOfTasks);
            }
            sc.close();
            return listOfTasks;
        } catch (FileNotFoundException e) {
            throw new InvalidCommandException("");
        }
    }

    private void loadLine(Scanner scanner, TaskList listOfTasks) throws InvalidCommandException {
        String[] command = scanner.nextLine().split(",");
        Task newTask;
        switch (command[0]) {
        case "T":
            newTask = new Todo(command[2]);
            break;
        case "D":
            newTask = new Deadline(command[2], command[3]);
            break;
        case "E":
            newTask = new Event(command[2], command[3], command[4]);
            break;
        default:
            throw new InvalidCommandException("");
        }
        if (command[1].equals("X")) {
            newTask.markAsDone();
        }
        listOfTasks.add(newTask);
    }

    public void writeToFile(TaskList listOfTasks) {
        try {
            List<String> commandsToWrite = new ArrayList<>();
            for (Task task : listOfTasks) {
                String command = task.getTaskType() + "," + task.getStatusIcon() + "," + task.getDescription() + ","
                        + task.getTimeline();
                commandsToWrite.add(command);
            }
            assert path != null;

            Files.write(path, commandsToWrite);
        } catch (IOException e) {
            System.out.println("There is an error when writing to the file");
        }
    }
}
