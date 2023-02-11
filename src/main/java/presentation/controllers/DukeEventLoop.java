package presentation.controllers;

import core.singletons.Singletons;
import domain.entities.DataLoader;
import domain.entities.core.Commandable;
import domain.entities.core.EventLoop;
import domain.entities.core.NestedCommandableObject;
import domain.entities.core.StringReadable;
import domain.entities.core.Writable;
import domain.usecases.ByeUsecase;
import domain.usecases.TaskManagerUsecase;
import domain.usecases.UnknownCommandUsecase;
import presentation.ui.SystemErr;
import presentation.ui.SystemIn;

/**
 * The event loop for managing Duke.
 * <p>
 * To initialize and facilitate reading data from persistence, use the {@link
 * #createInitializingLoop()} method.
 * <p>
 * To create the actual main event loop, use the {@link #createEventLoop(Writable)} ()}
 * method.
 */
public class DukeEventLoop extends EventLoop {
    /**
     * Creates a {@link DukeEventLoop} instance.
     *
     * @param rootCommandable the root executable that this event loop would
     *                        iterate over and over again.
     * @param reader          the reader that this event loop would read from.
     * @param errorWriter     the error writer that this event loop would write
     *                        errors to.
     */
    private DukeEventLoop(Commandable rootCommandable, StringReadable reader,
                          Writable errorWriter) {
        super(rootCommandable, reader, errorWriter);
    }

    /**
     * Creates the main event loop for Duke.
     *
     * @return the main event loop for Duke.
     */
    public static DukeEventLoop createEventLoop(Writable target) {
        // creates a new system in instance. We want to make sure that
        // the scanner is not closed for each event loop, because scanners
        // are managed by the event loops.
        final StringReadable reader = new SystemIn();
        final NestedCommandableObject executable =
                new NestedCommandableObject(target);
        final ByeUsecase bye = Singletons.get(ByeUsecase.class);
        bye.register(executable);
        final TaskManagerUsecase manager =
                Singletons.get(TaskManagerUsecase.class);
        manager.redirectOutput(target);
        manager.redirectErrorOutput(target);
        manager.register(executable);
        final UnknownCommandUsecase unknown =
                Singletons.get(UnknownCommandUsecase.class);
        unknown.register(executable);
        return new DukeEventLoop(executable, reader, target);
    }

    /**
     * Creates the event loop for initializing Duke.
     *
     * @return the event loop for initializing Duke.
     */
    public static DukeEventLoop createInitializingLoop() {
        final StringReadable readable = Singletons.get(DataLoader.class);
        final Writable errorWriter = Singletons.get(SystemErr.class);
        final NestedCommandableObject executable =
                new NestedCommandableObject(errorWriter);
        final TaskManagerUsecase manager =
                Singletons.get(TaskManagerUsecase.class);
        manager.redirectOutput(Singletons.get(SystemErr.class));
        manager.registerReader(executable);
        return new DukeEventLoop(executable, readable, errorWriter);
    }
}
