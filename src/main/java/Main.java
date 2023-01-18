import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Lulu lulu = new Lulu();
        lulu.greet();
        while (sc.hasNext()) {
            String s = sc.nextLine();
            if (s.compareTo("bye") == 0) {
                lulu.exit();
                break;
            } else if (s.compareTo("list") == 0) {
                lulu.list();
            } else {
                lulu.add(s);
            }
        }
    }
}
