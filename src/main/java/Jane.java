import java.util.Scanner;
public class Jane {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String output = "";
        Scanner in = new Scanner(System.in);
        while (!output.equals("bye")) {
           output = in.nextLine();
            System.out.println(output);
        }
        System.out.println("Bye! Hope to see you again soon!");
    }
}