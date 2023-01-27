package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    private final String separator;
    private final Scanner sc;

    public Ui () {
        this.separator = "____________________________________________________________";
        this.sc = new Scanner(System.in);
    }

    public void welcome() {
        String dog = "⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠀⣀⣀⣀⣀⢀⣀⣀⣀⣀⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⣀⣰⣿⣿⡻⠟⠋⠉⠉⣻⠟⠉⠉⠉⠛⢯⡛⢿⣿⣷⣤⣀⠀⠀⠀⠀⠀\n" +
                "⠀⣠⣴⠾⠛⢋⣿⠟⠋⠀⠀⠀⠀⢀⡟⠀⠀⠀⠀⠀⠀⠈⠂⣹⣿⡈⠙⠻⢶⣄⡀⠀\n" +
                "⣸⠏⠀⠀⠀⣾⣋⣀⣀⡀⠀⠀⠀⢸⠁⠀⠀⢀⣀⣀⣀⡀⠀⠈⠻⣧⠀⠀⠀⠉⠻⣦\n" +
                "⢿⡀⠀⣿⣿⠟⣫⣽⣿⣿⣿⣿⣶⣶⣶⡶⠛⣻⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⢀⣿\n" +
                "⠸⣧⠀⠈⣿⢸⣿⣿⣿⣿⣿⣿⣿⠁⢹⡇⣼⣿⣿⣿⣿⣿⣿⣿⠁⣼⡇⠀⠀⠀⣼⠇\n" +
                "⠀⠹⣷⡀⢹⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⠏⠀⣿⡇⠀⣠⡾⠋⠀\n" +
                "⠀⠀⠈⢿⣿⢿⡿⠿⠿⣿⠟⠉⠀⠀⠀⠀⠙⠛⢿⡿⠿⠛⠉⠀⠀⡿⣷⣾⠏⠀⠀⠀\n" +
                "⠀⠀⠀⠈⠋⠘⣷⠀⢀⡿⢰⣾⣟⣛⣿⣿⣷⡄⠀⢻⣆⠀⠀⠀⢰⡇⠘⠋⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠹⣧⣼⠃⠈⣧⣼⣿⣇⣈⣿⠃⠀⠀⣿⣀⣀⣴⠟⠁⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠙⢿⣆⠀⠈⠙⢿⡛⠉⠁⠀⠀⣠⡿⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⡿⢶⣶⣾⣿⣶⣤⣤⣶⢿⣼⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣧⡼⠁⠉⠏⠁⠈⢹⣠⣾⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣧⠀⠀⠀⠀⠀⣸⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣷⣤⣤⣤⡾⣿⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠿⢶⣾⣶⠾⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀";
        System.out.println("Good ta see yer dawg, Duke's at yer service.\n" + dog);
    }

    private boolean shouldIgnore(String inputLine) {
        return inputLine.trim().isEmpty();
    }

    public String readCommand() {
        String command = sc.nextLine();

        while (shouldIgnore(command)) {
            command = sc.nextLine();
        }

        return command;
    }

    public void showLine() {
        System.out.println(this.separator);
    }

    public void showLoadingError() {
        showLine();
        System.out.println("No existing data, creating new files now");
        showLine();
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void printBye() {
        System.out.println("See yer again RUFF!");
    }

    public void printTaskList(TaskList taskList) {
        System.out.println("Here are the tasks in your list dawg:");
        taskList.printList();
    }

    public void printMarkStatus(TaskList taskList, int index) {
        taskList.markStatus(index);
        System.out.println("The task is marked, dawg");
        System.out.println(taskList.getTask(index - 1));
    }

    public void printUnMarkStatus(TaskList taskList, int index) {
        taskList.unMarkStatus(index);
        System.out.println("Gotcha dawg, unmarked");
        System.out.println(taskList.getTask(index - 1));
    }

    public void printAddTask(TaskList taskList, Task task) {
        System.out.println("Gotcha, I've added:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.listSize() + " in the list!");
    }

    public void printDelete(TaskList taskList, Task task) {
        System.out.println("Removing your task? It's gone now RUFF:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.listSize() + " in the list!");
    }
}
