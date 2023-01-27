package aqua.logic;

import aqua.manager.LogicManager;
import aqua.manager.IoManager;


/**
 * The executor of ExecutionService.
 */
public class Executor {
    private final LogicManager manager;
    private final IoManager uiManager;


    public Executor(LogicManager manager, IoManager uiManager) {
        this.manager = manager;
        this.uiManager = uiManager;
    }


    public void processInput() {
        // get input string
        String input = uiManager.readLine();

        // form command input
        CommandLineInput commandInput;
        try {
            commandInput = manager.getInputParser().parse(input);
        } catch (Throwable ex) {
            uiManager.replyException(ex);
            return;
        }

        // start service
        initiateService(commandInput.getDispatcher(manager));
    }


    private void initiateService(ExecutionService service) {
        service.setOnSucceeded(s -> handleExecutionSuccess(service));
        service.setOnFailed(f -> handleExecutionFailure(service));
        service.start();
    }


    private void handleExecutionSuccess(ExecutionService service) {
        uiManager.reply(service.getValue());
        service.followUpDispatcher().ifPresent(this::initiateService);
    }


    private void handleExecutionFailure(ExecutionService service) {
        uiManager.replyException(service.getException());
    }
}
