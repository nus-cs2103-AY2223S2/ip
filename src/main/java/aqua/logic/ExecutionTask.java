package aqua.logic;

import aqua.exception.IllegalSyntaxException;
import aqua.exception.ProcedureExecutionException;
import aqua.manager.LogicManager;

import javafx.concurrent.Task;


/**
 * An implementation of {@link Task} that can be executed to produce a result
 * and from that a String result message to be displayed. The result message is
 * retrieved by {@link #valueProperty()} instead of {@link #messageProperty()}.
 *
 * @param <T> the return type of the result after execution.
 */
public abstract class ExecutionTask<T> extends Task<String> {
    /** The arguments to work on. */
    private final ArgumentMap args;
    /** The AppManager to work on. */
    private final LogicManager manager;


    /**
     * Constructs an ExecutionTask with the given parameters.
     *
     * @param args - the argument map to work on.
     * @param manager - the AppManager to work on.
     */
    public ExecutionTask(ArgumentMap args, LogicManager manager) {
        this.args = args;
        this.manager = manager;
    }


    /**
     * Processes the arguments and execute the task to produce a result.
     *
     * @param args - the argument to process.
     * @param manager - the AppManager to work on.
     * @return the result of the execution.
     * @throws IllegalSyntaxException if the argument given contains invalid
     *      syntax.
     * @throws ProcedureExecutionException - if the task failed to execute
     *      completely.
     */
    public abstract T process(ArgumentMap args, LogicManager manager)
            throws IllegalSyntaxException, ProcedureExecutionException;

    /**
     * Returns the message of the execution of the task.
     *
     * @param data - the data produced after execution of the task.
     * @param manager - the AppManager to pull additional data from.
     */
    public abstract String getDataDisplay(T data, LogicManager manager);


    /**
     * Executes the task on the same thread where this method is called.
     *
     * @return the String result message to be displayed.
     * @throws IllegalSyntaxException if the argument given contains invalid
     *      syntax.
     * @throws ProcedureExecutionException if the task failed to execute
     *      completely.
     */
    public String execute() throws IllegalSyntaxException, ProcedureExecutionException {
        T data = process(args, manager);
        return getDataDisplay(data, manager);
    }


    /**
     * {@inheritDoc}
     *
     * @return the String result message to be displayed.
     * @throws IllegalSyntaxException if the argument given contains invalid
     *      syntax.
     * @throws ProcedureExecutionException if the task failed to execute
     *      completely.
     */
    @Override
    protected String call() throws IllegalSyntaxException, ProcedureExecutionException {
        return execute();
    }
}
