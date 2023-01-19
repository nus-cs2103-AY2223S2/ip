import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! I'm DonkeyChat!\nWhat can I do for you?");
        boolean isRunning = true;
        while(isRunning){
            String currCommand = input.nextLine();
            switch(currCommand){
                case "bye":
                    System.out.println("Adios!");
                    isRunning = false;
                    break;
                default:
                    System.out.println(currCommand);
                    break;
            }
        }
    }
}
