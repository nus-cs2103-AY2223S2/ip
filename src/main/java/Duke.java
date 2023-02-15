
import java.io.IOException;


import java.nio.file.FileAlreadyExistsException;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
public class Duke {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        TaskList list = new TaskList();
        Storage storage = new Storage(list);
        Parser parser = new Parser(storage);
        String home = System.getProperty("user.home");
        UI ui = new UI();
        ui.printLogo();
        String userInput = scanner.nextLine();


        while (!userInput.equals("bye")) {
            parser.parseAndExecute(userInput, list);
            userInput = scanner.nextLine();
        }
        System.out.println("Thanks and have a great day ahead!");


    }
}




