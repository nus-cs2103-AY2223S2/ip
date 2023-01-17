import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static String pad(String val) {
        return "     ---------------------------\n     " + val + "\n     ---------------------------";
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Duke. How may I be of assistance?\n");
        String str = sc.nextLine();
        while (!str.equals("bye")){
            System.out.println(pad(str));
            str = sc.nextLine();
        }
        System.out.println(pad("Thank you for your patronage. Goodbye!"));
    }
}
