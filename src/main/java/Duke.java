import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("Greetings, I am Nibbalim!\n" + "I will echo whatever you say!\n");
        Scanner sc = new Scanner(System.in);
        Level1 level = new Level1(sc);
        level.output();
        System.out.println("Kay thanks bye!");
    }
}
