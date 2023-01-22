import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Greetings, I am Nibbalim!\n" + "Your wish is my command!\n");
        Scanner sc = new Scanner(System.in);
        DukeList level = new DukeList(sc);
        try {
            level.output();
        } catch (DukeException e) {
            System.out.println(e);
        }
        System.out.println("Kay thanks bye!");
    }
}
