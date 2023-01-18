import java.util.Scanner;
public class Duke {
    /** Scanner used by each duke */
    private Scanner scanner;
    /** Whether the duke is still running or has been commanded to end */
    private boolean isRunning;

    /** Array of tasks */
    private Task[] tasks;

    /** Number of tasks stored */
    private int numTasks;

    /**
     * Runs the duke.
     */
    public void run() {
        this.init();
        while (this.isRunning) {
            this.execute(this.readCommand());
        }
        this.exit();
    }

    private String[] parseCommand(String command) {
        int firstSpace = command.indexOf(" ");
        if (firstSpace != -1) {
            // insert a " /" so that command can be split by " /"
            command = command.substring(0, firstSpace)
                    + " /" + command.substring(firstSpace);
        }
        String[] result = command.split(" /");
        for (int i = 1; i < result.length; i++) {
            firstSpace = result[i].indexOf(" ");
            result[i] = result[i].substring(firstSpace + 1);
        }
        return result;
    }

    private void addTask(Task task) {
        this.tasks[this.numTasks] = task;
        this.numTasks += 1;
        System.out.println("added " + task);
    }

    private void showTasks() {
        for (int i = 0; i < this.numTasks; i++) {
           System.out.println(i + ". " + this.tasks[i]);
        }
    }

    private void toggleTask(Task task) {
        task.toggleDone();
        System.out.println("Task " + task + " marked as " + (task.getIsDone() ? "" : "not ") + "done");
    }
    private void init() {
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
        this.tasks = new Task[100];
        this.numTasks = 0;
        System.out.println("Hello!");
    }

   private String readCommand() {
        return scanner.nextLine();
    }

    private void execute(String command) {
        String[] args = parseCommand(command);
        if (args[0].equals("bye")) {
            this.isRunning = false;
        } else if (args[0].equals("todo")) {
            this.addTask(new Todo(args[1]));
        } else if (args[0].equals("deadline")) {
            this.addTask(new Deadline(args[1], args[2]));
        } else if (args[0].equals("event")) {
            this.addTask(new Event(args[1], args[2], args[3]));
        } else if (args[0].equals("list")) {
            this.showTasks();
        } else if (args[0].equals("mark")) {
            this.toggleTask(this.tasks[Integer.parseInt(args[1])]);
        }
    }

    private void exit() {
        this.scanner.close();
        System.out.println("GoodBye!");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
