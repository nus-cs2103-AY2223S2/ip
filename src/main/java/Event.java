public class Event extends Task{
    private String date1;
    private String date2;

    public Event(String date1, String date2, String name){
        super(name);
        this.date1 = date1;
        this.date2 = date2;
    }

    @Override
    public String toString(){
        String s = "[ E ]" + super.toString() + String.format("(%s %s)", date1, date2);
        return s;
    }
}
