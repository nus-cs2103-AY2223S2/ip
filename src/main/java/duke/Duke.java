package duke;

import duke.task.TaskList;

import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.LocalDate;

public class Duke {
    private FileManager fileManager;
    private TaskList taskList;
    private Parser parser;

    public Duke(String filePath) {
        this.fileManager = new FileManager(filePath);
        this.taskList = this.fileManager.getFile();
        this.parser =  new Parser(this.fileManager, this.taskList);
    }

    /**
     * Returns void.
     * <p>
     * Runs the Pandora bot
     */
    public void run() {
        String greetings = "Heyyo, Pandora at your service \n"
                + "What can I do for you?";
        System.out.println(greetings);

        //Input objects
        Scanner userInput = new Scanner(System.in);;
        String userMessage = "dummyInput";

        while (!userMessage.equals("bye")) {
            userMessage = userInput.nextLine();
            parser.parse(userMessage);
        }
    }
    public static void main(String[] args) {
        new Duke("data\\pandora.txt").run();
    }
}
