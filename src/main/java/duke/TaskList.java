package duke;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;

    TaskList() {
        tasks = new ArrayList<>();
    }

    void addTask(Task task) {
        tasks.add(task);
    }

    String deleteTask(int taskNum) {
        Task task = tasks.remove(taskNum - 1);
        return String.format("Noted, I've removed this task:\n   %s\nYou now have %d tasks in the list\n",
                task.toString(), getNumOfTasks());
    }

    String markTask(int taskNum) {
        Task task = tasks.get(taskNum - 1);
        task.completeTask();
        return String.format("Nice! I've marked this task as done:\n   %s\n", task);
    }

    String unmarkTask(int taskNum) {
        Task task = tasks.get(taskNum - 1);
        task.undoTask();
        return String.format("OK, I've unmarked this task as not done yet:\n   %s\n", task);
    }

    String findTask(String keyword) {
        TaskList tasksFound = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().indexOf(keyword) != -1) {
                tasksFound.addTask(task);
            }
        }
        return tasksFound.getListOfTasks();
    }

    String addTaskText(Task task) {
        return String.format("Got it. I've added this task:\n   %s\nNow you have %s task%s in the list.\n",
                task.toString(), getNumOfTasks(), getNumOfTasks() == 1 ? "" : "s");
    }

    int getNumOfTasks() {
        return tasks.size();
    }
    String getSavedListOfTasks() {
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            listString.append(String.format("%s\n", tasks.get(i).getSaveTaskString()));
        }
        return listString.toString();
    }

    String getListOfTasks() {
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return listOfTasks.toString();
    }

    /**
     * Returns String representation of the task list numbered.
     *
     * @return String representation of the task list numbered.
     */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "There are no tasks in your list";
        }
        StringBuilder printedList = new StringBuilder("Here are the tasks in your list:\n");
        printedList.append(getListOfTasks());
        return printedList.toString();
    }
}
