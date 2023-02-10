package aqua.logic;

import java.util.Optional;

import aqua.exception.ProcedureException;
import aqua.exception.SyntaxException;
import javafx.concurrent.Service;


/**
 * Implementation of {@link Service} to handle the execution of its
 * encapsulated {@link ExecutionTask}.
 */
public abstract class ExecutionService extends Service<Void> {
    private final ExecutionTask<?> task;


    private ExecutionService(ExecutionTask<?> task) {
        this.task = task;
    }


    /**
     * Creates a {@code ExecutionService}.
     *
     * @param task - the task to execute.
     * @return a service to execute the given task.
     */
    public static ExecutionService of(ExecutionTask<?> task) {
        return new ExecutionService(task) {
            @Override
            public Optional<ExecutionService> getNextService() {
                return Optional.empty();
            }
        };
    }


    /**
     * Returns the follow up service, that should be started if the task
     * of this service is started and executed successfully. Return result is
     * wrapped in an {@code Optinoal}. If there are no follow services,
     * {@code Optional.empty} is returned.
     *
     * @return the follow up service wrapped in an {@code Optional}.
     */
    public abstract Optional<ExecutionService> getNextService();


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
     * Sets the follow up service.
     *
     * @param service - the follow up service to set to.
     * @return this service when its follow up service is set as specified.
     */
    public ExecutionService setFollowUp(ExecutionService service) {
        return new ExecutionService(this.task) {
            @Override
            public Optional<ExecutionService> getNextService() {
                return Optional.ofNullable(service);
            }
        };
    }
}
