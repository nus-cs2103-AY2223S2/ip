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
    HELLO("hello") {
        @Override
        public String execute(String input) throws SundayException {
            boolean isNewUser = list.load();
            return Ui.getWelcomeMessage(isNewUser);
        }
    },

    /**
     * Lists all the tasks in the task list.
     */
    LIST("list") {
        @Override
        public String execute(String input) {
            if (list.isEmpty()) {
                return Ui.getEmptyTaskListMessage();
            } else {
                return Ui.getTaskListMessage(list);
            }
        }
    },

    /**
     * Adds a deadline task to the task list.
     */
    DEADLINE("deadline") {
        @Override
        public String execute(String input) throws SundayException {
            try {
                String[] strArr = input.split(" ");
                int i = 0;
                StringBuilder sb = new StringBuilder();
                while (!(strArr[i].equals("/by") || strArr[i].equals("(by:"))) {
                    sb.append(strArr[i]);
                    sb.append(" ");
                    i++;
                }
                assert strArr[i].equals("/by") || strArr[i].equals("(by:");
                String description = sb.toString();

                sb.setLength(0);
                i++; // skip "/by" or "(by:"
                while (i < strArr.length) {
                    sb.append(strArr[i]);
                    sb.append(" ");
                    i++;
                }
                String by = sb.toString();

                Task deadline = new Deadline(description, by);
                list.add(deadline);
                return Ui.getAddedTaskMessage(deadline, list.getUncompletedSize());
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
        public String execute(String input) throws SundayException {
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
                assert strArr[j].equals("/from") || strArr[j].equals("(from:");
                String description = sb.toString();

                sb.setLength(0);
                j++; // skip "/from" or "(from:"
                while (!(strArr[j].equals("/to") || strArr[j].equals("to:"))) {
                    sb.append(strArr[j]);
                    sb.append(" ");
                    j++;
                }
                assert strArr[j].equals("/to") || strArr[j].equals("to:");
                String start = sb.toString();

                sb.setLength(0);
                j++; // skip "/to" or "to:"
                while (j < strArr.length) {
                    sb.append(strArr[j]);
                    sb.append(" ");
                    j++;
                }
                String end = sb.toString();
                Task event = new Event(description, start, end);
                list.add(event);
                return Ui.getAddedTaskMessage(event, list.getUncompletedSize());
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
        public String execute(String input) throws SundayException {
            try {
                assert !input.substring(1).isEmpty();
                String description = input.substring(1);
                Task toDo = new ToDo(description);
                list.add(toDo);
                return Ui.getAddedTaskMessage(toDo, list.getUncompletedSize());
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
        public String execute(String input) throws SundayException {
            try {
                int index = Integer.parseInt(input.substring(1)) - 1;
                Task marked = list.mark(index);
                return Ui.getMarkedTaskMessage(marked, list.getUncompletedSize());
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
        public String execute(String input) throws SundayException {
            try {
                int index = Integer.parseInt(input.substring(1)) - 1;
                Task unmarked = list.unmark(index);
                return Ui.getUnmarkedTaskMessage(unmarked, list.getUncompletedSize());
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
        public String execute(String input) throws SundayException {
            try {
                int index = Integer.parseInt(input.substring(1)) - 1;
                Task deleted = list.delete(index);
                return Ui.getDeletedTaskMessage(deleted, list.getUncompletedSize());
            } catch (NumberFormatException e) {
                throw new SundayException("OOPS!!! You did not specify which task you wanted me to delete");
            } catch (IndexOutOfBoundsException e) {
                throw new SundayException("OOPS!!! Seems like that task does not exist");
            }
        }
    },

    FIND("find") {
        @Override
        public String execute(String input) {
            String keyword = input.substring(1);
            TaskList found = list.find(keyword);
            return Ui.getListFoundMessage(found);
        }
    },

    /**
     * Exits the task management system.
     */
    BYE("bye") {
        @Override
        public String execute(String input) throws SundayException {
            boolean didSave = list.save();
            return Ui.getGoodbyeMessage(didSave);
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
     * @return The response to the shown to the user.
     * @throws SundayException If any error occurs during the execution of the command.
     */
    public abstract String execute(String input) throws SundayException;
}
