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
            }
            switch(command[0]) {
                case "list":
                    lulu.list();
                    break;
                case "mark":
                    lulu.mark(Integer.valueOf(command[1]));
                    break;
                case "unmark":
                    lulu.unmark(Integer.valueOf(command[1]));
                    break;
                default:
                    lulu.add(s);
            }
        }
    }
}
