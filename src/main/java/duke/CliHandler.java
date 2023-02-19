package duke;

import java.util.Scanner;

public class CliHandler {
    private static final Scanner sc = new Scanner(System.in);
    
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private static final String SEPARATOR = "____________________________________________________________";
    private CommandHandler commandHandler;
    private Parser parser;
    CliHandler() {
        this.commandHandler = new CommandHandler();
        this.parser = new Parser();
    }

    /**
     * Shows Duke's starting dialogue.
     */
    public void showStartingDialogue() {
        System.out.println(LOGO + "\n");
        System.out.println(SEPARATOR);
        System.out.println("Duke: ");
        System.out.println("Hello! I'm DUKE!\nWhat can I do for you?");
        System.out.println(SEPARATOR);
    }

    /**
     * Responsible for taking in user inputs and passing them to the commandHandler.
     * @param tasks A TaskList of the current tasks in Duke.
     * @return true if Duke should continue running, false otherwise.
     */
    public boolean run(TaskList tasks) {
        boolean isRunning = true;
        System.out.println("You: ");
        String input = sc.nextLine();
        Command command = this.parser.parseCommand(input);
        System.out.println(SEPARATOR);
        System.out.println("Duke: ");
        
        String response = "";
        //TODO: Refactor Duke CLI to work with the new Ui interface
        //response = this.commandHandler.handleCommand(command, tasks);

        System.out.print(response);
        System.out.println(SEPARATOR);
        
        if (this.commandHandler.isByeCommand(command)) {
            isRunning = false;
        }
        
        return isRunning;
    }
    
    
}
