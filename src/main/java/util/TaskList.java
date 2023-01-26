package util;

import duke.DeadlineTask;
import duke.Event;
import duke.Task;
import duke.ToDo;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private static int TASK_TYPE = 0;
    private static int IS_DELETED = 1;
    private static int TASK_NAME = 2;
    private static int IS_DONE = 3;
    protected ArrayList<Task> taskList = new ArrayList<>();
    protected BufferedReader br;

    // T | false | book buy | false

    public TaskList() {}

    public TaskList(BufferedReader br) throws DukeException{
        this.br = br;
        this.load();
    }

    // Add new taskList to the list
    // Outputs a String with the details of the task and the number of taskList in the list
    public void addTask(Task task) {
        taskList.add(task);
        System.out.println(String.format("     Got it. I've added this task:\n" +
                "       %s\n" + this.numTasks(), task));
    }

    public void listTasks() {
        System.out.println("    Here are the tasks in your list:");
        int counter = 1;
        for (Task t : taskList) {
            System.out.println(counter + ". " + t.toString());
            counter++;
        }
    }

    public void load() throws DukeException {
        try {
            while (true) {
                String taskLine = br.readLine();
                if (taskLine == null) {
                    break;
                }
                String[] taskArr = taskLine.split("\\|");
                String taskType = taskArr[TASK_TYPE];
                if (taskType.equals("T")) {
                    if (Boolean.parseBoolean(taskArr[IS_DELETED])) {
                        continue;
                    }
                    boolean completion = Boolean.parseBoolean(taskArr[IS_DONE]);
                    ToDo t = new ToDo(taskArr[TASK_NAME], completion);
                    this.taskList.add(t);
                } else if (taskType.equals("D")) {
                    if (Boolean.parseBoolean(taskArr[IS_DELETED])) {
                        continue;
                    }
                    LocalDateTime deadline = LocalDateTime.parse(taskArr[4]);
                    boolean completion = Boolean.parseBoolean(taskArr[IS_DONE]);
                    DeadlineTask d = new DeadlineTask(taskArr[TASK_NAME], deadline, completion);
                    this.taskList.add(d);
                    // D|false|return book|false|2000-12-18T23:59
                } else if (taskType.equals("E")) {
                    if (!Boolean.parseBoolean(taskArr[IS_DELETED])) {
                        continue;
                    }
                    LocalDateTime start = DateTimeParser.dateTimeParser(taskArr[4] + " " + taskArr[5]);
                    LocalDateTime end = DateTimeParser.dateTimeParser(taskArr[6] + " " + taskArr[7]);
                    boolean completion = Boolean.parseBoolean(taskArr[IS_DONE]);
                    Event e = new Event(taskArr[TASK_NAME], start, end, completion);
                    e.setCompletion(Boolean.parseBoolean(taskArr[IS_DONE]));
                    this.taskList.add(e);
                }
            }
            br.close();
        }
        catch (IOException e){
            throw new DukeException("Unable to read from file, creating a new file");
        }
    }

    public String saveTaskList() {
        StringBuilder sb = new StringBuilder();
        for (Task t: taskList) {
            sb.append(t.saveTaskString()).append("\n");
        }
        return sb.toString();
    }

    public void manageTask(String command) throws DukeException {
        String[] input = command.split(" ");
        if ((input.length != 2)) {
            throw new DukeException(input[0]);
        }
        int taskNumber = Integer.parseInt(input[1]) - 1;
        if (taskNumber >= taskList.size() || (taskNumber < 0)) { //negative and out of range is invalid
            throw new DukeException("bounds");
        }
        if (input[0].equals("delete")) {
            Task task = taskList.remove(taskNumber);
            System.out.println(String.format("    Noted. I've removed this task:\n       %s\n%s",
                    task, numTasks()));
        } else {
            boolean completion = input[0].equals("mark");
            Task task = taskList.get(taskNumber);
            task.setCompletion(completion);
            Task.saveTaskData(task, completion ? 1 : 0);
        }
    }

    public String numTasks() {
        return String.format("     Now you have %d tasks in the list", taskList.size());
    }
}
