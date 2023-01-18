import java.util.Scanner;
public class Duke {
    private Scanner sc = new Scanner(System.in);

    private void inputResponse(String userInput){
        if(userInput.equals("bye")) {
            System.out.println("\tBye. Hope to see you again soon!");
            return;
        } else {
            System.out.println("\t" + userInput);
            String input = sc.nextLine();
            inputResponse(input);
        }
    }


    public static void main(String[] args) {
        Duke dukeObj = new Duke();

        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?");

        String input = dukeObj.sc.nextLine();
        dukeObj.inputResponse(input);
    }
}
