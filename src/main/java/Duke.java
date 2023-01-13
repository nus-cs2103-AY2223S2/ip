import java.util.Scanner;

public class Duke {
    public static void printRowLine() {
        println("_________________________________________");
    }

    public static void println(String s) {
        System.out.println("\t" + s);
    }

    public static void main(String[] args) {
        printRowLine();
        println("\tHi There! I'm Shao");
        println("\tWhat can I do for you?");
        printRowLine();
        Scanner scan = new Scanner(System.in);
        while (true) {
            String input = scan.next();
            if (input.equalsIgnoreCase("bye")) {
                printRowLine();
                println("Bye! Have a nice day!");
                printRowLine();
                println("");
                break;
            }
            printRowLine();
            println(input);
            printRowLine();
            println("");
        }
        scan.close();
    }
}
