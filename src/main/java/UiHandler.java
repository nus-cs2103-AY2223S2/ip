import java.util.List;
import java.util.Scanner;

public class UiHandler {
    private static final Scanner sc = new Scanner(System.in);
    
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private static final String separator = "____________________________________________________________";
    private CommandHandler commandHandler;
    private Parser parser;
    UiHandler() {
        this.commandHandler = new CommandHandler();
        this.parser = new Parser();
    }
    public void showStartingDialogue() {
        System.out.println(logo + "\n");
        System.out.println(separator);
        System.out.println("Duke: ");
        System.out.println("Hello! I'm DUKE!\nWhat can I do for you?");
        System.out.println(separator);
    }
    
    public boolean run(List<Task> tasks) {
        boolean isRunning = true;
        System.out.println("You: ");
        String input = sc.nextLine();
        Command command = this.parser.parseCommand(input);
        System.out.println(separator);
        System.out.println("Duke: ");
        
        String response = "";
        response = this.commandHandler.handleCommand(command, tasks);

        System.out.print(response);
        System.out.println(separator);
        
        if (this.commandHandler.isByeCommand(command)) {
            isRunning = false;
        }
        
        return isRunning;
    }
    
    
}
