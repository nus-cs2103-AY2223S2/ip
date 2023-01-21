package aqua.logic;

import aqua.exception.IllegalSyntaxException;
import aqua.exception.ProcedureExecutionException;
import aqua.manager.AppManager;


public abstract class ExecutionTask<T> {
    private final ArgumentMap args;
    private final AppManager manager;


    public ExecutionTask(ArgumentMap args, AppManager manager) {
        this.args = args;
        this.manager= manager;
    }


    public abstract T process(ArgumentMap args, AppManager manager)
            throws IllegalSyntaxException, ProcedureExecutionException;

    public abstract String getDataDisplay(T data, AppManager manager);


    public String execute() throws IllegalSyntaxException, ProcedureExecutionException {
        T data = process(args, manager);
        return getDataDisplay(data, manager);
    }
}
