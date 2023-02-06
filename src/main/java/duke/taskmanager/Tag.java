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
}



