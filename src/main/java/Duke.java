import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;

public class Duke {

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws DukeException, IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.chatDuke();
    }

    public void chatDuke() throws IOException {

        Save save = new Save();
        List<Task> allTasks = save.loadTxtFile();

        this.printGreetingMessage();

        boolean saidBye = false;
        while (!saidBye) {
            String command = sc.nextLine();
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

            try {
                if (command.equals("list")) {
                    this.printCommandList(allTasks);
                } else if (command.startsWith("mark")) {
                    DukeException.missingIndexException(command);
                    DukeException.invalidIndexException(command, allTasks.size());
                    String[] str = command.split(" ");
                    int taskIndex = Integer.parseInt(str[1]) - 1;
                    Task oldTask = allTasks.get(taskIndex);
                    if (oldTask.getTaskType().equals("[T]")) {
                        Todo todo = new Todo(oldTask.getTaskNumber(),
                                true, oldTask.getTask(),
                                allTasks.size());
                        allTasks.set(taskIndex, todo);
                        todo.markAsDone();
                        save.saveListToFile(command, todo, allTasks);
                    } else if (oldTask.getTaskType().equals("[D]")) {
                        Deadline deadline = new Deadline(oldTask.getTaskNumber(),
                                true, oldTask.getTask(),
                                oldTask.getDeadline(), allTasks.size());
                        allTasks.set(taskIndex, deadline);
                        deadline.markAsDone();
                        save.saveListToFile(command, deadline, allTasks);
                    } else if (oldTask.getTaskType().equals("[E]")) {
                        Event event = new Event(oldTask.getTaskNumber(),
                                true, oldTask.getTask(),
                                oldTask.getEventStartTime(),
                                oldTask.getEventEndTime(), allTasks.size());
                        allTasks.set(taskIndex, event);
                        event.markAsDone();
                        save.saveListToFile(command, event, allTasks);
                    }
                }else if (command.startsWith("unmark")) {
                    DukeException.missingIndexException(command);
                    DukeException.invalidIndexException(command, allTasks.size());
                    String[] str = command.split(" ");
                    int taskIndex = Integer.parseInt(str[1]) - 1;
                    Task oldTask = allTasks.get(taskIndex);
                    if (oldTask.getTaskType().equals("[T]")) {
                        Todo todo = new Todo(oldTask.getTaskNumber(),
                                false, oldTask.getTask(),
                                allTasks.size());
                        allTasks.set(taskIndex, todo);
                        todo.unmarkAsUndone();
                        save.saveListToFile(command, todo, allTasks);
                    } else if (oldTask.getTaskType().equals("[D]")) {
                        Deadline deadline = new Deadline(oldTask.getTaskNumber(),
                                false, oldTask.getTask(),
                                oldTask.getDeadline(), allTasks.size());
                        allTasks.set(taskIndex, deadline);
                        deadline.unmarkAsUndone();
                        save.saveListToFile(command, deadline, allTasks);
                    } else if (oldTask.getTaskType().equals("[E]")) {
                        Event event = new Event(oldTask.getTaskNumber(),
                                false, oldTask.getTask(),
                                oldTask.getEventStartTime(),
                                oldTask.getEventEndTime(), allTasks.size());
                        allTasks.set(taskIndex, event);
                        event.unmarkAsUndone();
                        save.saveListToFile(command, event, allTasks);
                    }
                } else if (command.startsWith("todo")) {
                    DukeException.emptyCommandException(command);
                    String[] str = command.split("todo");
                    String taskName = str[1];
                    Todo todo = new Todo(allTasks.size(), false,
                            taskName, allTasks.size() + 1);
                    allTasks.add(todo);
                    todo.printToDoTask();
                    save.saveListToFile(command, todo, allTasks);
                } else if (command.startsWith("deadline")) {
                    DukeException.emptyCommandException(command);
                    DukeException.missingTimingException(command);
                    String[] str = command.split("/by ");
                    String taskName = str[0].split("deadline")[1];
                    LocalDateTime taskDeadline = LocalDateTime.parse(str[1], dateTimeFormatter);
                    Deadline deadline = new Deadline(allTasks.size(), false,
                            taskName, taskDeadline, allTasks.size() + 1);
                    allTasks.add(deadline);
                    deadline.printDeadlineTask();
                    save.saveListToFile(command, deadline, allTasks);
                } else if (command.startsWith("event")) {
                    DukeException.emptyCommandException(command);
                    DukeException.missingTimingException(command);
                    String[] str = command.split("/from ");
                    String taskName = str[0].split("event")[1];
                    String[] eventStartEndTime = str[1].split(" /to ");
                    LocalDateTime eventStartTime = LocalDateTime.parse(eventStartEndTime[0], dateTimeFormatter);
                    LocalDateTime eventEndTime = LocalDateTime.parse(eventStartEndTime[1], dateTimeFormatter);
                    Event event = new Event(allTasks.size(), false,
                            taskName, eventStartTime, eventEndTime, allTasks.size() + 1);
                    allTasks.add(event);
                    event.printEventTask();
                    save.saveListToFile(command, event, allTasks);
                } else if (command.startsWith("delete")) {
                    DukeException.missingIndexException(command);
                    DukeException.invalidIndexException(command, allTasks.size());
                    String[] str = command.split(" ");
                    int taskIndex = Integer.parseInt(str[1]) - 1;
                    Task task = allTasks.get(taskIndex);
                    task.printDelete(allTasks);
                    allTasks.remove(taskIndex);
                    save.saveListToFile(command, task, allTasks);
                } else if (command.startsWith("find deadlines or events on")) {
                    DukeException.emptyCommandException(command);
                    String[] str = command.split("find deadlines or events on ");
                    String dateTime = str[1];
                    DateTimeFormatter dateTimeFormatter2 =
                            DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dateTime1 = LocalDate.parse(dateTime, dateTimeFormatter2);
                    this.printDeadlineOrEventsOnDay(dateTime1, allTasks);
                } else if (command.equals("bye")){
                    saidBye = true;
                    this.printByeMessage();
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

    public void printGreetingMessage() {
        System.out.println("\t____________________________________________________________" +
                "\n\t Hello! I'm Duke\n" +
                "\t What can I do for you?" +
                "\n\t____________________________________________________________");
    }

    public void printCommandList(List<Task> allTasks) {
        DateTimeFormatter dateTimeFormatter1 =
                DateTimeFormatter.ofPattern("MMM dd yyyy HHmm a");
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < allTasks.size(); i++) {
            int numbering = i + 1;
            Task task = allTasks.get(i);
            String time = "";
            if (task.getTaskType().equals("[D]")) {
                time = " (by: " +
                        task.getDeadline().format(dateTimeFormatter1) + ")";
            } else if (task.getTaskType().equals("[E]")) {
                time = " (from: " +
                        task.getEventStartTime().format(dateTimeFormatter1)
                        + " to: "
                        + task.getEventEndTime().format(dateTimeFormatter1)
                        + ")";
            }
            System.out.println("\t " + numbering + "." +
                    task.getTaskType() + task.getTaskStatus() + " "
                    + task.getTask() + time);
        }

        System.out.println("\t____________________________________________________________");
    }

    public void printDeadlineOrEventsOnDay(LocalDate dateTime, List<Task> allTasks) {
        DateTimeFormatter dateTimeFormatter1 =
                DateTimeFormatter.ofPattern("MMM dd yyyy HHmm a");
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Here are the tasks in your list at this day:");
        int numbering = 1;
        for (int i = 0; i < allTasks.size(); i++) {
            Task task = allTasks.get(i);
            String time = "";
            if (task.getTaskType().equals("[D]") &&
                    task.getDate().equals(dateTime)) {
                time = " (by: " +
                        task.getDeadline().format(dateTimeFormatter1) + ")";
            } else if (task.getTaskType().equals("[E]") &&
                    task.getDate().equals(dateTime)) {
                time = " (from: " +
                        task.getEventStartTime().format(dateTimeFormatter1)
                        + " to: "
                        + task.getEventEndTime().format(dateTimeFormatter1)
                        + ")";
            } else {
                continue;
            }
            System.out.println("\t " + numbering + "." +
                    task.getTaskType() + task.getTaskStatus() + " "
                    + task.getTask() + time);
            numbering += 1;
        }
    }

    public void printByeMessage() {
        System.out.println("\t____________________________________________________________" +
                "\n\t Bye. Hope to see you again soon!" +
                "\n\t____________________________________________________________");
    }

}
