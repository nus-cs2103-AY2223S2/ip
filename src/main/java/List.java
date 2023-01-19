public class List {
    //taskList containing the tasks added.
    //We assume that a maximum of 100 tasks can be added.
    private Task[] taskList = new Task[100];

    //counter to keep track of the number of items added so far
    private int count = 0;

    /**
     * Add tasks into the list and display added task when done.
     * @param name The name of the task to be added.
     */
    public void add(String name) {
        Task t = new Task(name);
        this.taskList[count] = t;
        this.count++;
        System.out.println("added: " + name + "\n");
    }

    /**
     * Mark the task at the given index as done.
     * @param index The index number of the task given.
     */
    public void mark(int index) {
        this.taskList[index-1].mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + this.taskList[index-1] + "\n");
    }

    /**
     * Mark the task at the given index as not done.
     * @param index The index number of the task given.
     */
    public void unmark(int index) {
        this.taskList[index-1].unmark();
        System.out.println("OK, I've marked this task as not done:");
        System.out.println(" " + this.taskList[index-1] + "\n");
    }

    /**
     * Print the list.
     */
    public void print() {
        if (this.count == 0) {
            System.out.println("There are no items in the list.\n");
        }
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < this.count; i++) {
            String toPrint = String.format("%d. %s",i+1, this.taskList[i]);
            System.out.println(toPrint);
        }
        System.out.println("");
    }
}
