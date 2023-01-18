import java.util.Scanner;
public class Duke {


    public static void main(String[] args) {
        System.out.println("Duke started, initializing Duke behaviour");
        DukeBehaviour mainBehaviour = new DukeBehaviour();
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?\n");
        Scanner inputScanner = new Scanner(System.in);
        while (mainBehaviour.isActive){
            String userInput = inputScanner.next();
            mainBehaviour.receiveInput(userInput);
        }
        System.out.println("Bye. Hope to see you again soon!\n");
        return;
    }
}
