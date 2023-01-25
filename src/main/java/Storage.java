import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Storage {
    private String filepath;
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> load() throws IOException {

        ArrayList<Task> list = new ArrayList <Task>();
        File directory = new File("data");
        //Create directory "data", if exist do nothing
        directory.mkdir();
        File file = new File(this.filepath);
        //Create file if does not exist, if exist read the file
        if (!file.createNewFile()) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                String[] tokens = st.split(",");
                Task t = null;
                if( tokens[0].equals("todo"))
                    t = new Todo( tokens[2] , Boolean.parseBoolean(tokens[1]));
                else if ( tokens[0].equals("deadline"))
                    t = new Deadline( tokens[2], LocalDateTime.parse(tokens[3]),Boolean.parseBoolean(tokens[1]));
                else if ( tokens[0].equals("event"))
                    t = new Event( tokens[2],LocalDateTime.parse(tokens[3]),LocalDateTime.parse(tokens[4]),Boolean.parseBoolean(tokens[1]));

                list.add(t);

            }
            br.close();
        }

        return list;
    }

    public void write(Task t){
        try {
            FileWriter fw = new FileWriter("data/duke.txt", true);
            fw.write(t.toFile() + "\n");
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
