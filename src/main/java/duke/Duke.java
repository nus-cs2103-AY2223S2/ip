package duke;

public class Duke {

    private Art ar;
    private Storage st;
    private Functions fn;
    private UI ui;

    public Duke(String fp) {
        this.ar = new Art();
        try {
            st = new Storage(fp);
            this.fn = new Functions(new TaskList(), st);
        } catch (Exception e) {
            System.out.println(e);
        }
        this.ui = new UI(fn);
    }

    public void run(){
        ar.show();
        boolean flag = true;
        while (flag) {
            ui.getInput();
            //could use enums here to check user input before going into switch case
            try {
                flag = ui.action();
            } catch (DukeException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
