import java.util.Scanner;
public class Duke {
    private static String[] records = new String[100];
    private static int index = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Echo! I'm Bond.\nWhat is my mission?");

        while (true) {
            String userinput = sc.nextLine();
            if (userinput.equals("bye")) {
                System.out.println("Roger. Agent Bond signing off ~");
                break;
            }
            else if (userinput.equals("missions")) {
                for (int i = 0; i < index; i++) {
                    System.out.println((i+1) + ". " + records[i]);
                }
            } else {
                records[index] = userinput;
                index++;
                System.out.println("Registered: " + userinput);
            }
        }
    }
}
