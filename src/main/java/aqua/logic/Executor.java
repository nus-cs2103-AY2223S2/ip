package aqua.logic;

import aqua.manager.AppManager;


/**
 * The executor of ExecutionService.
 */
public class Executor {
    private final AppManager manager;


    public Executor(AppManager manager) {
        this.manager = manager;
    }


    public void processInput(String input) {
        CommandLineInput commandInput;
        try {
            commandInput = manager.getInputParser().parse(input);
        } catch (Throwable ex) {
            manager.getUiManager().replyException(ex);
            return;
        }
        initiateService(commandInput.getDispatcher(manager));
    }


    private void initiateService(ExecutionService service) {
        service.setOnSucceeded(s -> handleExecutionSuccess(service));
        service.setOnFailed(f -> handleExecutionFailure(service));
        service.start();
    }


    private void handleExecutionSuccess(ExecutionService service) {
        manager.getUiManager().reply(service.getValue());
        service.followUpDispatcher().ifPresent(this::initiateService);
    }


    private void handleExecutionFailure(ExecutionService service) {
        manager.getUiManager().replyException(service.getException());
    }
}
