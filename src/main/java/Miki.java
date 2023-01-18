import java.util.Scanner;

public class Miki {
    private static void printDiv() {
        System.out.println("    ____________________________________________________________");
    }

    private static void print(String s) {
        System.out.println("     " + s);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printDiv();
        String username = System.getProperty("user.name");
        print("in honour / fuzuki miki / 2020 | 2021");
        print("\uD83C\uDF80✨");
        print("Hello " + username + " !! Konmiki! ＼(￣▽￣)/");
        printDiv();
        boolean exitCmd = false;
        while (!exitCmd) {
            String cmd = sc.nextLine();
            if (cmd.equals("bye")) {
                exitCmd = true;
            } else {
                printDiv();
                print(cmd);
                printDiv();
            }
        }
        printDiv();
        print("Otsumiki! I'll see you later!");
        printDiv();
    }
}
