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
        String[] list = new String[100];
        String echo = input.nextLine();
        int counter = 0;
        while(true){
            if(echo.equals("bye")) {
                System.out.println("    -------------------------------------------\n    Bye. Hope to see you again soon!\n    -------------------------------------------");
                break;
            } else if(echo.equals("list")) {
                System.out.println("    -------------------------------------------");
                for (int i = 0; i < counter; i++) {
                    System.out.println("    " + String.valueOf(i + 1) + ": " + list[i]);
                }
                System.out.println("    -------------------------------------------");
            } else {
                list[counter] = echo;
                System.out.println("    -------------------------------------------\n    " + "added: " + echo +"\n    -------------------------------------------");
                counter++;
            }
            echo = input.nextLine();

        }
    }
}
