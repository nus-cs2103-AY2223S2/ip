package duke.taskmanager;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Tasks> lst;
    private static final String FILE_PATH = Paths.get(Paths.get(System.getProperty("user.home")).toString(),
            "tasks.txt").toString();

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
        assert lst != null;
        lst.remove(i);
    }

    public void add(Tasks t) {
        //write to file data/tasks
        assert lst != null;

        lst.add(t);
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException IGNORED) {

            }
        }
        try {
            BufferedWriter io = new BufferedWriter(new FileWriter("duke/bot/data/tasks.txt", true));
            io.newLine();
            io.append(t.icon()).append(" ∵ ").append(" ").append(" ∵ ").append(t.desc.split(" ", 2)[1]);
            io.close();
        } catch(IOException e){
            try {
                BufferedWriter io = new BufferedWriter(new FileWriter(FILE_PATH));
                io.newLine();
                io.append(t.icon()).append(" ∵ ").append(" ").append(" ∵ ").append(t.desc.split(" ", 2)[1]);
                io.close();
            } catch(IOException ignored){

            }
        }

    }

    public int size() {
        return lst.size();
    }

    public static void rewrite(TaskList lst) {
        //rewrite file when un/mark or un/mark specific line if possible
        PrintWriter writer;
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            writer = new PrintWriter("duke/bot/data/tasks.txt");
            writer.close();
        } catch (FileNotFoundException e) {
            try {
                writer = new PrintWriter(FILE_PATH);
                writer.close();
            } catch (FileNotFoundException IGNORED) {

            }
        }
        List<Tasks> todos = lst.getList();
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("duke/bot/data/tasks.txt", true));
        } catch (IOException e) {
            try {
                bw = new BufferedWriter(new FileWriter(FILE_PATH, true));
            } catch (IOException IGNORED) {

            }
        }
        for(Tasks i : todos) {
            assert bw != null;
            try {
                bw.newLine();
                bw.append(i.icon()).append(" ∵ ").append(i.completed()).append(" ∵ ").append(i.desc.split(" ", 2)[1]);
            } catch (IOException e) {

            }
        }
        try {
            assert bw != null;
            bw.close();
        } catch (NullPointerException | IOException ignored) {

        }
    }


}
