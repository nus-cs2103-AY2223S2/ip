import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LINE = "__________________________________________________________\n";
    private ArrayList<Task> taskList = new ArrayList<>(100);
    private int counter = 0;
    private enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, THROUGH
    }

    public void greet() {
        System.out.println(Duke.LINE
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + Duke.LINE);
    }

    public void exit() {
        System.out.println(Duke.LINE
                + "Bye. Hope to see you again soon!\n"
                + Duke.LINE);
    }

    public void list() {
        System.out.println(Duke.LINE + "Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            System.out.println(i + 1 + "." + taskList.get(i).toString());
        }
        System.out.println(Duke.LINE);
    }

    public void mark(String input) {
        int position = Integer.parseInt(input) - 1;
        Task toChange = taskList.get(position);
        toChange.mark();
        System.out.println(Duke.LINE + "Nice! I've marked this task as done:\n"
                + toChange.toString() + "\n" + Duke.LINE);
    }

    public void unmark(String input) {
        int position = Integer.parseInt(input) - 1;
        Task toChange = taskList.get(position);
        toChange.unmark();
        System.out.println(Duke.LINE + "Okay, I've marked this task as not done yet:\n"
                + toChange.toString() + "\n" + Duke.LINE);
    }

    public void todo(String input) {
        Task toAdd = new Todos(input);
        taskList.add(toAdd);
        counter++;
        System.out.println(Duke.LINE + "Got it. I've added this task:\n" + toAdd.toString());
        System.out.println("Now you have " + counter + " tasks in the list.");
        System.out.println(Duke.LINE);
    }

    public void deadline(String input) {
        String[] splited = input.split(" /by ");
        Task toAdd = new Deadlines(splited[0], splited[1]);
        taskList.add(toAdd);
        counter++;
        System.out.println(Duke.LINE + "Got it. I've added this task:\n" + toAdd.toString());
        System.out.println("Now you have " + counter + " tasks in the list.");
        System.out.println(Duke.LINE);
    }

    public void event(String input) {
        String[] splited = input.split(" /from | /to " );
        Task toAdd = new Events(splited[0], splited[1], splited[2]);
        taskList.add(toAdd);
        counter++;
        System.out.println(Duke.LINE + "Got it. I've added this task:\n" + toAdd.toString());
        System.out.println("Now you have " + counter + " tasks in the list.");
        System.out.println(Duke.LINE);
    }

    public void delete(String input) {
        int position = Integer.parseInt(input) - 1;
        Task toRemove = taskList.remove(position);
        counter--;
        System.out.println(Duke.LINE + "Noted. I've removed this task:\n" + toRemove.toString());
        System.out.println("Now you have " + counter + " tasks in the list.");
        System.out.println(Duke.LINE);
    }

    public void through(String input) {
        LocalDateTime date = LocalDateTime.parse(input);
        int i = 1;
        System.out.println(Duke.LINE + "Here are the tasks occurring through "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ":");
        for (Task t : this.taskList) {
            if (t.isWithinDate(date)) {
                System.out.println(i + "." + t);
                i++;
            }
        }
        System.out.println(Duke.LINE);
    }

    public void start(Scanner sc) {
        this.greet();

        while(sc.hasNext()) {
            try {
                String[] input = sc.nextLine().split(" ", 2);
                Command c = Command.valueOf(input[0].toUpperCase());

                switch (c) {
                    case BYE:
                        this.exit();
                        break;

                    case LIST:
                        this.list();
                        break;

                    case MARK:
                        if (input.length < 2) {
                            throw new NumberMissingException();
                        }
                        this.mark(input[1]);
                        break;

                    case UNMARK:
                        if (input.length < 2) {
                            throw new NumberMissingException();
                        }
                        this.unmark(input[1]);
                        break;

                    case TODO:
                        if (input.length < 2) {
                            throw new EmptyDescriptionException();
                        }
                        this.todo(input[1]);
                        break;

                    case DEADLINE:
                        if (input.length < 2) {
                            throw new EmptyDescriptionException();
                        }
                        this.deadline(input[1]);
                        break;

                    case EVENT:
                        if (input.length < 2) {
                            throw new EmptyDescriptionException();
                        }
                        this.event(input[1]);
                        break;

                    case DELETE:
                        if (input.length < 2) {
                            throw new NumberMissingException();
                        }
                        this.delete(input[1]);
                        break;

                    case THROUGH:
                        if (input.length < 2) {
                            throw new EmptyDescriptionException();
                        }
                        this.through(input[1]);
                        break;

                }
            }
            catch (IllegalArgumentException e) {
                System.out.println(Duke.LINE + "Command not recognised. Please input a valid command");
                System.out.println(Duke.LINE);
            } catch (DateTimeParseException e) {
                System.out.println(Duke.LINE + "Key in date and time in this format. yyyy-mm-ddThh:mm");
                System.out.println(Duke.LINE);
            }
            catch (Exception e) {
                System.out.println(Duke.LINE + e.getMessage());
                System.out.println(Duke.LINE);
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();
        duke.start(scanner);
    }
}
