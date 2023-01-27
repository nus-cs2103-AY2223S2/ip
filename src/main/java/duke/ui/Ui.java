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
        System.out.println("Good ta see yer dawg, Duke.Duke's at yer service.\n" + dog);
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
        System.out.println("Unable to load, creating a data/duke.txt");
    }

    public void printBye() {
        System.out.println(separator);
        System.out.println("See yer again RUFF!");
        System.out.println(separator);
    }

    public void printTaskList(TaskList taskList) {
        System.out.println(separator);
        System.out.println("Here are the tasks in your list dawg:");
        taskList.printList();
        System.out.println(separator);
    }

    public void printMarkStatus(TaskList taskList, int index) {
        taskList.markStatus(index);
        System.out.println(separator);
        System.out.println("The Duke.task is marked, dawg");
        System.out.println(taskList.getTask(index - 1));
        System.out.println(separator);
    }

    public void printUnMarkStatus(TaskList taskList, int index) {
        taskList.unMarkStatus(index);
        System.out.println(separator);
        System.out.println("Gotcha dawg, unmarked");
        System.out.println(taskList.getTask(index - 1));
        System.out.println(separator);
    }

    public void printAddTask(TaskList taskList, Task task) {
        System.out.println(separator);
        System.out.println("Gotcha, I've added:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.listSize() + " in the list!");
        System.out.println(separator);
    }

    public void printDelete(TaskList taskList, Task task) {
        System.out.println(separator);
        System.out.println("Removing your Duke.task? It's gone now RUFF:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.listSize() + " in the list!");
        System.out.println(separator);
    }
}
