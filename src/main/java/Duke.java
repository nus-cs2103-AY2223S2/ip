import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String currWord = null;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(new TextBorder("Waddup the name's Duncan. Sorry but Duke couldn't make it, had a pretty bad stomach-ache."));
        System.out.println(new TextBorder("So what do you need bro?"));
        while (true) {
            currWord = scanner.next();
            if (currWord.equals("bye") ) {
                System.out.println(new TextBorder("Sweet, seeya."));
                break;
            }
            System.out.println(new TextBorder(currWord));
        }
    }
}
