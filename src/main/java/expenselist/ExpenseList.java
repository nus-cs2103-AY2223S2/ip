package expenselist;

import java.util.ArrayList;

import expense.Expense;
/**
 * An ExpenseList class to encapsulate the list of expense.
 */
public class ExpenseList {
    private ArrayList<Expense> listOfExpenses;

    /**
     * Constructs a ExpenseList object.
     */
    public ExpenseList() {
        this.listOfExpenses = new ArrayList<Expense>();
    }

    /**
     * Constructs a ExpenseList object, given a ArrayList of expenses already.
     *
     * @param list given ArrayList to be instantiated as listOfExpenses;
     */
    public ExpenseList(ArrayList<Expense> list) {
        this.listOfExpenses = list;
    }

    /**
     * Adds an Expense object to the list.
     *
     * @param expense Expense object to be added to the list.
     */
    public void addExpense(Expense expense) {
        this.listOfExpenses.add(expense);
        // call UI
    }

    /**
     * Removes an expense item given the index of object.
     *
     * @param index index of expense object to be removed.
     */
    public void removeExpense(int index) {
        this.listOfExpenses.remove(index);
    }

    /**
     * Gets list of expenses.
     *
     * @return listOfExpenses.
     */
    public ArrayList<Expense> getListOfExpenses() {
        return this.listOfExpenses;
    }

    /**
     * Returns total amounts of money spent on in the whole list.
     *
     * @return total amount of money in a double.
     */
    public double getTotal() {
        double total = 0;
        for (int i = 0; i < this.listOfExpenses.size(); i++) {
            total += this.listOfExpenses.get(i).getAmountSpent();
        }
        return total;
    }

    public Expense get(int index) {
        return this.listOfExpenses.get(index);
    }
}
