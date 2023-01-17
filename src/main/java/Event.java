public class Event extends Task {
    private String start;
    private String end;
    private char type = 'E';
    public Event(String description) {
        super(description);
    }
    public void setStart(String time){
        this.start = time;
    }
    public void setEnd(String time){
        this.end = time;
    }
    public String getStart(){
        return this.start;
    }
    public String getEnd(){
        return this.end;
    }
    @Override
    public char getType() {
        return type;
    }
}
