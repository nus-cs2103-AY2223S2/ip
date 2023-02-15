package duke.query;

import java.util.StringTokenizer;

/**
 * The QueryParser class is a utility class that parses raw query strings into Query objects.
 */
public class QueryParser {
    /**
     * Parses a query into an array of Strings.
     *
     * @param rawQuery a raw query string from user input
     * @return a string array with query type, description and command parameters
     */
    public static Query parseQuery(String rawQuery) {
        StringTokenizer st = new StringTokenizer(rawQuery);
        String command = st.nextToken();
        Query query = new Query(command);

        try {
            String param = st.nextToken("/").strip();
            query.setParam(param);

            while (st.hasMoreTokens()) {
                String key = st.nextToken(" ").strip();
                String arg = st.nextToken("/").strip();
                if (!key.isBlank()) {
                    query.setArgument(key, arg);
                }
            }
        } catch (Exception e) {
            // Ignore exceptions from string tokenizer.
        }
        return query;
    }
}
