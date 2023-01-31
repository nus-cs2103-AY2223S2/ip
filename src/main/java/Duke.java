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

    public void mark(String command) throws DukeException{
        if (command.trim().equals("mark")) {
            throw new DukeException("\t ☹ OOPS!!! The description of a mark cannot be empty.\n");
        }
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            if (index + 1 > taskIndex || index < 0) {
                throw new DukeException("\t ☹ OOPS!!! Please input a valid number.\n");
            }
            tasks[index].mark();
        } catch (NumberFormatException ex) {
            System.out.println("\t ☹ OOPS!!! Please input a valid number.\n");
        }
    }

    public void unmark(String command) throws DukeException{
        if (command.trim().equals("mark")) {
            throw new DukeException("\t ☹ OOPS!!! The description of a unmark cannot be empty.\n");
        }
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            if (index + 1 > taskIndex || index < 0) {
                throw new DukeException("\t ☹ OOPS!!! Please input a valid number.\n");
            } else if (tasks[index].getStatusIcon().equals(" ")) {
                throw new DukeException("\t ☹ OOPS!!! This task has not been marked yet.\n");
            }
            tasks[index].unmark();
        } catch (NumberFormatException ex) {
            System.out.println("\t ☹ OOPS!!! Please input a valid number.\n");
        }
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
    public void todo(String command) throws DukeException {
        if (command.trim().equals("todo")) {
            throw new DukeException("\t ☹ OOPS!!! The description of a todo cannot be empty.\n");
        }
        String description = command.split(" ", 2)[1];
        Todo todo = new Todo(description);
        addTask(todo);
    }

    public void deadline(String command) throws DukeException {
        if (command.trim().equals("deadline")) {
            throw new DukeException("\t ☹ OOPS!!! The description of a deadline cannot be empty.\n");
        } else if (command.trim().contains(" /by ") == false) {
            throw new DukeException("\t ☹ OOPS!!! There is no deadline date or the wrong format\n");
        }

        command = command.split(" ", 2)[1];
        if (command.startsWith("/by")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.\n");
        } else if (command.endsWith("/by")) {
            throw new DukeException(("☹ OOPS!!! The due date of a deadline cannot be empty.\n"));
        }

        String info[] = command.split(" /by ");
        String description = info[0];
        String by = info[1];
        Deadline deadline = new Deadline(description, by);
        addTask(deadline);
    }

    public void event(String command) throws DukeException{
        command = command.trim();
        if (command.equals("event")) {
            throw new DukeException("\t ☹ OOPS!!! The description of a event cannot be empty.\n");
        } else if (command.contains(" /from ") == false || command.contains(" /to ") == false) {
            throw new DukeException("\t ☹ OOPS!!! The from date or due date of a deadline cannot be empty.\n"
                    + "\t Or the format is wrong.\n");
        }

        command = command.split(" ", 2)[1];
        if (command.startsWith("/from") || command.endsWith("/to") || command.contains("/from /to")) {
            throw new DukeException("\t ☹ OOPS!!! The description, from date or due date of a event cannot be empty.\n");
        }

        String info[] = command.split(" /from ");
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
            try {
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
                } else {
                    display();
                    System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means.\n");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
