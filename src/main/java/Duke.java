import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner usrInput = new Scanner(System.in);
        String input = usrInput.nextLine();
        while(!input.equals("bye")){
            System.out.println(input);
            input = usrInput.nextLine();
        }
        System.out.println("Goodbye!");
    }

}
