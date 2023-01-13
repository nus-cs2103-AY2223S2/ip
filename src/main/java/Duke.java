import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        dukeLoop();
    }

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("---------------------\n");
    }

    /**
     * Runs the core, repeating functions of Duke
     */
    private static void dukeLoop() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();

            switch (input) {
                case "bye":
                    sc.close();
                    exit();
                    return;
                default:
                    echo(input);
                    break;
            }
        }

    }

    /**
     * Prints the partitions, ----,
     * then prints the string in-between with a tab spacing
     * 
     * @param w - The string in between the ---- partitions
     */
    private static void printWithPartition(String w) {
        System.out.println("---------------------");
        System.out.println("\t" + w);
        System.out.println("---------------------");
    }

    private static void echo(String w) {
        printWithPartition("Duke: " + w);
    }

    private static void exit() {
        printWithPartition("Duke: " + "Goodbye!");
    }

}
