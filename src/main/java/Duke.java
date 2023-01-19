import java.util.*;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        System.out.println("Leo: Yoooz it's your boy Leo! What's up?");
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("You: ");
            String str = sc.nextLine();
            if(str.equals("bye")) {
                System.out.println("Leo: Good talk man, catch you again some other time!");
                break;
            }
            System.out.println("Leo: " + str);
        }
    }
}
