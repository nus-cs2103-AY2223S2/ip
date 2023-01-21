package aqua;

import java.util.Scanner;

import aqua.logic.CommandLineInput;
import aqua.logic.ExecutionDispatcher;
import aqua.manager.AppManager;


public class Aqua {
    private static String SEPARATOR =
            "____________________________________________________________";

    private final AppManager manager = new AppManager();


    public static void main(String[] args) {
        new Aqua().start();
    }


    private void start() {
        System.out.println(formatMessage(manager.getReplyFormatManager().getGreeting()));
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
            replyError(ex);
            return;
        }
        initiateDispatcher(commandInput.getDispatcher(manager));
    }


    private void initiateDispatcher(ExecutionDispatcher dispatcher) {
        try {
            replyMessage(dispatcher.dispatch());
        } catch (Throwable ex) {
            replyError(ex);
        }
        dispatcher.followUpDispatcher().ifPresent(this::initiateDispatcher);
    }


    private void replyMessage(String msg) {
        System.out.println(formatMessage(msg));
    }


    private void replyError(Throwable ex) {
        System.out.println(formatMessage(manager.getReplyFormatManager().getExceptionReply(ex)));
    }


    private String formatMessage(String msg) {
        StringBuilder builder = new StringBuilder();
        try (Scanner scanner = new Scanner(msg)) {
            while (scanner.hasNextLine()) {
                builder.append(String.format("\t  %s\n", scanner.nextLine()));
            }
        }
        return String.format("\t%s\n%s\t%s\n",
            SEPARATOR, builder.toString(), SEPARATOR
        );
    }
}
