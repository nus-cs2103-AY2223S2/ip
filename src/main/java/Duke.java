import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "  /\\_/\\\n"
                + " /◞   ◟\\ \n"
                + "( ◕   ◕ )\n"
                + " \\     /\n"
                + "  \\   /\n"
                + "   \\ /\n"
                + "    ●\n";
        System.out.println(logo + "BorzAI\n");
        System.out.println("When all I do is for you, Kermie ♥\n"
                + "What can I do for you?\n");
        Scanner scan = new Scanner(System.in);
        while (true) {
            String input = scan.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(input + "\n");
        }
        System.out.println("Woof (╯ᆺ╰๑)");
    }
}
