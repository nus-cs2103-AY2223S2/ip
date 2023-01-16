import java.util.ArrayList;

public class Saturday {
    private ArrayList<String> list = new ArrayList<>(); // TODO: Create custom todo List Class

    public Saturday() {
        Utils.divider();
        System.out.println("\t Hello! I'm Saturday\n\t What can I do for you?");
        Utils.divider();
        System.out.println("");
    }

    public void output(String text) {
        Utils.divider();
        System.out.println("\t" + text);
        Utils.divider();
        System.out.println("");
    }

    public void input(String text) {
        if (text.equals("list")) {
            displayList();
        } else {
            add(text);
        }
    }

    public void add(String text) {
        list.add(text);
        output("added: " + text);
    }

    public void displayList() {
        Utils.divider();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t" + (i+1) + ". " + list.get(i));
        }
        Utils.divider();
        System.out.println("");
    }

    public void exit() {
        output("Bye. Hope to see you again soon!");
    }

}
