import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        final String START_MESSAGE = "Hello! I'm Duke\n What can I do for you?";
        final String END_MESSAGE= "Goodbye";
        final String TERMINATING_MESSAGE = "bye";


        System.out.println("Hello from\n" + logo);
        System.out.println(START_MESSAGE);
        Scanner input = new Scanner(System.in);
        while(true){
           String nextLine = input.nextLine();
            switch(nextLine){
                case TERMINATING_MESSAGE:
                    System.out.println(END_MESSAGE);
                    return;
                default:
                    System.out.println(nextLine);

            }

        }
    }
}
