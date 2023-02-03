package main;

import java.util.Scanner;

import task.Task;

/**
 * Creates a Ui to interact with the user
 */
public class Ui {
    private StringBuilder output = new StringBuilder();

    public Ui() {
        this.output = new StringBuilder();
    }

    private void resetOutput() {
        output.setLength(0);
    }

    public String showLine() {
        String temp = output.toString();
        resetOutput();
        return temp;
        
    }

    private void appendToOutput(String str) {
        output.append(str);
    }

    public void printMarkedTask(Task task) {
        appendToOutput("Duke: Ok! I've marked this task as done:" + "\n");
        appendToOutput(task.toString());
    }

    public void printUnMarkedTask(Task task) {
        appendToOutput("Duke: Ok! I've marked this task as not done yet:" + "\n");
        appendToOutput(task.toString());
    }

    public void printAddedTask(Task task) {
        appendToOutput("Got it. I've added this task: " + task.toString());
    }

    public void printDeletedTask(Task task, TaskList list) {
        appendToOutput("Noted. I've removed this task:" + task);
        appendToOutput("Here are the remaining Tasks: ");
        this.list(list);
    }

    public void list(TaskList list) {
        appendToOutput("Here are the task in your list: " + "\n");
        for (int i = 1; i < list.size() + 1; i++) {
            appendToOutput(i + "." + list.getList().get(i - 1) + "\n");
        }
    }

    public void bye() {
        appendToOutput("Duke: " + "Bye" + ". Hope I never see you again!");
    }

    public String showError(String errorMessage) {
        return ("There seems to be a problem: " + errorMessage);
    }
}
