package red.task;

/**
 * This class is for tasks that do not have a timeframe associated with it.
 */
public class ToDoTask extends Task{

    /**
     * The constructor of the ToDoTask that takes in description of the task.
     */
    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Compares this object to the specified object.
     *
     * @param obj the object to compare with.
     * @return true if the objects are the same; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ToDoTask)) {
            return false;
        }
        ToDoTask checkedObj = (ToDoTask) obj;
        boolean isSameDescription = this.description.equals(checkedObj.description);

        if (isSameDescription) {
            return true;
        }

        return false;
    }
    public static void main(String[] args) {
        ToDoTask arg = new ToDoTask("balls");
        ToDoTask argg = new ToDoTask("balls");
        System.out.println(arg.equals(argg));

    }
}
