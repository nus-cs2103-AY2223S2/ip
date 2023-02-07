package duke.util;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String WELCOME_MESSAGE = "Welcome to Duke!\nHow can I help you?";

    private boolean isRunning = true;
    private Scanner sc;

    public Ui () { //handle interaction with user
        this.sc = new Scanner(System.in);
    }

    public String[] readUserInput() {
        String userInput = this.sc.nextLine();
        return userInput.split(" ", 2);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayExitMessage() {
        displayMessage("Exiting Duke now...\n" + "Bye, see you again!");
    }

    public void displayWelcomeMessage() {
        displayMessage(WELCOME_MESSAGE);
    }

    public void displayTasks(TaskList taskList) {
        if (taskList.size() == 0) {
            displayMessage("You currently have no tasks!");
        } else {
            String toDisplay = "Here are the tasks in your list:";
            for (int i = 0; i < taskList.size(); i++) {
                toDisplay += String.format("\n%s. %s", i + 1, taskList.getTask(i));
            }
            displayMessage(toDisplay);
        }
    }

    public void displayMatchedTasks(ArrayList<Task> matchedTasks) {
        if (matchedTasks.size() == 0) {
            displayMessage("No tasks found!");
        } else {
            String toDisplay = "Here are the matching tasks in your list:";
            for (int i = 0; i < matchedTasks.size(); i++) {
                toDisplay += String.format("\n%s. %s", i + 1, matchedTasks.get(i));
            }
            displayMessage(toDisplay);
        }
    }

    public void displayAddedTask(Task newTask, TaskList tasks) {
        String toDisplay = String.format("Gotcha! I have added this task:\n%s\nNow you have %d tasks in the list.",
                newTask.toString(), tasks.size());
        displayMessage(toDisplay);
    }

    public void displayDeletedTask(Task taskToDelete, TaskList tasks) {
        String toDisplay = String.format("Alright, I have removed this task:\n%s\nNow you have %d tasks in the list.",
                taskToDelete.toString(), tasks.size());
        displayMessage(toDisplay);
    }

    public void displayMarkedTask(TaskList taskList, int index) {
        displayMessage("Ok, I've marked this task as done:\n" + taskList.getTask(index).toString());
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public void exit() {
        this.isRunning = false;
    }
}
