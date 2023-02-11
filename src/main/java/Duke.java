import java.time.format.DateTimeFormatter;

import core.exceptions.LoadException;
import core.exceptions.WriteException;
import core.singletons.Singletons;
import core.utils.TokenUtilities;
import data.DataLoaderImpl;
import data.DataSaverImpl;
import domain.entities.DataLoader;
import domain.entities.DataSaver;
import domain.entities.core.Writable;
import domain.usecases.ByeUsecase;
import domain.usecases.EchoUsecase;
import domain.usecases.TaskManagerUsecase;
import domain.usecases.UnknownCommandUsecase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import presentation.controllers.DukeEventLoop;
import presentation.gui.MainWindow;
import presentation.ui.DummyWritable;
import presentation.ui.SystemErr;
import presentation.ui.SystemOut;


/**
 * The main class for Duke.
 */
public class Duke extends Application {
    private static DukeEventLoop initOrCrash(Writable target) {
        try {
            registerSingletons(target);
        } catch (WriteException e) {
            System.err.println("Unable to initialize app.");
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (LoadException e) {
            System.err.println("Unable to initialize app.");
            System.err.println(e.getMessage());
            System.exit(1);
        }
        DukeEventLoop loop = DukeEventLoop.createInitializingLoop();
        loop.run();
        return DukeEventLoop.createEventLoop(target);
    }

    /**
     * This would register the singletons that we would be using later on.
     */
    private static void registerSingletons(Writable target) throws WriteException,
            LoadException {
        Singletons.registerSingleton(DateTimeFormatter.class,
                DateTimeFormatter.ofPattern("MMM d yyyy"));
        // Persistence related
        final String fileName = "duke.txt";
        final DataLoader dataLoader = new DataLoaderImpl(fileName);
        final DataSaver dataSaver = new DataSaverImpl(fileName);
        Singletons.registerSingleton(DataSaver.class, dataSaver);
        Singletons.registerSingleton(DataLoader.class, dataLoader);

        // Output related
        Singletons.registerLazySingleton(SystemErr.class, SystemErr::new);
        Singletons.registerLazySingleton(SystemOut.class, SystemOut::new);
        Singletons.registerLazySingleton(DummyWritable.class, DummyWritable::new);

        // Use cases
        Singletons.registerLazySingleton(
                ByeUsecase.class, () -> new ByeUsecase(target)
        );
        Singletons.registerLazySingleton(
                EchoUsecase.class, () -> new EchoUsecase(target)
        );
        Singletons.registerLazySingleton(
                TaskManagerUsecase.class, () -> new TaskManagerUsecase(
                        Singletons.get(DummyWritable.class),
                        target,
                        Singletons.get(DataSaver.class))
        );
        Singletons.registerLazySingleton(
                UnknownCommandUsecase.class, () -> new UnknownCommandUsecase(target)
        );
        Singletons.registerLazySingleton(
                TokenUtilities.class,
                TokenUtilities::new
        );
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            final FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource(
                    "/presentation/gui/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            primaryStage.setScene(scene);
            MainWindow window = fxmlLoader.getController();
            final DukeEventLoop loop = initOrCrash(window);
            window.setDukeEventLoop(loop);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
