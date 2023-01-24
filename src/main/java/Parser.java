import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    private TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    public boolean execute(Command command, String echo) throws DukeException {
        Task task = null; // to be converted into the right (sub) task below
        switch (command) {
            case BYE: {
                return true;
            }
            case LIST: { // list all the stored tasks
                System.out.println(Span.format("Here are the tasks in your list:\n" + tasks.listTasks()));
                break;
            }
            case MARK: {
                if (echo.length() < 6 || !echo.matches("mark\\s+\\d+")) {
                    throw new DukeException("<mark> command should receive a task number!");
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
                    throw new DukeException("<unmark> command should receive a task number!");
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
                    throw new DukeException("<delete> command should receive a task number!");
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
                    throw new DukeException("The description of a <todo> cannot be empty!");
                }
                String info = echo.substring(5).trim(); // remove any trailing spaces at the front
                task = new ToDo(info); // pass description of todo
                break;
            }
            case DEADLINE: {
                if (echo.length() < 10) {
                    throw new DukeException("The description of a <deadline> cannot be empty!");
                }
                String info = echo.substring(9);
                if (!info.matches(".+ /by .+")) { // info.contains(" /by ")
                    throw new DukeException("<deadline> is to be used as such: $ deadline <des> /by <YYYY-MM-DD>");
                }
                String[] sp = info.split(" /by ");
                if (sp[1].trim().equals("")) {
                    throw new DukeException("<deadline> is missing <YYYY-MM-DD>");
                }
                task = new Deadline(sp[0].trim(), sp[1].trim()); // remove any trailing white sp
                break;
            }
            case EVENT: {
                if (echo.length() < 7) {
                    throw new DukeException("The description of a <event> cannot be empty!");
                }
                String info = echo.substring(6);
                if (!info.matches(".+ /from .+ /to .+")) { // (!info.contains(" /from ") || !info.contains(" /to "))
                    throw new DukeException("<event> is to be used as such: $ event <des> /from <YYYY-MM-DD> /to <YYYY-MM-DD>");
                }
                String[] sp = info.split(" /from ");
                if (sp[1].trim().equals("")) {
                    throw new DukeException("<event> is missing start-time <YYYY-MM-DD>");
                }
                String[] time = sp[1].split(" /to ");
                if (time[1].trim().equals("")) {
                    throw new DukeException("<event> is missing end-time <YYYY-MM-DD>");
                }
                task = new Event(sp[0].trim(), time[0].trim(), time[1].trim()); // remove any trailing white sp
                break;
            }
            case GETEVENTSON: {
                if (echo.length() < 12) {
                    throw new DukeException("Date input in the format of YYYY-MM-DD required!");
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
        return false;
    }
}