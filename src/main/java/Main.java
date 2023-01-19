import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Lulu lulu = new Lulu();
        lulu.greet();
        while (sc.hasNext()) {
            String s = sc.nextLine();
            String[] command = s.split(" ");
            if (command[0].compareTo("bye") == 0) {
                lulu.exit();
                break;
            } else if (command[0].compareTo("list") == 0) {
                lulu.list();
            } else if (command[0].compareTo("mark") == 0) {
                lulu.mark(Integer.valueOf(command[1]));
            } else if (command[0].compareTo("unmark") == 0) {
                lulu.unmark(Integer.valueOf(command[1]));
            } else {
                lulu.add(s);
            }
        }
    }
}
