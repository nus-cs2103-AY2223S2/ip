package aqua;

import java.util.Scanner;

import aqua.exception.LoadException;
import aqua.logic.CommandLineInput;
import aqua.logic.ExecutionDispatcher;
import aqua.logic.command.ListCommand;
import aqua.manager.AppManager;
import aqua.storage.Loader;


public class Aqua {
    private final AppManager manager = new AppManager();


    public static void main(String[] args) {
        new Aqua().start();
    }


    private void start() {
        manager.getUiManager().greet();
        try {
            Loader.load(manager.getTaskManager().getSavePath(), manager);
            manager.getUiManager().replyLoadSuccess();
            initiateDispatcher(new ListCommand().getDispatcher(null, manager));
        } catch (LoadException loadEx) {
            manager.getUiManager().replyException(loadEx);
        }
        try (Scanner scanner = new Scanner(System.in)) {
            while (!manager.isClosed()) {
                String input = scanner.nextLine();
                processInput(input);
            }
        }
    }


    private void processInput(String input) {
        CommandLineInput commandInput;
        try {
            commandInput = manager.getInputParser().parse(input);
        } catch (Throwable ex) {
            manager.getUiManager().replyException(ex);
            return;
        }
        initiateDispatcher(commandInput.getDispatcher(manager));
    }


    private void initiateDispatcher(ExecutionDispatcher dispatcher) {
        try {
            manager.getUiManager().reply(dispatcher.dispatch());
        } catch (Throwable ex) {
            manager.getUiManager().replyException(ex);
            return;
        }
        dispatcher.followUpDispatcher().ifPresent(this::initiateDispatcher);
    }
}
