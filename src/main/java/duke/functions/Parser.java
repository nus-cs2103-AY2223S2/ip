package duke.functions;

import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DateTimeFormatException;
import duke.exceptions.InvalidArgumentsException;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.MultipleArgumentsException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * The main class for parsing commands via the command line.
 * @author JamesLiuZX
 *     AY2223-S2 CS2103T
 */
public class Parser {
    private TaskList list;

    /**
     * Default constructor for a parser object.
     *
     * @param list The main list initialised in Ui.java.
     */
    public Parser(TaskList list) {
        this.list = list;
    }

    /**
     * Main method to handle logic for input from command line
     *
     * @param input String input written in the command line.
     * @throws InvalidCommandException Error from an invalid command.
     */
    public String handleInput(String input) throws InvalidCommandException {
        String[] split = input.split(" ", 2);
        String cmd = split[0];
        switch (cmd) {
        case "list":
            return this.list.toString();
        case "mark":
            return mark(split);
        case "unmark":
            return unmark(split);
        case "delete":
            return delete(split);
        case "deadline":
            return deadline(split);
        case "event":
            return event(split);
        case "todo":
            return todo(split);
        case "bye":
            return bye(split);
        case "find":
            return find(split);
        case "help":
            return Ui.help();
        case "usage":
            return Ui.helpDetailed();
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Main method to parse database duke.functions.Duke.txt to populate current TaskList.
     *
     * @param fileReader
     * @param dl TaskList from
     */
    public void parseDatabase(Scanner fileReader, TaskList dl) {
        int index = -1;
        while (fileReader.hasNextLine()) {
            String input = fileReader.nextLine();
            String[] split = input.split("\\|");
            String cmd = split[0];
            String status = split[1];
            String taskName = split[2];
            index++;
            switch (cmd) {
            case "T":
                dl.insertToDo(taskName, true);
                if (status.equals("X")) {
                    dl.mark(index);
                }
                break;
            case "D":
                String deadline = "a";
                dl.insertDeadline(taskName, deadline, true);
                if (status.equals("X")) {
                    dl.mark(index);
                }
                break;
            case "E":
                String startTime = split[3];
                String endTime = split[3];
                dl.insertEvent(taskName, startTime, endTime, true);
                if (status.equals("X")) {
                    dl.mark(index);
                }
                break;
            default:
                break;
            }
        }
    }


    private String mark(String[] split) throws InvalidCommandException {
        try {
            if (split.length != 2) {
                throw new MultipleArgumentsException();
            }
            Integer index = Integer.parseInt(split[1]);
            return this.list.mark(index);
        } catch (NumberFormatException | MultipleArgumentsException e) {
            return "Please input a number after the command.";
        }
    }

    private String unmark(String[] split) throws InvalidCommandException {
        try {
            if (split.length != 2) {
                throw new MultipleArgumentsException();
            }
            Integer index = Integer.parseInt(split[1]);
            return this.list.unMark(index);
        } catch (NumberFormatException | MultipleArgumentsException e) {
            return "Please input a number after the command.";
        }
    }

    private String delete(String[] split) {
        try {
            if (split.length != 2) {
                throw new MultipleArgumentsException();
            }
            Integer index = Integer.parseInt(split[1]);
            return this.list.deleteTask(index);
        } catch (NumberFormatException | MultipleArgumentsException e) {
            return "Please input a number after the command.";
        }
    }

    private String deadline(String[] split) {
        try {
            String[] secondSplit = split[1].split("/by ");
            if (secondSplit.length != 2) {
                throw new InvalidArgumentsException();
            }
            return this.list.insertDeadline(secondSplit[0], secondSplit[1]);
        } catch (InvalidArgumentsException | DateTimeFormatException e) {
            return e.getMessage();
        }
    }

    private String event(String[] split) {
        try {
            String[] secondSplit = split[1].split("/from |/to ", 3);
            System.out.println(secondSplit);
            if (secondSplit.length != 3) {
                throw new InvalidArgumentsException();
            }
            return this.list.insertEvent(secondSplit[0], secondSplit[1], secondSplit[2]);
        } catch (InvalidArgumentsException e) {
            return e.getMessage();
        }
    }

    private String todo(String[] split) {
        try {
            if (split.length != 2) {
                throw new InvalidArgumentsException();
            }
            String todoName = split[1];
            return this.list.insertToDo(todoName);
        } catch (InvalidArgumentsException e) {
            return e.getMessage();
        }
    }

    private String find(String[] split) {
        try {
            if (split.length != 2) {
                throw new InvalidArgumentsException();
            }
            String name = split[1];
            ArrayList<Task> results = this.list.findMatchingTasks(name);
            String output = "Here are the tasks in your list: \n";
            if (!results.isEmpty()) {
                int index = 0;
                for (Task task : results) {
                    output += String.format("%d.%s%n\n", index, task);
                    index++;
                }
            } else {
                return "There are no matching tasks currently!";
            }
            return output;
        } catch (InvalidArgumentsException e) {
            return e.getMessage();
        }
    }

    private String bye(String[] split) throws InvalidCommandException {
        try {
            if (split.length != 1) {
                throw new InvalidArgumentsException();
            }
            return "Bye. Come back soon!";
        } catch (InvalidArgumentsException e) {
            return e.getMessage();
        }
    }

}
