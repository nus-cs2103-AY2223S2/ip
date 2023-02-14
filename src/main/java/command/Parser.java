package command;

import UI.Ui;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;


import java.util.Locale;

import static java.lang.Integer.parseInt;

public class Parser {
    // Interprets user input
    TaskList list;
    public Parser(TaskList list) {
        this.list = list;
    }

    /**
     * Central hub for processing user input and determining which specific process command should be used.
     * @param input the line of input that the user keyed in
     */
    public String processInput(String input) {
        String[] inputAnalyzed = input.split(" ");
        if (input.contains("#")) {
            return Ui.showInvalidInputError("Illegal character '#'.");
        }
        assert !input.contains("#"): "Input should not have #";
        try {
            switch (inputAnalyzed[0].toLowerCase(Locale.ROOT)) {
            case "bye":
                return processByeOperation(inputAnalyzed);
            case "list":
                return processListOperation(inputAnalyzed);
            case "mark":
                return processMarkOperation(inputAnalyzed);
            case "unmark":
                return processUnmarkOperation(inputAnalyzed);
            case "deadline":
                return processDeadlineOperation(input);
            case "todo":
                return processTodoOperation(input);
            case "event":
                return processEventOperation(input);
            case "delete":
                return processDeleteOperation(inputAnalyzed);
            case "find":
                return processFindOperation(input);
            default:
                return Ui.unknownCommand();
                //Fallthrough
            }
        } catch (InvalidInputException e) {
            return Ui.showInvalidInputError(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            return Ui.showArrayOutOfBoundsError();
        } catch (NumberFormatException e) {
            return Ui.showInvalidInputError("I only take integers for number inputs.");
        }
    }

    /**
     * Processes user input when the starting command is bye, terminating the program.
     * @param inputAnalyzed the split-up version of the user's input
     * @throws InvalidInputException for when the user types anything more than bye
     */
    private String processByeOperation(String[] inputAnalyzed) throws InvalidInputException {
        //Check if there is anything other than bye
        if (inputAnalyzed.length > 1) {
            throw new InvalidInputException("Incorrect format. Correct form should be \"bye\".");
        } else {
            return Ui.close();
        }
    }

    /**
     * Processes user input when the starting command is list, displaying the details of the entries in the TaskList.
     * @param inputAnalyzed the split-up version of the user's input
     * @throws InvalidInputException for when the user types anything more than list
     */
    private String processListOperation(String[] inputAnalyzed) throws InvalidInputException {
        //Check if there is anything other than list
        if (inputAnalyzed.length > 1) {
            throw new InvalidInputException("Incorrect format. Correct form should be \"list\".");
        } else {
            return Ui.showListState(list);
        }
    }

    /**
     * Processes user input when the starting command is mark, marking the task at the index provided as completed.
     * @param inputAnalyzed the split-up version of the user's input
     * @throws InvalidInputException for when the user types the complete mark command incorrectly
     * @throws IndexOutOfBoundsException for when the user inputs an invalid index
     * @throws NumberFormatException for when the user doesn't input an integer in their input
     */
    private String processMarkOperation(String[] inputAnalyzed) throws InvalidInputException,
            IndexOutOfBoundsException, NumberFormatException {
        // Parse
        if (inputAnalyzed.length != 2) {
            throw new InvalidInputException("Incorrect format. Correct form should be \"mark i\", "
                    + "with i being an integer.");
        }
        int index = parseInt(inputAnalyzed[1]);
        // List
        list.mark(index - 1);
        // UI.Ui
        return Ui.showMarkSuccess(list.get(index - 1));
    }


    /**
     * Processes user input when the starting command is unmark, marking the task at the index provided as not
     * completed.
     * @param inputAnalyzed the split-up version of the user's input
     * @throws InvalidInputException for when the user types the complete unmark command incorrectly
     * @throws IndexOutOfBoundsException for when the user inputs an invalid index
     * @throws NumberFormatException for when the user doesn't input an integer in their input
     */

    private String processUnmarkOperation(String[] inputAnalyzed) throws InvalidInputException, IndexOutOfBoundsException,
            NumberFormatException {
        if (inputAnalyzed.length != 2) {
            throw new InvalidInputException("Incorrect format. Correct form should be \"unmark i\", "
                    + "with i being an integer.");
        }
        int index = parseInt(inputAnalyzed[1]);
        list.unmark(index - 1);
        return Ui.showUnmarkSuccess(list.get(index - 1));
    }


    /**
     * Processes user input when the starting command is delete, deleting the task at the provided index.
     * @param inputAnalyzed the split-up version of the user's input
     * @throws InvalidInputException for when the user types the complete delete command incorrectly
     * @throws IndexOutOfBoundsException for when the user inputs an invalid index
     * @throws NumberFormatException for when the user doesn't input an integer in their input
     */

    private String processDeleteOperation(String[] inputAnalyzed) throws InvalidInputException, IndexOutOfBoundsException,
            NumberFormatException {
        if (inputAnalyzed.length != 2) {
            throw new InvalidInputException("Incorrect format. Correct form should be \"delete i\", "
                    + "with i being an integer.");
        }
        int index = parseInt(inputAnalyzed[1]);
        Task temp = list.get(index - 1);
        list.remove(index - 1);
        return Ui.showDeleteSuccess(temp, list);
    }


    /**
     * Processes user input when the starting command is deadline, adding a new deadline task to the TaskList.
     * @param input the user input
     * @throws InvalidInputException for when the user inputs the deadline command in the incorrect format
     */

    private String processDeadlineOperation(String input) throws IndexOutOfBoundsException, InvalidInputException {
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
        return Ui.showAddTaskSuccess(newDead, list);
    }


    /**
     * Processes user input when the starting command is to-do, adding a new to-do task to the TaskList.
     * @param input the user input
     * @throws InvalidInputException for when the user inputs the to-do command in the incorrect format
     */

    private String processTodoOperation(String input) throws InvalidInputException {
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
        //UI.Ui
        return Ui.showAddTaskSuccess(newTodo, list);
    }


    /**
     * Processes user input when the starting command is event, adding a new event task to the TaskList.
     * @param input the user input
     * @throws InvalidInputException for when the user inputs the event command in the incorrect format
     */

    private String processEventOperation(String input) throws IndexOutOfBoundsException, InvalidInputException {
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
        //UI.Ui Section
        return Ui.showAddTaskSuccess(newEvent, list);
    }

    /**
     * Returns a list of all the tasks in the TaskList containing the provided keyword.
     * @param input the user's input
     * @throws InvalidInputException when the user does not input anything for a keyword
     */
    private String processFindOperation(String input) throws InvalidInputException {
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
        return Ui.showFindListState(foundItems,keyword);
    }
}
