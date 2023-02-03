package duke;

/**
 * Represents a Task, storing the task name, and its string as was inputted
 * for data storage convenience.
 */
public class Task {
    private String name;
    private String inpString;
    private boolean isDone = false;

    public Task(String name, String inpString){
        this.name = name;
        this.inpString = inpString;
    }

    /**
     * Mark a Task as done, and output the task for the user to see.
     */
    public void markDone(){
        isDone = true;
        this.printTask();
    }

    /**
     * Mark a Task as undone, and output the task for the user to see.
     */
    public void markUndone() {
        isDone = false;
        this.printTask();
    }

    /**
     * Output the Task for user to see.
     */
    public void printTask(){
        if (isDone){
            System.out.print("[X] " + name);
        } else {
            System.out.print("[ ] " + name);
        }
    }

    public boolean contains(String subS){
        return name.contains(subS);
    }

    @Override
    public String toString(){
        if (isDone){
            return this.inpString + "@" + "1\n";
        } else {
            return this.inpString + "@" + "0\n";
        }
    }

}
