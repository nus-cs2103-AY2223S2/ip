package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Stores file path and has methods to implement file I/O operations
 */
public class Storage {
    private final Path filePath;
    private final Path foldPath;

    public Storage() throws DukeException {
        try {
            String fileSep = System.getProperty("file.separator");
            String userDir = System.getProperty("user.dir");
            foldPath = Paths.get( userDir + fileSep + "data");
            filePath = Paths.get(foldPath + fileSep + "duke.txt");
            if (!Files.isDirectory(foldPath)) {
                Files.createDirectory(foldPath);
                Files.createFile(filePath);
            } else if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new DukeException("An error occurred while loading duke.storage.");
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File f = new File(String.valueOf(filePath));
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String taskData = s.nextLine();
                String[] splitData = taskData.split("[|]", 5);
                Task task;
                switch (taskData.charAt(0)) {
                case 'T':
                    task = new ToDo(splitData[2]);
                    break;
                case 'D':
                    task = new Deadline(splitData[2], splitData[3]);
                    break;
                case 'E':
                    task = new Event(splitData[2], splitData[3], splitData[4]);
                    break;
                default:
                    throw new DukeException("Invalid duke.storage format.");
                }
                if (splitData[1].equals("X")) {
                    task.markAsDone();
                }
                list.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Unable to load.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Save file corrupted.");
        }
        return list;
    }

    public void store(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath.toString());
            for (int i = 0; i < tasks.getSize(); ++i) {
                Task task = tasks.getTask(i);
                StringBuilder text = new StringBuilder();
                text.append(task.getType() + "|" + task.getStatusIcon() + "|");
                if (task instanceof ToDo) {
                    text.append(task.getDescription() + "\n");
                } else {
                    text.append(task.getDescription() + "|");
                    if (task instanceof Deadline) {
                        text.append(((Deadline) task).getBy() + "\n");
                    } else if (task instanceof Event) {
                        text.append(((Event) task).getFrom() + "|" + ((Event) task).getTo() + "\n");
                    }
                }
                fw.write(text.toString());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
