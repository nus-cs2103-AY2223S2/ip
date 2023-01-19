import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello i'm\n" + logo);
        System.out.println("What can i do for you?");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        
        while (!input.equals("bye")){
            System.out.println(input);
            input = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again!");
    }
}
