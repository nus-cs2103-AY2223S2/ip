package duke.main;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.nio.file.Paths;
import duke.task.*;

/**
 * Loads previous task list and saves updated task list to a file on
 * hard disk.
 */
public class Storage {

    private final String filePath;
    private final String filePathParent;

    Storage(String filePath) {
        this.filePath = filePath;
        this.filePathParent = Paths.get(filePath).getParent().toString();
    }

    /**
     * Load previous task list if it exists.
     */
    public List<Task> loadTxtFile() throws IOException {

        List<Task> allTasks = new ArrayList<>();

        File file3 = new File(this.filePathParent);
        if (!file3.exists()) {
            file3.mkdir();
        }
        File file = new File(this.filePath);
        if (file.exists()) {
            List<String> taskList = new ArrayList<>();
            FileReader file2 = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(file2);
            String taskInformation = bufferedReader.readLine();
            while (taskInformation != null) {
                taskList.add(taskInformation);
                taskInformation = bufferedReader.readLine();
            }
            for (int i = 0; i < taskList.size(); i++) {
                String task = taskList.get(i);
                String[] task1 = task.split(" / ");
                boolean taskStatus = false;
                if (task1[1].equals("[ ]")) {
                    taskStatus = false;
                } else if (task1[1].equals("[X]")){
                    taskStatus = true;
                }
                DateTimeFormatter dateTimeFormatter1 =
                        DateTimeFormatter.ofPattern("MMM dd yyyy HHmm a");
                if (task.startsWith("T")) {
                    Todo todo = new Todo(i + 1, taskStatus, task1[2],
                            taskList.size());
                    allTasks.add(todo);
                } else if (task.startsWith("D")) {
                    Deadline deadline = new Deadline(i + 1, taskStatus, task1[2],
                            LocalDateTime.parse(task1[3], dateTimeFormatter1), taskList.size());
                    allTasks.add(deadline);
                } else if (task.startsWith("E")) {
                    String[] taskTiming = task1[3].split("-");
                    Event event = new Event(i + 1, taskStatus, task1[2],
                            LocalDateTime.parse(taskTiming[0], dateTimeFormatter1),
                            LocalDateTime.parse(taskTiming[1], dateTimeFormatter1), taskList.size());
                    allTasks.add(event);
                }
            }
        }
        return allTasks;
    }

    /**
     * Saves the updated task list to existing file on hard disk.
     * If file does not exist, create a new one.
     */
    public void saveListToFile(String command, Task task, TaskList taskList) throws IOException {
        File file3 = new File(this.filePathParent);
        if (!file3.exists()) {
            file3.mkdir();
        }
        File file = new File(this.filePath);
        FileWriter file1 = new FileWriter(file, true);
        BufferedWriter buffer = new BufferedWriter(file1);

        DateTimeFormatter dateTimeFormatter1 =
                DateTimeFormatter.ofPattern("MMM dd yyyy HHmm a");

        if (command.startsWith("todo")) {
            String content = "T / " + task.getTaskStatus() + " / " + task.getTask() + "\n";
            buffer.write(content);
        } else if (command.startsWith("deadline")) {
            String content = "D / " + task.getTaskStatus() + " / " +
                    task.getTask() + " / " +
                    task.getDeadline().format(dateTimeFormatter1) + "\n";
            buffer.write(content);
        } else if (command.startsWith("event")) {
            String content = "E / " + task.getTaskStatus() + " / " +
                    task.getTask() + " / " +
                    task.getEventStartTime().format(dateTimeFormatter1) + "-" +
                    task.getEventEndTime().format(dateTimeFormatter1) + "\n";
            buffer.write(content);
        } else if (command.startsWith("mark")) {
            FileReader file2 = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(file2);
            String[] str = command.split(" ");
            int taskIndex = Integer.parseInt(str[1]);
            String unchangedTasks = "";
            for (int i = 1; i < taskIndex; i++) {
                unchangedTasks = unchangedTasks + bufferedReader.readLine() + "\n";
            }
            if (task.getTaskType().equals("[T]")) {
                unchangedTasks = unchangedTasks + "T / [X] / " + task.getTask() + "\n";
            } else if (task.getTaskType().equals("[D]")) {
                unchangedTasks = unchangedTasks + "D / [X] / " +
                        task.getTask() + " / " +
                        task.getDeadline().format(dateTimeFormatter1) + "\n";
            } else if (task.getTaskType().equals("[E]")) {
                unchangedTasks = unchangedTasks + "E / [X] / " +
                        task.getTask() + " / " +
                        task.getEventStartTime().format(dateTimeFormatter1) + "-" +
                        task.getEventEndTime().format(dateTimeFormatter1) + "\n";
            }
            bufferedReader.readLine();
            for (int i = taskIndex + 1; i <= taskList.getNumberOfTask(); i++) {
                unchangedTasks = unchangedTasks + bufferedReader.readLine() + "\n";
            }
            file.createNewFile();
            file1 = new FileWriter(file);
            buffer = new BufferedWriter(file1);
            buffer.write(unchangedTasks);
        } else if (command.startsWith("unmark")) {
            FileReader file2 = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(file2);
            String[] str = command.split(" ");
            int taskIndex = Integer.parseInt(str[1]);
            String unchangedTasks = "";
            for (int i = 1; i < taskIndex; i++) {
                unchangedTasks = unchangedTasks + bufferedReader.readLine() + "\n";
            }
            if (task.getTaskType().equals("[T]")) {
                unchangedTasks = unchangedTasks + "T / [ ] / " + task.getTask() + "\n";
            } else if (task.getTaskType().equals("[D]")) {
                unchangedTasks = unchangedTasks + "D / [ ] / " +
                        task.getTask() + " / " +
                        task.getDeadline().format(dateTimeFormatter1) + "\n";
            } else if (task.getTaskType().equals("[E]")) {
                unchangedTasks = unchangedTasks + "E / [ ] / " +
                        task.getTask() + " / " +
                        task.getEventStartTime().format(dateTimeFormatter1) + "-" +
                        task.getEventEndTime().format(dateTimeFormatter1) + "\n";
            }
            bufferedReader.readLine();
            for (int i = taskIndex + 1; i <= taskList.getNumberOfTask(); i++) {
                unchangedTasks = unchangedTasks + bufferedReader.readLine() + "\n";
            }
            file.createNewFile();
            file1 = new FileWriter(file);
            buffer = new BufferedWriter(file1);
            buffer.write(unchangedTasks);
        } else if (command.startsWith("delete")) {
            FileReader file2 = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(file2);
            String undeletedTasks = "";
            String[] str = command.split(" ");
            int taskIndex = Integer.parseInt(str[1]);
            for (int i = 1; i < taskIndex; i++) {
                undeletedTasks = undeletedTasks + bufferedReader.readLine() + "\n";
            }
            bufferedReader.readLine();
            for (int i = taskIndex; i <= taskList.getNumberOfTask(); i++) {
                undeletedTasks = undeletedTasks + bufferedReader.readLine() + "\n";
            }
            file.createNewFile();
            file1 = new FileWriter(file);
            buffer = new BufferedWriter(file1);
            buffer.write(undeletedTasks);
        }
        buffer.close();
    }
}
