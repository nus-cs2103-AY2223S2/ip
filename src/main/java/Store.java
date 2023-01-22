import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

enum TaskType {
    T, D, E
}

public class Store {
    private ArrayList<Task> database;

    public Store() {
        this.database = new ArrayList<>();
    }

    public int getTotal() {
        return database.size();
    }

    /**
     * Stores the Task into the ArrayList.
     * 
     * @param task Task to be stored
     */
    public void store(Task task) {
        database.add(task);
    }

    /**
     * Marks the task as completed.
     * 
     * @param index The task to be marked as done.
     * @return Message to be printed by the bot.
     * @throws KiraException Invalid-Index
     */
    public String mark(int index) throws KiraException {
        if (index > database.size() || index <= 0) {
            throw new KiraException("There is no such task!\n");
        }
        Task currentTask = database.get(index - 1);
        currentTask.mark();

        StringBuilder ret = new StringBuilder("I have marked this as done~\n");
        ret.append(currentTask.toString());
        ret.append("\n");
        return ret.toString();
    }
    
    /**
     * Marks the task as incomplete.
     * 
     * @param index The task to be marked as incomplete.
     * @return Message to be printed by the bot.
     * @throws KiraException Invalid-Index
     */
    public String unmark(int index) throws KiraException {
        if (index > database.size() || index <= 0) {
            throw new KiraException("There is no such task!\n");
        }
        Task currentTask = database.get(index - 1);
        currentTask.unmark();

        StringBuilder ret = new StringBuilder("This task has been undone~\n");
        ret.append(currentTask.toString());
        ret.append("\n");
        return ret.toString();
    }

    /**
     * Lists all the current tasks in the array.
     * 
     * @return Message to be printed by the bot.
     */
    public String list() {
        StringBuilder ret = new StringBuilder("Here is all the items stored~\n");

        for (int i = 0; i < database.size(); i++) {
            ret.append(i + 1);
            ret.append("." + database.get(i));
            ret.append("\n");
        }

        return ret.toString();
    }

    /**
     * Deletes the task from the database.
     * 
     * @param index The task to be deleted.
     * @return Message to be printed by the bot
     * @throws KiraException Invalid-Index
     */
    public String delete(int index) throws KiraException {
        if (index > database.size() || index <= 0) {
            throw new KiraException("There is no such task!\n");
        }
        Task currentTask = database.get(index - 1);
        database.remove(index - 1);

        StringBuilder ret = new StringBuilder("This task has been removed~\n");
        ret.append(currentTask.toString());
        ret.append("\n");
        return ret.toString();
    }

    /**
     * Saves current tasks list to a CSV file.
     * 
     * @throws KiraException File-Output-Issue
     */
    public void save() throws KiraException {
        try {
            File saveFile = new File("./store.csv");
            FileWriter fw = new FileWriter(saveFile);
            for (Task t : this.database) {
                fw.write(t.saveFormat());
                fw.write("\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            throw new KiraException(e.getMessage());
        }
    }

    public void load() throws KiraException {
        try {
            File loadFile = new File("./store.csv");
            Scanner sc = new Scanner(loadFile);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] parsed = task.split("\",\"");
                switch (TaskType.valueOf(parsed[0])) {
                case T:
                    ToDo tdo = new ToDo(parsed[1]);
                    if (parsed[2].equals("y")) {
                        tdo.mark();
                    }
                    store(tdo);
                    break;
                case D:
                    Deadline deadline = new Deadline(parsed[1], parsed[3]);
                    if (parsed[2].equals("y")) {
                        deadline.mark();
                    }
                    store(deadline);
                    break;
                case E:
                    Event evt = new Event(parsed[1], parsed[3], parsed[4]);
                    if (parsed[2].equals("y")) {
                        evt.mark();
                    }
                    store(evt);
                    break;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new KiraException(e.getMessage());
        }
    }
}
