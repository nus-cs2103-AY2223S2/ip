public class Event extends Task{
    protected String duration;
    private int isDone;

    public Event(String description, String duration, Integer isDone){
        super(description, isDone = isDone);
        this.duration = duration;
        this.isDone = isDone;
    }

    @Override
    public void unMark(){
        isDone = 0;
    }

    @Override
    public void markAsDone(){
        isDone = 1;
    }

    @Override
    public String getType() {
        return "Event";
    }

    @Override
    public String dataFormat(){
        if (isDone == 1) {
            return "E/1/" + description + "/" + duration;
        }else {
            return "E/0/" + description + "/" + duration;
        }
    }

    public String getStatusIcon(){
        return (isDone == 1 ? "[X]": "[ ]");
    }


    @Override
    public String toString(){
        return "[E]["+ isDone + "]" + description + "(" + this.duration + ")";
    }
}
