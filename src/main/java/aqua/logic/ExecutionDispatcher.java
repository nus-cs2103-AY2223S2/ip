package aqua.logic;

import java.util.Optional;

import aqua.exception.IllegalSyntaxException;
import aqua.exception.ProcedureExecutionException;


/**
 * Dispatcher that dispatches the execution of ExecutionTasks.
 */
public abstract class ExecutionDispatcher {
    /** The task to dispatch. */
    private final ExecutionTask<?> task;


    private ExecutionDispatcher(ExecutionTask<?> task) {
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
    public static ExecutionDispatcher of(ExecutionTask<?> ... tasks) {
        ExecutionDispatcher startingDispatcher = null;
        for (int i = 0; i < tasks.length; i++) {
            ExecutionTask<?> task = tasks[tasks.length - i - 1];
            startingDispatcher = ExecutionDispatcher.of(task).setFollowUp(startingDispatcher);
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
    public static ExecutionDispatcher of(ExecutionTask<?> task) {
        return new ExecutionDispatcher(task) {
            @Override
            public Optional<ExecutionDispatcher> followUpDispatcher() {
                return Optional.empty();
            }
        };
    }


    /**
     * Dispatches and executes the task.
     * 
     * @throws IllegalSyntaxException if there are syntax errors.
     * @throws ProcedureExecutionExecution if the task fail to execute
     *      completely.
     */
    public String dispatch() throws IllegalSyntaxException, ProcedureExecutionException {
        return task.execute();
    }


    /**
     * Sets the follow up dispatcher.
     * 
     * @param dispatcher - the follow up dispatcher to set to.
     * @return a dispatcher of this dispatcher with its follow up task set to
     *      the dispatcher specified.
     */
    public ExecutionDispatcher setFollowUp(ExecutionDispatcher dispatcher) {
        return new ExecutionDispatcher(this.task) {
            @Override
            public Optional<ExecutionDispatcher> followUpDispatcher() {
                return Optional.ofNullable(dispatcher);
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
    public abstract Optional<ExecutionDispatcher> followUpDispatcher();
}
