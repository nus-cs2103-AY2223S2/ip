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

                switch(input){
                    case("bye"):
                        break;
                    case("list"):
                        for(int i = 0; i < tasks.size(); i++){
                            System.out.println((i + 1) + ". " + tasks.get(i).desc);
                        }
                        break;
                    default:
                        tasks.add(new Task(input, false));
                        System.out.println("added: " + input);
                }
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
