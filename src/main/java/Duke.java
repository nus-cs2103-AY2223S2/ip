import java.util.Scanner;
import java.util.ArrayList;


public class Duke {


    public static void main(String[] args) {

        System.out.println("Hewwo! I'm UwU_TaskMaster! How c-can I hewp you?!?");

        Scanner scanner = new Scanner(System.in);

        String input;

        ArrayList<String> taskList = new ArrayList<String>();

        while(true) {

            System.out.print("You: ");
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("UwU_TaskMaster ＵｗＵ: Bye bye... UwU");
                break;
            }

            if (input.equals("list")) {
                if (taskList.size() == 0) {
                    System.out.println("UwU_TaskMaster ＵｗＵ: Wooks wike *screams* you do not have any OwO tasks!");
                    System.out.println("__________________________________________");
                    continue;
                }

                System.out.println("\nUwU_TaskMaster ＵｗＵ: Heww are your tasks UwU!");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }
                System.out.println("__________________________________________");
            } else {

                if (!input.equals("")) {
                    taskList.add(input);
                    System.out.println("\nUwU_TaskMaster ＵｗＵ: Y-Y-Youw t-task has been successfuwwy added!!");
                    System.out.println("__________________________________________");
                }

            }
        }
        scanner.close();
    }
}
