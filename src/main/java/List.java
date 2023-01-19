public class List {
    //taskList containing the tasks added.
    //We assume that a maximum of 100 tasks can be added.
    private String[] taskList = new String[100];

    //counter to keep track of the number of items added so far
    private int count = 0;

    /**
     * Add tasks into the list and display added task when done.
     * @param task The task to be added.
     */
    public void add(String task) {
        taskList[count] = task;
        count++;
        System.out.println("added: " + task + "\n");
    }

    /**
     * Print the list.
     */
    public void print() {
        if (count == 0) {
            System.out.println("There are no items in the list.\n");
        }
        for(int i = 0; i < count; i++) {
            String toPrint = String.format("%d. %s",i+1, taskList[i]);
            System.out.println(toPrint + "\n");
        }
    }
}
