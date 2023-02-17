package storage;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import exception.DukeException;
import expense.Expense;
import parser.Parser;
import task.Task;

/**
 * Represents a class that encapsulates all methods that works with files, including loading and saving.
 */
public class Storage {
    private String filepath;
    // filepath should be "data/duke.txt"

    /**
     * Constructs a Storage object.
     *
     * @param filePath String corresponding to relative path of duke.txt which is the saved list between sessions.
     * @return Storage Object.
     */
    public Storage(String filePath) {
        this.filepath = filePath;

        // Checks if the file exists, else creates it

        try {
            File mySaveFile = new File(filePath);
            if (!mySaveFile.exists()) {
                mySaveFile.createNewFile();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /**
     * Returns an Arraylist which corresponds to the list of tasks saved in object filepath.
     *
     * @return ArrayList object which is a list of Tasks saved from previous session.
     * @throws DukeException If unable to read/parse the file in object filepath.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> listToStore = new ArrayList<Task>();

        try {
            File mySaveFile = new File("data/duke.txt");
            Scanner s = new Scanner(mySaveFile);
            while (s.hasNext()) {

                Task task;
                String nextLine = s.nextLine();
                if (nextLine.charAt(0) == '1') {
                    task = Parser.parseFileReader(nextLine.substring(2), true);
                } else {
                    task = Parser.parseFileReader(nextLine.substring(2), false);
                }
                listToStore.add(task);
            }
            s.close();
        } catch (DukeException e) {
            throw e;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listToStore;
    }

    /**
     * Returns an Arraylist which corresponds to the list of tasks saved in object filepath.
     *
     * @return ArrayList object which is a list of Expense saved from previous session.
     * @throws DukeException If unable to read/parse the file in object filepath.
     */
    public ArrayList<Expense> expensesLoad() throws DukeException {
        ArrayList<Expense> listToStore = new ArrayList<Expense>();

        try {
            File mySaveFile = new File(this.filepath);
            Scanner s = new Scanner(mySaveFile);
            while (s.hasNext()) {

                Expense expense;
                String nextLine = s.nextLine();
                expense = Parser.parseExpenseEcho(nextLine);
                listToStore.add(expense);
            }
            s.close();
        } catch (DukeException e) {
            throw e;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listToStore;
    }

    /**
     * Stores the current Arraylist in current session to hard drive.
     *
     * @param listToStore ArrayList of Tasks to be stored in object filepath.
     * @return String to be output as response
     */
    public String save(ArrayList<Task> listToStore) {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            fw.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

        while (!listToStore.isEmpty()) {
            try {
                FileWriter fw = new FileWriter("data/duke.txt", true);
                fw.write(listToStore.get(0).getCommand());
                listToStore.remove(0);
                fw.write(System.lineSeparator());
                fw.close();
            } catch (Exception e) {
                // TODO: handle exception
                return "    An Error has occurred\n";
            }
        }
        return "";
    }

    /**
     * Stores the current Arraylist in current session to hard drive.
     *
     * @param listToStore ArrayList of Expenses to be stored in object filepath.
     * @return String to be output as response.
     */
    public String saveExpense(ArrayList<Expense> listToStore) {
        while (!listToStore.isEmpty()) {
            try {
                FileWriter fw = new FileWriter("data/expenses.txt", true);
                fw.write(listToStore.get(0).getCommand());
                listToStore.remove(0);
                fw.write(System.lineSeparator());
                fw.close();
            } catch (Exception e) {
                // TODO: handle exception
                return "    An Error has occurred\n";
            }
        }
        return "";
    }
}
