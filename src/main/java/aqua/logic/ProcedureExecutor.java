package aqua.logic;

import aqua.exception.IllegalSyntaxException;
import aqua.exception.ProcedureExecutionException;
import aqua.manager.AppManager;


public interface ProcedureExecutor<T> {
    public T process(ArgumentMap args, AppManager manager)
            throws IllegalSyntaxException, ProcedureExecutionException;
}
