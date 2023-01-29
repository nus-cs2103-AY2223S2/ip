import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String MESSAGE_START = "Hello! I'm Duke\nWhat can I do for you?";

        System.out.println("Hello from\n" + logo);
        System.out.println(MESSAGE_START);
        Chatbot chatbot;
        try {
            Path path = Paths.get(Messages.SAVE_DIRECTORY);
            if (!Files.exists(path)) {
                new File(Messages.SAVE_DIRECTORY).mkdir();
            }
            if(!Files.exists(Paths.get(Messages.SAVE_LOCATION))) {
                new File(Messages.SAVE_LOCATION).createNewFile();
            }
            chatbot = Chatbot.loadFromData(new File(Messages.SAVE_LOCATION));

        } catch(IOException e) {
            e.printStackTrace();
            return;
        }
        chatbot.readInput();
    }





}
