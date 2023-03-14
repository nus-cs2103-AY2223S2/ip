package treebot.tasks;


import treebot.exception.TaskFactoryException;
import treebot.props.DeadlineProps;
import treebot.props.EventProps;
import treebot.props.TodoProps;


/**
 * Represents a factory that makes <code>Task</code> objects according to parameters.
 */
public class TaskFactory {

    /**
     * Returns a Task according to the given instruction.
     * Parses the instruction to determine which type of Command to return.
     *
     * @param
     * @return Task object as specified by the instruction.
     * @throws TaskFactoryException
     */


    public static Todo makeTodo(TodoProps props) {
        return new Todo(props.getTaskDescription());

    }

    public static Deadline makeDeadline(DeadlineProps props) {
        return new Deadline(props.getTaskDescription(), props.getDeadline());

    }

    public static Event makeEvent(EventProps props) {
        return new Event(props.getTaskDescription(), props.getStartDate(), props.getEndDate());
    }





}
