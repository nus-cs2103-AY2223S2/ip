import java.util.ArrayList;

public class DukeList {
    ArrayList<String> list = new ArrayList();

    public DukeList() {}

    public void add(String s) {
        list.add(s);
        System.out.println(new TextBorder("added: " + s));
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        String out = "";
        int num = 1;
        for (String s : list) {
            out += num + ". " + s + "\n";
            num ++;
        }
        return out;
    }
}
