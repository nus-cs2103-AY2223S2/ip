public class Deadline extends Task{

    private String date;
    public Deadline(String name, String date){
        super(name);
        this.date = date;
    }

    @Override
    public void printTask(){
        System.out.print("[D]");
        super.printTask();
        System.out.println("(by: " + date + ")");
    }
}
