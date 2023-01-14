import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\n" +
                            "What can I do for you?\n" + logo);
        Scanner input = new Scanner(System.in);
        String echo = input.nextLine();
        while(true){
            if(echo.equals("bye")) {
                System.out.println("    -------------------------------------------\n    Bye. Hope to see you again soon!\n    -------------------------------------------");
                break;
            } else {
                System.out.println("    -------------------------------------------\n    " + echo + "\n    -------------------------------------------");
            }
            echo = input.nextLine();

        }
    }
}
