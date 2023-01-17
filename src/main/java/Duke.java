import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] userInputs = new String[100];
        int numUserInputs = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        boolean continueRunning = true;
        String greeting = "______________________________________\n"
                + "Hey there buddy! I'm Duke\n"
                + "What can I do for you today?\n"
                + "______________________________________\n";
        System.out.print(greeting);
        Scanner scanner = new Scanner(System.in);
        while(continueRunning){
            String userInput = scanner.nextLine();
            String resultString = "";
            if (userInput.equals("bye")){
                resultString = "______________________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "______________________________________\n";
                continueRunning = false;
            } else if(userInput.equals("list")){
                StringBuilder listOfInputs = new StringBuilder();
                for(int i = 0; i < numUserInputs; i++){
                    listOfInputs.append(i + 1).append(". ").append(userInputs[i]).append("\n");
                }
                resultString = "______________________________________\n"
                        + listOfInputs
                        + "______________________________________\n";
            } else {
                userInputs[numUserInputs] = userInput;
                numUserInputs++;
                resultString = "______________________________________\n"
                        + "added: "
                        + userInput + "\n"
                        + "______________________________________\n";
            }
            System.out.print(resultString);
        }
    }
}
