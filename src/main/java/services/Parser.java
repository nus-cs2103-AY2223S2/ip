package services;

import types.ICommand;

import java.util.ArrayList;
import java.util.Objects;

public final class Parser {
    private final ArrayList<ICommand> handlerRegistry = new ArrayList<>();
    private final ArrayList<ICommand> errorRegistry = new ArrayList<>();
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

    public void registerError(ICommand c) {
        this.errorRegistry.add(c);
    }

    @SuppressWarnings("unused")
    public void registerError(Class<ICommand> c) {
        this.errorRegistry.add(CommandHelper.getObject(c));
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
        if (Objects.equals(expr, "")) return;

        if (CommandHelper.checkAndRun(exitHandler, expr)) {
            toExit.run();
            return;
        }

        if (CommandHelper.checkAndRun(handlerRegistry, expr) ||
            CommandHelper.checkAndRun(errorRegistry, expr)) {
            return;
        }

        this.defaultHandler.take(expr);
    }
}
