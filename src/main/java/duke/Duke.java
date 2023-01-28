package duke;

import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.function.Predicate;
import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidArgumentException;
import duke.exceptions.DukeInvalidCommandException;
import duke.exceptions.DukeSaveLoadException;
import duke.tasks.Task;
import duke.tasks.TaskDeadline;
import duke.tasks.TaskEvent;
import duke.tasks.TaskList;
import duke.tasks.TaskTodo;

public class Duke {
    private Ui ui = new Ui();
    private Storage storage = new Storage("_duke_data.txt");
    private TaskList tasks;
    private Parser currentCommand;

    public Duke() {
        try {
            this.tasks = this.storage.load();
        } catch (DukeSaveLoadException e) {
            this.ui.showError(e);
            this.tasks = new TaskList();
        }
    }

    public void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        this.ui.show("Hello from\n" + logo);

        whileLoop:
        while (true) {
            if (!this.ui.hasCommand()) {
                continue;
            }
            
            String input = this.ui.readCommand();
            this.currentCommand = new Parser(input);
        
            try {
                switch (this.currentCommand.baseCommand) {
                case "todo":
                    this.addTodo();
                    break;
                case "deadline":
                    this.addDeadline();
                    break;
                case "event":
                    this.addEvent();
                    break;
                case "mark":
                    this.mark();
                    break;
                case "unmark":
                    this.unmark();
                    break;
                case "delete":
                    this.delete();
                    break;
                case "list":
                    this.list();
                    break;
                case "quit":
                case "exit":
                case "bye":
                    this.ui.show("Bye. Hope to see you again soon!");
                    break whileLoop;
                default:
                    throw new DukeInvalidCommandException();
                }

                this.storage.save(this.tasks);
            } catch (DukeException e) {
                this.ui.showError(e);
            }
        }

        this.ui.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    private void addTodo() throws DukeInvalidArgumentException {
        if (this.currentCommand.hasEmptyBody()) {
            throw new DukeInvalidArgumentException("The description of a todo cannot be empty.");
        }
        
        String description = this.currentCommand.body;
        Task task = new TaskTodo(description);
        this.tasks.add(task);
        this.ui.show(
            "Got it. I've added this task:\n"
                + "  " + task.toString() + "\n"
                + this.tasks.getStatus()
        );
    }

    private void addDeadline() throws DukeInvalidArgumentException {
        if (this.currentCommand.hasEmptyBody()) {
            throw new DukeInvalidArgumentException("The description of a deadline cannot be empty.");
        }
        if (!this.currentCommand.namedParameters.containsKey("by")) {
            throw new DukeInvalidArgumentException("The \"/by\" parameter of a deadline is missing.");
        }
        if (this.currentCommand.namedParameters.get("by").isEmpty()) {
            throw new DukeInvalidArgumentException("The \"/by\" parameter of a deadline cannot be empty.");
        }
        
        try {
            String description = this.currentCommand.body;
            Task task = new TaskDeadline(description, this.currentCommand.namedParameters.get("by"));
            this.tasks.add(task);
            this.ui.show(
                "Got it. I've added this task:\n"
                    + "  " + task.toString() + "\n"
                    + this.tasks.getStatus()
            );
        } catch (DateTimeParseException e) {
            throw new DukeInvalidArgumentException(
                "The \"/by\" value must be in the form \"yyyy-mm-dd\" (eg. 2019-10-15)."
            );
        }
    }

    private void addEvent() throws DukeInvalidArgumentException {
        if (this.currentCommand.hasEmptyBody()) {
            throw new DukeInvalidArgumentException("The description of an event cannot be empty.");
        }
        if (!this.currentCommand.namedParameters.containsKey("from")) {
            throw new DukeInvalidArgumentException("The \"/from\" parameter of an event is missing.");
        }
        if (this.currentCommand.namedParameters.get("from").isEmpty()) {
            throw new DukeInvalidArgumentException("The \"/from\" parameter of an event cannot be empty.");
        }
        if (!this.currentCommand.namedParameters.containsKey("to")) {
            throw new DukeInvalidArgumentException("The \"/to\" parameter of an event is missing.");
        }
        if (this.currentCommand.namedParameters.get("to").isEmpty()) {
            throw new DukeInvalidArgumentException("The \"/to\" parameter of an event cannot be empty.");
        }

        try {
            String description = this.currentCommand.body;
            Task task = new TaskEvent(
                description, 
                this.currentCommand.namedParameters.get("from"), 
                this.currentCommand.namedParameters.get("to")
            );
            this.tasks.add(task);
            this.ui.show(
                "Got it. I've added this task:\n"
                    + "  " + task.toString() + "\n"
                    + this.tasks.getStatus()
            );
        } catch (DateTimeParseException e) {
            throw new DukeInvalidArgumentException(
                "The \"/from\" and \"/to\" values must be in the form \"yyyy-mm-dd\" (eg. 2019-10-15)."
            );
        }
    }

    private void mark() throws DukeInvalidArgumentException {
        if (this.currentCommand.hasEmptyBody()) {
            throw new DukeInvalidArgumentException("No task index given.");
        }
        
        Predicate<String> isNumeric = str -> str.matches("^-?\\d+$");
        int taskIndex = Optional.of(this.currentCommand.body)
            .filter(isNumeric)
            .map(body -> Integer.parseInt(body) - 1)
            .filter(i -> i >= 0)
            .orElseThrow(() -> new DukeInvalidArgumentException(
                "Invalid task index. Index needs to be a positive integer."
            ));
        Task task = Optional.of(taskIndex)
            .filter(index -> index < this.tasks.size())
            .map(index -> this.tasks.get(index))
            .orElseThrow(() -> new DukeInvalidArgumentException(
                "Task index is beyond the range of the task list."
            ));
        task.markAsDone();
        this.ui.show(
            "Nice! I've marked this task as done:\n"
                + "  " + task.toString()
        );
    }

    private void unmark() throws DukeInvalidArgumentException {
        if (this.currentCommand.hasEmptyBody()) {
            throw new DukeInvalidArgumentException("No task index given.");
        }
        
        Predicate<String> isNumeric = str -> str.matches("^-?\\d+$");
        int taskIndex = Optional.of(this.currentCommand.body)
            .filter(isNumeric)
            .map(body -> Integer.parseInt(body) - 1)
            .filter(i -> i >= 0)
            .orElseThrow(() -> new DukeInvalidArgumentException(
                "Invalid task index. Index needs to be a positive integer."
            ));
        Task task = Optional.of(taskIndex)
            .filter(index -> index < this.tasks.size())
            .map(index -> this.tasks.get(index))
            .orElseThrow(() -> new DukeInvalidArgumentException(
                "Task index is beyond the range of the task list."
            ));
        task.markAsNotDone();
        this.ui.show(
            "OK, I've marked this task as not done yet:\n"
                + "  " + task.toString()
        );
    }

    private void delete() throws DukeInvalidArgumentException {
        if (this.currentCommand.hasEmptyBody()) {
            throw new DukeInvalidArgumentException("No task index given.");
        }
        
        Predicate<String> isNumeric = str -> str.matches("^-?\\d+$");
        int taskIndex = Optional.of(this.currentCommand.body)
            .filter(isNumeric)
            .map(body -> Integer.parseInt(body) - 1)
            .filter(i -> i >= 0)
            .orElseThrow(() -> new DukeInvalidArgumentException(
                "Invalid task index. Index needs to be a positive integer."
            ));
        Task task = Optional.of(taskIndex)
            .filter(index -> index < this.tasks.size())
            .map(index -> this.tasks.get(index))
            .orElseThrow(() -> new DukeInvalidArgumentException(
                "Task index is beyond the range of the task list."
            ));
        this.tasks.remove(taskIndex);
        this.ui.show(
            "Noted. I've removed this task:\n"
                + "  " + task.toString()
        );
    }

    private void list() {
        this.ui.show(this.tasks.toString());
    }
}
