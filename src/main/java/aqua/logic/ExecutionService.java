package aqua.logic;

import java.util.Optional;

import aqua.exception.SyntaxException;
import aqua.exception.ProcedureException;
import javafx.concurrent.Service;


/**
 * Implementation of {@link Service} to handle the execution of its
 * encapsulated {@link ExecutionTask}.
 */
public abstract class ExecutionService extends Service<Void> {
    /** The task to dispatch. */
    private final ExecutionTask<?> task;


    private ExecutionService(ExecutionTask<?> task) {
        this.task = task;
    }


    /**
     * Creates dispatchers that dispatches the given tasks in the given
     * order. The follow up tasks of the dispatchers is set to the
     * dispatcher created from the next task in the given tasks.
     *
     * @param tasks - the tasks to dispatch.
     * @return a ExecutionDispatcher that dispatches the given tasks in
     *      the given order.
     */
    public static ExecutionService of(ExecutionTask<?> ... tasks) {
        ExecutionService startingDispatcher = null;
        for (int i = 0; i < tasks.length; i++) {
            ExecutionTask<?> task = tasks[tasks.length - i - 1];
            startingDispatcher = ExecutionService.of(task).setFollowUp(startingDispatcher);
        }
        return startingDispatcher;
    }


    /**
     * Creates a dispatcher that will dispatch and execute the specified task.
     * Created dispatcher will have no follow up dispatchers.
     *
     * @param task - the task to dispatch and execute.
     * @return a dispatcher that will dispatch the specifed task without follow
     *      up dispatchers.
     */
    public static ExecutionService of(ExecutionTask<?> task) {
        return new ExecutionService(task) {
            @Override
            public Optional<ExecutionService> followUpDispatcher() {
                return Optional.empty();
            }
        };
    }

    /**
     * Returns the follow up dispatcher, that should be dispatched if the task
     * of this dispatcher is dispatched and executed successfully, wrapped in
     * an {@code Optinoal}. If there is no follow dispatcher,
     * {@code Optional.empty} is returned.
     *
     * @return the follow up dispatcher wrapped around an {@code Optional}.
     */
    public abstract Optional<ExecutionService> followUpDispatcher();


    /**
     * Executes the encapsulated task process.
     *
     * @throws SyntaxException if there are syntax errors.
     * @throws ProcedureExecutionExecution if the task fail to execute
     *      completely.
     */
    public void process() throws SyntaxException, ProcedureException {
        task.process();
    }


    @Override
    protected ExecutionTask<?> createTask() {
        return task;
    }


    /**
     * Sets the follow up dispatcher.
     *
     * @param dispatcher - the follow up dispatcher to set to.
     * @return a dispatcher of this dispatcher with its follow up task set to
     *      the dispatcher specified.
     */
    public ExecutionService setFollowUp(ExecutionService dispatcher) {
        return new ExecutionService(this.task) {
            @Override
            public Optional<ExecutionService> followUpDispatcher() {
                return Optional.ofNullable(dispatcher);
            }
        };
    }
}
