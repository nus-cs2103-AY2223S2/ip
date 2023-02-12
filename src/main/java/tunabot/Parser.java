package tunabot;

import tunabot.exceptions.InputException;
import tunabot.task.Deadline;
import tunabot.task.Event;
import tunabot.task.Task;

/**
 * Class handling the parsing of commands
 */
public class Parser {
    /**
     * Returns boolean to determine whether to continue receiving input
     * If input "bye" is received return true else return false
     * @param input command
     * @param tasks TaskList
     * @return true If input is "bye" else false
     * @throws InputException If the command does not fit required format
     */
    public static String parse(String input, TaskList tasks) throws InputException {
        assert tasks != null : "Task list should exist at this point";
        String[] command = input.split(" ", 2);
        switch (command[0]) {
        case "list":
            return Ui.list(tasks);
        case "bye":
            return "    Bye! Blub blub!";
        case "mark":
            return mark(tasks, command);
        case "unmark":
            return unmark(tasks, command);
        case "delete":
            return delete(tasks, command);
        case "todo":
            return newTask(tasks, command);
        case "event":
            return newEvent(tasks, command);
        case "deadline":
            return newDeadline(tasks, command);
        case "find":
            return find(tasks, command);
        case"update":
            return update(tasks, command);
        default:
            return "BLUB OH! INVALID COMMAND!";
        }
    }

    private static String update(TaskList tasks, String[] command) throws InputException {
        if (command.length < 2) {
            throw new InputException("BLUB! Needs more info to update!");
        }
        String[] details = command[1].split(" /name | /from | /by | /to ");
        try {
            int index = Integer.parseInt(details[0]) - 1;
            Task updateTask = tasks.get(index);
            if (updateTask instanceof Event) {
                Event updateEvent = (Event) updateTask;
                if (details.length < 4) {
                    throw new InputException("BLUB! Updating an Event needs a new name,"
                        + " new start date and new end date!");
                }
                updateEvent.setName(details[1]);
                updateEvent.setStart(details[2]);
                updateEvent.setEnd(details[3]);
            } else if (updateTask instanceof Deadline) {
                Deadline updateDeadline = (Deadline) updateTask;
                if (details.length < 3) {
                    throw new InputException("BLUB! Updating a Deadline needs a new name and new deadline!");
                }
                updateDeadline.setName(details[1]);
                updateDeadline.setDeadline(command[2]);
            } else {
                if (details.length < 2) {
                    throw new InputException("BLUB! Updating a Task needs a new name!");
                }
                updateTask.setName(details[1]);
            }
            return "Task has been updated!\n" + Ui.list(tasks);
        } catch (IllegalArgumentException e) {
            throw new InputException("BLUB! Index chosen isn't a number!");
        } catch (IndexOutOfBoundsException e) {
            throw new InputException("BLUB! Task chosen isn't on the list!");
        }
    }

    private static String find(TaskList tasks, String[] command) throws InputException {
        if (command.length < 2) {
            throw new InputException("BLUB! find needs a search target!");
        } else {
            String target = command[1];
            TaskList targetList = tasks.find(target);
            if (targetList.size() == 0) {
                return "BLUB! No matching tasks!";
            } else {
                String found = "BLUB! Here are your matching task(s)!\n";
                found += Ui.list(targetList);
                return found;
            }
        }
    }

    private static String newEvent(TaskList tasks, String[] command) throws InputException {
        if (command.length < 2) {
            throw new InputException("BLUB! Event needs a name, "
                + "a start time and end time!");
        } else {
            String[] details = command[1].split(" /from | /to ", 3);
            if (details.length < 3) {
                throw new InputException("BLUB! Event is missing info!"
                    + " Please check input. BLUB!");
            }
            Event newEvent = new Event(details[0], details[1], details[2]);
            tasks.add(newEvent);
            return Ui.add(newEvent, tasks.size());
        }
    }

    private static String newTask(TaskList tasks, String[] command) throws InputException {
        if (command.length < 2) {
            throw new InputException("BLUB! Task needs a name!");
        } else {
            Task newTask = new Task(command[1]);
            tasks.add(newTask);
            return Ui.add(newTask, tasks.size());
        }
    }

    private static String delete(TaskList tasks, String[] command) throws InputException {
        if (command.length < 2) {
            throw new InputException("BLUB! No task chosen!");
        }
        try {
            int index = Integer.parseInt(command[1]);

            String delete = Ui.delete(tasks.get((index - 1)), tasks.size() - 1);
            tasks.remove(index - 1);
            return delete;
        } catch (IllegalArgumentException e) {
            throw new InputException("BLUB! Index chosen isn't a number!");
        } catch (IndexOutOfBoundsException e) {
            throw new InputException("BLUB! Task chosen isn't on the list!");
        }
    }

    private static String unmark(TaskList tasks, String[] command) throws InputException {
        if (command.length < 2) {
            throw new InputException("BLUB! No task chosen!");
        }
        try {
            int index = Integer.parseInt(command[1]);
            tasks.get(index - 1).unmarkDone();
            return Ui.unmark(tasks.get(index - 1));
        } catch (IllegalArgumentException e) {
            throw new InputException("BLUB! Index chosen isn't a number!");
        } catch (IndexOutOfBoundsException e) {
            throw new InputException("BLUB! Task chosen isn't on the list!");
        }
    }

    private static String mark(TaskList tasks, String[] command) throws InputException {
        if (command.length < 2) {
            throw new InputException("BLUB! No task chosen!");
        }
        try {
            int index = Integer.parseInt(command[1]);
            tasks.get(index - 1).markDone();
            return Ui.mark(tasks.get(index - 1));
        } catch (IllegalArgumentException e) {
            throw new InputException("BLUB! Index chosen isn't a number!");
        } catch (IndexOutOfBoundsException e) {
            throw new InputException("BLUB! Task chosen isn't on the list!");
        }
    }

    private static String newDeadline(TaskList tasks, String[] command) throws InputException {
        if (command.length < 2) {
            throw new InputException("BLUB! Deadline needs a name and due date!");
        } else {
            String[] details = command[1].split(" /by ", 2);
            if (details.length < 2) {
                throw new InputException("BLUB! Deadline is missing info!"
                    + " PLease check input. BLUB!");
            }
            Deadline newDeadline = new Deadline(details[0], details[1]);
            tasks.add(newDeadline);
            return Ui.add(newDeadline, tasks.size());
        }
    }
}
