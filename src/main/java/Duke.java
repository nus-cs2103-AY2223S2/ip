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
            echo = echo.toLowerCase().trim(); // convert user input to lowercase characters & remove trailing white sp
            try {
                if (echo.equals("bye")) {
                    System.out.println(Span.format(goodbyeMsg));
                    return;
                } else if (echo.equals("list")) {
                    System.out.println(Span.format("Here are the tasks in your list:\n" + tasks.listTasks())); // list all the stored tasks
                } else if (echo.matches("mark") || echo.matches("mark\\s+\\d+")) {
                    if (echo.length() < 6) {
                        throw new MissingArgumentException("<mark> command is missing a task number!", "mark");
                    }
                    int taskNum = Integer.parseInt(echo.substring(5).trim());
                    if (1 <= taskNum && taskNum <= tasks.size()) { // otherwise, invalid mark and ignore for now
                        String marked = tasks.toggleMark(taskNum);
                        System.out.println(Span.format("Nice! I've marked this task as done:\n\t" + marked));
                    } else {
                        throw new InvalidIndexException(taskNum);
                    }
                } else if (echo.matches("unmark") || echo.matches("unmark\\s+\\d+")) {
                    if (echo.length() < 8) {
                        throw new MissingArgumentException("<unmark> command is missing a task number!", "unmark");
                    }
                    int taskNum = Integer.parseInt(echo.substring(7).trim());
                    if (1 <= taskNum && taskNum <= tasks.size()) { // otherwise, invalid unmark and ignore for now
                        String unmarked = tasks.toggleUnmark(taskNum);
                        System.out.println(Span.format("OK, I've marked this task as not done yet:\n\t" + unmarked));
                    } else {
                        throw new InvalidIndexException(taskNum);
                    }
                }  else if (echo.matches("delete") || echo.matches("delete\\s+\\d+")) {
                    if (echo.length() < 8) {
                        throw new MissingArgumentException("<delete> command is missing a task number!", "delete");
                    }
                    int taskNum = Integer.parseInt(echo.substring(7).trim());
                    if (1 <= taskNum && taskNum <= tasks.size()) {
                        Task removed = tasks.delete(taskNum);
                        System.out.println(Span.format("Noted. I've removed this task:\n\t" +
                                removed.getStatusIcon() + "\n" +
                                "Now you have " + tasks.size() +
                                " task(s) in the list."));
                    } else {
                        throw new InvalidIndexException(taskNum);
                    }
                } else { // add task
                    Task task = null; // to be converted into the right (sub) task below
                    if (echo.matches("todo") || echo.matches("todo\\s.*")) {
                        if (echo.length() < 6) {
                            throw new MissingArgumentException("The description of a <todo> cannot be empty!", "todo");
                        }
                        String info = echo.substring(5).trim(); // remove any trailing spaces at the front
                        task = new ToDo(info); // pass description of todo
                    } else if (echo.matches("deadline") || echo.matches("deadline .+ /by .+")) {
                        if (echo.length() < 10) {
                            throw new MissingArgumentException("The description of a <deadline> cannot be empty!", "deadline");
                        }
                        String info = echo.substring(9);
                        if (!info.contains(" /by ")) {
                            throw new MissingArgumentException("<deadline> is to be used as such: $ deadline <des> /by <time>", "deadline");
                        }
                        String[] sp = info.split(" /by ");
                        if (sp[1].trim().equals("")) {
                            throw new MissingArgumentException("<deadline> is missing <time>", "deadline");
                        }
                        task = new Deadline(sp[0].trim(), sp[1].trim()); // remove any trailing white sp
                    } else if (echo.matches("event") || echo.matches("event .+ /from .+ /to .+")) {
                        if (echo.length() < 7) {
                            throw new MissingArgumentException("The description of a <event> cannot be empty!", "deadline");
                        }
                        String info = echo.substring(6);
                        if (!info.contains(" /from ") || !info.contains(" /to ")) {
                            throw new MissingArgumentException("<event> is to be used as such: $ event <des> /from <start> /to <end>", "event");
                        }
                        String[] sp = info.split(" /from ");
                        if (sp[1].trim().equals("")) {
                            throw new MissingArgumentException("<event> is missing <start>", "event");
                        }
                        String[] time = sp[1].split(" /to ");
                        if (time[1].trim().equals("")) {
                            throw new MissingArgumentException("<event> is missing <end>", "event");
                        }
                        task = new Event(sp[0].trim(), time[0].trim(), time[1].trim()); // remove any trailing white sp
                    } else {
                        throw new UnknownCommandException(echo);
                    }
                    tasks.addTask(task);
                    System.out.println(Span.format("Got it. I've added this task:\n\t" +
                            task.getStatusIcon() +
                            "\n" +
                            "Now you have " +
                            tasks.size() +
                            " task(s) in the list."));
                }
            } catch (UnknownCommandException e) {
                System.out.println(Span.format(e.toString()));
            } catch (MissingArgumentException e) {
                System.out.println(Span.format(e.toString()));
            } catch (InvalidIndexException e) {
                System.out.println(Span.format(e.toString()));
            }
        }
    }
}
