package duke.taskmanager;


import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class Tag {
    private static Map<String, List<String>> uniTagsList = new HashMap<>();

    private static final String FILE_PATH = Paths.get(Paths.get(System.getProperty("user.home")).toString(),
            "tags.txt").toString();

    public static void tag(String str, Tasks t) {
        assert uniTagsList!=null;
        if (!uniTagsList.containsKey(str)) {
            List<String> lst = new ArrayList<>();
            lst.add(t.getDesc());
            uniTagsList.put(str, lst);
            save(str, t);
        } else {
            if (!uniTagsList.get(str).contains(t.getDesc())) {
                uniTagsList.get(str).add(t.getDesc());
                save(str, t);
            }
        }
    }

    public static String returnTagged(String key) {
        String res = " ";
        if(!uniTagsList.keySet().contains(key)) {
            return "no such tag";
        } else {
            for (String t : uniTagsList.get(key)) {
                res += t;
                res += ", ";
            }
            return res;
        }
    }

    public static void clearTags(Tasks t) {
        for(String k: uniTagsList.keySet()) {
            if(uniTagsList.get(k).contains(t.getDesc())) {
                uniTagsList.get(k).remove(t.getDesc());
            }
            if(uniTagsList.get(k).isEmpty()) {
                uniTagsList.remove(k);
            }
        }
    }

    public static void rewrite() {
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
        Set<String> tags = uniTagsList.keySet();
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("duke/bot/data/tasks.txt", true));
        } catch (IOException e) {
            try {
                bw = new BufferedWriter(new FileWriter(FILE_PATH, true));
            } catch (IOException IGNORED) {

            }
        }
        for(String k : tags) {
            for (String i : uniTagsList.get(k)) {
                assert bw != null;
                try {
                    bw.newLine();
                    bw.append(k + ":" + i);
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

    public static void save(String str, Tasks t) {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException IGNORED) {

            }
        }
        try {
            BufferedWriter io = new BufferedWriter(new FileWriter("duke/bot/data/tags.txt", true));
            io.newLine();
            io.append(str + ":" + t.getDesc());
            io.close();
        } catch (IOException e) {
            try {
                BufferedWriter io = new BufferedWriter(new FileWriter(FILE_PATH, true));
                io.newLine();
                io.append(str + ":" + t.getDesc());
                io.close();
            } catch (IOException ignored) {

            }
        }
    }
    public static Map<String, List<String>> load() {
        try {
            File f = new File(FILE_PATH);
            Scanner sc = new Scanner(f);
            if(sc.hasNextLine()){sc.nextLine();}
            while(sc.hasNextLine()) {
                String tagsEntry = sc.nextLine();
                String tag = tagsEntry.split(":")[0];
                Tasks task = new Tasks(tagsEntry.split(":")[1]);
                if (!uniTagsList.keySet().contains(tag)) {
                    List<String> lst = new ArrayList<>();
                    lst.add(task.getDesc());
                    uniTagsList.put(tag, lst);
                } else {
                    uniTagsList.get(tag).add(task.getDesc());
                }
            }
            sc.close();
        } catch (Exception ignored) {

        }
        return uniTagsList;
    }



}



