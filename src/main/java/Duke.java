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
        Task[] list = new Task[100];
        String echo = input.nextLine();
        int counter = 0;
        String echoSplit[] = echo.split(" ");

        while(true){
            if(echo.equals("bye")) {
                System.out.println("    -------------------------------------------\n    Bye. Hope to see you again soon!\n    -------------------------------------------");
                break;
            } else if(echo.equals("list")) {
                System.out.println("    -------------------------------------------");
                for (int i = 0; i < counter; i++) {
                    System.out.println("    " + String.valueOf(i + 1) + ".[" + list[i].getStatusIcon() + "] " + list[i].description);
                }
                System.out.println("    -------------------------------------------");
            } else if(echoSplit[0].equals("mark")) {
                int index = Integer.valueOf(echoSplit[1]) - 1;
                list[index].isDone = true;

                System.out.println("    -------------------------------------------");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("    " + "[" + list[index].getStatusIcon() + "] " + list[index].description);
                System.out.println("    -------------------------------------------");

            } else if(echoSplit[0].equals("unmark")) {
                int index = Integer.valueOf(echoSplit[1]) - 1;
                list[index].isDone = false;

                System.out.println("    -------------------------------------------");
                System.out.println("Nice! I've marked this task as not done yet:");
                System.out.println("    " + "[" + list[index].getStatusIcon() + "] " + list[index].description);
                System.out.println("    -------------------------------------------");
            } else {
                list[counter] = new Task(echo);
                System.out.println("    -------------------------------------------\n    " + "added: " + echo +"\n    -------------------------------------------");
                counter++;
            }
            echo = input.nextLine();
            echoSplit = echo.split(" ");
        }
    }
}
