public class Store {
    private Task[] database;
    private int total;

    public Store() {
        this.database = new Task[100];
        this.total = 0;
    }

    public int getTotal() {
        return this.total;
    }

    /**
     * Stores the Task into the array.
     * Throws KiraException if database is full.
     * 
     * @param task Task to be stored
     */
    public void store(Task task) throws KiraException {
        if (this.total > 100) {
            throw new KiraException("The task list is full!\n");
        }
        this.database[this.total] = task;
        this.total++;
    }

    /**
     * Marks the task as completed.
     * Throw KiraException if index is invalid
     * 
     * @param index The task to be marked as done.
     * @return Message to be printed by the bot.
     */
    public String mark(int index) throws KiraException {
        if (index > this.total || index <= 0) {
            throw new KiraException("There is no such task!\n");
        }
        Task currentTask = this.database[index - 1];
        currentTask.mark();

        StringBuilder ret = new StringBuilder("I have marked this as done~\n");
        ret.append(currentTask.toString());
        ret.append("\n");
        return ret.toString();
    }
    
    /**
     * Marks the task as incomplete.
     * Throw KiraException if index is invalid
     * 
     * @param index The task to be marked as incomplete.
     * @return Message to be printed by the bot.
     */
    public String unmark(int index) throws KiraException {
        if (index > this.total || index <= 0) {
            throw new KiraException("There is no such task!\n");
        }
        Task currentTask = this.database[index - 1];
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

        for (int i = 0; i < this.total; i++) {
            ret.append(i + 1);
            ret.append("." + database[i]);
            ret.append("\n");
        }

        return ret.toString();
    }
}
