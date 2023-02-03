package duke;

import duke.tasktypes.Deadlines;
import duke.tasktypes.Events;
import duke.tasktypes.Task;
import duke.tasktypes.ToDo;

/**
 * Class used to interpret the user's input for Duke chatbot.
 */
public class Parser {
    public Parser() {}
    /**
     * Function to redirect user's input to the relevant functions.
     * @param input String representation of the user's input.
     * @param listOfTasks The list of tasks which has been loaded into the Duke chatbot instance.
     * @throws DukeExceptions if the user inputs an invalid command.
     */
    public void readInput(String input, TaskList listOfTasks) throws DukeExceptions {
        try {
            if (input.startsWith("todo")) {
                handleToDoTask(input, listOfTasks);
                return;
            }

            if (input.startsWith("find")) {
                handleFindTask(input, listOfTasks);
                return;
            }

            if (input.startsWith("deadline")) {
                handleDeadlineTask(input, listOfTasks);
                return;
            }

            if (input.startsWith("event")) {
                handleEventTask(input, listOfTasks);
                return;
            }

            if (input.equals("list")) {
                handleList(listOfTasks);
                return;
            }

            if (input.startsWith("delete")) {
                handleDelete(input, listOfTasks);
                return;
            }

            if (input.startsWith("checkdue")) {
                handleCheckDue(input, listOfTasks);
                return;
            }

            if (input.startsWith("mark")) {
                handleMark(input, listOfTasks);
                return;
            }

            if (input.startsWith("unmark")) {
                handleUnmark(input, listOfTasks);
                return;
            }

            throw new DukeExceptions("");
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }

    /**
     * Function to handle find command from the user input
     * @param input String representation of the user's input.
     * @param listOfTasks The list of tasks which has been loaded into the Duke chatbot instance.
     * @throws DukeExceptions if the keyword given is invalid.
     */
    public void handleFindTask(String input, TaskList listOfTasks) throws DukeExceptions {
        try {
            String[] checkForKeywordArr = input.split(" ");
            if (checkForKeywordArr.length < 2) {
                throw new DukeExceptions("find");
            }
            String keyword = input.substring(4);
            listOfTasks.findTasks(keyword);
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }

    /**
     * Function to handle ToDo tasks from the user input.
     * @param input String representation of the user's input.
     * @param listOfTasks The list of tasks which has been loaded into the Duke chatbot instance.
     * @throws DukeExceptions if name of the task is not properly given by user.
     */
    public void handleToDoTask(String input, TaskList listOfTasks) throws DukeExceptions {
        try {
            String useForInit = input.substring(4);
            Task toAdd = new ToDo(useForInit);
            listOfTasks.addTask(toAdd);
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }

    /**
     * Function to handle Deadlines tasks from the user's input.
     * @param input String representation of the user's input.
     * @param listOfTasks The list of tasks which has been loaded into the Duke chatbot instance.
     * @throws DukeExceptions if date of task is wrongly given or if name of task is not properly given by user.
     */
    public void handleDeadlineTask(String input, TaskList listOfTasks) throws DukeExceptions {
        try {
            String useForInit = input.substring(8);
            Task toAdd = new Deadlines(useForInit);
            listOfTasks.addTask(toAdd);
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }

    /**
     * Function to handle Events tasks from the user's input.
     * @param input String representation of the user's input.
     * @param listOfTasks The list of tasks which has been loaded into the Duke chatbot instance.
     * @throws DukeExceptions if event details (from and to dates) are not properly given by user.
     */
    public void handleEventTask(String input, TaskList listOfTasks) throws DukeExceptions {
        try {
            String useForInit = input.substring(5);
            Task toAdd = new Events(useForInit);
            listOfTasks.addTask(toAdd);
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }

    /**
     * Function to redirect listing out the list of tasks when user input is "list".
     * @param listOfTasks The arraylist containing the tasks from Duke chatbot.
     */
    public void handleList(TaskList listOfTasks) {
        listOfTasks.toRead();
    }

    /**
     * Function to handle deleting tasks from user input.
     * @param input String representation of user input.
     * @param listOfTasks The list of tasks which has been loaded from the Duke chatbot instance.
     * @throws DukeExceptions if the index indicated in the input string is invalid (<= 0 or greater than list size)
     */
    public void handleDelete(String input, TaskList listOfTasks) throws DukeExceptions {
        try {
            String[] commandSplit = input.split(" ");
            if (commandSplit.length <= 1) {
                throw new DukeExceptions(" ");
            }
            listOfTasks.deleteTask(Integer.parseInt(commandSplit[1]));
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }

    /**
     * Function to redirect checking task's due date when user input is "checkdue"
     * @param input String representation of user input.
     * @param listOfTasks The list of tasks which has been loaded from the Duke chatbot instance.
     */
    public void handleCheckDue(String input, TaskList listOfTasks) {
        String[] commandSplit = input.split(" ");
        listOfTasks.checkDueDate(Integer.parseInt(commandSplit[1]));
    }

    /**
     * Function to redirect marking task as done.
     * @param input String representation of user input.
     * @param listOfTasks The list of tasks which has been loaded from the Duke chatbot instance.
     * @throws DukeExceptions if the index in the input is invalid (<= 0 or greater than list size)
     */
    public void handleMark(String input, TaskList listOfTasks) throws DukeExceptions {
        try {
            String[] commandSplit = input.split(" ");
            if (commandSplit.length <= 1) {
                throw new DukeExceptions("");
            }
            listOfTasks.markTask(Integer.parseInt(commandSplit[1]));
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }

    /**
     * Function to redirect marking task as not done.
     * @param input String representation of user input.
     * @param listOfTasks The list of tasks which has been loaded from the Duke chatbot instance.
     * @throws DukeExceptions if the index in the input is invalid (<= 0 or greater than list size)
     */
    public void handleUnmark(String input, TaskList listOfTasks) throws DukeExceptions {
        try {
            String[] commandSplit = input.split(" ");
            if (commandSplit.length <= 1) {
                throw new DukeExceptions("");
            }
            listOfTasks.unmarkTask(Integer.parseInt(commandSplit[1]));
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }
}
