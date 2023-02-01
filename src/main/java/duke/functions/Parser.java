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

    public static String[] handleInput(
            String input, String regex, int limit, int minSize) throws DukeException {
        String[] subInputs = input.split(regex, limit);
        if (subInputs.length < minSize) {
            throw new InputDukeException();
        }
        return subInputs;
    }

    public static boolean handleCommand(String[] inputs, ToDoList list) throws Exception {
        String command = inputs[0];
        int index;

        switch (command) {
        case "bye":
            return true;
        case "list":
            Ui.listMsg(list.toString());
            break;
        case "mark":
            index = Integer.parseInt(inputs[1]);
            list.markTask(index); //numbersformatexception to be handled
            Ui.taskMarking(list, index, command);
            break;
        case "unmark":
            index = Integer.parseInt(inputs[1]);
            list.unmarkTask(index); //numbersformatexception to be handled
            Ui.taskMarking(list, index, command);
            break;
        case "delete":
            index = Integer.parseInt(inputs[1]);
            Task removed = list.delete(index); //numbersformatexception to be handled
            Ui.taskAddDelete(list, removed, command);
            break;
        case "todo":
        case "event":
        case "deadline":
            Parser.handleTaskCommand(inputs, list);
            break;
        default:
            throw new DukeException("The Duke does not understand your words!");
        }
        return false;
    }

    public static void handleTaskCommand(String[] inputs, ToDoList list) throws DukeException {
        String command = inputs[0];

        if (inputs.length < 2) {
            throw new InputDukeException();
        }
        if (command.equals("todo")) {
            ToDoTask toAdd = new ToDoTask(inputs[1]);
            list.add(toAdd);
            Ui.taskAddDelete(list, toAdd, "add");
            return;
        }
        if (command.equals("deadline")) {
            String[] subInputs = Parser.handleInput(inputs[1], " /by ", 2, 2);
            DeadlineTask toAdd = new DeadlineTask(subInputs[0], subInputs[1]);
            list.add(toAdd);
            Ui.taskAddDelete(list, toAdd, "add");
            return;
        }
        if (command.equals("event")) {
            String[] subInputs = Parser.handleInput(inputs[1], " /from ", 2, 2);
            String[] subInputDurations = Parser.handleInput(subInputs[1], " /to ", 2, 2);
            EventTask toAdd = new EventTask(subInputs[0],
                    subInputDurations[0],
                    subInputDurations[1]);
            list.add(toAdd);
            Ui.taskAddDelete(list, toAdd, "add");
        }
    }
}
