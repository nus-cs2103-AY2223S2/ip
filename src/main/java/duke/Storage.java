package duke;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

public class Storage {

    private static String strDir = "../../../data";
    private static String fileName = "../../../data/duke.txt";

    public static void saveToFile(TaskList tasks) throws IOException {

        // create directory
        Path path = Paths.get(strDir);
        Files.createDirectories(path);
    
        // output string to file
        PrintWriter out = new PrintWriter(fileName);
        out.println(tasks.outputList());
        out.close();

    }

    public static void loadFromFile(TaskList tasks) throws IOException {
        try { 
            Path fileNamePath = Path.of(fileName);
            String strData = Files.readString(fileNamePath);

            String[] strTasks = strData.split("\n");
            for (String strTask : strTasks) {
                if (strTask.length() == 1) return;  // for handling empty file, it still contains "\n"
                ParsedLoadedTask parsedTaskInfo = Parser.parseLoadTask(strTask);
                Task task;

                switch (parsedTaskInfo.taskType) {
                case ('T'):
                    task = new ToDo(parsedTaskInfo.taskName, parsedTaskInfo.isDone);
                    tasks.add(task);
                    break;
                case ('D'):
                    task = new Deadline(parsedTaskInfo.taskName, parsedTaskInfo.dueDate, parsedTaskInfo.isDone);
                    tasks.add(task);
                    break;
                case ('E'):
                    task = new Event(parsedTaskInfo.taskName, parsedTaskInfo.fromDate, 
                    parsedTaskInfo.toDate, parsedTaskInfo.isDone);
                    tasks.add(task);
                    break;
                }
            }
        } catch (NoSuchFileException e) {
            // do nothing if no file exists (nothing to )
        }
    }
}
