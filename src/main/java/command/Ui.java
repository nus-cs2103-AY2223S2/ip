package command;

import task.Task;
import task.TaskList;

import java.util.Scanner;

public class Ui {
    // For all interactions with the user
    private Scanner userInput;
    private Boolean isOpenForInput;
    public Ui() {
        userInput = new Scanner(System.in);
        isOpenForInput = true;
    }
    //@@author IceFire
    //Reused from https://stackoverflow.com/questions/36514289
    // with minor modifications
    private void dashedLine()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 90; i++) {
            sb.append("-");
        }
        System.out.println(sb.toString());
    }

    public void close() {
        dashedLine();
        System.out.println("Pleasure doing business with you.");
        isOpenForInput = false;
        dashedLine();
    }

    public boolean isOpenForInput() {
        return isOpenForInput;
    }

    public void unknownCommand() {
        dashedLine();
        System.out.println("Sorry sir, didn't quite get that.");
        dashedLine();
    }

    public void startMessage() {
        dashedLine();
        String intro = "My name is Skyler White yo \nHow can I help you?";
        System.out.println(intro);
        dashedLine();
    }

    public String getInput() {
        return userInput.nextLine();
    }

    public void showLoadingError() {
        dashedLine();
        System.out.println("Looks like you don't have any old lists for me to include. That's alright; we'll start" +
                " from scratch!");
        dashedLine();
    }

    public void showArrayOutOfBoundsError() {
        dashedLine();
        System.out.println("Woah there. Got an index problem. That entry does not exist.");
        dashedLine();
    }

    public void showInvalidInputError(String message) {
        dashedLine();
        System.out.println("Whoa. That command doesn't look right. Here's what seems to be wrong:\n" + message);
        dashedLine();
    }

    public void showNumberFormatError() {
        dashedLine();
        System.out.println("I only take integers for that command, kid.");
        dashedLine();
    }

    public void showMarkSuccess(Task task) {
        dashedLine();
        System.out.println("Gotcha. Just marked this task as done:\n" + task);
        dashedLine();
    }

    public void showUnmarkSuccess(Task task) {
        dashedLine();
        System.out.println("Gotcha. Just marked this task as not done:\n" + task);
        dashedLine();
    }

    public void showAddTaskSuccess(Task task, TaskList list) {
        dashedLine();
        System.out.println("Gotcha. Just added this task to the list:\n" + task + "\n");
        showListLength(list);
        dashedLine();
    }

    public void showDeleteSuccess(Task task, TaskList list) {
        dashedLine();
        System.out.println("Gotcha. Just marked this task as not done:\n" + task + "\n");
        showListLength(list);
        dashedLine();
    }

    public void showListState(TaskList list) {
        dashedLine();
        for (int i = 1; i <= list.size(); i++){
            System.out.println(i + ". " + list.get(i-1));
        }
        dashedLine();
    }

    public void showListLength(TaskList list) {
        System.out.println("You have " + list.size() + " tasks left. Anything else?");
    }

    public void showFindListState(TaskList list, String keyword) {
        dashedLine();
        System.out.println("Righto, here are the tasks that contain the word \""
                                                                + keyword + "\":");
        for (int i = 1; i <= list.size(); i++){
            System.out.println(i + ". " + list.get(i-1));
        }
        dashedLine();
    }
}
