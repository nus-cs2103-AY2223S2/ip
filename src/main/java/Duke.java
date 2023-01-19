
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static String LINE = "____________________________________________________________\n";

    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(LINE + " Hello! I'm Duke\n What can I do for you?\n" + LINE);

        List<Task> tasks = new ArrayList<Task>();
        boolean flag_continue = true;
        while (flag_continue) {
            Scanner sc = new Scanner(System.in);
            String[] input = sc.nextLine().trim().split(" ", 2);
            StringBuilder output = new StringBuilder(LINE);
            try {
                switch(input[0]) {
                    case "bye":
                        flag_continue = false;
                        output.append(" Bye. Hope to see you again soon!\n");
                        break;

                    case "list":
                        if(tasks.size() == 0) { throw new DukeException("You have 0 tasks in the list\n"); }
                        for(int i = 0; i < tasks.size(); i++) {
                            output.append(i + 1).append(".").append(tasks.get(i)).append("\n");
                        }
                        break;

                    case "mark":
                        try {
                            int n = Integer.parseInt(input[1]) - 1;
                            Task task = tasks.get(n);
                            task.mark();
                            tasks.set(n, task);
                            output.append("Nice! I've marked this task as done:\n  ").append(task).append("\n");
                            break;
                        } catch(ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("The index to be marked cannot be empty.");
                        } catch(IndexOutOfBoundsException e) {
                            throw new DukeException("The index to be marked must be in the list.");
                        } catch(NumberFormatException e) {
                            throw new DukeException("The index to be marked must be an integer.");
                        }

                    case "unmark":
                        try {
                            int n = Integer.parseInt(input[1]) - 1;
                            Task task = tasks.get(n);
                            task.unmark();
                            tasks.set(n, task);
                            output.append("OK, I've marked this task as not done yet:\n  ").append(task).append("\n");
                            break;
                        } catch(ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("The index to be unmarked cannot be empty.");
                        } catch(IndexOutOfBoundsException e) {
                            throw new DukeException("The index to be unmarked must be in the list.");
                        } catch(NumberFormatException e) {
                            throw new DukeException("The index to be unmarked must be an integer.");
                        }

                    case "todo":
                        try {
                            input[1] = input[1].trim();
                            if(input[1].equals("")) { throw new ArrayIndexOutOfBoundsException(); }
                            Task task = new Todo(input[1]);
                            tasks.add(task);
                            output.append(task).append("\n");
                            output.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
                            break;
                        } catch(ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }

                    case "deadline":
                        try {
                            String[] cmd = input[1].split(" /by ", 2);
                            if(cmd[0].trim().equals("")) { throw new DukeException("The description of a deadline cannot be empty."); }
                            if(cmd[1].trim().equals("")) { throw new DukeException("The deadline of a deadline cannot be empty."); }
                            Task task = new Deadline(cmd[0], cmd[1]);
                            tasks.add(task);
                            output.append(task).append("\n");
                            output.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
                            break;
                        } catch(ArrayIndexOutOfBoundsException e) {
                            if(input.length == 1) {
                                throw new DukeException("The description of a deadline cannot be empty.");
                            } else {
                                throw new DukeException("A deadline must have a deadline.");
                            }
                        }

                    case "event":
                        try {
                            String[] cmd = input[1].split(" /by ", 2);
                            String[] time = cmd[1].split(" /to ", 2);
                            Task task = new Event(cmd[0], time[0], time[1]);
                            tasks.add(task);
                            output.append(task).append("\n");
                            output.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
                            break;
                        } catch(ArrayIndexOutOfBoundsException e) {
                            if(input.length == 1) { throw new DukeException("The description of an event cannot be empty."); }
                            String[] cmd = input[1].split(" /by ", 2);
                            if(cmd.length == 1) {
                                throw new DukeException("An event must have a duration.");
                            } else {
                                throw new DukeException("An invalid duration was given.");
                            }
                        }

                    default:
                        /*task = new Task(input);
                        tasks.add(task);
                        output.append(task).append("\n");
                        output.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
                        break;*/
                        throw new DukeException();
                }

                output.append(LINE);
                System.out.println(output);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
