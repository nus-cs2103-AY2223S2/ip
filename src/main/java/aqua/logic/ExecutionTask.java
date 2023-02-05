package aqua.logic;

import aqua.exception.SyntaxException;
import aqua.exception.ProcedureException;
import aqua.manager.LogicManager;
import javafx.concurrent.Task;


/**
 * An implementation of {@link Task} that can be executed to produce a result
 * and from that a String result message to be displayed. The result message is
 * retrieved by {@link #valueProperty()} instead of {@link #messageProperty()}.
 *
 * @param <T> the return type of the result after execution.
 */
public abstract class ExecutionTask<T> extends Task<Void> {
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
     * @throws SyntaxException if the argument given contains invalid
     *      syntax.
     * @throws ProcedureException - if the task failed to execute
     *      completely.
     */
    protected abstract T process(ArgumentMap args, LogicManager manager)
            throws SyntaxException, ProcedureException;


    /**
     * Executes the task process.
     *
     * @return the output of the execution.
     * @throws SyntaxException if the argument given contains invalid
     *      syntax.
     * @throws ProcedureException if the task failed to execute
     *      completely.
     */
    public T process() throws SyntaxException, ProcedureException {
        return process(args, manager);
    }


    /**
     * {@inheritDoc}
     *
     * @return the String result message to be displayed.
     * @throws SyntaxException if the argument given contains invalid
     *      syntax.
     * @throws ProcedureException if the task failed to execute
     *      completely.
     */
    @Override
    protected Void call() throws SyntaxException, ProcedureException {
        process();
        return null;
    }
}
