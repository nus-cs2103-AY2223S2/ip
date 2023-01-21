import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DukeList {
    private Scanner sc;
    private List<String> list;

    public DukeList(Scanner sc) {
        this.sc = sc;
        this.list = new ArrayList<String>();
    }

    public void output() {
        while (true) {
            String input = sc.nextLine();
            if ("bye".equals(input)) {
                break;
            } else if ("list".equals(input)) {
                for (int i = 0; i < this.list.size(); i++) {
                    int j = i + 1;
                    System.out.println(j + ". " + this.list.get(i));
                }
            } else {
                this.list.add(input);
                System.out.println("added: " + input);
            }
        }
    }
}
