package services;

import types.ICommand;

import java.util.ArrayList;

public class Parser {
    private final ArrayList<ICommand> handlerRegistry = new ArrayList<>();
    private ICommand defaultHandler;
    private ICommand exitHandler;
    private Runnable toExit;

    @SuppressWarnings("unused")
    public void registerCommand(ICommand c) {
        this.handlerRegistry.add(c);
    }

    @SuppressWarnings("unused")
    public void registerCommand(Class<ICommand> c) {
        this.handlerRegistry.add(CommandHelper.getObject(c));
    }

    @SuppressWarnings("unused")
    public void setDefaultHandler(Class<ICommand> c) {
        this.defaultHandler = CommandHelper.getObject(c);
    }

    public void setDefaultHandler(ICommand c) {
        this.defaultHandler = c;
    }

    public void setExitHandler(Class<ICommand> c) {
        this.exitHandler = CommandHelper.getObject(c);
    }

    @SuppressWarnings("unused")
    public void setExitHandler(ICommand c) {
        this.exitHandler = c;
    }

    public void setToExit(Runnable toExit) {
        this.toExit = toExit;
    }

    public void handle(String expr) {
        if (this.exitHandler.canTake(expr)) {
            this.exitHandler.take(expr);
            toExit.run();
            return;
        }

        for (ICommand c : handlerRegistry) {
            if (c.canTake(expr)) {
                c.take(expr);
                return;
            }
        }

        this.defaultHandler.take(expr);
    }
}
