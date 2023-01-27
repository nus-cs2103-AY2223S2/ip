public class ToDo extends Task{

    public ToDo(String name){
        super(name);
    }

    @Override
    public void printTask(){
        System.out.print("[T]");
        super.printTask();
        System.out.println();
    }
}
