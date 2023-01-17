public class Events extends  Task{
    private String start;
    private String end;
    public Events(String desciption, String start, String end){
        super(desciption);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to:%s)", super.toString(), this.start, this.end );
    }
}
