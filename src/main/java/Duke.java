import java.util.Scanner;

public class Duke {

    public static MyDuke duke = new MyDuke();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InvalidCommandException {
        duke.init();
        processCommands(sc);
    }

    public static void processCommands(Scanner sc) throws InvalidCommandException {
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

    public static void showReply() {
        System.out.print("|     ");
    }

    public static String[] tokenise(Scanner sc) {
        String[] tokens = sc.nextLine().split(" ");
        return tokens;
    }

    public static boolean handle(String[] tokens) throws InvalidCommandException {
        String cmd = tokens[0];
        if (cmd.equals("\n")) { return false;    }
        else if (cmd.equals("bye")) {   showReply(); duke.quit(); return true; }
        else {  showReply(); duke.exec(tokens); } 
        return false;
    }
    
}
