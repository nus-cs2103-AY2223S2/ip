package aqua.logic;

import aqua.exception.IllegalSyntaxException;
import aqua.exception.ProcedureExecutionException;
import aqua.manager.IoManager;
import aqua.manager.LogicManager;

public abstract class ExecutionDisplayerTask<T> extends ExecutionTask<T> {
    private final IoManager ioManager;


    public ExecutionDisplayerTask(ArgumentMap args, LogicManager logicManager, IoManager ioManager) {
        super(args, logicManager);
        this.ioManager = ioManager;
    }


    protected abstract void display(T data, IoManager manager);


    @Override
    protected Void call() throws IllegalSyntaxException, ProcedureExecutionException {
        T data = process();
        display(data, ioManager);
        return null;
    }
}
