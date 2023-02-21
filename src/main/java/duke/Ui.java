package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import duke.exceptions.DukeyException;

/**
 * Deals with user interactions, namely reading input from the user and printing messages to the user.
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);
    private Parser parser = new Parser();

    private String readLine() {
        return scanner.nextLine();
    }

    public ActionEnum readCommand() throws DukeyException {
        this.print("Dukey command: ");
        String input = this.readLine();
        return parser.parseCommand(input);
    }

    public String readEchoInput() {
        return this.readLine();
    }

    public String readTaskName(String type) throws DukeyException {
        this.print(type + " task name: ");
        String input = this.readLine();
        return parser.parseTaskName(input);
    }

    public LocalDate readTime(String type) throws DukeyException {
        this.print(type + ": ");
        String timeString = this.readLine();
        return parser.parseDate(timeString);
    }

    public String readKeyword() throws DukeyException {
        this.print("Search keyword: ");
        String keyword = this.readLine();
        return parser.parseKeyword(keyword);
    }

    private void printLine(String string) {
        System.out.println(string);
    }

    private void print(String string) {
        System.out.print(string);
    }


    public String getTaskNumber() {
        this.print("Task number: ");
        return this.readLine();
    }

    public void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        this.printLine("Hello from\n" + logo);
        this.printLine("Welcome welcome!");
    }

    public void printGoodbyeMessage() {
        this.printLine("DukeyList: Goodbye!! Please return to Dukey again soon!! :)");
    }

    public void printLnBreak() {
        this.printLine("_________________________________________________________");
    }

    public void printErrorBreak() {
        this.printLine("__________________________error__________________________");
    }

    public void echo(String string) {
        this.printLine("Dukey: " + string);
    }

    public void printWrongCommandMessage() {
        this.printLine("Error! Unknown command. Try again!");
    }

    public void printTask(int taskNumber, Task task) {
        this.printLine((taskNumber + 1) + ". " + task);
    }

    public void printTask(Task task) {
        this.printLine(task.toString());
    }

    public void printEmptyListMessage() {
        this.printLine("DukeyList: DukeyList is empty!");
    }

    public void readList(ArrayList<Task> list) {
        if (list.isEmpty()) {
            this.printEmptyListMessage();
        } else {
            this.printLine("DukeyList:");
            Iterator<Task> it = list.iterator();
            it.forEachRemaining(x -> System.out.println((list.indexOf(x) + 1) + ". " + x.toString()));
        }
    }

    public void printFoundTaskList(ArrayList<TaskNumberPair> foundTaskList) {
        if (foundTaskList.isEmpty()) {
            this.printLine("No tasks found matching this keyword!");
        } else {
            this.printLine("DukeyList found these tasks matching the keyword:");
            Iterator<TaskNumberPair> it = foundTaskList.iterator();
            it.forEachRemaining(x -> this.printLine((x.getNumber() + 1) + ". " + x.getTask()));
        }
    }

    public void printAddedMessage(Task task) {
        this.printLine("");
        this.printLine(task.getMessageWhenAdded() + " " + task);
    }

    public void printMarkedMessage(int taskNumber, Task taskToMark) {
        this.printLine("DukeyList: Task number " + (taskNumber + 1) + " has been marked as done!");
        this.printTask(taskNumber, taskToMark);
    }

    public void printUnmarkedMessage(int taskNumber, Task taskToUnmark) {
        this.printLine("DukeyList: Task number " + (taskNumber + 1) + " has been unmarked.");
        this.printTask(taskNumber, taskToUnmark);
    }

    public void printDeletedMessage(Task taskToDelete) {
        this.printLine("DukeyList: The following item has been removed!");
        this.printTask(taskToDelete);
    }

    public void printSavedMessage() {
        System.out.println("DukeList saved!");
    }


    public void printLoadMessage(int status) {
        if (status == 0) {
            this.printLine("DukeyList is empty, starting a new list.");
        } else {
            System.out.println("Saved list loaded:");
        }
    }

    public void printClearedMessage() {
        this.printLine("DukeyList save has been cleared.");
    }


    public void printSize(int size) {
        if (size == 1) {
            printLine("DukeyList now has 1 task.");
        } else {
            printLine("DukeyList now has " + size + " tasks.");
        }
    }

    public void printExceptionMessage(DukeyException e) {
        printLine(e.getMessage());
        printErrorBreak();
    }

    public void printInstruction() {
        printLine("DukeyList: Welcome to DukeyList!! To use DukeyList, " +
                "type the appropriate command and follow the prompts:");
        printLine("To list: 'list'");
        printLine("To exit: 'bye'");
        printLine("To add a todo: 'todo'");
        printLine("To add a deadline: 'deadline'");
        printLine("To add an event: 'event'");
        printLine("To mark a task as done: 'mark'");
        printLine("To unmark a task: 'unmark'");
        printLine("To delete a task: 'delete'");
        printLine("To clear the list: 'clearList'");
        printLine("To save the list: 'save'");
        printLine("To find tasks using keywords: 'find'");
        printLnBreak();
    }

    public void printEchoInstruction() {
        printLine("Start by typing something and Dukey will echo!! Type bye to exit!! ");
    }

}
