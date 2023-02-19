package aqua.logic;

import aqua.exception.ProcedureException;
import aqua.exception.SyntaxException;
import aqua.manager.LogicManager;
import javafx.concurrent.Task;


/**
 * An implementation of {@link Task} that defines the task of a command.
 *
 * @param <T> the return type of the result after execution.
 */
public abstract class ExecutionTask<T> extends Task<Void> {
    private final ArgumentMap args;
    private final LogicManager manager;


    /**
     * Constructs an {@code ExecutionTask} with the given parameters.
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
     * @return {@code null} value.
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
