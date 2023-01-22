import java.util.Scanner;
public class Duke {
    //@@author IceFire
    //Reused from https://stackoverflow.com/questions/36514289
    // with minor modifications
    private static String dashedLine()
    {
        StringBuilder sb = new StringBuilder(90);
        for (int i = 0; i < 90; i++) {
            sb.append("-");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";*/

        Scanner userInput = new Scanner(System.in);
        System.out.println(dashedLine());
        System.out.println("Hi everyone, Dukethony Codetano here, the internet's busiest program; hope you're doing well. \n"
                + "How can I help you?");
        System.out.println(dashedLine());
        while(true) {
            String input = userInput.nextLine();
            if (input.equals("bye")) {
                System.out.println(dashedLine());
                System.out.println("Tran \n" +
                        "sition. Anthony Fantano, Code, Forever.");
                System.out.println(dashedLine());
                break;
            }
            System.out.println(dashedLine());
            System.out.println(input);
            System.out.println(dashedLine());
        }
    }
}
