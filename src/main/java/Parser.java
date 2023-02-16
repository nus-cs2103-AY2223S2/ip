import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public void parse(String command, Ui ui, TaskList allTasks, Storage storage) {

        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        try {
            if (command.equals("list")) {
                ui.printCommandList(allTasks.getAllTasks());
            } else if (command.startsWith("mark")) {
                DukeException.missingIndexException(command);
                DukeException.invalidIndexException(command, allTasks.getNumberOfTask());
                String[] str = command.split(" ");
                int taskIndex = Integer.parseInt(str[1]) - 1;
                Task oldTask = allTasks.getTask(taskIndex);
                Task task = allTasks.markTaskAsDone(oldTask, taskIndex);
                task.markAsDone();
                storage.saveListToFile(command, task, allTasks);
            } else if (command.startsWith("unmark")) {
                DukeException.missingIndexException(command);
                DukeException.invalidIndexException(command, allTasks.getNumberOfTask());
                String[] str = command.split(" ");
                int taskIndex = Integer.parseInt(str[1]) - 1;
                Task oldTask = allTasks.getTask(taskIndex);
                Task task = allTasks.unmarkTaskAsUndone(oldTask, taskIndex);
                task.unmarkAsUndone();
                storage.saveListToFile(command, task, allTasks);
            } else if (command.startsWith("todo")) {
                DukeException.emptyCommandException(command);
                String[] str = command.split("todo");
                String taskName = str[1];
                Todo todo = new Todo(allTasks.getNumberOfTask(), false,
                        taskName, allTasks.getNumberOfTask() + 1);
                allTasks.addTask(todo);
                todo.printToDoTask();
                storage.saveListToFile(command, todo, allTasks);
            } else if (command.startsWith("deadline")) {
                DukeException.emptyCommandException(command);
                DukeException.missingTimingException(command);
                String[] str = command.split("/by ");
                String taskName = str[0].split("deadline")[1];
                LocalDateTime taskDeadline = LocalDateTime.parse(str[1], dateTimeFormatter);
                Deadline deadline = new Deadline(allTasks.getNumberOfTask(), false,
                        taskName, taskDeadline, allTasks.getNumberOfTask() + 1);
                allTasks.addTask(deadline);
                deadline.printDeadlineTask();
                storage.saveListToFile(command, deadline, allTasks);
            } else if (command.startsWith("event")) {
                DukeException.emptyCommandException(command);
                DukeException.missingTimingException(command);
                String[] str = command.split("/from ");
                String taskName = str[0].split("event")[1];
                String[] eventStartEndTime = str[1].split(" /to ");
                LocalDateTime eventStartTime = LocalDateTime.parse(eventStartEndTime[0], dateTimeFormatter);
                LocalDateTime eventEndTime = LocalDateTime.parse(eventStartEndTime[1], dateTimeFormatter);
                Event event = new Event(allTasks.getNumberOfTask(), false,
                        taskName, eventStartTime, eventEndTime, allTasks.getNumberOfTask() + 1);
                allTasks.addTask(event);
                event.printEventTask();
                storage.saveListToFile(command, event, allTasks);
            } else if (command.startsWith("delete")) {
                DukeException.missingIndexException(command);
                DukeException.invalidIndexException(command, allTasks.getNumberOfTask());
                String[] str = command.split(" ");
                int taskIndex = Integer.parseInt(str[1]) - 1;
                Task task = allTasks.getTask(taskIndex);
                task.printDelete(allTasks.getAllTasks());
                allTasks.deleteTask(taskIndex);
                storage.saveListToFile(command, task, allTasks);
            } else if (command.startsWith("find deadlines or events on")) {
                DukeException.emptyCommandException(command);
                String[] str = command.split("find deadlines or events on ");
                String dateTime = str[1];
                DateTimeFormatter dateTimeFormatter2 =
                        DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dateTime1 = LocalDate.parse(dateTime, dateTimeFormatter2);
                ui.printDeadlineOrEventsOnDay(dateTime1, allTasks);
            } else if (command.equals("bye")){
                boolean saidBye = true;
                ui.printByeMessage();
            } else {
                DukeException.invalidCommandException(command);
            }
        } catch (DukeException d) {
            System.out.println(d.getMessage());
        } catch (NumberFormatException nfe) {
            System.out.println("\t____________________________________________________________" +
                    "\n\t ☹ OOPS!!! The task index to delete or un/mark a task cannot be a non-integer." +
                    "\n\t____________________________________________________________");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
