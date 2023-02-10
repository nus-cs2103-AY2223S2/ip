package expenselist;

import java.util.ArrayList;

import expense.Expense;
/**
 * An ExpenseList class to encapsulate the list of expense.
 */
public class ExpenseList {
    private ArrayList<Expense> listOfExpenses;

    /**
     * Constructor for a ExpenseList object.
     */
    public ExpenseList() {
        this.listOfExpenses = new ArrayList<Expense>();
    }

    /**
     * Constructor for a ExpenseList object, given a ArrayList of expenses already.
     * @param list given ArrayList to be instantiated as listOfExpenses;
     */
    public ExpenseList(ArrayList<Expense> list) {
        this.listOfExpenses = list;
    }

    /**
     * Function to add an Expense object into the list.
     * @param expense Expense object to be added to the list.
     */
    public void addExpense(Expense expense) {
        this.listOfExpenses.add(expense);
        // call UI
    }

    /**
     * Function to remove an expense item, given the index of the object.
     * @param index index of expense object to be removed.
     */
    public void removeExpense(int index) {
        this.listOfExpenses.remove(index);
    }

    /**
     * Getter function to return the list of expenses.
     * @return listOfExpenses.
     */
    public ArrayList<Expense> getListOfExpenses() {
        return this.listOfExpenses;
    }

    /**
     * Function to return the total expenses of the whole list.
     * @return
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
