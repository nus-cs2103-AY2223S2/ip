public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
        //System.out.println("Todo constructor called");
    }

    @Override
    String getTypeIcon() {
        return "[T]";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


}
