package duke.functions;

import duke.ToDoList;
import duke.exceptions.DukeException;
import duke.exceptions.InputDukeException;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.ToDoTask;

public class Parser {
    private  Parser() {
    }

    public static String[] inputHandler(String input, String regex, int limit, int minSize) throws DukeException {
        String[] sub = input.split(regex, limit);
        if (sub.length < minSize) {
            throw new InputDukeException();
        }
        return sub;
    }

    public static boolean commandHandler(String[] input, ToDoList ls) throws Exception {
        String command = input[0];
        int index;

        switch (command) {
        case "bye":
            return true;
        case "list":
            UI.listMsg(ls.toString());
            break;
        case "mark":
            index = Integer.parseInt(input[1]);
            ls.markTask(index); //numbersformatexception to be handled
            UI.taskMarking(ls, index, command);
            break;
        case "unmark":
            index = Integer.parseInt(input[1]);;
            ls.unmarkTask(index); //numbersformatexception to be handled
            UI.taskMarking(ls, index, command);
            break;
        case "delete":
            index = Integer.parseInt(input[1]);
            Task removed = ls.delete(index); //numbersformatexception to be handled
            UI.taskAddDelete(ls, removed, command);
            break;
        case "todo":
        case "event":
        case "deadline":
            Parser.taskCommandHandler(input, ls);
            break;
        default:
            throw new DukeException("The Duke does not understand your words!");
        }
        return false;
    }

    public static void taskCommandHandler(String[] input, ToDoList ls) throws DukeException {
        String command = input[0];

        if (input.length < 2) {
            throw new InputDukeException();
        }
        if (command.equals("todo")) {
            ToDoTask toAdd = new ToDoTask(input[1]);
            ls.add(toAdd);
            UI.taskAddDelete(ls, toAdd, "add");
            return;
        }
        if (command.equals("deadline")) {
            String[] sub = Parser.inputHandler(input[1], " /by ", 2, 2);
            DeadlineTask toAdd = new DeadlineTask(sub[0], sub[1]);
            ls.add(toAdd);
            UI.taskAddDelete(ls, toAdd, "add");
            return;
        }
        if (command.equals("event")) {
            String[] sub = Parser.inputHandler(input[1], " /from ", 2, 2);
            String[] subDuration = Parser.inputHandler(sub[1], " /to ", 2, 2);
            EventTask toAdd = new EventTask(sub[0], subDuration[0], subDuration[1]);
            ls.add(toAdd);
            UI.taskAddDelete(ls, toAdd, "add");
        }
    }
}
