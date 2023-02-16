package command;

import java.text.ParseException;

import collections.TaskList;
import exceptions.SundayException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import utilities.Ui;

/**
 * Enum representing different commands that can be executed in the task management system.
 * The commands include INITIALIZE, LIST, DEADLINE, EVENT, TODO, MARK, UNMARK, DELETE, and BYE.
 *
 * @author Tan Yan-Hao Joshua
 *
 */
public enum Command {

    /**
     * Initializes the task list.
     */
    INITIALIZE("init") {
        @Override
        public void execute(String filepath) throws SundayException {
            Ui.printWelcome();
            boolean isFirstLaunch = list.load();
            if (isFirstLaunch) {
                Ui.printCreatedSaveFile();
            } else {
                Ui.printLoadedSaveFile();
            }
        }
    },

    /**
     * Lists all the tasks in the task list.
     */
    LIST("list") {
        @Override
        public void execute(String input) {
            if (list.isEmpty()) {
                Ui.printEmptyTaskList();
            } else {
                Ui.printTaskList(list);
            }
        }
    },

    /**
     * Adds a deadline task to the task list.
     */
    DEADLINE("deadline") {
        @Override
        public void execute(String input) throws SundayException {
            try {
                input = input.substring(1);
                String[] strArr = input.split(" ");
                int i = 0;
                StringBuilder sb = new StringBuilder();
                while (!(strArr[i].equals("/by") || strArr[i].equals("(by:"))) {
                    sb.append(strArr[i]);
                    sb.append(" ");
                    i++;
                }
                String description = sb.toString().substring(0, sb.length() - 1);

                sb.setLength(0);
                i++; // skip "/by" or "(by:"
                while (i < strArr.length) {
                    sb.append(strArr[i]);
                    sb.append(" ");
                    i++;
                }
                String by = sb.toString().substring(0, sb.length() - 1);

                Task deadline = new Deadline(description, by);
                list.add(deadline);
                Ui.printAddedTask(deadline, list.getUncompletedSize());
            } catch (StringIndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! The description of a deadline cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! The description of this deadline is invalid.");
            } catch (ParseException e) {
                throw new SundayException(
                        "OOPS! It appears the deadline given was not of the format dd/mm/yyyy hhmm");
            }
        }
    },

    /**
     * Adds an event task to the task list.
     */
    EVENT("event") {
        @Override
        public void execute(String input) throws SundayException {
            try {
                input = input.substring(1);
                String[] strArr = input.split(" ");
                int j = 0;
                StringBuilder sb = new StringBuilder();
                while (!(strArr[j].equals("/from") || strArr[j].equals("(from:"))) {
                    sb.append(strArr[j]);
                    sb.append(" ");
                    j++;
                }
                String description = sb.toString().substring(0, sb.length() - 1);

                sb.setLength(0);
                j++; // skip "/from" or "(from:"
                while (!(strArr[j].equals("/to") || strArr[j].equals("to:"))) {
                    sb.append(strArr[j]);
                    sb.append(" ");
                    j++;
                }
                String start = sb.toString().substring(0, sb.length() - 1);

                sb.setLength(0);
                j++; // skip "/to" or "to:"
                while (j < strArr.length) {
                    sb.append(strArr[j]);
                    sb.append(" ");
                    j++;
                }
                String end = sb.toString().substring(0, sb.length() - 1);
                Task event = new Event(description, start, end);
                list.add(event);
                Ui.printAddedTask(event, list.getUncompletedSize());
            } catch (StringIndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! The description of an event cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! The description of this event is invalid.");
            } catch (ParseException e) {
                throw new SundayException(
                        "OOPS! It appears the deadline given was not of the format dd/mm/yyyy hhmm");
            }
        }
    },

    /**
     * Adds a to-do task to the task list.
     */
    TODO("todo") {
        @Override
        public void execute(String input) throws SundayException {
            try {
                String description = input.substring(1);
                Task toDo = new ToDo(description);
                list.add(toDo);
                Ui.printAddedTask(toDo, list.getUncompletedSize());
            } catch (StringIndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! The description of a todo cannot be empty.");
            }
        }
    },

    /**
     * Marks a task as complete.
     */
    MARK("mark") {
        @Override
        public void execute(String input) throws SundayException {
            try {
                int index = Integer.parseInt(input.substring(1)) - 1;
                Task marked = list.mark(index);
                Ui.printMarkedTask(marked, list.getUncompletedSize());
            } catch (NumberFormatException e) {
                throw new SundayException("OOPS!!! You did not specify which task you wanted me to mark");
            } catch (IndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! Seems like that task does not exist.");
            }
        }
    },

    /**
     * Unmarks a task as complete.
     */
    UNMARK("unmark") {
        @Override
        public void execute(String input) throws SundayException {
            try {
                int index = Integer.parseInt(input.substring(1)) - 1;
                Task unmarked = list.unmark(index);
                Ui.printUnmarkedTask(unmarked, list.getUncompletedSize());
            } catch (NumberFormatException e) {
                throw new SundayException("OOPS!!! You did not specify which task you wanted me to unmark");
            } catch (IndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! Seems like that task does not exist.");
            }
        }
    },

    /**
     * Deletes a task from the task list.
     */
    DELETE("delete") {
        @Override
        public void execute(String input) throws SundayException {
            try {
                int index = Integer.parseInt(String.valueOf(input.substring(1))) - 1;
                Task deleted = list.delete(index);
                Ui.printDeletedTask(deleted, list.getUncompletedSize());
            } catch (NumberFormatException e) {
                throw new SundayException("OOPS!!! You did not specify which task you wanted me to delete");
            } catch (IndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! Seems like that task does not exist");
            }
        }
    },

    FIND("find") {
        @Override
        public void execute(String input) {
            String keyword = input.substring(1);
            TaskList found = list.find(keyword);
            Ui.printListFound(found);
        }
    },

    /**
     * Exits the task management system.
     */
    BYE("bye") {
        @Override
        public void execute(String input) throws SundayException {
            boolean didSave = list.save();
            Ui.printGoodbye(didSave);
        }
    };

    /**
     * A static instance of TaskList to store tasks.
     */
    private static TaskList list = new TaskList();

    /**
     * The command string.
     */
    private String command;

    /**
     * Constructor for the Command enum.
     *
     * @param command The string representation of the command.
     */
    Command(String command) {
        this.command = command;
    }

    /**
     * Executes the command with the given input.
     *
     * @param input The input to be used for executing the command.
     * @throws SundayException If any error occurs during the execution of the command.
     */
    public abstract void execute(String input) throws SundayException;
}
