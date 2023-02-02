package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.DukeInvalidArgumentException;
import duke.parser.Parser;

public class TaskList {
    private final ArrayList<Task> TASKS;

    public TaskList(ArrayList<Task> taskList) {
        TASKS = taskList;
    }

    public void addToDo(String userCommand) throws DukeException {

        String[] userCommandParts = userCommand.split(" ", 2);
        if (userCommandParts.length < 2) {
            throw new DukeInvalidArgumentException("Please enter task description");
        }

        String taskDescription = userCommandParts[1].trim();
        ToDo toDo = new ToDo(taskDescription);
        TASKS.add(toDo);
        System.out.println("Got it. I've added this task:\n\t" + toDo
                + "\nNow you have " + TASKS.size() + " task(s) in the list.");
    }

    public void addDeadline(String userCommand) throws DukeException {

        String[] userCommandParts = userCommand.split(" /by", 2);
        String description = userCommandParts[0].replace("deadline", "").trim();

        if (description.isEmpty()) {
            throw new DukeInvalidArgumentException("Please enter task description!");
        }

        if (userCommandParts.length < 2) {
            throw new DukeInvalidArgumentException("Invalid format!\n"
                    + "Use `deadline {description} /by {due date}`");
        }

        if (userCommandParts[1].trim().isEmpty()) {
            throw new DukeInvalidArgumentException("Please provide due date after `/by` parameter");
        }

        LocalDateTime dueDate = Parser.parseDateTime(userCommandParts[1].trim()) ;
        Deadline deadline = new Deadline(description, dueDate);
        TASKS.add(deadline);

        System.out.println("Got it. I've added this task:\n\t" + deadline
                + "\nNow you have " + TASKS.size() + " task(s) in the list.");
    }

    public void addEvent(String userCommand) throws DukeException {

        String[] userCommandParts = userCommand.split(" /from", 2);
        String description = userCommandParts[0].replace("event", "").trim();

        if (description.isEmpty()) {
            throw new DukeInvalidArgumentException("Please enter task description!");
        }

        if (userCommandParts.length < 2) {
            throw new DukeInvalidArgumentException("Invalid format!\n"
                    + "Use `event {description} /from {start date/time} /to {end date/time}`");
        }

        String[] timeParts = userCommandParts[1].split(" /to", 2);

        if (timeParts.length < 1 || timeParts[0].trim().isEmpty()) {
            throw new DukeInvalidArgumentException("Please provide start date/time after `/from` parameter");
        }

        if (timeParts.length < 2 || timeParts[1].trim().isEmpty()) {
            throw new DukeInvalidArgumentException("Please provide end date/time after `/to` parameter");
        }

        LocalDateTime startDateTime = Parser.parseDateTime(timeParts[0].trim());
        LocalDateTime endDateTime = Parser.parseDateTime(timeParts[1].trim());

        if (!endDateTime.isAfter(startDateTime)) {
            throw new DukeInvalidArgumentException("The end date/time should be after the start date/time");
        }

        Event event = new Event(description, startDateTime, endDateTime);
        TASKS.add(event);

        System.out.println("Got it. I've added this task:\n\t" + event
                + "\nNow you have " + TASKS.size() + " task(s) in the list.");
    }

    public void printTaskList() {
        if (TASKS.size() == 0) {
            System.out.println("There are no tasks in your Task List!");
        } else {
            System.out.println("Your Tasks:");
            for (int i = 0; i < TASKS.size(); i++) {
                System.out.println((i + 1) + ". " + TASKS.get(i));
            }
        }
    }

    public void markTask(String userCommand) throws DukeException {
        try {
            String[] userCommandParts = userCommand.split(" ");
            if (userCommandParts.length != 2) {
                throw new DukeInvalidArgumentException("Use format: mark {task no.}");
            }

            String taskNumber = userCommandParts[1];
            int taskIndex = Integer.parseInt(taskNumber) - 1;

            if (TASKS.size() == 0) {
                System.out.println("There are no tasks in your Task List!");

            } else if (0 <= taskIndex && taskIndex < TASKS.size()) {
                Task task = TASKS.get(taskIndex);
                task.setDone();
                System.out.println("Task marked as completed\n" + task);

            } else {
                throw new DukeInvalidArgumentException("Please provide a valid Task number\n"
                        + "You have " + TASKS.size() + " task(s) in your Task List");
            }

        } catch (NumberFormatException exception) {
            throw new DukeInvalidArgumentException("Please provide a valid Task number");
        }
    }

    public void unmarkTask(String userCommand) throws DukeException {
        try {
            String[] userCommandParts = userCommand.split(" ");
            if (userCommandParts.length != 2) {
                throw new DukeInvalidArgumentException("Use format: unmark {task no.}");
            }

            String taskNumber = userCommandParts[1];
            int taskIndex = Integer.parseInt(taskNumber) - 1;

            if (TASKS.size() == 0) {
                System.out.println("There are no tasks in your Task List!");

            } else if (0 <= taskIndex && taskIndex < TASKS.size()) {
                Task task = TASKS.get(taskIndex);
                task.setNotDone();
                System.out.println("Task marked as not completed\n" + task);

            } else {
                throw new DukeInvalidArgumentException("Please provide a valid Task number\n"
                        + "You have " + TASKS.size() + " task(s) in your Task List");
            }

        } catch (NumberFormatException exception) {
            throw new DukeInvalidArgumentException("Please provide a valid Task number");
        }

    }
    public void deleteTask(String userCommand) throws DukeException {
        try {
            String[] userCommandParts = userCommand.split(" ");
            if (userCommandParts.length != 2) {
                throw new DukeInvalidArgumentException("Use format: delete {task no.}");
            }

            String taskNumber = userCommandParts[1];
            int taskIndex = Integer.parseInt(taskNumber) - 1;

            if (TASKS.size() == 0) {
                System.out.println("There are no tasks in your Task List!");
            } else if (0 <= taskIndex && taskIndex < TASKS.size()) {
                Task task = TASKS.get(taskIndex);
                TASKS.remove(taskIndex);

                System.out.println("Noted. I've deleted this task:\n\t" + task
                        + "\nNow you have " + TASKS.size() + " task(s) in the list.");
            } else {
                throw new DukeInvalidArgumentException("Please provide a valid Task number\n"
                        + "You have " + TASKS.size() + " task(s) in your Task List");
            }

        } catch (NumberFormatException exception) {
            throw new DukeInvalidArgumentException("Please provide a valid Task number");
        }
    }

    public ArrayList<Task> getTaskList() {
        return TASKS;
    }
}
