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
    private SpeakerRegistry speakerRegistry;
    private Runnable toExit;

    public void registerCommand(IHandler c) {
        handlerRegistry.add(c);
    }

    public void setSpeakerRegistry(SpeakerRegistry speakerRegistry) {
        this.speakerRegistry = speakerRegistry;
    }

    public void registerCommand(Class<IHandler> c) {
        handlerRegistry.add(CommandHelper.getObject(c));
    }

    public void registerError(IHandler c) {
        errorRegistry.add(c);
    }

    public void registerError(Class<IHandler> c) {
        errorRegistry.add(CommandHelper.getObject(c));
    }

    public void setDefaultHandler(Class<IHandler> c) {
        defaultHandler = CommandHelper.getObject(c);
    }

    public void setDefaultHandler(IHandler c) {
        defaultHandler = c;
    }

    public void setExitHandler(Class<IHandler> c) {
        exitHandler = CommandHelper.getObject(c);
    }

    public void setExitHandler(IHandler c) {
        exitHandler = c;
    }

    public void setToExit(Runnable toExit) {
        this.toExit = toExit;
    }

    public void handle(String expr) {
        if (Objects.equals(expr, ""))
            return;

        if (CommandHelper.checkAndRun(speakerRegistry, exitHandler, expr)) {
            toExit.run();
            return;
        }

        if (CommandHelper.checkAndRun(speakerRegistry, handlerRegistry, expr) ||
                CommandHelper.checkAndRun(speakerRegistry, errorRegistry, expr)) {
            return;
        }

        speakerRegistry.broadcast(defaultHandler.take(expr));
    }
}
