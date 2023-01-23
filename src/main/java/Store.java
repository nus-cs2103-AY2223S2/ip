import java.util.ArrayList;

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
     * Find all deadlines or events that are ongoing today.
     * 
     * @return Message to be printed by the bot
     */
    public String findToday() {
        ArrayList<Task> todays = new ArrayList<>();
        for (Task t : database) {
            if (t instanceof Deadline) {
                Deadline temp = (Deadline) t;
                if (temp.matchToday()) {
                    todays.add(temp);
                }
            } else if (t instanceof Event) {
                Event temp = (Event) t;
                if (temp.withinTimeframe()) {
                    todays.add(temp);
                }
            }
        }
        StringBuilder ret = new StringBuilder("Here is a list of tasks by today~\n");
        for (Task t : todays) {
            ret.append(t.toString());
            ret.append("\n");
        }
        return ret.toString();
    }
}
