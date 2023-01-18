import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String chopper =
                        "           /\\_/\\\n" +
                        "          ( o.o )\n" +
                        "           > ^ <\n";
        System.out.println(chopper);
        System.out.println("    Hello I'm chopper\n    What can I do for you?");

        ArrayList<Task> Tasks = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);


        while (true) {
            String input = sc.nextLine();
            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 0; i < Tasks.size(); i++) {
                        int num = i + 1;
                        System.out.print("    " + num + ". " + Tasks.get(i) + "\n");
                    }
                } else if (input.length() >= 8 && input.substring(0, 8).equals("deadline")) {

                    int index_by = input.indexOf("/");
                    if (index_by - 1 < 9) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                    if (index_by + 4 > input.length()) {
                        throw new DukeException("OOPS!!! You are missing the deadline of a deadline.");
                    }
                    Deadline d = new Deadline(input.substring(9, index_by - 1),
                            input.substring(index_by + 4, input.length()));
                    Tasks.add(d);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + d);
                    System.out.println("    Now you have " + Tasks.size() + " tasks in the list.");

                } else if (input.length() >= 5 && input.substring(0, 5).equals("event")) {
                    int index_from = input.indexOf("/");
                    int index_to = input.lastIndexOf("/");
                    if (index_from - 1 < 6) {
                        throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                    }
                    if (index_from + 6 > index_to - 1) {
                        throw new DukeException("OOPS!!! You are missing the beginning of the event date.");
                    }
                    if (index_to + 4 > input.length()) {
                        throw new DukeException("OOPS!!! You are missing the ending of the event date.");
                    }
                    Event e = new Event(input.substring(6, index_from - 1),
                            input.substring(index_from + 6, index_to - 1),
                            input.substring(index_to + 4, input.length()));
                    Tasks.add(e);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + e);
                    System.out.println("    Now you have " + Tasks.size() + " tasks in the list.");

                } else if (input.length() >= 6 && input.substring(0, 6).equals("unmark")) {
                    if (input.length() <= 7) {
                        throw new DukeException("OOPS!!! You are missing the number of the task to be unmarked.");
                    }
                    int index = Integer.parseInt(input.substring(7));
                    Task task = Tasks.get(index - 1);
                    task.unmark();
                    System.out.println("    OK, I've marked this task as not done yet:");
                    System.out.println("      " + task);

                } else if (input.length() >= 4) {
                    if (input.substring(0, 4).equals("mark")) {
                        if (input.length() <= 5) {
                            throw new DukeException("OOPS!!! You are missing the number of the task to be marked.");
                        }
                        int index = Integer.parseInt(input.substring(5));
                        Task task = Tasks.get(index - 1);
                        task.mark();
                        System.out.println("    Nice! I've marked this task as done:");
                        System.out.println("      " + task);
                    } else if (input.substring(0, 4).equals("todo")) {
                        if (5 > input.length()) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        Todo td = new Todo(input.substring(5, input.length()));
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("      " + td);
                        Tasks.add(td);
                        System.out.println("    Now you have " + Tasks.size() + " tasks in the list.");
                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException de){
                System.out.println(de.getMessage());
            } catch (NumberFormatException nfe) {
                System.out.println("OOPS!!! Mark or unmark has to be followed by an int.");
            } catch (IndexOutOfBoundsException i) {
                System.out.println("OOPS!!! There are insufficient tasks.");
            }
        }
        System.out.println("    " + "Bye. Hope to see you again soon!");
    }

}
