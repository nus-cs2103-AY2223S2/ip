public class Event extends Task{

    private String startDate;
    private String endDate;
    public Event(String name, String startDate, String endDate){
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void printTask(){
        System.out.print("[E]");
        super.printTask();
        System.out.println("(from: " + startDate + "to: " + endDate + ")");

    }

}
