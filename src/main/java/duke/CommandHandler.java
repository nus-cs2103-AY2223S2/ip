package duke;

import java.time.format.DateTimeParseException;
import java.util.List;

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
            return endDuke();
        //Fallthrough (java doesn't let me compile if I add a break)
        case "list":
            return showTasks(tasks);
        //Fallthrough
        case "mark":
            s = markTask(command.getArguments().get(0), tasks);
            storage.saveData(tasks);
            return s;
        //Fallthrough
        case "unmark":
            s = unmarkTask(command.getArguments().get(0), tasks);
            storage.saveData(tasks);
            return s;
        //Fallthrough
        case "todo":
            s = addTodo(command.getArguments().get(0), tasks);
            storage.saveData(tasks);
            return s;
        //Fallthrough
        case "deadline":
            s = addDeadline(command.getArguments().get(0), command.getArguments().get(1), tasks);
            storage.saveData(tasks);
            return s;
        //Fallthrough
        case "event":
            s = addEvent(command.getArguments().get(0),
                    command.getArguments().get(1), command.getArguments().get(2), tasks);
            storage.saveData(tasks);
            return s;
        //Fallthrough
        case "delete":
            s = deleteEvent(command.getArguments().get(0), tasks);
            storage.saveData(tasks);
            return s;
        //Fallthrough
        case "noMatch":
            return noMatch();
        //Fallthrough
        case "invalid":
            return invalid(command.getArguments().get(0));
        //Fallthrough
        case "find":
            return findTasks(command.getArguments().get(0), tasks);
        default:
            return "";
        }
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
            return "Sorry, I didn't understand. Please enter a date or time in one of the following formats:\n" +
                    ">>31/01/1970 2359\n>>2359\n>>31/01/1970\n";
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
            return "Sorry, I didn't understand. Please enter a date or time in one of the following formats:\n" +
                    ">>31/01/1970 2359\n>>2359\n>>31/01/1970\n";
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
