public class Event extends Task {
    private String start;
    private String end;
    private char type = 'E';
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
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
    @Override
    public String toString(){
        return super.toString() + "(from: " + start + "to: " + end + ")";
    }
}
