import core.exceptions.WriteException;
import core.singletons.Singletons;
import core.utils.TokenUtilities;
import data.DataSaverImpl;
import domain.entities.DataSaver;
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
        try {
            configureInjections();
        } catch (WriteException e) {
            System.err.println("Unable to initialize app.");
            System.err.println(e.getMessage());
            System.exit(1);
        }
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
    private static void configureInjections() throws WriteException  {
        final DataSaver dataSaver = new DataSaverImpl("duke.txt");
        Singletons.registerSingleton(DataSaver.class, dataSaver);
        Singletons.registerLazySingleton(SystemErr.class, SystemErr::new);
        Singletons.registerLazySingleton(SystemOut.class, SystemOut::new);
        Singletons.registerLazySingleton(ByeUsecase.class,
                () -> new ByeUsecase(Singletons.get(SystemOut.class)));
        Singletons.registerLazySingleton(EchoUsecase.class,
                () -> new EchoUsecase(Singletons.get(SystemOut.class)));
        Singletons.registerLazySingleton(TaskManagerUsecase.class,
                () -> new TaskManagerUsecase(Singletons.get(SystemOut.class),
                        Singletons.get(DataSaver.class)));
        Singletons.registerLazySingleton(UnknownCommandUsecase.class,
                () -> new UnknownCommandUsecase(Singletons.get(SystemErr.class)));
        Singletons.registerLazySingleton(TokenUtilities.class,
                TokenUtilities::new);
    }
}
