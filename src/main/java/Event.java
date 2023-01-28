public class Event extends Task{
    protected String duration;

    public Event(String description, String duration){
        super(description);
        this.duration = duration;
    }

    @Override
    public String getType() {
        return "Event";
    }

    @Override
    public String dataFormat(){
        if (isDone) {
            return "E/1/" + description + "/" + duration;
        }else {
            return "E/0/" + description + "/" + duration;
        }
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(" + this.duration + ")";
    }
}
