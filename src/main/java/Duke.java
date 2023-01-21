import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {
        String welcomeMsg = "Hello i'm Duke\nWhat can I do for you?";
        String goodbyeMsg = "Bye. Hope to see you again soon!";
        System.out.println(Span.format(welcomeMsg));
        TaskList tasks = new TaskList(); // create a list for all the tasks
        Scanner scanner = new Scanner(System.in); // creates a scanner object
        while (scanner.hasNextLine()) {
            String echo = scanner.nextLine(); // get user input
            try {
                if (echo.equals("bye")) {
                    System.out.println(Span.format(goodbyeMsg));
                    return;
                } else if (echo.equals("list")) {
                    System.out.println(Span.format("Here are the tasks in your list:\n" + tasks.listTasks())); // list all the stored tasks
                } else if (echo.matches("mark \\d+")) {
                    String[] sp = echo.split(" ");
                    int taskNum = Integer.valueOf(sp[1]);
                    if (sp.length == 2 && 1 <= taskNum && taskNum <= tasks.size()) { // otherwise, invalid mark and ignore for now
                        String marked = tasks.toggleMark(taskNum-1);
                        System.out.println(Span.format("Nice! I've marked this task as done:\n\t" + marked));
                    }
                } else if (echo.matches("unmark \\d+")) {
                    String[] sp = echo.split(" ");
                    int taskNum = Integer.valueOf(sp[1]);
                    if (sp.length == 2 && 1 <= taskNum && taskNum <= tasks.size()) { // otherwise, invalid unmark and ignore for now
                        String unmarked = tasks.toggleUnmark(taskNum-1);
                        System.out.println(Span.format("OK, I've marked this task as not done yet:\n\t" + unmarked));
                    }
                }  else if (echo.matches("delete \\d+")) {
                    String[] sp = echo.split(" ");
                    int taskNum = Integer.valueOf(sp[1]);
                    if (sp.length == 2 && 1 <= taskNum && taskNum <= tasks.size()) { // otherwise, invalid delete, error handling later
                        Task removed = tasks.delete(taskNum);
                        System.out.println(Span.format("Noted. I've removed this task:\n\t" + removed.getStatusIcon() + "\n" + "Now you have " + tasks.size() + " task(s) in the list."));
                    }
                } else { // add task
                    Task task = null; // to be converted into the right (sub) task below
                    if (echo.matches("todo\\s*.*")) {
                        if (echo.length() < 6) {
                            throw new MissingArgumentException(Span.format("The description of a todo cannot be empty."), "todo");
                        }
                        String info = echo.substring(5);
                        task = new ToDo(info); // pass description of todo
                    } else if (echo.matches("deadline .+ /by .+")) {
                        String info = echo.substring(9);
                        String[] sp = info.split(" /by ");
                        task = new Deadline(sp[0], sp[1]);
                    } else if (echo.matches("event .+ /from .+ /to .+")) {
                        String info = echo.substring(6);
                        String[] sp = info.split(" /from ");
                        String[] time = sp[1].split(" /to ");
                        task = new Event(sp[0], time[0], time[1]);
                    } else {
                        throw new UnknownCommandException(Span.format("I'm sorry, but I don't know what that means :-("), echo);
                    }
                    tasks.addTask(task);
                    System.out.println(Span.format("Got it. I've added this task:\n\t" + task.getStatusIcon() + "\n" + "Now you have " + tasks.size() + " task(s) in the list."));
                }
            } catch (UnknownCommandException e) {
                System.out.println(Span.format(e.toString()));
            } catch (MissingArgumentException e) {
                System.out.println(Span.format(e.toString()));
            }
        }
    }
}
