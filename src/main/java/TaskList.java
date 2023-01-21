import java.util.ArrayList;
import java.lang.StringBuilder;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

public class TaskList {
    protected ArrayList<Task> taskList;
    protected int numOfTasks;
    protected File f;
    protected File dir;
    public TaskList() {
        this.taskList = new ArrayList<>();
        this.numOfTasks = 0;
    }
    public void printList() {
        for (int i = 0; i < this.numOfTasks; i++) System.out.println((i+1) + "." + this.taskList.get(i));
    }

    public void markStatus(String index) {
        taskList.get(Integer.parseInt(index) - 1).markStatus(true);
    }

    public void unMarkStatus(String index) {
        taskList.get(Integer.parseInt(index) - 1).markStatus(false);
    }



    public void addTasks(String[] inputArr, String taskType) throws EmptyDescException {
        String desc = "variable not initialised";
        StringBuilder sb = new StringBuilder();
        if (inputArr.length == 1) throw new EmptyDescException(taskType, "test");
        switch (taskType) {
            case "todo":
                // task is from index 1
                for (int i = 1; i < inputArr.length; i++) {
                    sb.append(inputArr[i]);
                    if (i != inputArr.length - 1) {
                        sb.append(" ");
                    }
                }
                Todo todo = new Todo(sb.toString());
                this.taskList.add(todo); this.numOfTasks++;
                System.out.println("Gotcha, I've added:");
                System.out.println("  " + todo);
                System.out.println("Now you have " + this.numOfTasks + " in the list!");
                break;

            case "deadline":
                for (int i = 1; i < inputArr.length; i++) {
                    if (inputArr[i].equals("/by")) {
                        desc = sb.toString();
                        sb.setLength(0);
                        continue;
                    }
                    sb.append(inputArr[i]);
                    // No need for whitespace at the end
                    if (i != inputArr.length - 1) {
                        sb.append(" ");
                    }
                }
                Deadline deadline = new Deadline(desc, sb.toString());
                this.taskList.add(deadline); this.numOfTasks++;
                System.out.println("Gotcha, I've added:");
                System.out.println("  " + deadline);
                System.out.println("Now you have " + this.numOfTasks + " in the list!");
                break;
            case "event":
                String from = "local variable not initialised";
                for (int i = 1; i < inputArr.length; i++) {
                    if (inputArr[i].equals("/from")) {
                        desc = sb.toString();
                        sb.setLength(0);
                        continue;
                    } else if (inputArr[i].equals("/to")) {
                        from = sb.toString();
                        sb.setLength(0);
                        continue;
                    }
                    sb.append(inputArr[i]);
                    // No need for whitespace at the end
                    if (i != inputArr.length - 1) {
                        sb.append(" ");
                    }
                }
                Event event = new Event(desc, from, sb.toString());
                this.taskList.add(event); this.numOfTasks++;
                System.out.println("Gotcha, I've added:");
                System.out.println("  " + event);
                System.out.println("Now you have " + this.numOfTasks + " in the list!");
                break;
        }
    }

    public void deleteTasks(int inputIndex) {
        Task task = this.taskList.get(inputIndex-1);
        this.taskList.remove(inputIndex-1);
        this.numOfTasks--;
        System.out.println("Removing your task? It's gone now RUFF:");
        System.out.println("  " + task);
        System.out.println("Now you have " + this.numOfTasks + " in the list!");
    }


}
