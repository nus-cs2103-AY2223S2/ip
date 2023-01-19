import java.util.ArrayList;

public class List {
    private ArrayList<String> list = new ArrayList<>();
    public List() {
        list.add("zeroth");
    }

    public void add(String input) {
        list.add(input);
        System.out.println(Duke.line);
        System.out.println("added: " + input);
        System.out.println(Duke.line);
    }

    public void print() {
        System.out.println(Duke.line);
        for (int i = 1; i < list.size(); i++) {
            System.out.println(i + ". " + list.get(i));
        }
        System.out.println(Duke.line);
    }
}
