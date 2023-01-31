package uwuke;

import java.util.Scanner;

import uwuke.input.Advisor;
import uwuke.input.Command;

import uwuke.output.DukeException;
import uwuke.output.Printer;
import uwuke.output.Storage;

import uwuke.task.TaskList;

public class UwUke {

    private final static int CAPACITY = 100;
    private static TaskList tasks;
    private static Scanner sc = new Scanner(System.in);

    private static void init() {
        Printer.uwu();
        tasks = new TaskList(CAPACITY);
        try {
            tasks = Storage.readSavedTasks();
        } catch (Exception e) {
            Printer.printError("Could not load save file");
            tasks = new TaskList();
        }
        sc = new Scanner(System.in);
    }

    private static void run() {
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            try {
                if (input.contains(",")) { // Can potentially cause fatal errors when trying to read files if commas were allowed.
                    throw new DukeException("Please do not use reserved character \',\'.");
                }
                switch (Command.matchCommand(input)) {
                case LIST:
                    Printer.printTasks(tasks.getList());
                    break;
                case DEADLINE:
                    tasks.addDeadline(input);
                    break;
                case EVENT:
                    tasks.addEvent(input);
                    break;
                case TODO:
                    tasks.addTodo(input);
                    break;
                case MARK:
                    tasks.markTask(input);
                    break;
                case UNMARK:
                    tasks.unmarkTask(input);
                    break;
                case DELETE:
                    tasks.deleteTask(input);
                    break;
                case FIND:
                    tasks.findTask(input);
                    break;
                default:
                    Printer.printWithDecorations(Advisor.advise(input));
                }
            } catch (Exception e) {
                Printer.printError(e.getMessage());
            } 

            try {
                input = sc.nextLine();
            } catch (Exception e) {
                Printer.printError("Error occurred when trying to read next line, try again.");
                input = "";
            }
        }

        try {
            Storage.saveTasks(tasks.getList());
        } catch (Exception e) {
            Printer.printWithDecorations("Error occured when trying to save tasks");
        }
    }

    public static void main(String[] args) {
        init();
        run();
        sc.close();
        Printer.printBye();
    }
}