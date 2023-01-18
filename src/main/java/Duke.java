import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String greeting = formatStr("Hello! I'm Muse\n"
                + "What can I do for you?");
        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);


        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(formatStr(input));
            input = sc.nextLine();
        }
        String goodbyeMessage = formatStr("Bye. Come back again!");
        System.out.println(goodbyeMessage);
    }

    public static String formatStr(String str) {
        String returnstr =  "############################\n"
                            + str + "\n"
                            + "############################";
        return returnstr;
    }
}

