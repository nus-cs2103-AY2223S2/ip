import java.io.IOException;
import java.lang.String;




public class Duke {
    private Storage storage;
    private TaskList list;
    private Ui ui;

    public Duke(String file){
        this.storage = new Storage(file);
        this.ui = new Ui();
        list = new TaskList(storage.readnWriteData());
        }

    public void run() {
        ui.greet();
        int flag = 0;
        while (flag != -1){
            try{
                String input = ui.readIn();
                flag = Parser.parse(input, list);
                storage.saveData(list);


            }
            catch (IOException e){
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args){
        new Duke("database/data.txt").run();
    }

}
