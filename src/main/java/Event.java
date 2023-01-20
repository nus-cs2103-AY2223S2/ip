public class Event extends Task{
    private String date1;
    private String date2;

    public Event(String name, boolean done, String date1, String date2){
        super(name, done);
        this.date1 = date1;
        this.date2 = date2;
    }

    public String getDate1() {
        return this.date1;
    }

    public String getDate2() {
        return this.date2;
    }

    @Override
    public String summary(){
        String s = "D___";
        String d = this.getDone()? "✓" : "X";
        return s + d + "___" + this.getTask() + "___" + this.getDate1() + "___" + this.getDate2();
    }

    @Override
    public String toString(){
        String s = "[ E ]" + super.toString() + String.format("(%s %s)", date1, date2);
        return s;
    }
}
