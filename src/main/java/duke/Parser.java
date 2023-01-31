package duke;

import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.TaskList;
import task.TodoTask;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    Ui ui;
    Storage storage;
    TaskList taskList;

    public Parser(Ui ui, Storage storage, TaskList taskList) {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    public boolean readInput(String input) throws DukeException, IOException {
        String firstInput = input.split(" ")[0];
        LocalDate now = LocalDate.now();

        try {
            switch (firstInput) {
            case "list":
                System.out.println("Here are the tasks you asked for!");
                for (int i = 0; i < taskList.size(); i += 1) {
                    int currItem = i + 1;
                    System.out.println(currItem + ": " + taskList.get(i));
                }
                System.out.println("You now have " + taskList.size() + " items in your list.");
                return true;

            case "bye":
                System.out.println("It was a pleasure to help, goodbye!");
                return false;

            case "mark":
                if (input.split(" ").length < 2) {
                    throw new DukeException("Mark? Mark what?");
                }
                try {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task selectedTask = taskList.get(taskIndex);
                    System.out.println("Done! I've marked this task as done :D");
                    selectedTask.check();
                    System.out.println(selectedTask);
                    return true;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Oops, that task number does not exist");
                }

            case "unmark":
                if (input.split(" ").length < 2) {
                    throw new DukeException("Unmark? Unmark what?");
                }
                try {
                    int untaskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task unselectedTask = taskList.get(untaskIndex);
                    System.out.println("This task is apparently not done huh D:");
                    unselectedTask.unCheck();
                    System.out.println(unselectedTask);
                    return true;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Oops, that task number does not exist");
                }

            case "todo":
                try {
                    String todoTaskName = input.substring(5);
                    TodoTask todoTask = new TodoTask(todoTaskName);
                    addTask(todoTask, todoTaskName);
                    return true;
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("Oops, you can't enter an empty task!");
                }

            case "deadline":
                String deadlineDetails = input.substring(9);
                if (deadlineDetails.split(" /by ").length < 2) {
                    throw new DukeException("Wait a minute, you're missing something! Could be the name or date...");
                }
                String deadlineName = deadlineDetails.split(" /by ")[0];
                String deadlineDateStr = deadlineDetails.split(" /by ")[1];
                LocalDate deadlineDate = LocalDate.parse(deadlineDateStr);
                // make sure date not before curr date
                if (deadlineDate.isBefore(now)) {
                    throw new DukeException("Wait! Time travelling is not in my kit!");
                }
                DeadlineTask deadlineTask = new DeadlineTask(deadlineName, deadlineDate);
                addTask(deadlineTask, deadlineName);
                return true;

            case "event":
                String eventDetails = input.substring(6);
                if (eventDetails.split(" /from ").length < 2 || eventDetails.split(" /to ").length < 2) {
                    throw new DukeException("Hold up, you might be missing something here buddy!");
                }
                String eventName = eventDetails.split(" /from ")[0];
                String eventDate = eventDetails.split(" /from ")[1];
                String eventStartStr = eventDate.split(" /to ")[0];
                String eventEndStr = eventDate.split(" /to ")[1];
                LocalDate eventStart = LocalDate.parse(eventStartStr);
                LocalDate eventEnd = LocalDate.parse(eventEndStr);
                if (eventStart.isBefore(now) || eventEnd.isBefore(now)) {
                    throw new DukeException("Wait! Time travelling is not in my kit!");
                }
                if (eventStart.isAfter(eventEnd)) {
                    throw new DukeException("Ohhh I wasn't aware time travels backwards for you :O");
                }
                EventTask eventTask = new EventTask(eventName, eventStart, eventEnd);
                addTask(eventTask, eventName);
                return true;

            case "delete":
                if (input.split(" ").length < 2) {
                    throw new DukeException("Delete? Delete what?");
                }
                try {
                    int deleteIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task deleteTask = taskList.get(deleteIndex);
                    System.out.println("Done! " + deleteTask + " has been deleted for good.");
                    taskList.remove(deleteIndex);
                    return true;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Oops, that task number does not exist");
                }
            case "save":
                storage.save(taskList);
                return true;
            default:
                throw new DukeException("Oops I do not recognise this command...");
            }
        } catch (IOException io) {
            throw new DukeException("Something is up with your files it seems");
        } catch (DateTimeParseException dl) {
            throw new DukeException("Beep boop this robot can only understand dates in the form yyyy-mm-dd");
        }
    }

    private void addTask(Task task, String name) {
        taskList.add(task);
        System.out.println("Item added: " + name);
    }
}
