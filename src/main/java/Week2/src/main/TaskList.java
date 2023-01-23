package Week2.src.main;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.util.Scanner;
public class TaskList<Task> {

    private int size = 0;

    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    @SuppressWarnings("unchecked")
    public TaskList(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        this.list = new ArrayList<Task>();
        while (sc.hasNext()) {
            //this.list.add((Task) new Todo(sc.next()));
            char type = sc.next().toString().charAt(1);
            if(type == 'T') {
                this.list.add((Task) new Todo(sc.next()));
            } else if(type == 'D') {
                String[] parts = sc.next().split("by:");
                this.list.add((Task) new Deadline(parts[0].substring(0, parts[0].length()-2), parts[1].substring(0, parts[1].length()-2)));
            } else if(type == 'E') {
                String[] froms = sc.next().split("from:");
                String[] tos = froms[1].split("to:");
                this.list.add((Task) new Event(froms[0].substring(0, froms[0].length()-2),
                                                froms[1].substring(0, froms[1].length()-2),
                                                tos[1].substring(0, tos[1].length()-2)));
            }
        }
        sc.close();
    }

    public int size() {
        return this.size;
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    public void remove(int index) {
        this.list.remove(index);
    }

    public void add(Task t) {
        this.list.add(t);
        this.size++;
    }
}
