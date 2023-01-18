import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        printASCII();

        try (Scanner sc = new Scanner(System.in)) {
            String input = "";

            do {
                input = sc.nextLine();

                System.out.println(input);

            } while (!input.equals("bye"));

        } catch (Exception e) {
            System.out.println("Encountered exception: " + e + "\n Exiting program");
        } finally {
            System.out.println("Goodbye!");
        }
    }

    static void printASCII(){
        String line = " ---------------------------------------------------------";
        String logo = "\t\t\t\t            | |       \n" +
                "\t\t\t\t _ __  _   _| | _____ \n" +
                "\t\t\t\t| '_ \\| | | | |/ / _ \\\n" +
                "\t\t\t\t| |_) | |_| |   <  __/\n" +
                "\t\t\t\t| .__/ \\__,_|_|\\_\\___|\n" +
                "\t\t\t\t| |                   \n" +
                "\t\t\t\t|_|                  ";
        System.out.println(line + "\n" + logo + "\n" + line);
        System.out.println("Welcome to PUKE, the worst chat bot in existence");
        System.out.println("Input a command");
    }
}
