package parser;

import java.util.ArrayList;

import exception.DukeException;
import exception.InvalidInputException;
import exception.NoExpenseParameterException;
import exception.NoTaskDescriptionException;
import expense.Expense;
import expense.FoodExpense;
import expense.MiscExpense;
import expense.TransportExpense;
import expenselist.ExpenseList;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import tasklist.TaskList;
import ui.Ui;

/**
 * Class to encapsulate all parser methods, which parse through user inputs.
 */
public class Parser {
    /**
     * Returns Task formed from given string.
     * If input string is not of recognised form, exception is thrown.
     *
     * @param echo Input string, treated as commands.
     * @return Task corresponding to given input commands.
     * @throws NoTaskDescriptionException If command is given for a task without a description.
     * @throws InvalidInputException If string does not start with recognised command.
     */
    public static Task parseEcho(String echo) throws DukeException {
        assert echo != null;
        if (echo.startsWith("todo")) {
            if (echo.substring(4).trim().isEmpty()) {
                throw(new NoTaskDescriptionException("     ☹ OOPS!!! The description of a todo cannot be empty."));
            }
            return new Todo(echo.substring(4).trim());

        } else if (echo.startsWith("deadline")) {
            String deadlineArguments = echo.substring(8).trim();
            if (deadlineArguments.isEmpty()) {
                throw(new NoTaskDescriptionException("     ☹ OOPS!!! The description of a deadline cannot be empty."));
            }
            String[] splitArguments = deadlineArguments.split("/");

            return new Deadline(splitArguments[0], splitArguments[1].substring(2).trim());

        } else if (echo.startsWith("event")) {
            String eventArguments = echo.substring(5).trim();
            if (eventArguments.isEmpty()) {
                throw(new NoTaskDescriptionException("     ☹ OOPS!!! The description of a event cannot be empty."));
            }
            String[] splitArguments = eventArguments.split("/");
            return new Event(splitArguments[0], splitArguments[1].substring(4).trim(),
                    splitArguments[2].substring(2).trim());

        } else {
            throw(new InvalidInputException("      ☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
        }
    }

    /**
     * Returns a Task object corresponding to input String.
     * Made to be used to read from saved files, hence containing addition argument to check if tasks are done.
     *
     * @param echo Input string to be parsed
     * @param isDone Boolean to check if corresponding created Task is marked as done
     * @return Task corresponding to given String
     */
    public static Task parseFileReader(String echo, boolean isDone) {
        if (echo.startsWith("todo")) {
            return new Todo(isDone, echo.substring(4).trim());
        } else if (echo.startsWith("deadline")) {
            String deadlineArguments = echo.substring(8).trim();
            String[] splitArguments = deadlineArguments.split("/");
            return new Deadline(isDone, splitArguments[0], splitArguments[1].substring(2).trim());
        } else if (echo.startsWith("event")) {
            String eventArguments = echo.substring(5).trim();
            String[] splitArguments = eventArguments.split("/");
            return new Event(isDone, splitArguments[0], splitArguments[1].substring(4).trim(),
                    splitArguments[2].substring(2).trim());
        } else {
            throw(new InvalidInputException("      ☹ OOPS!!! File format seems weird"));
        }
    }

    /**
     * Returns String based on the type of echo passed through, which would be the response put out to the UI.
     *
     * @param echo Input String to be parsed for commands.
     * @param tasks TaskList Object which encapsulates the current list of tasks.
     * @param ui Ui Object to call Ui-related methods.
     * @param storage Storage object to call storage-related methods. Mainly saving.
     * @param expenses ExpenseList to be saved to storage.
     * @param expenseStorage Storage object to call storage-related methods. Mainly saving.
     * @return String of response put out to UI.
     */
    public static String parseCommands(String echo, TaskList tasks, Ui ui, Storage storage, ExpenseList expenses,
                                       Storage expenseStorage) {
        String answer = "";
        if (echo.equals("bye")) {
            answer += ui.showSavingMessage();
            answer += storage.save(tasks.getList());
            answer += expenseStorage.saveExpense(expenses.getListOfExpenses());
            answer += ui.showSavedMessage();
            answer += ui.showClosingMessage();
        }

        if (echo.equals("list")) {
            answer += "    OK, Here are the items in your list: \n";
            answer += Ui.printArrayList(tasks.getList());
        }

        if (echo.startsWith("mark")) {
            try {
                int taskToModify = Integer.parseInt(echo.replaceAll("[^0-9]", ""));
                tasks.markDone(taskToModify - 1);
                answer += Ui.showMarkedMessage(tasks.get(taskToModify - 1));
            } catch (Exception e) {
                // TODO: handle exception
                return "";
            }
        }

        if (echo.startsWith("unmark")) {
            try {
                int taskToModify = Integer.parseInt(echo.replaceAll("[^0-9]", ""));
                tasks.markUndone(taskToModify - 1);
                // Call UI unmarked
                answer += Ui.showUnmarkedMessage(tasks.get(taskToModify - 1));
            } catch (Exception e) {
                // TODO: handle exception
                return "";
            }
        }

        if (echo.startsWith("delete") || echo.startsWith("remove")) {
            try {
                int taskToModify = Integer.parseInt(echo.replaceAll("[^0-9]", ""));
                Task removed = tasks.get(taskToModify - 1);
                tasks.removeTask(taskToModify - 1);
                // Call ui removed
                answer += Ui.showRemovedMessage(removed);
                answer += ui.printListNumber(tasks.getList());
            } catch (Exception e) {
                // TODO: handle exception
                return "";
            }
        }

        if (echo.startsWith("find")) {
            String taskToFind = echo.substring(4).trim();
            if (taskToFind.isEmpty()) {
                answer += "    OPPS!! You can't find nothing\n";
                return answer;
            }
            ArrayList<Task> foundList = tasks.findArray(taskToFind);
            answer += "    Here are the matching tasks in your list:\n";
            answer += Ui.printArrayList(foundList);
        }

        return answer;
    }

    /**
     * Returns an expense object depending on the type of echo passed through.
     *
     * @param echo String to be parsed.
     * @return Expense object that corresponds with passed echo.
     * @throws DukeException in the case of unhandled situations.
     */
    public static Expense parseExpenseEcho(String echo) throws DukeException {
        if (echo.startsWith("food")) {
            String foodArguments = echo.substring(4).trim();
            if (foodArguments.isEmpty()) {
                throw(new NoExpenseParameterException("   OOPS!! The parameters of a food expense cannot be empty"));
            }
            String[] splitArguments = foodArguments.split("/");
            double cost = Double.parseDouble(splitArguments[1]);
            return new FoodExpense(splitArguments[0], cost);
        } else if (echo.startsWith("transport")) {
            String transportArguments = echo.substring(9).trim();
            if (transportArguments.isEmpty()) {
                throw(new NoExpenseParameterException("    OPPS!! The parameters of a transport "
                        + "expense cannot be empty"));
            }
            String[] splitArguments = transportArguments.split("/");
            double cost = Double.parseDouble(splitArguments[1]);
            return new TransportExpense(splitArguments[0], cost);

        } else if (echo.startsWith("misc")) {
            String miscArguments = echo.substring(4).trim();
            if (miscArguments.isEmpty()) {
                throw(new NoExpenseParameterException("    OPPS!! The parameters of a misc expense cannot be empty"));
            }
            String[] splitArguments = miscArguments.split("/");
            double cost = Double.parseDouble(splitArguments[1]);
            return new MiscExpense(splitArguments[0], cost);
        } else {
            throw(new InvalidInputException("      ☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
        }
    }

    /**
     * Returns a String corresponding to response to action taken.
     *
     * @param echo String to be parsed.
     * @param list ExpenseList object to take actions with.
     * @return response to be output to UI.
     */
    public static String parseExpenseCommands(String echo, ExpenseList list) {
        String answer = "";
        if (echo.equals("total")) {
            answer += "    OK here's the total amount of expenses in your list:\n";
            answer += Ui.printTotalSpent(list);
        }
        if (echo.equals("list")) {
            answer += "    OK here's the expenses in your list:\n";
            answer += Ui.printExpenseArrayList(list.getListOfExpenses());
        }

        if (echo.startsWith("delete") || echo.startsWith("remove")) {
            try {
                int expenseToModify = Integer.parseInt(echo.replaceAll("[^0-9]", ""));
                Expense removed = list.get(expenseToModify - 1);
                list.removeExpense(expenseToModify - 1);
                answer += Ui.showRemovedExpenseMessage(removed);
            } catch (Exception e) {
                // TODO: handle exception
                return "";
            }
        }

        return answer;
    }

}
