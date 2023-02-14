package duke;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

public class CommandHandler {
    CommandHandler() {}

    /**
     * Determines the behaviour of Duke given a command.
     * @param command The command in which behaviour is to be determined.
     * @param tasks A TaskList containing the current list of tasks in Duke.
     *              If required, the TaskList will be modified in place as a side effect of the method call.
     * @return The response that Duke will display upon execution of the command.
     */
    public String handleCommand(Command command, TaskList tasks, Storage storage) {
        String s;
        switch (command.getDescription()) {
        case "bye":
            s = endDuke();
            break;
        case "list":
            assert command.getArguments().length == 0;
            s = showTasks(tasks);
            break;
        case "mark":
            assert command.getArguments().length == 1;
            s = markTask(command.getArguments()[0], tasks);
            storage.saveData(tasks);
            break;
        case "unmark":
            assert command.getArguments().length == 1;
            s = unmarkTask(command.getArguments()[0], tasks);
            storage.saveData(tasks);
            break;
        case "todo":
            assert command.getArguments().length == 1;
            s = addTodo(command.getArguments()[0], tasks);
            storage.saveData(tasks);
            break;
        case "deadline":
            assert command.getArguments().length == 2;
            s = addDeadline(command.getArguments()[0], command.getArguments()[1], tasks);
            storage.saveData(tasks);
            break;
        case "event":
            assert command.getArguments().length == 3;
            s = addEvent(command.getArguments()[0],
                    command.getArguments()[1], command.getArguments()[2], tasks);
            storage.saveData(tasks);
            break;
        case "delete":
            assert command.getArguments().length == 1;
            s = deleteEvent(command.getArguments()[0], tasks);
            storage.saveData(tasks);
            break;
        case "noMatch":
            s = noMatch();
            break;
        case "invalid":
            assert command.getArguments().length == 1;
            s = invalid(command.getArguments()[0]);
            break;
        case "find":
            assert command.getArguments().length == 1;
            s = findTasks(command.getArguments()[0], tasks);
            break;
        default:
            throw new IllegalArgumentException("Command is not valid and was not caught!");
        }
        return s;
    }

    /**
     * Determines if a command is the 'bye' command. Used to terminate the program.
     * @param command The command to be checked.
     * @return true if the command is the 'bye' command, false for all other commands.
     */
    public boolean isByeCommand(Command command) {
        return command.getDescription().equals("bye");
    }
    private String endDuke() {
        return "Bye! Hope to see you again soon!\n";
    }
    private String showTasks(TaskList tasks) {
        String response = "";
        if (!tasks.isEmpty()) {
            for (int i = 1; i <= tasks.size(); i++) {
                response += String.format("%d.%s", i, tasks.get(i - 1).printTask()) + "\n";
            }
        } else {
            response = "You currently have no items in your to-do list!\n";
        }
        return response;
    }
    private String markTask(String index, TaskList tasks) {
        assert index.matches("[0-9]+");
        String response = "";
        try {
            int taskIndex = Integer.parseInt(index) - 1;
            tasks.get(taskIndex).setDone(true);
            response += "Okay! I've marked this task as done!\n";
            response += tasks.get(taskIndex).printTask() + "\n";
        } catch (IndexOutOfBoundsException e) {
            response = "You don't have that many tasks!\n";
        }
        return response;
    }
    private String unmarkTask(String index, TaskList tasks) {
        assert index.matches("[0-9]+");
        String response = "";
        try {
            int taskIndex = Integer.parseInt(index) - 1;
            tasks.get(taskIndex).setDone(false);
            response += "Okay! I've marked this task as not done yet!\n";
            response += tasks.get(taskIndex).printTask() + "\n";
        } catch (IndexOutOfBoundsException e) {
            response = "You don't have that many tasks!\n";
        }
        return response;
    }
    private String addTodo(String description, TaskList tasks) {
        String response = "";
        Task newTask = new Todo(description);
        tasks.add(newTask);
        response = String.format("Added: %s\n", newTask.printTask());
        return response;
    }
    private String addDeadline(String description, String by, TaskList tasks) {
        String response = "";
        Task newTask;
        try {
            newTask = new Deadline(description, by);
        } catch (DateTimeParseException e) {
            return "Sorry, I didn't understand. Please enter a valid date or time.\n";
        } catch (DateTimeException e) {
            return "Sorry, that date is not valid. Please enter a date that exists.\n";
        }
        tasks.add(newTask);
        response = String.format("Added: %s\n", newTask.printTask());
        return response;
    }
    private String addEvent(String description, String from, String to, TaskList tasks) {
        String response = "";
        Task newTask;
        try {
            newTask = new Event(description, from, to);
        } catch (DateTimeParseException e) {
            return "Sorry, I didn't understand. Please enter a valid date or time.\n";
        } catch (DateTimeException e) {
            return "Sorry, that date is not valid. Please enter a date that exists.\n";
        }
        tasks.add(newTask);
        response = String.format("Added: %s\n", newTask.printTask());
        return response;
    }
    private String noMatch() {
        return "Sorry, I didn't understand that, please ask again.\n";
    }

    private String deleteEvent(String index, TaskList tasks) {
        String response = "";
        try {
            int taskIndex = Integer.parseInt(index) - 1;
            response = String.format("Okay! I deleted task %s\n", tasks.get(taskIndex).printTask());
            tasks.remove(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            response = "You don't have that many tasks!\n";
        }
        return response;
    }
    
    private String invalid(String reply) {
        return reply;
    }

    private String findTasks(String taskToFind, TaskList taskList) {
        String response = "";
        TaskList foundTasks = taskList.find(taskToFind);
        if (foundTasks.isEmpty()) {
            response = "You don't have any tasks matching that description!\n";
        } else {
            response = "I found these tasks in your task list!\n";
            for (Task t : foundTasks.getTasks()) {
                response += t.printTask() + "\n";
            }
        }
        return response;
    }
}
