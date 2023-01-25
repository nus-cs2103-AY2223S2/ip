package duke;

import duke.enums.Commands;
import duke.enums.TaskTypes;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.TaskStorage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

import java.time.LocalDate;

import java.util.Scanner;

/**
 * Main class for running Duke.
 */
public class Duke {
    private static final String FILE_PATH = "data/data.txt";
    private Ui ui;
    private Parser parser;
    private final Tasklist tasklist;
    private boolean isActive;

    public Duke() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.tasklist = new Tasklist(new TaskStorage(FILE_PATH));
        ui.greet();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Runs Duke and begins parsing input from the user.
     * Duke will deactivate and exit upon receiving "bye"
     * from user input.
     */
    public void run() {
        this.isActive = true;
        Scanner s = new Scanner(System.in);
        while (this.isActive) {
            String input = s.nextLine();
            try {
                Commands command = this.parser.parseInput(input);
                switch (command) {
                case BYE:
                    this.exit();
                    break;
                case LIST:
                    this.viewList();
                    break;
                case MARK:
                    this.mark(parser.getIndex());
                    break;
                case UNMARK:
                    this.unmark(parser.getIndex());
                    break;
                case TODO:
                    this.addTask(parser.getName());
                    break;
                case DEADLINE:
                    this.addTask(
                            parser.getName(),
                            parser.getDeadline());
                    break;
                case EVENT:
                    this.addTask(
                            parser.getName(),
                            parser.getStartDate(),
                            parser.getEndDate());
                    break;
                case DELETE:
                    this.deleteTask(parser.getIndex());
                    break;
                case DEFAULT:
                    this.ui.printOutput("I don't quite get what that means.");
                }
            } catch (DukeException e) {
                this.ui.printOutput(e.getMessage());
            }
        }
    }

    private void exit() {
        this.ui.printOutput("Bye. Hope to see you again soon!");
        this.isActive = false;
    }

    private void viewList() {
        ui.printLine();
        this.tasklist.viewList();
        ui.printLine();
    }

    private void mark(int index) throws DukeException {
        if (this.tasklist.mark(index)) {
            this.ui.printOutput("I've marked this as done:\n\t " + this.tasklist.get(index));
        } else {
            this.ui.printOutput("The selected task has already been marked as done.");
        }
    }

    private void unmark(int index) throws DukeException {
        if (this.tasklist.unmark(index)) {
            this.ui.printOutput("I've marked this as not done yet:\n\t "
                    + this.tasklist.get(index));
        } else {
            this.ui.printOutput("The selected task has not yet been marked as done.");
        }
    }

    private void addTask(String name) {
        Task t = new Todo(name);
        this.tasklist.addTask(t, TaskTypes.TODO);
        this.ui.printOutput(
                "I've added the following to your list of tasks:\n\t\t" +
                        t + "\n\t You now have " + this.tasklist.size() + " task(s) in the list.");
    }

    private void addTask(String name, LocalDate byDate) {
        Task t = new Deadline(name, byDate);
        this.tasklist.addTask(t, TaskTypes.DEADLINE);
        this.ui.printOutput(
                "I've added the following to your list of tasks:\n\t\t" +
                        t + "\n\t You now have " + this.tasklist.size() + " task(s) in the list.");
    }

    private void addTask(String name, LocalDate startDate, LocalDate endDate) {
        Task t = new Event(name, startDate, endDate);
        this.tasklist.addTask(t, TaskTypes.EVENT);
        this.ui.printOutput(
                "I've added the following to your list of tasks:\n\t\t" +
                        t + "\n\t You now have " + this.tasklist.size() + " task(s) in the list.");
    }

    private void deleteTask(int index) throws DukeException {
        Task task = this.tasklist.deleteTask(index);
        this.ui.printOutput("I've removed the following from your list of tasks:\n\t\t"
                + task + "\n\t You now have " + this.tasklist.size() + " task(s) in the list.");
    }
}
