import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class DukeFileManager {

    private static final String USER_HOME_DIRECTORY = System.getProperty("user.home");

    public static TaskList loadTaskListFromDisk() {
        Path dukeFilePath = Path.of(USER_HOME_DIRECTORY, "duke.txt");
        File dukeFile = new File(dukeFilePath.toString());
        TaskList taskList = new TaskList();

        try {
            Scanner sc = new Scanner(dukeFile);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task task = DukeFileManager.parseTask(line);
                taskList.addTask(task);
            }

            sc.close();
            return taskList;
        } catch (FileNotFoundException e) {
            // if this is a new user, there'll be no `duke.txt` to load from disk
            return taskList;
        } catch (DukeInvalidFileFormatException e) {
            // if the file format is corrupt, inform the user and return whatever was loaded
            // from disk
            System.out.println(e.getMessage());
            return taskList;
        }
    }

    private static Task parseTask(String line) throws DukeInvalidFileFormatException {
        // need to escape the literal character "|" since it is a special character used
        // in regex
        String[] tokens = line.split("\\|");

        if (tokens.length < 3) {
            throw new DukeInvalidFileFormatException();
        }

        String taskType = tokens[0];
        String isDoneString = tokens[1];
        boolean isDone;

        if (isDoneString.equals("1")) {
            isDone = true;
        } else if (isDoneString.equals("0")) {
            isDone = false;
        } else {
            throw new DukeInvalidFileFormatException();
        }

        if (taskType.equals("T")) {
            String[] taskNameArray = Arrays.copyOfRange(tokens, 2, tokens.length);
            String taskName = String.join("|", taskNameArray);
            TodoTask todoTask = new TodoTask(taskName);

            if (isDone) {
                todoTask.markDone();
            }

            return todoTask;
        } else if (taskType.equals("D")) {

            if (tokens.length < 4) {
                throw new DukeInvalidFileFormatException();
            }

            String[] taskNameArray = Arrays.copyOfRange(tokens, 2, tokens.length - 1);
            String taskName = String.join("|", taskNameArray);
            String by = tokens[tokens.length - 1];
            DeadlineTask deadlineTask = new DeadlineTask(taskName, by);

            if (isDone) {
                deadlineTask.markDone();
            }

            return deadlineTask;
        } else if (taskType.equals("E")) {

            if (tokens.length < 5) {
                throw new DukeInvalidFileFormatException();
            }

            String[] taskNameArray = Arrays.copyOfRange(tokens, 2, tokens.length - 2);
            String taskName = String.join("|", taskNameArray);
            String from = tokens[tokens.length - 2];
            String to = tokens[tokens.length - 1];
            EventTask eventTask = new EventTask(taskName, from, to);

            if (isDone) {
                eventTask.markDone();
            }

            return eventTask;
        } else {
            throw new DukeInvalidFileFormatException();
        }
    }

    public static void saveTaskListToDisk(TaskList taskList) {
        Path dukeFilePath = Path.of(USER_HOME_DIRECTORY, "duke.txt");
        File dukeFile = new File(dukeFilePath.toString());

        try {
            // overwrite old file
            FileWriter fw = new FileWriter(dukeFile, false);
            fw.write(taskList.toDukeFileString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: failed to save task list to disk");
        }
    }
}
