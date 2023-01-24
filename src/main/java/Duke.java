import java.util.Scanner;

import java.util.ArrayList;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {

    public static MyDuke duke = new MyDuke();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InvalidCommandException, IOException {
        duke.init();
        processCommands(sc);
    }

    public static void processCommands(Scanner sc) throws InvalidCommandException, IOException {
        boolean bye = false;
        showPrompt();
        while (!bye) {
            String[] tokens = tokenise(sc);
            bye = handle(tokens);
            if (!bye) {
                showPrompt();
            }
        }
    }

    private static void showPrompt() {
        System.out.print("MyDuke >  ");
    }

    private static void showReply() {
        System.out.print("|     ");
    }

    private static String[] tokenise(Scanner sc) {
        String[] tokens = sc.nextLine().split(" ");
        return tokens;
    }

    private static boolean handle(String[] tokens) throws InvalidCommandException {
        String cmd = tokens[0];
        if (cmd.equals("save")) { System.out.println("reached here"); return false;    }
        else if (cmd.equals("bye")) {   showReply(); duke.quit(); return true; }
        else {  showReply(); duke.exec(tokens); } 
        return false;
    }
    


}
