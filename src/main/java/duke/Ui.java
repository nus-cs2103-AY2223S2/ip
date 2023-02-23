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

    public ActionEnum readCommand(String command) throws DukeyException {
        return parser.parseCommand(command);
    }

    public String readEchoInput() {
        return this.readLine();
    }

    public String readTaskName(String input) throws DukeyException {
        return parser.parseTaskName(input);
    }

    public LocalDate readTime(String timeString) throws DukeyException {
        return parser.parseDate(timeString);
    }

    public String readKeyword(String keyword) throws DukeyException {
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

    public String printGoodbyeMessage() {
        return "DukeyList: Goodbye!! Please return to Dukey again soon!! :)";
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

    public String printTask(int taskNumber, Task task) {
        return (taskNumber + 1) + ". " + task;
    }

    public String printTask(Task task) {
        return task.toString();
    }

    public String printEmptyListMessage() {
        return "DukeyList: DukeyList is empty!";
    }

    public String readList(ArrayList<Task> list) {
        StringBuilder sb = new StringBuilder();
        if (list.isEmpty()) {
            sb.append(printEmptyListMessage());
        } else {
            sb.append("DukeyList:\n");
            Iterator<Task> it = list.iterator();
            it.forEachRemaining(x -> sb.append((list.indexOf(x) + 1) + ". " + x.toString() + '\n'));
        }
        return sb.toString();
    }

    public String printFoundTaskList(ArrayList<TaskNumberPair> foundTaskList) {
        StringBuilder sb = new StringBuilder();
        if (foundTaskList.isEmpty()) {
            sb.append("No tasks found matching this keyword!");
        } else {
            sb.append("DukeyList found these tasks matching the keyword:\n");
            Iterator<TaskNumberPair> it = foundTaskList.iterator();
            it.forEachRemaining(x -> sb.append((x.getNumber() + 1) + ". " + x.getTask() + "\n"));
        }

        return sb.toString();
    }

    public String printAddedMessage(Task task) {
        return '\n' + task.getMessageWhenAdded() + "\n" + task;
    }

    public String printMarkedMessage(int taskNumber, Task taskToMark) {
        String response = "";
        response +=  "DukeyList: Task number " + (taskNumber + 1) + " has been marked as done!\n";
        response += printTask(taskNumber, taskToMark);
        return response;
    }

    public String printUnmarkedMessage(int taskNumber, Task taskToUnmark) {
        String response = "";
        response +=  "DukeyList: Task number " + (taskNumber + 1) + " has been unmarked.\n";
        response += printTask(taskNumber, taskToUnmark);
        return response;
    }

    public String printDeletedMessage(Task taskToDelete) {
        String response = "";
        response += "DukeyList: The following item has been removed!\n";
        response += this.printTask(taskToDelete);
        return response;
    }

    public String printSavedMessage() {
        return "DukeList saved!";
    }


    public String printLoadMessage(int status) {
        if (status == 0) {
            return "DukeyList is empty, starting a new list.\n";
        } else {
            return "Saved list loaded:\n";
        }
    }

    public String printClearedMessage() {
        return "DukeyList save has been cleared.";
    }


    public String printSize(int size) {
        if (size == 1) {
           return "DukeyList now has 1 task.\n";
        } else {
            return "DukeyList now has " + size + " tasks.\n";
        }
    }

    public String printExceptionMessage(DukeyException e) {
        return ":(\n" + e.getMessage();
    }

    public String printInstruction() {
        StringBuilder sb = new StringBuilder();
        sb.append("DukeyList: Welcome to DukeyList!! To use DukeyList, " +
                "type the appropriate command and follow the prompts:\n");
        sb.append("To list: 'list'\n");
        sb.append("To exit: 'bye'\n");
        sb.append("To add a todo: 'todo / <name> '\n");
        sb.append("To add a deadline: 'deadline / <name> / deadline '\n");
        sb.append("To add an event: 'event'\n");
        sb.append("To mark a task as done: 'mark'\n");
        sb.append("To unmark a task: 'unmark'\n");
        sb.append("To delete a task: 'delete'\n");
        sb.append("To clear the list: 'clearList'\n");
        sb.append("To save the list: 'save'\n");
        sb.append("To find tasks using keywords: 'find'\n");

        return sb.toString();
    }

    public void printEchoInstruction() {
        printLine("Start by typing something and Dukey will echo!! Type bye to exit!! ");
    }

    public void close() {
        scanner.close();
    }

}
