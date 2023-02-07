package aqua.logic;

import aqua.exception.ProcedureException;
import aqua.exception.SyntaxException;
import aqua.manager.IoManager;
import aqua.manager.LogicManager;


/**
 * An {@code ExecutionTask} that will display the result of the execution.
 *
 * @param <T> the return type of the result after execution.
 */
public abstract class ExecutionDisplayerTask<T> extends ExecutionTask<T> {
    private final IoManager ioManager;


    /**
     * Constructs an {@code ExecutionDisplayerTask} from the given parameters.
     *
     * @param args - the arguments to work on.
     * @param logicManager - the logic manager to work on.
     * @param ioManager - the {@code IoManager} to display through.
     */
    public ExecutionDisplayerTask(ArgumentMap args, LogicManager logicManager, IoManager ioManager) {
        super(args, logicManager);
        this.ioManager = ioManager;
    }


    /**
     * Displays the result of the execution.
     *
     * @param data - the result of the execution.
     * @param manager - the {@code IoManager} to display through.
     */
    protected abstract void display(T data, IoManager manager);


    @Override
    protected Void call() throws SyntaxException, ProcedureException {
        T data = process();
        display(data, ioManager);
        return null;
    }
}
