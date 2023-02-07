import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.chatDuke();
    }

    public void chatDuke() {

        List<Task> allTasks = new ArrayList<>();

        this.printGreetingMessage();

        boolean saidBye = false;
        while (!saidBye) {
            String command = sc.nextLine();

            try {
                if (command.equals("list")) {
                    this.printCommandList(allTasks);
                } else if (command.startsWith("mark")) {
                    missingIndexException(command);
                    invalidIndexException(command, allTasks.size());
                    String[] str = command.split(" ");
                    int taskIndex = Integer.parseInt(str[1]) - 1;
                    Task oldTask = allTasks.get(taskIndex);
                    if (oldTask.getTaskType().equals("[T]")) {
                        Todo todo = new Todo(oldTask.getTaskNumber(),
                                true, oldTask.getTask(),
                                allTasks.size());
                        allTasks.set(taskIndex, todo);
                        todo.markAsDone();
                    } else if (oldTask.getTaskType().equals("[D]")) {
                        Deadline deadline = new Deadline(oldTask.getTaskNumber(),
                                true, oldTask.getTask(),
                                oldTask.getDeadline(), allTasks.size());
                        allTasks.set(taskIndex, deadline);
                        deadline.markAsDone();
                    } else if (oldTask.getTaskType().equals("[E]")) {
                        Event event = new Event(oldTask.getTaskNumber(),
                                true, oldTask.getTask(),
                                oldTask.getEventStartTime(),
                                oldTask.getEventEndTime(), allTasks.size());
                        allTasks.set(taskIndex, event);
                        event.markAsDone();
                    }
                } else if (command.startsWith("unmark")) {
                    missingIndexException(command);
                    invalidIndexException(command, allTasks.size());
                    String[] str = command.split(" ");
                    int taskIndex = Integer.parseInt(str[1]) - 1;
                    Task oldTask = allTasks.get(taskIndex);
                    if (oldTask.getTaskType().equals("[T]")) {
                        Todo todo = new Todo(oldTask.getTaskNumber(),
                                false, oldTask.getTask(),
                                allTasks.size());
                        allTasks.set(taskIndex, todo);
                        todo.unmarkAsUndone();
                    } else if (oldTask.getTaskType().equals("[D]")) {
                        Deadline deadline = new Deadline(oldTask.getTaskNumber(),
                                false, oldTask.getTask(),
                                oldTask.getDeadline(), allTasks.size());
                        allTasks.set(taskIndex, deadline);
                        deadline.unmarkAsUndone();
                    } else if (oldTask.getTaskType().equals("[E]")) {
                        Event event = new Event(oldTask.getTaskNumber(),
                                false, oldTask.getTask(),
                                oldTask.getEventStartTime(),
                                oldTask.getEventEndTime(), allTasks.size());
                        allTasks.set(taskIndex, event);
                        event.unmarkAsUndone();
                    }
                } else if (command.startsWith("todo")) {
                    emptyCommandException(command);
                    String[] str = command.split("todo");
                    String taskName = str[1];
                    Todo todo = new Todo(allTasks.size(), false,
                            taskName, allTasks.size() + 1);
                    allTasks.add(todo);
                    todo.printToDoTask();
                } else if (command.startsWith("deadline")) {
                    emptyCommandException(command);
                    missingTimingException(command);
                    String[] str = command.split("/by");
                    String taskName = str[0].split("deadline")[1];
                    String taskDeadline = str[1];
                    Deadline deadline = new Deadline(allTasks.size(), false,
                            taskName, taskDeadline, allTasks.size() + 1);
                    allTasks.add(deadline);
                    deadline.printDeadlineTask();
                } else if (command.startsWith("event")) {
                    emptyCommandException(command);
                    missingTimingException(command);
                    String[] str = command.split("/from");
                    String taskName = str[0].split("event")[1];
                    String[] eventStartEndTime = str[1].split("/to");
                    String eventStartTime = eventStartEndTime[0];
                    String eventEndTime = eventStartEndTime[1];
                    Event event = new Event(allTasks.size(), false,
                            taskName, eventStartTime, eventEndTime, allTasks.size() + 1);
                    allTasks.add(event);
                    event.printEventTask();
                } else if (command.equals("bye")){
                    saidBye = true;
                    this.printByeMessage();
                } else {
                    invalidCommandException(command);
                }
            } catch (DukeException d) {
                System.out.println(d.getMessage());
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
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < allTasks.size(); i++) {
            int numbering = i + 1;
            Task task = allTasks.get(i);
            String time = "";
            if (task.getTaskType().equals("[D]")) {
                time = " (by: " + task.getDeadline() + ")";
            } else if (task.getTaskType().equals("[E]")) {
                time = " (from: " + task.getEventStartTime() + " to: "
                        + task.getEventEndTime() + ")";
            }
            System.out.println("\t " + numbering + "." +
                    task.getTaskType() + task.getTaskStatus() + " "
                    + task.getTask() + time);
        }

        System.out.println("\t____________________________________________________________");
    }

    public void echoCommand(String command) {
        System.out.println("\t____________________________________________________________" +
                "\n\t" + " added: " + command +
                "\n\t____________________________________________________________");
    }

    public void printByeMessage() {
        System.out.println("\t____________________________________________________________" +
                "\n\t Bye. Hope to see you again soon!" +
                "\n\t____________________________________________________________");
    }

    public void emptyCommandException(String command) throws DukeException {
        switch (command) {
            case "todo":
                throw new DukeException("\t____________________________________________________________" +
                        "\n\t ☹ OOPS!!! The description of a todo cannot be empty." +
                        "\n\t____________________________________________________________");
            case "deadline":
                throw new DukeException("\t____________________________________________________________" +
                        "\n\t ☹ OOPS!!! The description of a deadline cannot be empty." +
                        "\n\t____________________________________________________________");
            case "event":
                throw new DukeException("\t____________________________________________________________" +
                        "\n\t ☹ OOPS!!! The description of an event cannot be empty." +
                        "\n\t____________________________________________________________");
        }
    }

    public void missingTimingException(String command) throws DukeException {
        if (command.startsWith("deadline") && !command.contains("/by")) {
            throw new DukeException("\t____________________________________________________________" +
                    "\n\t ☹ OOPS!!! The timing of a deadline cannot be empty." +
                    "\n\t____________________________________________________________");
        } else if (command.startsWith("event") && !command.contains("/from")) {
            throw new DukeException("\t____________________________________________________________" +
                    "\n\t ☹ OOPS!!! The start time of an event cannot be empty." +
                    "\n\t____________________________________________________________");
        } else if (command.startsWith("event") && !command.contains("/to")) {
            throw new DukeException("\t____________________________________________________________" +
                    "\n\t ☹ OOPS!!! The end time of an event cannot be empty." +
                    "\n\t____________________________________________________________");
        }
    }

    public void missingIndexException(String command) throws DukeException {
        if (command.equals("mark")) {
            throw new DukeException("\t____________________________________________________________" +
                    "\n\t ☹ OOPS!!! The task index to mark a task as done cannot be empty." +
                    "\n\t____________________________________________________________");
        } else if (command.equals("unmark")) {
            throw new DukeException("\t____________________________________________________________" +
                    "\n\t ☹ OOPS!!! The task index to unmark a task as not done cannot be empty." +
                    "\n\t____________________________________________________________");
        }
    }

    public void invalidIndexException(String command, int taskSize) throws DukeException {
        if (command.startsWith("mark") || command.startsWith("unmark")) {
            String index = command.split(" ")[1];
            int index1 = Integer.parseInt(index);
            if (index1 <= 0) {
                throw new DukeException("\t____________________________________________________________" +
                        "\n\t ☹ OOPS!!! The task index to mark a task as done cannot be zero or less." +
                        "\n\t____________________________________________________________");
            } else if (index.equals("")) {
                throw new DukeException("\t____________________________________________________________" +
                        "\n\t ☹ OOPS!!! The task index to mark a task as done cannot be empty." +
                        "\n\t____________________________________________________________");
            } else if (index1 > taskSize) {
                throw new DukeException("\t____________________________________________________________" +
                        "\n\t ☹ OOPS!!! The task index to mark a task as done cannot be more than" +
                        " number of tasks." +
                        "\n\t____________________________________________________________");
            }
        }
    }

    public void invalidCommandException(String command) throws DukeException {
        if (!command.startsWith("event") || !(command.startsWith("deadline")) ||
                !command.startsWith("todo") || command.startsWith("mark") ||
                !command.startsWith("unmark")) {
            throw new DukeException("\t____________________________________________________________" +
                    "\n\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(" +
                    "\n\t____________________________________________________________");
        }
    }

}
