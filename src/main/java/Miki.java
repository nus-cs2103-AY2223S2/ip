import java.util.Scanner;
import java.util.ArrayList;

public class Miki {
    private static void printDiv() {
        System.out.println("    ____________________________________________________________");
    }

    private static void print(String s) {
        System.out.println("     " + s);
    }

    public static void main(String[] args) {
        ArrayList<String> cmd_list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        printDiv();
        String username = System.getProperty("user.name");
        print("in honour / fuzuki miki / 2020 | 2021");
        print("\uD83C\uDF80✨");
        print("Hello " + username + " !! Konmiki! ＼(￣▽￣)/");
        printDiv();
        boolean exit_cmd = false;
        while (!exit_cmd) {
            System.out.print(">");
            String cmd = sc.nextLine();
            if (cmd.equals("bye")) {
                exit_cmd = true;
            } else if (cmd.equals("list")) {
                printDiv();
                for (int i = 0; i < cmd_list.size(); i++) {
                    print(Integer.toString(i + 1) + ". " + cmd_list.get(i));
                }
                printDiv();
            } else {
                cmd_list.add(cmd);
                printDiv();
                print("Added! [" + cmd + "]");
                printDiv();
            }
        }
        printDiv();
        print("Otsumiki! I'll see you later!");
        printDiv();
    }
}
