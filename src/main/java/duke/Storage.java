package duke;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.*;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Storage class is used for loading stored tasks from a file and saving tasks back to the file
 * upon any user modification.
 *
 * @author      Tseng Chen-Yu
 * @version     %I%, %G%
 * @since       1.0
 */
public class Storage {
    private File saveFile;
    private final static String SAVE_FILE_DIR_PATH = System.getProperty("user.dir") + "/data/";
    private final static String SAVE_FILE_PATH = SAVE_FILE_DIR_PATH + "dukeSave.txt";
    public Storage() {
        File savedFileDir = new File(SAVE_FILE_DIR_PATH);
        File savedTaskFile = new File(SAVE_FILE_PATH);
        try {
            if (!savedFileDir.exists()) {
                savedFileDir.mkdir();
            }
            if (!savedTaskFile.exists()) {
                savedTaskFile.createNewFile();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        assert savedFileDir.exists() : "saved file directory does not exist.";
        assert savedTaskFile.exists() : "saved task file does not exist.";
        this.saveFile = savedTaskFile;
    }

    private void processAndAddTodo(String taskDescription, boolean completed, ArrayList<Task> userTasks) {
        Todo newTodo = new Todo(taskDescription, completed);
        userTasks.add(newTodo);
    }

    private void processAndAddDeadline(String[] parts, String taskDescription,
                                       boolean completed, ArrayList<Task> userTasks) {
        try {
            String[] deadlineParts = parts[3].split(" ");
            String deadlineDate = deadlineParts[0];
            String deadlineTime = deadlineParts[1];
            LocalDate parsedDate = LocalDate.parse(deadlineDate);
            Date parsedTime = new SimpleDateFormat("hh:mm").parse(deadlineTime);
            Deadline newDeadline = new Deadline(taskDescription, parsedDate, parsedTime, completed);
            userTasks.add(newDeadline);
        } catch (DateTimeParseException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void processAndAddEvent(String[] parts, String taskDescription,
                                    boolean completed, ArrayList<Task> userTasks) {
        try {
            String[] eventStartParts = parts[3].split(" ");
            String[] eventEndParts = parts[4].split(" ");
            String eventStartDate = eventStartParts[0];
            String eventStartTime = eventStartParts[1];
            String eventEndDate = eventEndParts[0];
            String eventEndTime = eventEndParts[1];
            LocalDate parsedStartDate = LocalDate.parse(eventStartDate);
            LocalDate parsedEndDate = LocalDate.parse(eventEndDate);
            Date parsedStartTime = new SimpleDateFormat("hh:mm").parse(eventStartTime);
            Date parsedEndTime = new SimpleDateFormat("hh:mm").parse(eventEndTime);
            Event newEvent = new Event(taskDescription, parsedStartDate, parsedStartTime,
                    parsedEndDate, parsedEndTime, completed);
            userTasks.add(newEvent);
        } catch (DateTimeParseException | ParseException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> userTasks = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(saveFile, Charset.defaultCharset()));
            String taskStr = reader.readLine();
            while (taskStr != null) {
                String[] parts = taskStr.split("\\|");
                boolean completed = parts[1].equals("1");
                String taskDescription = parts[2];
                if (parts.length == 3) {
                   processAndAddTodo(taskDescription, completed, userTasks);
                }
                if (parts.length == 4) {
                   processAndAddDeadline(parts, taskDescription, completed, userTasks);
                }
                if (parts.length == 5) {
                   processAndAddEvent(parts, taskDescription, completed, userTasks);
                }
                taskStr = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userTasks;
    }

    public static void saveTasksToFile(ArrayList<Task> taskList) {
        try {
            String toWrite = "";
            FileWriter fileWriter = new FileWriter(SAVE_FILE_PATH);

            for (int i = 0; i < taskList.size(); i++) {
                String taskType = taskList.get(i).getClass().getTypeName();
                switch (taskType) {
                    case "task.Todo":
                        Todo todo = (Todo) taskList.get(i);
                        toWrite = "todo|" + (todo.getIsDone() ? 1 : 0) + "|" + todo.getDescription() + "\n";
                        fileWriter.write(toWrite);
                        break;
                    case "task.Deadline":
                        Deadline deadline = (Deadline) taskList.get(i);
                        toWrite = "deadline|" + (deadline.getIsDone() ? 1 : 0) + "|"
                                + deadline.getDescription() + "|" + deadline.getDeadline() + "\n";
                        fileWriter.write(toWrite);
                        break;
                    case "task.Event":
                        Event event = (Event) taskList.get(i);
                        toWrite = "event|" + (event.getIsDone() ? 1 : 0) + "|" + event.getDescription() + "|"
                                + event.getEventStart() + "|" + event.getEventEnd() + "\n";
                        fileWriter.write(toWrite);
                        break;
                    default:
                        break;
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
