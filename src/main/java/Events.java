public class Events extends  Task{
    protected String from;
    protected String to;
    public Events(String desciption, String start, String end){
        super(desciption);
        this.from = start;
        this.to = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from:%s to:%s)", super.toString(), this.from, this.to);
    }
    @Override
    public String changeFormat() {

        return String.format("T %s " + "| " + this.from  + "-" + this.to, super.changeFormat());

    }
}
