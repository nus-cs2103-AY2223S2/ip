import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private ArrayList<Task> list;
    Save s;
    Duke() {
        list = new ArrayList<>(100);
        s = new Save();
    }

    public static void main(String[] args) {
        Duke a = new Duke();
        System.out.println(a.greeting());
        a.echo();
    }

    private String greeting() {
        return "Hello! I'm Luminus\nWhat can I do for you?";
    }

    /**
     * Adds new todo task
     * @param input Input Chat which starts with "todo"
     * @throws StringIndexOutOfBoundsException if input format isn't correct
     */
    private void toDo(String input) throws StringIndexOutOfBoundsException  {
        try {
            ToDo temp = ToDo.createToDo(input);
            list.add(temp);
            s.write(temp);
            System.out.println("Got it. I've added this task:");
            System.out.println(temp);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        } catch (StringIndexOutOfBoundsException IOBE) {
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Adds new event task
     * @param input Input Chat which starts with "event"
     *              Format: Check Event.java
     * @throws StringIndexOutOfBoundsException if input format isn't correct
     */
    private void event(String input) throws StringIndexOutOfBoundsException {
        try {
            Event temp = Event.createEvent(input);
            list.add(temp);
            s.write(temp);
            System.out.println("Got it. I've added this task:");
            System.out.println(temp);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        } catch (StringIndexOutOfBoundsException IOBE) {
            System.out.println("OOPS!!! The description of a event is not correct.");
        }
    }

    /**
     * Adds new deadline task
     * @param input Input Chat which starts with "deadline"
     *              Format: Check Deadline.java
     * @throws StringIndexOutOfBoundsException if input format isn't correct
     */
    private void deadline(String input) throws StringIndexOutOfBoundsException {
        try {
            Deadline temp = Deadline.createDeadline(input);
            list.add(temp);
            s.write(temp);
            System.out.println("Got it. I've added this task:");
            System.out.println(temp);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        } catch (StringIndexOutOfBoundsException IOBE) {
            System.out.println("OOPS!!! The description of a deadline is not correct.");
        }
    }

    private void delete(String input) {
        int index = Integer.parseInt(input.substring(7));
        Task temp = list.get(index - 1);
        list.remove(index - 1);
        s.update(list);
        System.out.println("Noted. I've removed this task:");
        System.out.println(temp);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    private void echo() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= this.list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
                continue;
            }
            // Mark the task
            if (input.length() > 5 && input.substring(0,4).equals("mark")) {
                try {
                    int index = Integer.parseInt(input.substring(5));
                    this.list.get(index - 1).markAsDone();
                    s.update(list);
                } catch (IndexOutOfBoundsException IOBE) {
                    System.out.println("There is no such task for marking ☹");
                } catch (NumberFormatException e) {
                    System.out.println("Please key in number after mark ☹");
                } finally {
                    continue;
                }
            }
            // Unmark the task
            if (input.length() > 7 && input.substring(0,6).equals("unmark")) {
                try {
                    int index = Integer.parseInt(input.substring(7));
                    this.list.get(index - 1).markAsNotDone();
                    s.update(list);
                } catch (IndexOutOfBoundsException IOBE) {
                    System.out.println("There is no such task for unmarking ☹");
                } catch (NumberFormatException e) {
                    System.out.println("Please key in number after unmark ☹");
                } finally {
                    continue;
                }
            }
            // Add a toDo task to the list
            if (input.length() >= 4 && input.substring(0,4).equals("todo")) {
                toDo(input);
                continue;
            }
            // Add a event task to the list
            if (input.length() >= 5 && input.substring(0,5).equals("event")) {
                event(input);
                continue;
            }
            // Add a deadline task to the list
            if (input.length() >= 8 && input.substring(0,8).equals("deadline")) {
                deadline(input);
                continue;
            }
            // delete task from list
            if (input.length() > 7 && input.substring(0,6).equals("delete")) {
                try {
                    delete(input);
                } catch (IndexOutOfBoundsException IOBE) {
                    System.out.println("There is no such task for deletion ☹");
                } catch (NumberFormatException e) {
                    System.out.println("Please key in number after deletion ☹");
                }
                finally {
                    continue;
                }
            }
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
