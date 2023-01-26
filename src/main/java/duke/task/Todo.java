<<<<<<< HEAD:src/main/java/Duke/Task/Todo.java
package Duke.Task;

/**
 * Class to represent Todotask created by user
 */
=======
package duke.task;
>>>>>>> branch-A-CodingStandard:src/main/java/duke/task/Todo.java
public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    /**
     * @return String version of todotask
     */
    @Override
    public String toString() {
        if (super.getStatus()) {
            return String.format("[T][X] %s\n", super.getTaskName());
        } else {
            return String.format("[T][ ] %s\n",super.getTaskName());
        }
    }
}
