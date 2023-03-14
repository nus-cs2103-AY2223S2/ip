package Duke.Tasks;

public abstract class Task {

    public String task_name;
    public boolean status;
    public Task(String str) {
        task_name = str;
        status = false;
    }

    /**
     * Changes status of work done to true.
     */
    public void toggleTrue() {
        status = true;
    }

    /**
     * Changes status of work done to false.
     */
    public void toggleFalse() {
        status = false;
    }
}
