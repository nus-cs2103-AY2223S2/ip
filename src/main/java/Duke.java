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
                String[] inputArr = input.split(" ");

                switch(inputArr[0]){
                    case("bye"):
                        break;
                    case("list"):
                        System.out.println("Your current tracked tasks: ");
                        for(int i = 0; i < tasks.size(); i++){
                            Task curr = tasks.get(i);
                            System.out.println((i + 1) + ".[" + curr.getDoness() + "] " + curr.desc);
                        }
                        break;
                    case("mark"):
                        int index = Integer.parseInt(inputArr[1]) - 1;
                        tasks.get(index).markDone();
                        System.out.println("Marked as donezo:\n\t [X] " + tasks.get(index).desc);
                        break;
                    case("unmark"):
                        index = Integer.parseInt(inputArr[1]) - 1;
                        tasks.get(index).markUndone();
                        System.out.println("Not donezo anymore:\n\t [X] " + tasks.get(index).desc);
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
