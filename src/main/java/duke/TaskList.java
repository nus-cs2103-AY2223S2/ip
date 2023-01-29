package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    TaskList() {
        taskList = new ArrayList<>();
    }

    void addTask(Task task) {
        taskList.add(task);
    }

    String deleteTask(int taskNum) {
        Task task = taskList.remove(taskNum - 1);
        return String.format("Noted, I've removed this task:\n   %s\nYou now have %d tasks in the list\n",
                task.toString(), getNumOfTasks());
    }

    String markTask(int taskNum) {
        Task task = taskList.get(taskNum - 1);
        task.completeTask();
        return String.format("Nice! I've marked this task as done:\n   %s\n", task);
    }

    String unmarkTask(int taskNum) {
        Task task = taskList.get(taskNum - 1);
        task.undoTask();
        return String.format("OK, I've unmarked this task as not done yet:\n   %s\n", task);
    }

    String addTaskText(Task task) {
        return String.format("Got it. I've added this task:\n   %s\nNow you have %s task%s in the list.\n",
                task.toString(), getNumOfTasks(), getNumOfTasks() == 1 ? "" : "s");
    }

    int getNumOfTasks() {
        return taskList.size();
    }
    String getListOfTasks() {
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            listString.append(String.format("%s\n", taskList.get(i).getSaveTaskString()));
        }
        return listString.toString();
    }

    /**
     * Returns String representation of the task list numbered.
     *
     * @return String representation of the task list numbered.
     */

    @Override
    public String toString() {
        if (taskList.isEmpty()) {
            return "There are no tasks in your list";
        }
        StringBuilder printedList = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            printedList.append(String.format("%d. %s\n", i + 1, taskList.get(i)));
        }
        return printedList.toString();
    }

}
