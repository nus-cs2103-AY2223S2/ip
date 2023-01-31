import java.util.Scanner;

public class Duke {

    private Scanner input = new Scanner(System.in);
    private Task tasks[] = new Task[100];
    private int taskIndex = 0;

    public void start() {
        String logo = "\t  ____        _        \n"
                + "\t |  _ \\ _   _| | _____ \n"
                + "\t | | | | | | | |/ / _ \\\n"
                + "\t | |_| | |_| |   <  __/\n"
                + "\t |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?\n");
    }

    public void display() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    public void mark(String command) {
        int index = Integer.parseInt(command.split(" ")[1]) - 1;
        tasks[index].mark();
    }

    public void unmark(String command) {
        int index = Integer.parseInt(command.split(" ")[1]) - 1;
        tasks[index].unmark();
    }

    public void list() {
        for (int i = 0; i < taskIndex; i++) {
            System.out.print("\t " + (i + 1) + "." + tasks[i].toString() + "\n");
        }
        System.out.print("\n");
    }

    public void addTask(Task task) {
        tasks[taskIndex] = task;
        taskIndex++;
        System.out.println("\t Got it. I've added this task:\n"
                + "\t\t "+ tasks[taskIndex - 1].toString()
                + "\n\t Now you have " + taskIndex + " tasks in the list.\n");
    }
    public void todo(String command) {
        String description = command.split(" ", 2)[1];
        Todo todo = new Todo(description);
        addTask(todo);
    }

    public void deadline(String command) {
        String info[] = command.split(" ", 2)[1].split(" /by ");
        String description = info[0];
        String by = info[1];
        Deadline deadline = new Deadline(description, by);
        addTask(deadline);
    }

    public void event(String command) {
        String info[] = command.split(" ", 2)[1].split(" /from ");
        String description = info[0];
        String dates[] = info[1].split(" /to ");
        String from = dates[0];
        String to = dates[1];

        Event event = new Event(description, from, to);
        addTask(event);
    }

    public void run() {
        start();
        while (true) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                display();
                System.out.println("\t Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                display();
                list();
            } else if (command.startsWith("mark")) {
                display();
                mark(command);
            } else if (command.startsWith("unmark")) {
                display();
                unmark(command);
            } else if (command.startsWith("todo")) {
                display();
                todo(command);
            } else if (command.startsWith("deadline")) {
                display();
                deadline(command);
            } else if(command.startsWith("event")) {
                display();
                event(command);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
