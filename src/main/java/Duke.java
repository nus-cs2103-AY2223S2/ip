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
                System.out.println("Bye bye UwU!            - UwU_TaskMaster ＵｗＵ");
                break;
            }
            System.out.println("\n" + input + "            - UwU_TaskMaster ＵｗＵ");
            System.out.println("__________________________________________");
        }
        scanner.close();

    }
}
