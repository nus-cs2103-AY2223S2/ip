package duke;

import duke.task.*;

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
     * Executes action based on command given
     * @param taskList {@code TaskList} object which keeps track of tasks
     * @throws DukeException when command cannot be executed due to incorrect format
     */
    public void execArgs(TaskList taskList) throws DukeException {
        switch(this.command.toLowerCase()) {
            case "bye":
                UI.end();
                break;
            case "list":
                taskList.generate();
                break;
            case "todo":
                todo(taskList);
                break;
            case "deadline":
                deadline(taskList);
                break;
            case "event":
                event(taskList);
                break;
            case "mark":
                mark(taskList);
                break;
            case "unmark":
                unMark(taskList);
                break;
            case "delete":
                delete(taskList);
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Adds Todo task into taskList
     * @param taskList {@code TaskList} object which keeps track of tasks
     * @throws DukeException when there is no description of task given
     */
    private void todo(TaskList taskList) throws DukeException {
        if (this.args.length() == 0){
            throw new DukeException("What is the ToDo task???");
        } else {
            Task addTask = new ToDo(this.args.trim());
            taskList.add(addTask);
            System.out.println();
            System.out.println("Got it. I've added this task:\n" +
                    addTask.toString() + "\n" +
                    "Now you have " + taskList.taskCount() +
                    "in the list.\n");
        }
    }

    /**
     * Adds deadline task into taskList
     * @param taskList {@code TaskList} object which keeps track of tasks
     * @throws DukeException when description of task, deadline is missing
     * or format of deadline is incorrect
     */
    private void deadline(TaskList taskList) throws DukeException {
        if (args.length() == 0) {
            throw new DukeException("What is the Deadline task???");
        } else {
            Pattern deadlinePattern = Pattern.compile(".+/by \\d{2}/\\d{2}/\\d{4} \\d{4}");
            Matcher matchDeadline = deadlinePattern.matcher(this.args);
            if (matchDeadline.find()) {
                String desc = this.args.substring(0,
                        this.args.indexOf("/by")
                ).trim();

                String by = args.substring(
                        args.indexOf("/by") + "/by ".length()
                );
                Task addTask = new Deadline(desc, by);
                taskList.add(addTask);
                System.out.println("Got it. I've added this task:\n" +
                        addTask.toString() + "\n" +
                        "Now you have " + taskList.taskCount() +
                        "in the list.\n");
            } else {
                throw new DukeException("Incorrect format!\n" +
                        "Format should be: <desc> /by dd/MM/yy HHmm");
            }
        }
    }

    /**
     * Adds event task into taskList
     * @param taskList {@code TaskList} object which keeps track of tasks
     * @throws DukeException when description, start or end of event is missing or
     * incorrect format is given for start or end of event
     */
    private void event(TaskList taskList) throws DukeException {
        if (args.length() == 0) {
            throw new DukeException("What is the Event task???");
        } else {
            Pattern eventPattern = Pattern.compile(".+/from \\d{2}/\\d{2}/\\d{4} \\d{4} /to \\d{2}/\\d{2}/\\d{4} \\d{4}");
            Matcher matchEvent = eventPattern.matcher(this.args);
            if (matchEvent.find()) {
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

                Task addTask = new Events(desc, from, to);
                taskList.add(addTask);
                System.out.println("Got it. I've added this task:\n" +
                        addTask.toString() + "\n" +
                        "Now you have " + taskList.taskCount() +
                        "in the list.\n");
            } else {
                String errMessage = "Incorrect format!\n" +
                        "Format should be: <desc> /from dd/MM/yy HHmm" +
                        " /to dd/MM/yy HHmm";
                throw new DukeException(errMessage);
            }
        }
    }

    /**
     * marks specified task in taskList
     * @param taskList {@code TaskList} object which keeps track of tasks
     * @throws DukeException when index given is less than 0 or greater than size of taskList - 1
     */
    private void mark(TaskList taskList) throws DukeException {
        taskList.markTask(Integer.parseInt(this.args));
    }

    /**
     * unmarks specified task in taskList
     * @param taskList {@code TaskList} object which keeps track of tasks
     * @throws DukeException when index given is less than 0 or greater than size of taskList - 1
     */
    private void unMark(TaskList taskList) throws DukeException {
        taskList.unMarkTask(Integer.parseInt(this.args));
    }

    /**
     * deletes specified task in taskList
     * @param taskList {@code TaskList} object which keeps track of tasks
     * @throws DukeException when index given is less than 0 or greater than size of taskList - 1
     */
    private void delete(TaskList taskList) throws DukeException {
        taskList.remove(Integer.parseInt(this.args));
    }

    /**
     * Provides String representation of Command object
     * @return String representation with command word and arguments
     */
    @Override
    public String toString(){
        return this.command + " " + this.args;
    }
}

