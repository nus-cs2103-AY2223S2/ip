package connor.parser;

import connor.Connor;
import connor.task.*;
import connor.ui.Ui;

public class Parser {

    private void validateName(String input) throws InvalidTaskException {
        if (input.trim().length() < 1) {
            throw new InvalidTaskException();
        }
    }

    private String[] getNameDeadlinePair(String input) throws InvalidTaskException {
        int byIndex = input.indexOf("/by");
        if (byIndex < 1) {
            throw new InvalidTaskException();
        }
        String[] pair = new String[2];
        pair[0] = input.substring(0, byIndex - 1);
        validateName(pair[0]);
        pair[1] = input.substring(byIndex + 4);
        return pair;
    }

    private String[] getNameStartEndTuple(String input) throws InvalidTaskException {
        int fromIndex = input.indexOf("/from");
        int byIndex = input.indexOf("/to");
        if (fromIndex < 1 || byIndex < 1) {
            throw new InvalidTaskException();
        }
        String[] tuple = new String[3];
        tuple[0] = input.substring(0, fromIndex - 1);
        validateName(tuple[0]);
        tuple[1] = input.substring(fromIndex + 6, byIndex - 1);
        tuple[2] = input.substring(byIndex + 4);
        return tuple;
    }

    public Task parseCommand(String command, String information) throws InvalidTaskException {
        if (command.equals("TODO")) {
            validateName(information);
            return new Todo(information);
        } else if (command.equals("DEADLINE")) {
            String[] pair = getNameDeadlinePair(information);
            return new Deadline(pair[0], pair[1]);
        } else {
            String[] tuple = getNameStartEndTuple(information);
            return new Event(tuple[0], tuple[1], tuple[2]);
        }
    }

    private String getTask(String input) throws InvalidTaskException {
        if (input.indexOf(' ') == -1) {
            throw new InvalidTaskException();
        }
        return input.substring(input.indexOf(' ') + 1).trim();
    }

    private String getCommand(String input) {
        if (input.indexOf(' ') == -1) {
            return input.toUpperCase();
        } else {
            return input.substring(0, input.indexOf(' ')).toUpperCase();
        }
    }

    public boolean parse(String input, TaskList tasks, Ui ui) {
        String command = getCommand(input).trim();
        try {
            switch (Connor.Commands.valueOf(command)) {
            case HI:
                ui.greetings("HI");
                break;

            case BYE:
                ui.greetings("BYE");
                return true;

            case LIST:
                tasks.printList();
                break;

            case MARK:
                tasks.markDone(Integer.parseInt(getTask(input)), ui);
                break;

            case UNMARK:
                tasks.markUndone(Integer.parseInt(getTask(input)), ui);
                break;

            case TODO:
            case DEADLINE:
            case EVENT:
                Task task = parseCommand(command, getTask(input));
                tasks.addTask(task);
                ui.addTaskMessage(task, tasks.getSize());
                break;

            case DELETE:
                tasks.deleteTask(getTask(input), ui);
                break;

            case DELETEALL:
                tasks.deleteAllTask();
                ui.deleteAllMessage();
                break;
            }
        } catch (IllegalArgumentException e) {
            Ui.printMessage("INVALID COMMAND");
        } catch (InvalidTaskException e) {
            Ui.printMessage(e.getMessage());
        }
        return false;
    }
}
