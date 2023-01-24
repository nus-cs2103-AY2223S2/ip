import java.util.StringTokenizer;

public class QueryParser {
    public static String[] parseQuery(String query, String[] commands) {
        String[] result = new String[commands.length + 2];
        StringTokenizer st = new StringTokenizer(query);
        result[0] = st.nextToken();

        try {
            result[1] = st.nextToken("/");
            for (int i = 0; i < commands.length; i++) {
               String command = st.nextToken(" ");
               if (!command.equals(commands[i])) {
                   result[i + 2] = "";
               } else {
                   result[i + 2] = st.nextToken(i == commands.length - 1 ? "\n" : "/")
                           .stripLeading().stripTrailing();
               }
            }
        } catch (Exception e) {
        }
        return result;
    }
}
