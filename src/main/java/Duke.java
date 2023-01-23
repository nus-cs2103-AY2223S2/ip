import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Duke {

    public static void main(String[] args) {
        String welcomeMsg = "Hello i'm Duke\nWhat can I do for you?";
        String goodbyeMsg = "Bye. Hope to see you again soon!";
        System.out.println(Span.format(welcomeMsg));
        TaskList tasks = new TaskList(); // create a list for all the tasks
        Scanner scanner = new Scanner(System.in); // creates a scanner object
        while (scanner.hasNextLine()) {
            String echo = scanner.nextLine().trim(); // get user input and trim trailing white sp
            try {
                String firstWord = echo.split(" ")[0];
                Command command = Command.get(firstWord);
                Task task = null; // to be converted into the right (sub) task below
                switch (command) {
                    case BYE: {
                        System.out.println(Span.format(goodbyeMsg));
                        return;
                    }
                    case LIST: { // list all the stored tasks
                        System.out.println(Span.format("Here are the tasks in your list:\n" + tasks.listTasks()));
                        break;
                    }
                    case MARK: {
                        if (echo.length() < 6 || !echo.matches("mark\\s+\\d+")) {
                            throw new MissingArgumentException("<mark> command should receive a task number!", "mark");
                        }
                        int taskNum = Integer.parseInt(echo.substring(5).trim());
                        if (1 <= taskNum && taskNum <= tasks.size()) { // otherwise, invalid mark and ignore for now
                            String marked = tasks.toggleMark(taskNum);
                            System.out.println(Span.format("Nice! I've marked this task as done:\n\t" + marked));
                        } else {
                            throw new InvalidIndexException(taskNum);
                        }
                        break;
                    }
                    case UNMARK: {
                        if (echo.length() < 8 || !echo.matches("unmark\\s+\\d+")) {
                            throw new MissingArgumentException("<unmark> command should receive a task number!", "unmark");
                        }
                        int taskNum = Integer.parseInt(echo.substring(7).trim());
                        if (1 <= taskNum && taskNum <= tasks.size()) { // otherwise, invalid unmark and ignore for now
                            String unmarked = tasks.toggleUnmark(taskNum);
                            System.out.println(Span.format("OK, I've marked this task as not done yet:\n\t" + unmarked));
                        } else {
                            throw new InvalidIndexException(taskNum);
                        }
                        break;
                    }
                    case DELETE: {
                        if (echo.length() < 8 || !echo.matches("delete\\s+\\d+")) {
                            throw new MissingArgumentException("<delete> command should receive a task number!", "delete");
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
                        break;
                    }
                    case TODO: {
                        if (echo.length() < 6 || !echo.matches("todo\\s.*")) {
                            throw new MissingArgumentException("The description of a <todo> cannot be empty!", "todo");
                        }
                        String info = echo.substring(5).trim(); // remove any trailing spaces at the front
                        task = new ToDo(info); // pass description of todo
                        break;
                    }
                    case DEADLINE: {
                        if (echo.length() < 10) {
                            throw new MissingArgumentException("The description of a <deadline> cannot be empty!", "deadline");
                        }
                        String info = echo.substring(9);
                        if (!info.matches(".+ /by .+")) { // info.contains(" /by ")
                            throw new MissingArgumentException("<deadline> is to be used as such: $ deadline <des> /by <YYYY-MM-DD>", "deadline");
                        }
                        String[] sp = info.split(" /by ");
                        if (sp[1].trim().equals("")) {
                            throw new MissingArgumentException("<deadline> is missing <YYYY-MM-DD>", "deadline");
                        }
                        task = new Deadline(sp[0].trim(), sp[1].trim()); // remove any trailing white sp
                        break;
                    }
                    case EVENT: {
                        if (echo.length() < 7) {
                            throw new MissingArgumentException("The description of a <event> cannot be empty!", "event");
                        }
                        String info = echo.substring(6);
                        if (!info.matches(".+ /from .+ /to .+")) { // (!info.contains(" /from ") || !info.contains(" /to "))
                            throw new MissingArgumentException("<event> is to be used as such: $ event <des> /from <YYYY-MM-DD> /to <YYYY-MM-DD>", "event");
                        }
                        String[] sp = info.split(" /from ");
                        if (sp[1].trim().equals("")) {
                            throw new MissingArgumentException("<event> is missing start-time <YYYY-MM-DD>", "event");
                        }
                        String[] time = sp[1].split(" /to ");
                        if (time[1].trim().equals("")) {
                            throw new MissingArgumentException("<event> is missing end-time <YYYY-MM-DD>", "event");
                        }
                        task = new Event(sp[0].trim(), time[0].trim(), time[1].trim()); // remove any trailing white sp
                        break;
                    }
                    case GETEVENTSON: {
                        if (echo.length() < 12) {
                            throw new MissingArgumentException("Date input in the format of YYYY-MM-DD required!", "geteventson");
                        }
                        LocalDate date = LocalDate.parse(echo.substring(11).trim());
                        System.out.println(
                                Span.format("Here are the deadlines/events on "
                                        + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                                        + ":\n"
                                        + tasks.listAllOnDate(date)));
                    }
                }
                if (task != null) {
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
            } catch (InvalidDateException e) {
                System.out.println(Span.format(e.toString()));
            }
        }
    }
}
