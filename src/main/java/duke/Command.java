package duke;

import duke.task.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@code Command} class that executes command inputs
 */
public class Command {
    /**
     * Key command word
     */
    private String command;

    /**
     * string of arguments that is executed together with
     * command word
     */
    private String args;

    /**
     * Constructor method for {@code Command} class
     * @param command key command word for execution
     * @param args arguments that are to be executed together with command
     */
    public Command(String command, String args) {
        this.command = command.trim();
        this.args = args.trim();
    }

    /**
     * Executes action based on command given and returns message
     * when executed successfully
     * @param taskList {@code TaskList} object which keeps track of tasks
     * @throws DukeException when command cannot be executed due to incorrect format
     */
    public String execArgs(TaskList taskList) throws DukeException {
        switch(this.command.toLowerCase()) {
            case "bye":
                return this.command.toLowerCase();
            case "list":
                return taskList.generate();
            case "todo":
                return addToDoTask(taskList);
            case "deadline":
                return addDeadlineTask(taskList);
            case "event":
                return addEventTask(taskList);
            case "mark":
                return mark(taskList);
            case "unmark":
                return unmark(taskList);
            case "delete":
                return deleteTask(taskList);
            case "find":
                return findTasks(taskList);
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Adds Todo task into taskList
     * @param taskList {@code TaskList} object which keeps track of tasks
     * @throws DukeException when there is no description of task given
     */
    private String addToDoTask(TaskList taskList) throws DukeException {
        if (this.args.length() == 0) {
            throw new DukeException("What is the ToDo task???");
        } else {
            // Check if number of arguments returns non-negative value
            assert this.args.length() > 0;

            Task taskToAdd = new ToDo(this.args.trim());
            taskList.addTask(taskToAdd);

            return taskAddedMessage(taskToAdd, taskList);
        }
    }

    /**
     * Adds deadline task into taskList
     * @param taskList {@code TaskList} object which keeps track of tasks
     * @throws DukeException when description of task, deadline is missing
     * or format of deadline is incorrect
     */
    private String addDeadlineTask(TaskList taskList) throws DukeException {
        Pattern deadlinePattern = Pattern.compile(".+/by \\d{2}/\\d{2}/\\d{4} \\d{4}");
        Matcher matchDeadline = deadlinePattern.matcher(this.args);

        if (args.length() == 0) {
            throw new DukeException("What is the Deadline task???");
        } else if (matchDeadline.find()) {
            // Check if number of arguments returns non-negative value
            assert this.args.length() > 0;

            String desc = this.args.substring(0,
                    this.args.indexOf("/by")
            ).trim();

            String by = args.substring(
                    args.indexOf("/by") + "/by ".length()
            );

            Task taskToAdd = new Deadline(desc, by);
            taskList.addTask(taskToAdd);

            return taskAddedMessage(taskToAdd, taskList);
        } else {
            throw new DukeException("Incorrect format!\n"
                    + "Format should be: <desc> /by <dd/MM/yy> <HHmm>");
        }
    }

    /**
     * Adds event task into taskList
     * @param taskList {@code TaskList} object which keeps track of tasks
     * @throws DukeException when description, start or end of event is missing or
     * incorrect format is given for start or end of event
     */
    private String addEventTask(TaskList taskList) throws DukeException {
        Pattern eventPattern = Pattern
                .compile(".+/from \\d{2}/\\d{2}/\\d{4} \\d{4} /to \\d{2}/\\d{2}/\\d{4} \\d{4}");
        Matcher matchEvent = eventPattern.matcher(this.args);

        if (args.length() == 0) {
            throw new DukeException("What is the Event task???");
        } else if (matchEvent.find()) {
            // Check if number of arguments returns non-negative value
            assert this.args.length() > 0;
            String desc = args.substring(0,
                    args.indexOf("/from")
            );

            String from = args.substring(
                    args.indexOf("/from") + "/from ".length(),
                    args.indexOf("/to")
            );

            String to = args.substring(
                    args.indexOf("/to") + "/to ".length()
            );

            Task taskToAdd = new Events(desc, from, to);
            taskList.addTask(taskToAdd);

            return taskAddedMessage(taskToAdd, taskList);
        } else {
            String errMessage = "Incorrect format!\n"
                    + "Format should be: <desc> /from <dd/MM/yy> <HHmm>"
                    + " /to <dd/MM/yy> <HHmm>";
            throw new DukeException(errMessage);
        }
    }

    /**
     * marks specified task in taskList
     * @param taskList {@code TaskList} object which keeps track of tasks
     * @throws DukeException when index given is less than 0 or greater than size of taskList - 1
     */
    private String mark(TaskList taskList) throws DukeException {
        //Checks if argument is numeric
        assert Pattern.compile("\\d+").matcher(this.args).find();

        return taskList.markTask(Integer.parseInt(this.args));
    }

    /**
     * unmarks specified task in taskList
     * @param taskList {@code TaskList} object which keeps track of tasks
     * @throws DukeException when index given is less than 0 or greater than size of taskList - 1
     */
    private String unmark(TaskList taskList) throws DukeException {
        //Checks if argument is numeric
        assert Pattern.compile("\\d+").matcher(this.args).find();

        return taskList.unMarkTask(Integer.parseInt(this.args));
    }

    /**
     * deletes specified task in taskList
     * @param taskList {@code TaskList} object which keeps track of tasks
     * @throws DukeException when index given is less than 0 or greater than size of taskList - 1
     */
    private String deleteTask(TaskList taskList) throws DukeException {
        //Checks if argument is numeric
        assert Pattern.compile("\\d+").matcher(this.args).find();

        taskList.removeTask(Integer.parseInt(this.args));
        String deleteTaskMsg = "Deleted Task no. " + this.args;
        return deleteTaskMsg;
    }

    /**
     * Provides String representation of Command object
     * @return String representation with command word and arguments
     */
    @Override
    public String toString(){
        String str = this.command + " " + this.args;
        return str;
    }

    /**
     * Finds and prints all string representation of task objects that contain
     * specified word or phrase
     * @param taskList {@code TaskList} object which keeps track of tasks
     */
    private String findTasks(TaskList taskList) {
        List<String> descOfTasksFound = taskList.getMatchingDesc(this.args);
        if (descOfTasksFound.size() <= 0) {
            return "_____________________________________\n"
                    + "Sorry! No matches found!\n"
                    + "_____________________________________\n";
        } else {
            return generateMatchingTasks(descOfTasksFound);
        }
    }

    /**
     * Generates descriptions of all matching tasks found
     * @param tasksFound List of matching tasks found
     * @return String with descriptions of all matching tasks
     */
    private String generateMatchingTasks(List<String> tasksFound) {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "_____________________________________\n"
                        + "Here are the matching tasks in your list:\n"
        );
        for (String desc : tasksFound) {
            sb.append(desc);
        }
        sb.append("_____________________________________\n");

        return sb.toString().trim();
    }

    /**
     * Gives a message when task is added into tasklist
     * @param taskAdded last task added into tasklist
     * @param taskList keeps track of tasks
     * @return message that task has been added to tasklist
     */
    private String taskAddedMessage(Task taskAdded, TaskList taskList) {
        String taskAddedMsg = GUI.BORDERLINE
                + "Got it. I've added this task:\n"
                + taskAdded + "\n"
                + "Now you have " + taskList.taskCount()
                + "in the list.\n" + GUI.BORDERLINE;

        return taskAddedMsg;
    }
}
