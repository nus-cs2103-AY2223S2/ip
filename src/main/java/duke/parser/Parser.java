package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Arrays;
public class Parser {
    private final static String[] VALID_COMMANDS = {"mark", "unmark", "list", "delete", "bye", "todo", "event", "deadline"};

    /**
     * Returns a boolean stating if the command is valid.
     *
     * @param input Command to check.
     * @return True if command is valid, False otherwise.
     */
    public boolean isValidCommand(String input) {
        return Arrays.asList(VALID_COMMANDS).contains(input.split(" ")[0]);
    }

    public boolean checkFind(String input) {
        return input.split(" ")[0].equals("find");
    }
    public boolean checkTask(String input) {
        return checkTodo(input) || checkEvent(input) || checkDeadline(input);
    }
    public boolean checkDeadline(String input) {
        String cmd = input.split(" ")[0];
        return cmd.equals("deadline") || cmd.equals("Deadline");
    }

    public boolean checkEvent(String input) {
        String cmd = input.split(" ")[0];
        return cmd.equals("event") || cmd.equals("Event");
    }

    public boolean checkTodo(String input) {
        String cmd = input.split(" ")[0];
        return cmd.equals("todo") || cmd.equals("Todo");
    }
    public boolean checkMark(String input) {
        return input.split(" ")[0].equals("mark");
    }
    public boolean checkUnmark(String input) {
        return input.split(" ")[0].equals("unmark");
    }
    public boolean checkList(String input) {
        return input.split(" ")[0].equals("list");
    }
    public boolean checkBye(String input) {
        return input.split(" ")[0].equals("bye");
    }
    public boolean checkDelete(String input) {
        return input.split(" ")[0].equals("delete");
    }

    public boolean checkValidTask(String input) {
        String[] inputs = input.split(" ");
        return inputs.length >= 2;
    }

    public boolean checkValidDeadline(String input) {
        String[] nameAndDeadline = input.split(" ", 2)[1].split(" /by ");
        return nameAndDeadline.length >= 2;
    }

    public boolean checkValidEvent(String input) {
        String[] nameAndStart = input.split(" ", 2)[1].split(" /from ");
        if (nameAndStart.length < 2) {
            return false;
        }
        String[] startAndEnd = nameAndStart[1].split(" /to ");
        if (startAndEnd.length < 2) {
            return false;
        }
        return true;
    }

    public boolean isValidEvent(String input) {
        String[] nameAndStart = input.split(" ", 2)[1].split(" /from ");
        if (!checkValidEvent(input)) {
            return false;
        }
        String[] startAndEnd = nameAndStart[1].split(" /to ");
        String[] start = startAndEnd[0].split(" ");
        String[] end = startAndEnd[1].split(" ");
        if (!isValidDate(start[0]) || !isValidDate(end[0])) {
            return false;
        }
        if (start.length > 1 && (!isValidTime(start[1]) || !isValidTime(end[1]))) {
            return false;
        }
        return true;
    }

    public boolean isValidDate(String input) {
        String[] date = input.split("/");
        if (date.length < 3 || date[2].length() != 4 ||
                date[1].length() > 2 || date[0].length() > 2) {
            return false;
        }
        return true;
    }

    public boolean isValidTime(String input) {
        String[] time = input.split("");
        if (time.length != 4 || Integer.parseInt(input) > 2359) {
            return false;
        }
        return true;
    }

    public boolean isValidDeadline(String input) {
        String[] nameAndDeadline = input.split(" ", 2)[1].split(" /by ");
        if (!checkValidDeadline(input)) {
            return false;
        }
        String[] deadline = nameAndDeadline[1].split(" ");
        if (!isValidDate(deadline[0])) {
            return false;
        }
        if (deadline.length == 2 && !isValidTime(deadline[1])) {
            return false;
        }
        return true;
    }
    public Command parse(String input) {
        Command cmd;
        if (checkList(input)) {
            cmd = new ListCommand();
        } else if (checkFind(input)) {
            String word = input.split(" ")[1];
            cmd = new FindCommand(word);
        } else if (checkMark(input)) {
            int num = Integer.parseInt(input.split(" ")[1]);
            cmd = new MarkCommand(num);
        } else if (checkUnmark(input)) {
            int num = Integer.parseInt(input.split(" ")[1]);
            cmd = new UnmarkCommand(num);
        } else if (checkDelete(input)) {
            int num = Integer.parseInt(input.split(" ")[1]);
            cmd = new DeleteCommand(num);
        } else if (checkTask(input)) {
            if (!checkValidTask(input)) {
                cmd = new InvalidCommand("Please enter task description!");
            } else if (checkDeadline(input) && !isValidDeadline(input)) {
                cmd = new InvalidCommand("Format of deadline should be: " +
                        "deadline <description> /by: <dd/mm/yyyy hhmm");
            } else if (checkEvent(input) && !isValidEvent(input)) {
                cmd = new InvalidCommand("Format of event should be: " +
                        "event <description> /from: dd/mm/yyyy hhmm /to dd/mm/yyyy hhmm");
            } else {
                cmd = new AddCommand(input);
            }
        } else if (!isValidCommand(input)) {
            cmd = new InvalidCommand();
        } else {
            cmd = new ByeCommand();
        }
        return cmd;
    }

    public Task parseTask(String input) {
        Task newTask;
        String[] inputs = input.split(" ");
        String type = inputs[0];
        switch(type) {
            case "todo": {
                String name = input.split(" ", 2)[1];
                newTask = new ToDo(name);
                break;
            }
            case "deadline": {
                String[] nameAndDeadline = input.split(" ", 2)[1].split(" /by ");
                String name = nameAndDeadline[0];
                String[] deadline = nameAndDeadline[1].split(" ");
                String date = formatDate(deadline[0].split("/"));
                String remarks = " | " + nameAndDeadline[1];
                if (deadline.length == 1) {
                    newTask = new Deadline(name, date, remarks);
                } else {
                    String time = formatTime(deadline[1].split(""));
                    newTask = new Deadline(name, date, time, remarks);
                }
                break;
            }
            case "event": {
                String[] nameAndStart = input.split(" ", 2)[1].split(" /from ");
                String name = nameAndStart[0];
                String[] startAndEnd = nameAndStart[1].split(" /to ");
                String[] start = startAndEnd[0].split(" ");
                String startDate = formatDate(start[0].split("/"));
                String[] end = startAndEnd[1].split(" ");
                String endDate = formatDate(end[0].split("/"));
                String remarks = " | " + startAndEnd[0] + " | " + startAndEnd[1];
                if (start.length > 1) {
                    String startTime = formatTime(start[1].split(""));
                    String endTime = formatTime(end[1].split(""));
                    newTask = new Event(name, startDate, endDate, startTime, endTime, remarks);
                } else {
                    newTask = new Event(name, startDate, endDate, remarks);
                }
                break;
            }
            default: {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        return newTask;
    }

    public String formatDate(String[] date) {
        for (int i = 0; i < date.length; i++) {
            if (date[i].length() < 2) {
                date[i] = "0" + date[i];
            }
        }
        return date[2] + "-" + date[1] + "-" + date[0];
    }

    public String formatTime(String[] time) {
        return time[0] + time[1] + ":" + time[2] + time[3];
    }

}