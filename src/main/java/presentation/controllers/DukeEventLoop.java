package presentation.controllers;

import core.singletons.Singletons;
import domain.entities.core.*;
import domain.usecases.ByeUsecase;
import domain.usecases.TaskManagerUsecase;
import domain.usecases.UnknownCommandUsecase;
import presentation.ui.SystemErr;
import presentation.ui.SystemIn;

import java.util.Scanner;

/**
 * The event loop for managing Duke.
 */
public class DukeEventLoop extends EventLoop {
    private DukeEventLoop(Executable rootExecutable, StringReadable reader,
                          Writable errorWriter) {
        super(rootExecutable, reader, errorWriter);
    }

    public static DukeEventLoop createEventLoop() {
        // creates a new system in instance. We want to make sure that
        // the scanner is not closed for each event loop, because scanners
        // are managed by the event loops.
        final StringReadable reader = new SystemIn();
        Writable errorWriter = Singletons.get(SystemErr.class);
        final NestableExecutableObject executable =
                new NestableExecutableObject(errorWriter);
        final ByeUsecase bye = Singletons.get(ByeUsecase.class);
        bye.register(executable);
        final TaskManagerUsecase manager =
                Singletons.get(TaskManagerUsecase.class);
        manager.register(executable);
        final UnknownCommandUsecase unknown =
                Singletons.get(UnknownCommandUsecase.class);
        unknown.register(executable);
        return new DukeEventLoop(executable, reader, errorWriter);
    }
}
