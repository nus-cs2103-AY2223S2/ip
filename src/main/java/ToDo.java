public class ToDo extends Task{
    String tag = "[T]";
    public ToDo() {
        super.tag = tag;
    }

    @Override
    public void genDscp(String input) throws InvalidTodo{
        String dscp = input.replace("todo ", "");
        if (dscp.isBlank()) {
            throw new InvalidTodo();
        }
        super.task = dscp;
    }

    //Override toString
}
