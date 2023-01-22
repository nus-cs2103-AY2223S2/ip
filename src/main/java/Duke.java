import java.util.Scanner;

public class Duke {
    enum Command {
        TODO, DEADLINE, EVENT, DELETE, LIST, BYE, MARK, UNMARK;
    }
    public static void main(String[] args) {
        String chopper =
                        "           /\\_/\\\n" +
                        "          ( o.o )\n" +
                        "           > ^ <\n";
        System.out.println(chopper);
        System.out.println("    Hello I'm chopper\n    What can I do for you?");

        TaskList Tasks = new TaskList();
        Tasks = Save.loadTaskList();

        Scanner sc = new Scanner(System.in);


        while (true) {
            String input = sc.nextLine();

            try {
                String[] words = input.split(" ");
                Command command = Command.valueOf(words[0].toUpperCase());
                if (command == Command.BYE) {
                    Save.saveTaskList(Tasks);
                    System.out.println("    " + "Bye. Hope to see you again soon!");
                    break;
                }
                switch(command) {
                    case LIST:
                        list(Tasks);
                        break;
                    case DEADLINE:
                        deadline(Tasks, input);
                        break;
                    case UNMARK:
                        unmark(Tasks, input);
                        break;
                    case TODO:
                        todo(Tasks, input);
                        break;
                    case EVENT:
                        event(Tasks, input);
                        break;
                    case DELETE:
                        delete(Tasks, input);
                        break;
                    case MARK:
                        mark(Tasks, input);
                        break;
                }
            }  catch (IllegalArgumentException i) {
                System.out.println("    OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
    private static void list(TaskList tasks) {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            System.out.print("    " + num + ". " + tasks.get(i) + "\n");
        }
    }
    private static void deadline(TaskList tasks, String input) {
        try {
            int index_by = input.indexOf("/");
            if (index_by - 1 < 9) {
                throw new DukeException("    OOPS!!! The description of a deadline cannot be empty.");
            }
            if (index_by + 4 > input.length()) {
                throw new DukeException("    OOPS!!! You are missing the deadline of a deadline.");
            }
            Deadline d = new Deadline(input.substring(9, index_by - 1),
                    input.substring(index_by + 4, input.length()));
            tasks.add(d);
            Save.saveTaskList(tasks);
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + d);
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        } catch (DukeException de) {
            System.out.println(de.getMessage());
        }
    }
    private static void todo(TaskList tasks, String input) {
        try {
            if (5 > input.length()) {
                throw new DukeException("    OOPS!!! The description of a todo cannot be empty.");
            }
            Todo td = new Todo(input.substring(5, input.length()));
            tasks.add(td);
            Save.saveTaskList(tasks);
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + td);
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        } catch (DukeException de) {
            System.out.println(de.getMessage());
        }
    }
    private static void event(TaskList tasks, String input) {
        try {
            int index_from = input.indexOf("/");
            int index_to = input.lastIndexOf("/");
            if (index_from - 1 < 6) {
                throw new DukeException("    OOPS!!! The description of a event cannot be empty.");
            }
            if (index_from + 6 > index_to - 1) {
                throw new DukeException("    OOPS!!! You are missing the beginning of the event date.");
            }
            if (index_to + 4 > input.length()) {
                throw new DukeException("    OOPS!!! You are missing the ending of the event date.");
            }
            Event e = new Event(input.substring(6, index_from - 1),
                    input.substring(index_from + 6, index_to - 1),
                    input.substring(index_to + 4, input.length()));
            tasks.add(e);
            Save.saveTaskList(tasks);
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + e);
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        } catch (DukeException de) {
            System.out.println(de.getMessage());
        }
    }
    private static void delete(TaskList tasks, String input) {
        try {
            if (input.length() <= 7) {
                throw new DukeException("    OOPS!!! Delete must be followed by an int.");
            }
            int index = Integer.parseInt(input.substring(7));
            Task task = tasks.get(index - 1);
            tasks.remove(index - 1);
            Save.saveTaskList(tasks);
            System.out.println("    Noted. I've removed this task:");
            System.out.println("      " + task);
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        } catch (DukeException de) {
            System.out.println(de.getMessage());
        }
    }
    private static void mark(TaskList tasks, String input) {
        try {
            if (input.length() <= 5) {
                throw new DukeException("    OOPS!!! You are missing the number of the task to be marked.");
            }
            int index = Integer.parseInt(input.substring(5));
            Task task = tasks.get(index - 1);
            task.mark();
            Save.saveTaskList(tasks);
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      " + task);
        } catch (DukeException de) {
            System.out.println(de.getMessage());
        } catch (NumberFormatException nfe) {
            System.out.println("    OOPS!!! Mark has to be followed by an int.");
        } catch (IndexOutOfBoundsException i) {
            System.out.println("    OOPS!!! There are insufficient tasks.");
        }
    }
    private static void unmark(TaskList tasks, String input) {
        try {
            if (input.length() <= 7) {
                throw new DukeException("    OOPS!!! You are missing the number of the task to be unmarked.");
            }
            int index = Integer.parseInt(input.substring(7));
            Task task = tasks.get(index - 1);
            task.unmark();
            Save.saveTaskList(tasks);
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("      " + task);
        } catch (DukeException de) {
            System.out.println(de.getMessage());
        } catch (NumberFormatException nfe) {
            System.out.println("    OOPS!!! Unmark has to be followed by an int.");
        } catch (IndexOutOfBoundsException i) {
            System.out.println("    OOPS!!! There are insufficient tasks.");
        }
    }

}
