package duke; 

import java.util.Scanner;


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

    /**
     * Default constructor instantiates the scanner to read from the user
     * machine's keyboard
     */

    protected Ui() {
        this.scanner = new Scanner(System.in);    
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
       this.description = scanner.next();
    }
    
    /**
     * Match the user's input to the relevant type of input that can be
     * processed by Duke, and call the relevant classes in Parser. Due to
     * immutability, set the list of task to the new list of tasks returned
     * by Parser
     *
     * @return TaskList<Task>
     */

    TaskList<Task> execute(TaskList<Task> tasks) {        
        if (description.equals(Parser.SHOW_TASKS)) {
            this.tasks = tasks.listAllTasks();
        } else if (description.equals(Parser.TERMINATE)) {
            Parser.exit();
        } else if (description.equals(Parser.MARK)) {
            this.tasks = Parser.mark(scanner, tasks);
        }  else if (description.equals(Parser.UNMARK)) {
            this.tasks = Parser.unmark(scanner, tasks);
        } else if (description.equals(Parser.TODO)) {
            this.tasks = Parser.toDo(scanner, tasks);
        } else if (description.equals(Parser.DEADLINE)) {
            this.tasks = Parser.deadline(scanner, tasks);
        } else if (description.equals(Printable.EVENT)) {
            this.tasks = Parser.events(scanner, tasks);
        } else if (description.equals(Printable.DELETE)) {
            this.tasks = Parser.delete(scanner, tasks);
        }  else {
            dukeExceptionWarning(description, tasks);
        }
        return this.tasks;
    }
    
    TaskList<Task> dukeExceptionWarning(String description, TaskList<Task> tasks) {
        try {
            if (Parser.INVALID_COMMANDS.contains(description)) {
                throw new DukeUnknownException("Illegal command");
            } else {
                Task newTask = new Task(description);
                this.tasks = tasks.add(newTask);
                return this.tasks;
            }
        } catch (DukeUnknownException e) {
            System.out.println(Printable.ILLEGAL_COMMAND);
        }
        return tasks;
    }
}

