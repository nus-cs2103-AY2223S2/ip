package command;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

import java.util.Locale;

import static java.lang.Integer.parseInt;

public class Parser {
    // Interprets user input
    Ui ui;
    TaskList list;
    public Parser(Ui ui, TaskList list) {
        this.ui = ui;
        this.list = list;
    }

    public void processInput(String input) {
        String[] inputAnalyzed = input.split(" ");
        if (input.contains("#")) {
            ui.showInvalidInputError("Illegal character '#'.");
            return;
        }
        try {
            switch (inputAnalyzed[0].toLowerCase(Locale.ROOT)) {
            case "bye":
                processByeOperation(inputAnalyzed);
                break;
            case "list":
                processListOperation(inputAnalyzed);
                break;
            case "mark":
                processMarkOperation(inputAnalyzed);
                break;
            case "unmark":
                processUnmarkOperation(inputAnalyzed);
                break;
            case "deadline":
                processDeadlineOperation(input);
                break;
            case "todo":
                processTodoOperation(input);
                break;
            case "event":
                processEventOperation(input);
                break;
            case "delete":
                processDeleteOperation(inputAnalyzed);
                break;
            case "find":
                processFindOperation(input);
                break;
            default:
                ui.unknownCommand();
                //Fallthrough
            }
        } catch (InvalidInputException e) {
            ui.showInvalidInputError(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            ui.showArrayOutOfBoundsError();
        } catch (NumberFormatException e) {
            ui.showNumberFormatError();
        }

    }
    private void processByeOperation(String[] inputAnalyzed) throws InvalidInputException {
        //Check if there is anything other than bye
        if (inputAnalyzed.length > 1) {
            throw new InvalidInputException("Incorrect format. Correct form should be \"bye\".");
        } else {
            ui.close();
        }
    }

    private void processListOperation(String[] inputAnalyzed) throws InvalidInputException {
        //Check if there is anything other than list
        if (inputAnalyzed.length > 1) {
            throw new InvalidInputException("Incorrect format. Correct form should be \"list\".");
        } else {
            ui.showListState(list);
        }
    }

    private void processMarkOperation(String[] inputAnalyzed) throws InvalidInputException,
            IndexOutOfBoundsException, NumberFormatException {
        // Parse
        if (inputAnalyzed.length != 2) {
            throw new InvalidInputException("Incorrect format. Correct form should be \"mark i\", "
                    + "with i being an integer.");
        }
        int index = parseInt(inputAnalyzed[1]);
        // List
        list.mark(index);
        // command.Ui
        ui.showMarkSuccess(list.get(index - 1));
    }

    private void processUnmarkOperation(String[] inputAnalyzed) throws InvalidInputException, IndexOutOfBoundsException,
            NumberFormatException {
        if (inputAnalyzed.length != 2) {
            throw new InvalidInputException("Incorrect format. Correct form should be \"unmark i\", "
                    + "with i being an integer.");
        }
        int index = parseInt(inputAnalyzed[1]);
        list.unmark(index - 1);
        ui.showUnmarkSuccess(list.get(index - 1));
    }

    private void processDeleteOperation(String[] inputAnalyzed) throws InvalidInputException, IndexOutOfBoundsException,
            NumberFormatException {
        if (inputAnalyzed.length != 2) {
            throw new InvalidInputException("Incorrect format. Correct form should be \"delete i\", "
                    + "with i being an integer.");
        }
        int index = parseInt(inputAnalyzed[1]);
        Task temp = list.get(index - 1);
        list.remove(index - 1);
        ui.showDeleteSuccess(temp, list);
    }

    private void processDeadlineOperation(String input) throws IndexOutOfBoundsException, InvalidInputException {
        String[] deadlineAnalyze = input.split("/by");
        String date;
        try {
            date = deadlineAnalyze[1].trim();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("Missing deadline date.");
        }
        String details = deadlineAnalyze[0]
                            .split("deadline")[1]
                            .trim();
        if (details.equals("")) {
            throw new InvalidInputException("Missing deadline description.");
        }
        Deadline newDead = new Deadline(details, date);
        list.add(newDead);
        ui.showAddTaskSuccess(newDead, list);
    }

    private void processTodoOperation(String input) throws InvalidInputException {
        //Possible Errors:
        //No descriptor
        String[] todoAnalyze = input.split("todo ");
        //Analyze
        Todo newTodo;
        try {
            newTodo = new Todo(todoAnalyze[1].trim());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("Missing to-do description.");
        }
        //List
        list.add(newTodo);
        //command.Ui
        ui.showAddTaskSuccess(newTodo, list);
    }

    private void processEventOperation(String input) throws IndexOutOfBoundsException, InvalidInputException {
        //Analyze
        String[] eventAnalyze;
        String[] timeAnalyze;
        try {
            eventAnalyze = input.split("/from");
            timeAnalyze = eventAnalyze[1].split("/to");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("Missing /from or /to. Format is \"event [name] /from [time] /to [time]\"");
        }
        String start = timeAnalyze[0].trim();
        String over = timeAnalyze[1].trim();
        String details = eventAnalyze[0]
                            .split("event")[1]
                            .trim();
        if (start.equals("") || over.equals("") || details.equals("")) {
            throw new InvalidInputException("Missing details for at least one of the sections.");
        }
        //Add Item
        Event newEvent = new Event(details, start, over);
        list.add(newEvent);
        //command.Ui Section
        ui.showAddTaskSuccess(newEvent, list);
    }

    /**
     * Returns a list of all the tasks in the TaskList containing the provided keyword.
     * @param input the user's input
     * @throws InvalidInputException when the user does not input anything for a keyword
     */
    private void processFindOperation(String input) throws InvalidInputException {
        String[] inputAnalyzed = input.split("find ");
        String keyword;
        try {
            keyword = inputAnalyzed[1];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("Missing keyword.");
        }

        TaskList foundItems = new TaskList();
        for (Task task : list) {
            if (task.toString().contains(keyword)) {
                foundItems.add(task);
            }
        }
        ui.showFindListState(foundItems,keyword);
    }
}
