import core.singletons.Singletons;
import core.utils.TokenUtilities;
import domain.entities.core.Writable;
import domain.usecases.ByeUsecase;
import domain.usecases.EchoUsecase;
import domain.usecases.TaskManagerUsecase;
import domain.usecases.UnknownCommandUsecase;
import presentation.controllers.DukeEventLoop;
import presentation.ui.SystemErr;
import presentation.ui.SystemOut;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        configureInjections();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        final DukeEventLoop eventLoop = DukeEventLoop.createEventLoop();
        eventLoop.run();
    }

    /**
     * This would register the singletons that we would be using later on.
     */
    private static void configureInjections() {
        Singletons.registerLazySingleton(SystemErr.class, SystemErr::new);
        Singletons.registerLazySingleton(SystemOut.class, SystemOut::new);
        Singletons.registerLazySingleton(ByeUsecase.class,
                () -> new ByeUsecase(Singletons.get(SystemOut.class)));
        Singletons.registerLazySingleton(EchoUsecase.class,
                () -> new EchoUsecase(Singletons.get(SystemOut.class)));
        Singletons.registerLazySingleton(TaskManagerUsecase.class,
                () -> new TaskManagerUsecase(Singletons.get(SystemOut.class)));
        Singletons.registerLazySingleton(UnknownCommandUsecase.class,
                () -> new UnknownCommandUsecase(Singletons.get(SystemErr.class)));
        Singletons.registerLazySingleton(TokenUtilities.class,
                TokenUtilities::new);
    }
}
