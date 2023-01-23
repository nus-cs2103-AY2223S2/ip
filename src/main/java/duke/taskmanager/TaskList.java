package duke.taskmanager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Tasks> lst;

    public TaskList(List<Tasks> tLst) {
        this.lst = tLst;
    }

    public TaskList() {
        lst = new ArrayList<>(100);
    }

    public List<Tasks> getList() {
        return lst;
    }
    public boolean isEmpty() {
        return lst.isEmpty();
    }

    public Tasks get(int i) {
        return lst.get(i);
    }

    public void remove(int i) {
        //rewrite to file data/tasks or delete specific line if possible
        lst.remove(i);
    }

    public void add(Tasks t) throws IOException {
        //write to file data/tasks
        lst.add(t);
        try {
            BufferedWriter io = new BufferedWriter(new FileWriter("bot/data/tasks.txt", true));
            io.newLine();
            io.append(t.icon()).append(" ∵ ").append(" ").append(" ∵ ").append(t.desc.split(" ", 2)[1]);
            io.close();
        } catch(IOException io){
            throw new IOException(io.getMessage());
        }
    }

    public int size() {
        return lst.size();
    }

    public static void rewrite(TaskList lst) throws IOException {
        //rewrite file when un/mark or un/mark specific line if possible
        PrintWriter writer = new PrintWriter("bot/data/tasks.txt");
        writer.close();
        List<Tasks> todos = lst.getList();
        BufferedWriter bw = new BufferedWriter(new FileWriter("bot/data/tasks.txt", true));
        for(Tasks i : todos) {
            try {
                bw.newLine();
                bw.append(i.icon()).append(" ∵ ").append(i.completed()).append(" ∵ ").append(i.desc.split(" ", 2)[1]);
            } catch (IOException io) {
                throw new IOException(io.getMessage());
            }
        }
        bw.close();
    }


}
