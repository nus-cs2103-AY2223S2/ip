import java.io.IOException;
import java.sql.Array;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static String[] storage = new String[100];
    public static int pointer = -1;
    public static String pad(String val) {
        return "---------------------------\n" + val + "\n---------------------------";
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Duke. How may I be of assistance?\n");
        String str = sc.nextLine();
        while (true){
            if (str.equals("bye")) {
                break;
            } else if (str.equals("list")) {
                System.out.println("---------------------------");
                for (int i = 0; i < pointer + 1; i++) {
                    System.out.print(Integer.toString(i + 1) + ". " + storage[i] + "\n");
                }
                System.out.println("---------------------------");
            } else {
                storage[pointer+1] = str;
                pointer++;
                System.out.println(pad("added: " + str));
            }
            str = sc.nextLine();
        }
        System.out.println(pad("Thank you for your patronage. Goodbye!"));
    }
}
