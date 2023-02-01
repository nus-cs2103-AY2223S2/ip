package duke.taskmanager;

import java.io.*;
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
            BufferedWriter io = new BufferedWriter(new FileWriter("duke/bot/data/tasks.txt", true));
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

    public static void rewrite(TaskList lst) {
        //rewrite file when un/mark or un/mark specific line if possible
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("duke/bot/data/tasks.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        writer.close();
        List<Tasks> todos = lst.getList();
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("duke/bot/data/tasks.txt", true));
        } catch (IOException ignored) {

        }
        for(Tasks i : todos) {
            try {
                bw.newLine();
                bw.append(i.icon()).append(" ∵ ").append(i.completed()).append(" ∵ ").append(i.desc.split(" ", 2)[1]);
            } catch (IOException io) {
                try {
                    throw new IOException(io.getMessage());
                } catch (IOException ignored) {

                }
            }
        }
        try {
            bw.close();
        } catch (IOException ignored) {

        }
    }


}
