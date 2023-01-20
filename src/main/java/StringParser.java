public class StringParser {
    public void parseTodo(String input) throws DukeException{
        try{
            String[] wordArr = input.split(" ", 2);
            String desc = wordArr[1];
        } catch(ArrayIndexOutOfBoundsException e){
            throw new DukeException("Sorry, todo command needs description");
        }
    }
}
