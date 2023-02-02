package duke;

public class ToDo extends Task{

    public ToDo(String name, String inpString){
        super(name, inpString);
    }

    @Override
    public void printTask(){
        System.out.print("[T]");
        super.printTask();
        System.out.println();
    }
}
