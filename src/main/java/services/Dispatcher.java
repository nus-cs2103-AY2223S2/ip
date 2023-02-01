package services;

import java.util.ArrayList;
import java.util.Objects;

import types.IHandler;
import utilities.CommandHelper;

/**
 * A singleton class to execute all handlers.
 */
public final class Dispatcher {
    private final ArrayList<IHandler> handlerRegistry = new ArrayList<>();
    private final ArrayList<IHandler> errorRegistry = new ArrayList<>();
    private IHandler defaultHandler;
    private IHandler exitHandler;
    private SpeakerRegistry speakerRegistry;
    private Runnable toExit;

    /**
     * Adds a new command.
     * @param c Command to add.
     */
    public void registerCommand(IHandler c) {
        handlerRegistry.add(c);
    }

    /**
     * Sets the SpeakerRegistry.
     * @param speakerRegistry The SpeakerRegistry.
     */
    public void setSpeakerRegistry(SpeakerRegistry speakerRegistry) {
        this.speakerRegistry = speakerRegistry;
    }

    /**
     * Adds a new error.
     * @param c Error to add.
     */
    public void registerError(IHandler c) {
        errorRegistry.add(c);
    }

    /**
     * Sets the fallback handler. All unrecognized user input goes there.
     * Should take any string.
     * @param c The default handler.
     */
    public void setDefaultHandler(IHandler c) {
        defaultHandler = c;
    }

    /**
     * Sets the handler for exit. Will trigger exit of the program
     * once matches.
     * @param c The exit handler.
     */
    public void setExitHandler(IHandler c) {
        exitHandler = c;
    }

    /**
     * The lambda expression from driver class to indicate exit of the entire program.
     * @param toExit The lambda to trigger program exit.
     */
    public void setToExit(Runnable toExit) {
        this.toExit = toExit;
    }

    /**
     * Takes any user input and takes action accordingly.
     * @param expr User input to consider.
     */
    public void handle(String expr) {
        if (Objects.equals(expr, "")) {
            return;
        }

        if (CommandHelper.checkAndRun(speakerRegistry, exitHandler, expr)) {
            toExit.run();
            return;
        }

        if (CommandHelper.checkAndRun(speakerRegistry, handlerRegistry, expr)
                || CommandHelper.checkAndRun(speakerRegistry, errorRegistry, expr)) {
            return;
        }

        speakerRegistry.broadcast(defaultHandler.take(expr));
    }
}
