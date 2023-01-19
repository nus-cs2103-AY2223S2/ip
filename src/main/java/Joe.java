import java.util.Scanner;

public class Joe {
    static TaskList taskList = new TaskList();

    public Joe() {
        taskList = new TaskList();
    }

    public static void sayBye() {
        System.out.println("\tBye. Hope to see you again soon!");
        printNewLine();
        System.exit(0);
    }

    public static void getResponse(Scanner sc){
        String[] input = sc.nextLine().split(" ");
        int inputLength = input.length;
        printNewLine();
        if (inputLength !=0 ) {
            switch (input[0]) {
                case ("bye"):
                    sayBye();
                case ("list"):
                    taskList.returnList();
                    break;
                case ("mark"):
                    taskList.mark(input[1]);
                    break;
                case ("unmark"):
                    taskList.unmark(input[1]);
                    break;
                default:
                    taskList.addTask(input);
                    break;
            }
        } else {
            System.out.println("Incorrect/unknown input");
        }
    }
    public static void printNewLine() {
        String newline = "\t____________________________________________________________";
        System.out.println(newline);
    }
}
