import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("Greetings, I am Nibbalim!\n" + "Your wish is my command!\n");
        Scanner sc = new Scanner(System.in);
        DukeList level = new DukeList(sc);
        level.output();
        System.out.println("Kay thanks bye!");
    }
}
