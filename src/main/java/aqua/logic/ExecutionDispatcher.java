package aqua.logic;

import java.util.Optional;

import aqua.exception.IllegalSyntaxException;
import aqua.exception.ProcedureExecutionException;


public abstract class ExecutionDispatcher {
    private final ExecutionTask<?> task;


    private ExecutionDispatcher(ExecutionTask<?> task) {
        this.task = task;
    }


    public static ExecutionDispatcher of(ExecutionTask<?> ... tasks) {
        ExecutionDispatcher startingDispatcher = null;
        for (int i = 0; i < tasks.length; i++) {
            ExecutionTask<?> task = tasks[tasks.length - i - 1];
            startingDispatcher = ExecutionDispatcher.of(task).setFollowUp(startingDispatcher);
        }
        return startingDispatcher;
    }


    public static ExecutionDispatcher of(ExecutionTask<?> task) {
        return new ExecutionDispatcher(task) {
            @Override
            public Optional<ExecutionDispatcher> followUpDispatcher() {
                return Optional.empty();
            }
        };
    }


    public String dispatch() throws IllegalSyntaxException, ProcedureExecutionException {
        return task.execute();
    }


    public ExecutionDispatcher setFollowUp(ExecutionDispatcher dispatcher) {
        return new ExecutionDispatcher(this.task) {
            @Override
            public Optional<ExecutionDispatcher> followUpDispatcher() {
                return Optional.ofNullable(dispatcher);
            }
        };
    }

    
    public abstract Optional<ExecutionDispatcher> followUpDispatcher();
}
