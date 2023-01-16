import java.util.Scanner;

public class Duke {

    private String[] lists;
    private int cnt;

    public Duke() {
        this.lists = new String[100];
        this.cnt = 0;
    }

    private static void space() {
        System.out.print("     ");
    }

    private static void line() {
        space();
        System.out.println("--------------------------------------------------");
    }

    private static void greeting() {
        line();
        space();
        System.out.println("Hello! I'm WindyCall");
        space();
        System.out.println("How can I help you?");
        line();
    }

    private static void byeWords() {
        line();
        space();
        System.out.println("Bye. Always willing to provide my help for you!!!");
        line();
    }

    private static void echoCommand(String message) {
        line();
        space();
        System.out.println(message);
        line();
    }

    private void addCommand(String message) {
        line();
        space();
        System.out.println("added: " + message);
        this.lists[cnt] = message;
        this.cnt++;
        line();
    }

    private void displayLists() {
        line();
        for (int i = 0; i < this.cnt; i++) {
            space();
            System.out.println((i + 1) + ". " + this.lists[i]);
        }
        line();
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        greeting();
        Duke chatBox = new Duke();
        while(true) {
            String userCommand = scan.nextLine();
            if (userCommand.equals("bye")) {
                byeWords();
                break;
            }
            if (userCommand.equals("list")) chatBox.displayLists();
            else chatBox.addCommand(userCommand);
        }
        return ;
    }
}
