import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Echo! I'm Bond.\nWhat is my mission?");

        while (true) {
            String userinput = sc.nextLine();
            if (userinput.equals("bye")) {
                System.out.println("Roger. Agent Bond signing off ~");
                break;
            }
            System.out.println("Bond: " + userinput);
        }
    }
}
