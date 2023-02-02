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

public class Storage {
    private File file;
    private Path path;

    public Storage(String filePath) {
        if (filePath.isBlank()) {
            path = Paths.get(System.getProperty("user.dir"), "src", "main", "tasks.txt");
        } else {
            path = Paths.get(System.getProperty("user.dir"), filePath);
        }
        file = new File(path.toUri());
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Unable to create .txt file.");
        }
    }

    public void writeToFile(TaskList listOfTasks) {
        try {
            List<String> commandsToWrite = new ArrayList<>();
            for (Task task : listOfTasks) {
                String command = task.getTaskType() + "," + task.getStatusIcon() + "," + task.getDescription() + "," + task.getTimeline();
                commandsToWrite.add(command);
            }
            Files.write(path, commandsToWrite);
        } catch (IOException e) {
            System.out.println("There is an error when writing to the file");
        }
    }

    public TaskList loadFile() throws InvalidCommandException {
        path = Paths.get(System.getProperty("user.dir"), "src", "main", "tasks.txt");
        File fileToRead = new File(path.toUri());
        TaskList listOfTasks = new TaskList();
        try {
            Scanner sc = new Scanner(fileToRead);
            while (sc.hasNext()) {
                String[] command = sc.nextLine().split(",");
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
            sc.close();
            return listOfTasks;
        } catch (FileNotFoundException e) {
            throw new InvalidCommandException("");
        }
    }
}
