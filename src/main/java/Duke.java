import exceptions.DukeException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.Scanner;

public class Duke {

    private static final String LINE = "\t____________________________________________________________";
    private static final String LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String FILE_PATH = "data/data.txt";
    private final Tasklist tasklist;
    private boolean isActive;

    public Duke() {
        Path path = Paths.get(FILE_PATH);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path.getParent());
                Files.createFile(Paths.get(FILE_PATH));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.tasklist = new Tasklist(path);
        this.isActive = true;
        this.greet();
    }

    public void greet() {
        System.out.println("Hello from\n"
                + LOGO
                + "\nWhat can I do for you?");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();

        Scanner s = new Scanner(System.in);
        while (duke.isActive()) {
            String input = s.nextLine();
            duke.parseInput(input);
        }
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void parseInput(String input) {
        String[] delimited = input.split(" ");
        switch (delimited[0].toLowerCase()) {
        case "bye":
            this.isActive = false;
            this.printOutput("Bye. Hope to see you again soon!");
            break;
        case "list":
            System.out.println(LINE);
            this.tasklist.viewList();
            System.out.println(LINE);
            break;
        case "mark":
            try {
                this.mark(delimited);
            } catch (DukeException e) {
                this.printOutput(e.getMessage());
            }
            break;
        case "unmark":
            try {
                this.unmark(delimited);
            } catch (DukeException e) {
                this.printOutput(e.getMessage());
            }
            break;
        case "todo":
            try {
                this.addTask(TaskTypes.TODO, input, delimited);
            } catch (DukeException e) {
                this.printOutput(e.getMessage());
            }
            break;
        case "deadline":
            try {
                this.addTask(TaskTypes.DEADLINE, input, delimited);
            } catch (DukeException e) {
                this.printOutput(e.getMessage());
            }
            break;
        case "event":
            try {
                this.addTask(TaskTypes.EVENT, input, delimited);
            } catch (DukeException e) {
                this.printOutput(e.getMessage());
            }
            break;
        case "delete":
            try {
                this.deleteTask(delimited);
            } catch (DukeException e) {
                this.printOutput(e.getMessage());
            }
            break;
        default:
            this.printOutput("I don't quite get what that means.");
        }
    }

    private void printOutput(String text) {
        System.out.println(LINE);
        System.out.println("\t " + text);
        System.out.println(LINE);
    }

    private void mark(String[] input) throws DukeException {
        int index = this.retrieveIndex(input);
        if (this.tasklist.mark(index)) {
            printOutput("I've marked this as done:\n\t " + this.tasklist.get(index));
        } else {
            printOutput("The selected task has already been marked as done.");
        }
    }

    private void unmark(String[] input) throws DukeException {
        int index = this.retrieveIndex(input);
        if (this.tasklist.unmark(index)) {
            printOutput("I've marked this as not done yet:\n\t "
                    + this.tasklist.get(index));
        } else {
            printOutput("The selected task has not yet been marked as done.");
        }
    }

    private int retrieveIndex(String[] input) throws DukeException {
        if (input.length < 2) {
            throw new DukeException("Please provide a task number.");
        }
        try {
            int index = Integer.parseInt(input[1]);
            if (index > this.tasklist.size()) {
                throw new DukeException("Invalid task number provided. "
                        + "Given task number is " + index
                        + " but there are only " + this.tasklist.size()
                        + " task(s) in the list");
            }
            if (index < 1) {
                throw new DukeException("Invalid task number provided. " +
                        "Number cannot be less than 1.");
            }
            return index - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number provided.");
        }
    }

    private void addTask(TaskTypes type, String input, String[] delimitedInput) throws DukeException {
        if (delimitedInput.length < 2) {
            throw new DukeException("Invalid description provided. The description of a task cannot be empty.");
        }
        Task task = new Task("");
        String[] temp;
        String name;
        switch (type) {
        case TODO:
            name = input.split(" ", 2)[1];
            task = new Todo(name);
            break;
        case DEADLINE:
            if (!input.contains("/by")) {
                throw new DukeException("Please provide a deadline using /by");
            }
            temp = input.split(" /by ");
            if (temp.length < 2) {
                throw new DukeException("Please provide a valid deadline.");
            }
            name = temp[0].split(" ", 2)[1];
            try {
                LocalDate deadline = LocalDate.parse(temp[1]);
                task = new Deadline(name, deadline);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please provide the deadline in the following format: YYYY-MM-DD.");
            }
            break;
        case EVENT:
            if (!input.contains("/from") || !input.contains("/to")) {
                throw new DukeException("Please provide a start date and end date using /from and /to respectively.");
            }
            temp = input.split(" /from ");
            name = temp[0].split(" ", 2)[1];
            String[] dates = temp[1].split(" /to ");
            if (dates.length < 2) {
                throw new DukeException("Please provide a valid start and end date.");
            }
            try {
                LocalDate startDate = LocalDate.parse(dates[0]);
                LocalDate endDate = LocalDate.parse(dates[1]);
                task = new Event(name, startDate, endDate);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please provide the dates in the following format: YYYY-MM-DD.");
            }
            break;
        }
        this.tasklist.addTask(task, type);
        this.printOutput(
                "I've added the following to your list of tasks:\n\t\t" +
                        task + "\n\t You now have " + this.tasklist.size() + " task(s) in the list.");
    }

    private void deleteTask(String[] delimitedInput) throws DukeException {
        int index = retrieveIndex(delimitedInput);
        Task task = this.tasklist.deleteTask(index);
        this.printOutput("I've removed the following from your list of tasks:\n\t\t"
                + task + "\n\t You now have " + this.tasklist.size() + " task(s) in the list.");
    }
}
