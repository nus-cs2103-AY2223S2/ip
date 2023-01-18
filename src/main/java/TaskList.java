import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    TaskList() {
        taskList = new ArrayList<Task>();
    }

    String addTask(String description) {
        taskList.add(new Task(description));
        return String.format("\tadded: %s\n", description);
    }

    String markTask(int taskNum) {
        Task task = taskList.get(taskNum - 1);
        task.completeTask();
        return String.format("\tNice! I've marked this task as done:\n\t\t%s\n", task);
    }

    String unmarkTask(int taskNum) {
        Task task = taskList.get(taskNum - 1);
        task.undoTask();
        return String.format("\tOK, I've unmarked this task as not done yet:\n\t\t%s\n", task);
    }

    @Override
    public String toString() {
        String printedList = "\tHere are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            printedList += String.format("\t%d. %s\n", i + 1, taskList.get(i));
        }
        return printedList;
    }

}
