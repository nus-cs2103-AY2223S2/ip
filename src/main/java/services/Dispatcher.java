package services;

import types.IHandler;
import utilities.CommandHelper;

import java.util.ArrayList;
import java.util.Objects;

public final class Dispatcher {
    private final ArrayList<IHandler> handlerRegistry = new ArrayList<>();
    private final ArrayList<IHandler> errorRegistry = new ArrayList<>();
    private IHandler defaultHandler;
    private IHandler exitHandler;
    private Runnable toExit;

    public void registerCommand(IHandler c) {
        this.handlerRegistry.add(c);
    }

    public void registerCommand(Class<IHandler> c) {
        this.handlerRegistry.add(CommandHelper.getObject(c));
    }

    public void registerError(IHandler c) {
        this.errorRegistry.add(c);
    }

    public void registerError(Class<IHandler> c) {
        this.errorRegistry.add(CommandHelper.getObject(c));
    }

    public void setDefaultHandler(Class<IHandler> c) {
        this.defaultHandler = CommandHelper.getObject(c);
    }

    public void setDefaultHandler(IHandler c) {
        this.defaultHandler = c;
    }

    public void setExitHandler(Class<IHandler> c) {
        this.exitHandler = CommandHelper.getObject(c);
    }

    public void setExitHandler(IHandler c) {
        this.exitHandler = c;
    }

    public void setToExit(Runnable toExit) {
        this.toExit = toExit;
    }

    public void handle(String expr) {
        if (Objects.equals(expr, ""))
            return;

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
