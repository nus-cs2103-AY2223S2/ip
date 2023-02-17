package duke;

import exception.DukeException;
import expense.Expense;
import expenselist.ExpenseList;
import javafx.scene.image.Image;
import parser.Parser;
import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;


/**
 * Represents the whole program, containing the main program for the whole bot.
 */
public class Duke {
    private Storage storage;
    private Storage expensesStorage;
    private TaskList tasks;
    private ExpenseList expenses;
    private Ui ui;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    /**
     * Constructs a Duke Object.
     * Initializes the whole program as well as required objects.
     * If path leads to a file that does not exist, the file will be created.
     *
     * @param filePath String of path to stored list from previous sessions.
     * @return Duke object.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        expensesStorage = new Storage("data/expenses.txt");
        try {
            tasks = new TaskList(storage.load());
            expenses = new ExpenseList(expensesStorage.expensesLoad());
        } catch (DukeException e) {
            ui.showLoadingError();
        }
    }

    public String getResponse(String input) {
        // Supposed to change
        if (input.startsWith("e ")) {
            String expenseInput = input.substring(1).trim();
            String response = Parser.parseExpenseCommands(expenseInput, this.expenses);
            if (!response.isEmpty()) {
                return response;
            }
            Expense expense;
            try {
                expense = Parser.parseExpenseEcho(expenseInput);
            } catch (DukeException e) {
                return e.getMessage();
            }
            expenses.addExpense(expense);
            response += Ui.showAddedExpenseMessage(expense);
            return response;
        }
        String response = Parser.parseCommands(input, this.tasks, this.ui, this.storage, this.expenses,
                this.expensesStorage);
        if (!response.isEmpty()) {
            return response;
        }
        Task item;
        try {
            item = Parser.parseEcho(input);
        } catch (DukeException e) {
            // TODO: handle exception
            return e.getMessage();
        }
        assert item != null;
        tasks.addTask(item);
        response += ui.showAddedMessage(item);
        response += ui.printListNumber(tasks.getList());
        return response;
    }


}
