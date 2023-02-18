package duke.parser;

import java.time.LocalDateTime;
import java.util.Objects;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteTaskCommand;
import duke.command.FindTaskCommand;
import duke.command.GetAllTaskCommand;
import duke.command.MarkTaskCommand;
import duke.command.SortCommand;
import duke.command.UnmarkTaskCommand;
import duke.datetime.DateTime;
import duke.dukeexception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;

/**
 * Class to parse user input
 */
public class Parser {
    public Parser() {}

    /**
     * Parse data from data.txt in storage and adds it to TaskList
     *
     * @param str String to be parsed
     * @param tl TaskList where Tasks are added to
     */
    public void parseAndAddStorageTask(String str, TaskList tl) {
        //parse from string that is either a event, deadline or todoevent
        String[] arr = str.split(",");
        Task task;
        try {
            if (Objects.equals(arr[0], "T")) {
                task = new Todo(arr[2]);
            } else if (Objects.equals(arr[0], "D")) {
                LocalDateTime deadline = DateTime.parseDateTimeString(arr[3]);
                task = new Deadline(arr[2], deadline);
            } else if (Objects.equals(arr[0], "E")) {
                String fromString = arr[3].strip();
                String toString = arr[4].strip();
                LocalDateTime from = DateTime.parseDateTimeString(fromString);
                LocalDateTime to = DateTime.parseDateTimeString(toString);
                task = new Event(arr[2], from, to);
            } else {
                System.out.println("Parser parseAndAddStorageTask() Error in text:" + str);
                return;
            }
            tl.addTask(task);
            if (Objects.equals(arr[1], "1")) {
                task.setIsCompleted(true);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Parser parseAndAddStorageTask() Index out of bound when parsing string:" + str);
        }
    }

    /**
     * Parse input from user given in CLI
     *
     * @param str String input of user
     * @return Command corresponding to the input of user
     * @throws DukeException If user input is unknown or incorrect format
     */
    public Command parse(String str) throws DukeException {
        if (Objects.equals(str, "bye")) {
            return null;
        }
        if (Objects.equals(str, "list")) {
            return new GetAllTaskCommand();
        }
        Command command = this.isMark(str);
        if (!Objects.equals(command, null)) {
            return command;
        }
        command = this.isUnmark(str);
        if (!Objects.equals(command, null)) {
            return command;
        }
        command = this.isTodo(str);
        if (!Objects.equals(command, null)) {
            return command;
        }
        command = this.isDeadline(str);
        if (!Objects.equals(command, null)) {
            return command;
        }
        command = this.isEvent(str);
        if (!Objects.equals(command, null)) {
            return command;
        }
        command = this.isDelete(str);
        if (!Objects.equals(command, null)) {
            return command;
        }
        command = this.isFind(str);
        if (!Objects.equals(command, null)) {
            return command;
        }
        command = this.isSort(str);
        if (!Objects.equals(command, null)) {
            return command;
        }
        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }

    private Command isSort(String s) throws DukeException {
        if (s.length() == 4 && Objects.equals(s, "sort")) {
            return new SortCommand();
        }
        return null;
    }

    /**
     * Check if user input String is to Mark task as done
     *
     * @param s Stirng input of user
     * @return MarkTask Command if user input is correct, else null
     * @throws DukeException If no spacing/index/index are not numbers only
     */
    private Command isMark(String s) throws DukeException {
        //checks if input is mark and throw exception for wrong format if matches
        if (s.length() >= 4 && Objects.equals(s.substring(0, 4), "mark")) {
            if (s.length() < 5) {
                throw new DukeException("Please provide a index to mark.");
            } else if (!Objects.equals(s.charAt(4), ' ')) {
                throw new DukeException("Please provide a spacing after the mark keyword.");
            } else if (s.length() < 6) {
                throw new DukeException("Please provide the index to mark.");
            } else if (!s.substring(5, s.length()).matches("[0-9]+")) {
                throw new DukeException("Please provide only positive integers for index to mark.");
            } else {
                return new MarkTaskCommand(Integer.parseInt(s.substring(5, s.length())));
            }
        }
        return null;
    }

    /**
     * Check if user input String is to Unmark task
     *
     * @param s Stirng input of user
     * @return MarkTask Command if user input is correct, else null
     * @throws DukeException If no spacing/index/index are not numbers only
     */
    private Command isUnmark(String s) throws DukeException {
        //checks if input is unmark and throw exception for wrong format if matches
        if (s.length() >= 6 && Objects.equals(s.substring(0, 6), "unmark")) {
            if (s.length() < 7) {
                throw new DukeException("Please provide a index to unmark.");
            } else if (!Objects.equals(s.charAt(6), ' ')) {
                throw new DukeException("Please provide a spacing after the unmark keyword");
            } else if (s.length() < 8) {
                throw new DukeException("Please provide the index to unmark.");
            } else if (!s.substring(7, s.length()).matches("[0-9]+")) {
                throw new DukeException("Please provide only positive integers for index to unmark.");
            } else {
                return new UnmarkTaskCommand(Integer.parseInt(s.substring(7, s.length())));
            }
        }
        return null;
    }

    /**
     * Check if user input String is to add todotask
     *
     * @param s Stirng input of user
     * @return AddTodo Command if user input is correct, else null
     * @throws DukeException If no spacing/taskname
     */
    private Command isTodo(String s) throws DukeException {
        //checks if input is todotask and throw exception for wrong format if matches
        if (s.length() >= 4 && Objects.equals(s.substring(0, 4), "todo")) {
            if (s.length() < 5) {
                throw new DukeException("The description of a todo cannot be empty.");
            } else if (!Objects.equals(s.charAt(4), ' ')) {
                throw new DukeException("Please provide a spacing after the todo keyword.");
            } else if (s.length() < 6) {
                throw new DukeException("The description of a todo cannot be empty.");
            } else {
                return new AddTodoCommand(s.substring(5, s.length()));
            }
        }
        return null;
    }

    /**
     * Check if user input String is to add Deadline task
     *
     * @param s Stirng input of user
     * @return AddDeadline Command if user input is correct, else null
     * @throws DukeException If no spacing/taskanme/date/date wrong format
     */
    private Command isDeadline(String s) throws DukeException {
        //checks if input is deadline task and throw exception for wrong format if matches
        if (s.length() >= 8 && Objects.equals(s.substring(0, 8), "deadline")) {
            if (s.length() < 9) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } else if (!Objects.equals(s.charAt(8), ' ')) {
                throw new DukeException("Please provide a spacing after the deadline keyword");
            } else if (s.length() < 10) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } else {
                s = s.substring(9, s.length());
                String[] parts = s.split("/");
                String deadlineString = parts[1].substring(3, parts[1].length()).strip();
                String name = parts[0].strip();
                return new AddDeadlineCommand(name, deadlineString);
            }
        }
        return null;
    }

    /**
     * Check if user input String is to add Event task
     *
     * @param s Stirng input of user
     * @return AddEvent Command if user input is correct, else null
     * @throws DukeException If no spacing/taskanme/date/date wrong format
     */
    private Command isEvent(String s) throws DukeException {
        //check if input is event task and throw exception for wrong format if matches
        if (s.length() >= 5 && Objects.equals(s.substring(0, 5), "event")) {
            if (s.length() < 6) {
                throw new DukeException("The description of a event cannot be empty.");
            } else if (!Objects.equals(s.charAt(5), ' ')) {
                throw new DukeException("Please provide a spacing after the event keyword");
            } else if (s.length() < 7) {
                throw new DukeException("The description of a event cannot be empty.");
            } else {
                s = s.substring(6, s.length());
                String[] parts = s.split("/");
                String name = parts[0].strip();
                String fromString = parts[1].substring(5, parts[1].length()).strip();
                String toString = parts[2].substring(3, parts[2].length()).strip();
                return new AddEventCommand(name, fromString, toString);
            }
        }
        return null;
    }

    /**
     * Check if user input String is to Delete task
     *
     * @param s Stirng input of user
     * @return DeleteTask Command if user input is correct, else null
     * @throws DukeException If no spacing/index/index not all numbers
     */
    private Command isDelete(String s) throws DukeException {
        //checks if input is delete and throw exception for wrong format if matches
        if (s.length() >= 6 && Objects.equals(s.substring(0, 6), "delete")) {
            if (s.length() < 7) {
                throw new DukeException("Please provide a index to delete.");
            } else if (!Objects.equals(s.charAt(6), ' ')) {
                throw new DukeException("Please provide a spacing after the delete keyword.");
            } else if (s.length() < 8) {
                throw new DukeException("Please provide the index to delete.");
            } else if (!s.substring(7, s.length()).matches("[0-9]+")) {
                throw new DukeException("Please provide only positive integers for index to delete.");
            } else {
                return new DeleteTaskCommand(Integer.parseInt(s.substring(7, s.length())));
            }
        }
        return null;
    }

    /**
     * Check if user input string is to find task using keyword
     *
     * @param s String input of user
     * @return FindTaskCommand if user input is Find... else null
     * @throws DukeException If no spacing or incorrect indentation
     */
    private Command isFind(String s) throws DukeException {
        if (s.length() >= 4 && Objects.equals(s.substring(0, 4), "find")) {
            if (s.length() < 5) {
                throw new DukeException("Please provide a keyword to find.");
            } else if (!Objects.equals(s.charAt(4), ' ')) {
                throw new DukeException("Please provide a spacing after the find keyword.");
            } else if (s.length() < 6) {
                throw new DukeException("Please provide the keyword to find.");
            } else {
                return new FindTaskCommand(s.substring(5, s.length()));
            }
        }
        return null;
    }
}
