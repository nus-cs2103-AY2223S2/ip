import java.util.*;

public class Task {
    private List<String> list;
    private boolean[] tag;

    public Task() {
        this.list = new ArrayList<>();
        this.tag = new boolean[100];
    }

    public void add(String input) {
        this.list.add(input);
        System.out.println("added: " + input);
    }

    public void list() {
        System.out.println("list: ");
        for(int i=1; i<list.size() + 1; i++) {
            System.out.println(i + ". " + this.tag(i-1) + " " + list.get(i-1));
        }
    }

    public void bye() {
        System.out.println("Duke: " + "Bye" + ". Hope I never see you again!");
    }

    public void mark(int i) {
        tag[i-1] = true;
        System.out.println("Duke: Nice! I've marked this task as done:" + "\n" + this.tag(i-1) + " " + this.list.get(i-1));
    }

    public void unmark(int i) {
        tag[i-1] = false;
        System.out.println("Duke: Ok! I've marked this task as not done yet:" + "\n" + this.tag(i-1) + " " + this.list.get(i-1));
    }

    private String tag(int i) {
        if(tag[i]) {
            return "[X]";
        }
        else {
            return "[]";
        }
    }
}
