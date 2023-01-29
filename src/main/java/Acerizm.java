public class Acerizm {
    private Storage storage;
    private Ui ui;

    public Acerizm(){
        this.ui = new Ui();
        this.storage = new Storage();
        try{

        } catch (DukeException duke) {

        }
    }
    public void run() throws Exception {
        ui.chat();
    }
    public static void main(String[] args) throws Exception {
        new Acerizm().run();
    }

    

}
