package duke;

public class ToDos extends Task{
    protected String by;
    private int isDone;

    public ToDos(String description, Integer isDone){
        super(description, isDone);
        this.isDone = isDone;
    }

    @Override
    public void markAsDone(){
        isDone = 1;
    }

    @Override
    public void unMark(){
        isDone = 0;
    }

    @Override
    public String getType() {
        return "Todo";
    }

    @Override
    public String dataFormat(){
        if (isDone == 1) {
            return "T/1/" + description;
        }else {
            return "T/0/" + description;
        }
    }

    public String getStatusIcon(){
        return (isDone == 1 ? "[X]": "[ ]");
    }

    @Override
    public String toString(){
        return "[T]["+ isDone + "]" + description;
    }
}

