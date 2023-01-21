import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
public class Storage {
    String fp;
    File f;

    public Storage(String fp) throws IOException{
        this.fp = fp;
        this.f = new File(fp);
        if (f.createNewFile()) {}
    }

    public void save(TaskList tl) {
        //overwrite all in tasklist into file
        try{
            FileWriter fw = new FileWriter(fp);
            for (int i = 0; i < tl.count(); i++) {
                fw.write(tl.getTask(i).toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
