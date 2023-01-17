import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static MyDuke duke = new MyDuke();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        duke.init();
        processCommands(sc);
    }

    public static void processCommands(Scanner sc) {
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

    public static void showPrompt() {
        System.out.print("MyDuke >  ");
    }

    public static String[] tokenise(Scanner sc) {
        String[] tokens = (sc.nextLine()).split(" ");
        return tokens;
    }

    public static boolean handle(String[] tokens) {
        String cmd = tokens[0];
        if (cmd == null) { }
        else if (cmd.toLowerCase().equals("bye")) {   duke.quit(); return true; }
        else {  duke.exec(tokens); }
        
        return false;
    }
    
}
