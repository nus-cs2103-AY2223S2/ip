package utilities;

import containers.FileContainer;
import handlers.ETodoEmptyDescription;
import handlers.JAddTask;
import handlers.JBye;
import handlers.JDeleteTask;
import handlers.JFind;
import handlers.JMarkTask;
import handlers.JShowTaskList;
import handlers.JThrowException;
import services.Dispatcher;
import services.SpeakerRegistry;
import services.TaskList;
import types.ISpeaker;

/**
 * Provides dispatcher on user need.
 */
public class DispatcherProvider {
    public static Dispatcher getDefaultDispatcher(Runnable stopperLambda, ISpeaker ... speakers) {
        Dispatcher dispatcher = new Dispatcher();
        SpeakerRegistry sp = new SpeakerRegistry();
        TaskList ts = new TaskList(FileContainer.ofLocation("./data/tasks.txt", true));
        dispatcher.setSpeakerRegistry(sp);
        for (ISpeaker i : speakers) {
            sp.registerSpeaker(i);
        }
        dispatcher.setDefaultHandler(new JThrowException());
        dispatcher.registerCommand(new JAddTask(ts));
        dispatcher.registerCommand(new JFind(ts));
        dispatcher.registerCommand(new JShowTaskList(ts));
        dispatcher.registerCommand(new JMarkTask(ts));
        dispatcher.registerCommand(new JDeleteTask(ts));
        dispatcher.registerError(new ETodoEmptyDescription());
        dispatcher.setExitHandler(new JBye());
        dispatcher.setToExit(stopperLambda);

        return dispatcher;
    }
}
