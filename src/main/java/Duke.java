import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {

        System.out.println("Hewwo! I'm UwU_TaskMaster! How c-can I hewp you?!?");

        Scanner scanner = new Scanner(System.in);

        String input;

        while(true) {
            System.out.print("You: ");
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("UwU_TaskMaster ＵｗＵ: Bye bye... UwU");
                break;
            }
            System.out.println("\nUwU_TaskMaster ＵｗＵ: " + input);
            System.out.println("__________________________________________");
        }
        scanner.close();

    }
}
