import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help you?");
        while(true) {
            Scanner myObj = new Scanner(System.in);
            String input = myObj.nextLine();
            if(input.equals("bye")){
                System.out.println("bai");
                break;
            }
            System.out.println(input);
        }
    }
}
