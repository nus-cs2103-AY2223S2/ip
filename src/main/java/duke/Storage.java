package duke;

import exception.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

import java.io.*;
import java.util.Scanner;

public class Storage {
    private static String filePath;
    private static String directoryPath;
    private TaskList tasks;

    public Storage(String filePath, String directoryPath) {
        this.filePath = filePath;
        this.directoryPath = directoryPath;
    }

    public TaskList loadTasks() throws FileNotFoundException, IOException, DukeException {
        File directory = new File(directoryPath);
        final File file = new File(filePath);

        if (!directory.exists()) {
            directory.mkdir();
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
            }
        }
        tasks = new TaskList();
        Scanner sc = new Scanner(file);
        String taskString;
        String[] s;
        while (sc.hasNext()) {
            taskString = sc.nextLine();
            s = taskString.split(" \\| ");

            switch (s[0].strip()) {
            case "T":
                Todo todo = new Todo(s[2]);
                if (s[1].equals("1")) {
                    todo.markDone();
                }
                tasks.addTask(todo, true);
                break;
            case "D":
                Deadline deadline = new Deadline(s[2], s[3]);
                if (s[1].equals("1")) {
                    deadline.markDone();
                }
                tasks.addTask(deadline, true);
                break;
            case "E":
                Event event = new Event(s[2], s[3], s[4]);
                tasks.addTask(event, true);
                break;
            default:
                break;
            }
        }
        return tasks;
    }

    public void saveTasks() throws IOException {
        String path = "./data/duke.txt";
        String directoryPath = "./data";
        File directory = new File(directoryPath);
        final File file = new File(path);

        if (!directory.exists()) {
            directory.mkdir();
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
            }
        }

        final FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        for (int i = 0; i < tasks.getSize(); i++) {
            bw.append(tasks.getTask(i).getData());
        }
        bw.close();
    }
}
