package duke;

import java.util.List;

public class Response {
    /**
     * Returns the greeting message.
     */
    public String showGreeting() {
        return "Karen:\n"
                + "Can I speak to your manager?\n"
                + "Just kidding, this is Karen. How can I help you today?";
    }

    /**
     * Returns the exit message.
     */
    public String showExit() {
        return "Karen:\n" + "Bye. This was of great inconvenience.";
    }

    /**
     * Returns message about statistics for the past week.
     *
     * @param noOfCompletedTasksPastWeek Number of completed tasks last week.
     * @return Message about statistics for the past week.
     */
    public String showStats(int noOfCompletedTasksPastWeek) {
        if (noOfCompletedTasksPastWeek == 0) {
            return "What were you doing last week? You finished nothing.";
        } else {
            return "Not too shabby, you managed to finish "
                    + Integer.toString(noOfCompletedTasksPastWeek)
                    + " tasks last week.";
        }
    }

    /**
     * Returns the tasks in the task list.
     *
     * @param taskList Current list of tasks.
     */
    public String showTaskList(List<Task> taskList) {
        String taskListString = "";

        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            taskListString += (i + 1) + ". " + currTask.toString() + "\n";
        }

        return taskListString;
    }

    /**
     * Returns message when a task is marked as done.
     *
     * @param task Task marked as done.
     */
    public String showTaskMarkDone(Task task) {
        return "Congrats, I guess you get a medal?\n" + task;
    }

    /**
     * Returns message when a task is marked as not done.
     *
     * @param task Task marked as not done.
     */
    public String showTaskMarkUndone(Task task) {
        return "Why are you so lazy?\n" + task;
    }

    /**
     * Returns message when a task is deleted from the task list.
     *
     * @param task Task that was deleted.
     * @param taskList Current list of tasks.
     */
    public String showDeleteTask(Task task, List<Task> taskList) {
        return "Okay okay, this has been removed:\n"
                + task.toString()
                + "\nNow you have " + taskList.size() + " tasks left.";
    }

    /**
     * Returns message when tasks are deleted from the task list.
     *
     * @param removedTasks Tasks that were removed from task list.
     * @param taskList Current list of tasks.
     * @param keyword Keyword that was used to remove tasks.
     * @return
     */
    public String showDeleteAllTasks(List<Task> removedTasks, List<Task> taskList, String keyword) {
        String message = "Okay fine, I have removed these tasks which contain " + keyword + ":\n";

        for (Task t : removedTasks) {
            message += t.toString() + "\n";
        }

        message += "Now you have " + taskList.size() + " tasks left.";

        return message;
    }

    /**
     * Returns message when no tasks are found.
     *
     * @param keyword Keyword used to search for tasks.
     * @return
     */
    public String showNoTasksFound(String keyword) {
        return "Are you sure there are any tasks which contain "
                + keyword + "?";
    }

    /**
     * Returns message when a new task is added to the task list.
     *
     * @param task Task that was added.
     * @param taskList Current list of tasks.
     */
    public String showAddTask(Task task, List<Task> taskList) {
        return "You better finish this soon:\n"
                + task
                + "\nCan you finish all " + taskList.size() + " tasks in your list?";
    }

    /**
     * Returns list of found tasks.
     *
     * @param taskList Current list of tasks
     * @return Found tasks.
     */
    public String showFoundTasks(List<Task> taskList) {
        if (taskList.isEmpty()) {
            return "What are you trying to find?";
        } else {
            String foundTasks = "Here you go, do you need anything else?\n";

            for (int i = 0; i < taskList.size(); i++) {
                Task currTask = taskList.get(i);
                foundTasks += (i + 1) + ". " + currTask.toString() + "\n";
            }

            return foundTasks;
        }
    }

    /** Error methods */
    /**
     * Returns error message.
     *
     * @param message Error message to be printed.
     */
    public String showError(String message) {
        return message;
    }
}
