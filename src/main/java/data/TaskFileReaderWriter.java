package data;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.*;
import java.util.Scanner;

public class TaskFileReaderWriter {


    private static final String DIRECTORY_NAME = "userData";
    private static final String FILE_NAME = "test.txt";


    public TaskFileReaderWriter() {}

    public boolean createTaskFile() {

        File taskFile = new File(DIRECTORY_NAME + File.separator + FILE_NAME);

        if (!taskFile.exists()) {
            boolean isMkdirSuccess = taskFile.getParentFile().mkdirs();
            if (!isMkdirSuccess) {
                return false;
            }
        }

        try {
            boolean isNewFileSuccess = taskFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Task readTaskString(String taskString) {

        String[] taskArr = taskString.split("\\|");
        String category = taskArr[0];
        boolean isCompleted = Boolean.parseBoolean(taskArr[1]);
        String details = taskArr[2];

        Task newTask = null;

        switch (category) {
            case "Deadline":
                String deadline = taskArr[3];
                newTask = new Deadline(details, deadline, isCompleted);
                break;
            case "Event":
                String start = taskArr[3];
                String end = taskArr[4];
                newTask = new Event(details, start, end, isCompleted);
                break;
            case "To-Do":
                newTask = new ToDo(details, isCompleted);
                break;

        }
        return newTask;
    }

    public boolean updateTaskFile(TaskManager taskManager) {
        File taskFile = new File(DIRECTORY_NAME + File.separator + FILE_NAME);

        String taskString = "";
        String details = "";
        boolean isCompleted;

        try (FileWriter fileWriter = new FileWriter(taskFile)) {
            for (Task task : taskManager.getTasks()) {
                if (task instanceof Deadline) {
                    Deadline deadlineTask = (Deadline) task;
                    details = deadlineTask.getDetails();
                    String deadline = deadlineTask.getDeadline();
                    isCompleted = deadlineTask.isCompleted();
                    taskString = "Deadline|" + isCompleted + "|" + details + "|" + deadline;

                } else if (task instanceof Event) {
                    Event eventTask = (Event) task;
                    details = eventTask.getDetails();
                    String start = eventTask.getStart();
                    String end = eventTask.getEnd();
                    isCompleted = eventTask.isCompleted();
                    taskString = "Event|" + isCompleted + "|" + details + "|" + start + "|" + end;

                } else if (task instanceof ToDo) {
                    ToDo toDoTask = (ToDo) task;
                    details = toDoTask.getDetails();
                    isCompleted = toDoTask.isCompleted();
                    taskString = "To-Do|" + isCompleted + "|" + details;
                }
                fileWriter.write(taskString + System.getProperty( "line.separator" ));
            }
        } catch (IOException e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public TaskManager loadDataFromFile() {
        TaskManager taskManager = new TaskManager();
        try {
            Scanner scanner = new Scanner(new File(DIRECTORY_NAME + File.separator + FILE_NAME));

            while (scanner.hasNextLine()) {
                String taskString = scanner.nextLine();

                if (!taskString.isBlank()) {
                    Task task = readTaskString(taskString);
                    taskManager.addTask(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return taskManager;
    }
}
