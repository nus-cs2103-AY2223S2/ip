import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
public class Parser {
    private final Scanner scanner;
    Parser() {
        this.scanner = new Scanner(System.in);
    }

    public String[] readLine() {
        return this.scanner.nextLine().split(" ");
    }

    public String readCommand(String[] readLine) {
        return readLine[0];
    }

    public int singleQueryInteger(String[] readLine) throws DukeException {
        int s;

        try {
            s = Integer.valueOf(readLine[1]);
        } catch (NumberFormatException arr) {
            throw new InvalidException();
        } catch (Exception err) {
            throw new IncompleteException();
        }
        return s;
    }

    public ArrayList<String> queries(
            String[] readLine, List<String> keywords) throws DukeException {

        ArrayList<String> arr = new ArrayList<>();




        try {
            int index = 0;
            String concat = "";

            for (int i = 1; i < readLine.length; i++){
                String s = readLine[i];


                if (s.charAt(0) != '/') {
                    concat += s + " ";

                } else if (s.substring(1).equals(keywords.get(index))) {
                    arr.add(concat.substring(0, concat.length() - 1));
                    concat = "";
                    index++;

                } else {
                    throw new InvalidException();
                }


            }
            arr.add(concat.substring(0, concat.length() - 1));

        } catch (Exception err) {

            throw new InvalidException();
        }

        return arr;
    }






}
