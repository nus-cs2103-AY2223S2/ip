package duke;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.PriorityBlockingQueue;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Ui is a class to handle the intial greetings, final greetings, and all
 * the deciphering of the user's input. Ui invokes the appropriate classes
 * when required
 *
 * @author Muhammad Reyaaz
 * @version %I% %G%
 * @since 11
 */
class Ui {
    private Scanner scanner;
    private String description;
    private TaskList<Task> tasks;
    //private final TaskScheduler recurList = new TaskScheduler(1, new PriorityBlockingQueue<>(100, Comparator.comparing(Recur::getMockRemainingTime)));;
    /**
     * Default constructor instantiates the scanner to read from the user
     * machine's keyboard
     */
    protected Ui() {
        scanner = new Scanner(System.in);
    }

    Ui(String description) {
        if (!description.endsWith(" ")) {
            description = description + " ";
        }
        assert(description.length() > 1);
        scanner = new Scanner(description);
        this.description = scanner.next();
    }

    /**
     * Display the custom football Alex Furguson character message
     */
    void showWelcome() {
        Parser.greet();
    }
    /**
     * Handle the user input from the user's machine keyboard
     */
    void readCommand() {
        description = scanner.next();
    }
    /**
     * Match the user's input to the relevant type of input that can be
     * processed by Duke, and call the relevant classes in Parser. Due to
     * immutability, set the list of task to the new list of tasks returned
     * by Parser
     * @return TaskList
     */
    TaskList<Task> execute(TaskList<Task> tasks, List<Timeline> recurResponse) {
        if (description.equals(Parser.SHOW_TASKS)) {
            this.tasks = tasks.listAllTasks();
        } else if (description.equals(Parser.TERMINATE)) {
            Parser.exit();
        } else if (description.equals(Parser.MARK)) {
            this.tasks = Parser.mark(scanner, tasks);
        } else if (description.equals(Parser.UNMARK)) {
            this.tasks = Parser.unmark(scanner, tasks);
        } else if (description.equals(Parser.TODO)) {
            this.tasks = Parser.toDo(scanner, tasks);
        } else if (description.equals(Parser.DEADLINE)) {
            this.tasks = Parser.deadline(scanner, tasks);
        } else if (description.equals(Parser.EVENT)) {
            this.tasks = Parser.events(scanner, tasks);
        } else if (description.equals(Parser.DELETE)) {
            this.tasks = Parser.delete(scanner, tasks);
        } else if (description.equals(Parser.FIND)) {
            this.tasks = Parser.find(scanner, tasks);
        } else if (description.equals(Parser.RECUR)) {
            this.tasks = Parser.recur(scanner, tasks, recurResponse);
        } else {
            dukeExceptionWarning(description, tasks);
        }
        return this.tasks;
    }

    /**
     * Checks the user input against a list of invalid commands
     * (blacklist). If the input is blacklisted, a new DukeUnknownException
     * is thrown. Otherwise, a new Task will be created and added to the lit
     * of previously keyed in Task.
     *
     * @param description The user's task title in String
     * @throws IllegalArgumentException
     * @throws DukeUnknownException
     *
     *
     */
    void dukeExceptionWarning(String description, TaskList<Task> tasks) {
        try {
            if (Parser.INVALID_COMMANDS.contains(description)) {
                throw new DukeUnknownException("Illegal command");
            } else {
                Task newTask = new Task(description + scanner.nextLine());
                this.tasks = tasks.add(newTask);
                //return this.tasks;
            }
        } catch (DukeUnknownException e) {
            System.out.println(Parser.ILLEGAL_COMMAND);
        }
    }
}
