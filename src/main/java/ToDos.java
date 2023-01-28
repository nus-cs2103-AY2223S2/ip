public class ToDos extends Task{
    protected String by;

    public ToDos(String description){
        super(description);
    }

    @Override
    public String getType() {
        return "Todo";
    }

    @Override
    public String dataFormat(){
        if (isDone) {
            return "T/1/" + description;
        }else {
            return "T/0/" + description;
        }
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}

