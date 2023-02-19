public class ToDo extends Task{
<<<<<<< .merge_file_a17176
    String tag = "[T]";
    public ToDo() {
        super.tag = tag;
    }
    public ToDo(String description) {
        super.tag = tag;
        super.description = description;
    }

    @Override
    public void genDscp(String input) throws InvalidTodo{
        String dscp = input.replace("todo ", "");
        if (dscp.isBlank()) {
            throw new InvalidTodo();
        }
        super.task = dscp;
=======
    String tag = "T";
    public ToDo() {
        super.tag = tag;
    }

    @Override
    public void genDscp(String input) throws InvalidTodo {
        if (input.isBlank()) {
            throw new InvalidTodo();
        }
        super.description = input;
>>>>>>> .merge_file_a15092
    }

    //Override toString
}
