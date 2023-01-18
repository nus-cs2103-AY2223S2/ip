public class Task {
//    private boolean done;
//    private String type;

    private String description;
    public Task(String description){
        this.description = description;
    }

    @Override
    public String toString(){
        return description;
    }
}
