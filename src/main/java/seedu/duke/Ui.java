package seedu.duke;

import seedu.duke.tasks.*;

import java.time.LocalDate;

public class Ui {

    /**
     * Prints out greetings for user to see
     */
    public String sayGreetings() {
        return "TOP OF THE MORNING TO YOU LADDIES!\nWhat can I do for you? :)";
    }

    /**
     * Prints out current list of Tasks for user to see
     *
     * @param taskList current Task List
     */
    public String showList(TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.get(i);
            stringBuilder.append(String.format("%d.%s\n", i + 1, task));
        }
        return stringBuilder.toString();
    }

    /**
     * Prints out line after successfully adding a Task for user to see
     *
     * @param newTask Task that has been added
     * @param updatedList updated Task List
     */
    public String sayAddedTask(Task newTask, TaskList updatedList) {
        return String.format("Got it, I've added this task:\n%s\nNow you have %d tasks in the list.%n",
                newTask, updatedList.getSize());
    }

    /**
     * Prints out line after successfully deleting a Task for user to see
     *
     * @param deletedTask Task that has been deleted
     * @param updatedList updated Task List
     */
    public String sayDeletedTask(Task deletedTask, TaskList updatedList) {
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.%n",
                deletedTask, updatedList.getSize());
    }

    /**
     * Prints out line after successfully marking a Task for user to see
     *
     * @param markedTask Task that has been marked
     */
    public String sayMarkedTask(Task markedTask) {
        return String.format("Nice! I've marked this task as done:\n%s", markedTask);
    }

    /**
     * Prints out line after successfully unmarking a Task for user to see
     *
     * @param unmarkedTask Task that has been unmarked
     */
    public String sayUnmarkedTask(Task unmarkedTask) {
        return String.format("Ok, I've marked this task as not done yet:\n%s", unmarkedTask);
    }

    /**
     * Prints out line after successfully finding matching Tasks for user to see
     *
     * @param matchingTasks Tasks that match the keyword provided by user
     */
    public String sayMatchingTasks(TaskList matchingTasks) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.getSize(); i++) {
            stringBuilder.append(String.format("%d: %s\n", i + 1, matchingTasks.get(i)));
        }
        return stringBuilder.toString();
    }

    /**
     * Prints out line after successfully finding the scheduled Tasks for user to see
     *
     * @param scheduledTasks Tasks that are scheduled on the input date provided by user
     */
    public String sayScheduledTasks(TaskList scheduledTasks, String formattedDateStr) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the scheduled tasks for ").append(formattedDateStr).append(" :\n");
        for (int i = 0; i < scheduledTasks.getSize(); i++) {
            stringBuilder.append(String.format("%d: %s\n", i + 1, scheduledTasks.get(i)));
        }
        return stringBuilder.toString();
    }

    /**
     * Prints out line if there is an error loading for user to see
     */
    public String showLoadingError(){
        return "Unable to load!";
    }

    /**
     * Prints out goodbye line for user to see
     */
    public String sayGoodbye() {
        return "Goodbye!!!! Hope I don't see you again!";
    }
}
