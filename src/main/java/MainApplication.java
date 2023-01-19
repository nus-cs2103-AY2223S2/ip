import java.util.Scanner;

/**
 * MainApplication is the main application of the Duke program. It serves as a
 * translation layer between the command line and model classes.
 */
public class MainApplication {
    private TaskApplication taskApplication;
    private String DELIMITER =  "\t---";
    public MainApplication() {
        this.taskApplication = new TaskApplication();
    }

    private void listTasksCommand() {
        System.out.println("\tHere are the tasks in your list:");
        this.taskApplication.printAllTasks();
    }

    private void markCommand(String command) throws DukeException{
        int index;
        String[] tokens = command.split(" ");
        if (tokens.length < 2) throw new DukeInvalidArgumentException("The (mark)" +
                "command requires 1 integer argument");
        index = -1;
        try {
            index = Integer.parseInt(tokens[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("\tThe (mark)" +
                    "command requires 1 integer argument\n");
        }
        this.taskApplication.markTask(index);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + this.taskApplication.getTask(index));
    }

    private void unmarkCommand(String command) throws DukeException {
        int index;
        String[] tokens = command.split(" ");
        if (tokens.length < 2) throw new DukeInvalidArgumentException("\tThe (unmark)" +
                "command requires 1 integer argument\n");
        index = -1;
        try {
            index = Integer.parseInt(tokens[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("The (mark)" +
                    "command requires 1 integer argument");
        }
        this.taskApplication.unmarkTask(index);
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t" + this.taskApplication.getTask(index));
    }

    private void todoCommand(String command) throws DukeException {
        Task t;
        String[] tokens = command.split(" ");
        if (tokens.length < 2) throw new DukeInvalidArgumentException("\tThe (todo)" +
                "command requires 1 String argument to set the title of the task.\n");
        tokens = command.split(" ", 2);
        t = new ToDo(tokens[1]);
        this.taskApplication.addTask(t);
        System.out.printf("\tGot it. I've added this task:\n" +
                "\t%s\n\tNow you have %d tasks in the list.\n", t, this.taskApplication.getNoOfTasks());
    }

    private void deadlineCommand(String command) throws DukeException {
        Task t;
        String[] tokens = tokens = command.split("/");
        t = new Deadline(tokens[0].split(" ", 2)[1],
                tokens[1].split(" ", 2)[1]);
        this.taskApplication.addTask(t);
        System.out.printf("\tGot it. I've added this task:\n" +
                "\t%s\n\tNow you have %d tasks in the list.\n", t, this.taskApplication.getNoOfTasks());
    }

    private void eventCommand(String command) throws DukeException {
        Task t;
        String[] tokens = command.split("/");
        t = new Event(tokens[0].split(" ", 2)[1],
                tokens[1].split(" ", 2)[1],
                tokens[2].split(" ", 2)[1]);
        this.taskApplication.addTask(t);
        System.out.printf("\tGot it. I've added this task:\n" +
                "\t%s\n\tNow you have %d tasks in the list.\n", t, this.taskApplication.getNoOfTasks());
    }

    private void parseCommand(String command) throws DukeException {
        String[] tokens = command.split(" ", 2);

        switch(tokens[0]) {
            case "list":
                this.listTasksCommand();
                break;
            case "mark":
                this.markCommand(command);
                break;
            case "unmark":
                this.unmarkCommand(command);
                break;
            case "todo":
                this.todoCommand(command);
                break;
            case "deadline":
                this.deadlineCommand(command);
                break;
            case "event":
                this.eventCommand(command);
                break;
            default:
                throw new DukeUnknownCommandException("\tUnknown command\n");
        }
    }
    public void start() {
        // Initialise variables
        Scanner scanner = new Scanner(System.in);
        String command;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        while (scanner.hasNextLine()) {
            command = scanner.nextLine();
            System.out.println(DELIMITER);

            if (command.equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println(DELIMITER);
                break;
            }

            try {
                this.parseCommand(command);
            } catch (DukeException e) {
                // Any DukeException is non-fatal, so we just print them
                System.out.print(e.getMessage());
            }

            System.out.println(DELIMITER);
        }
    }
}
