package core.utils;
import java.util.*;

/**
 * A helper class for joining the tokens to strings according to the delim
 * provided.
 */
public class TokenUtilities {
    /**
     * Instantiates a TokenUtilities.
     */
    private TokenUtilities() {}

    /**
     * The singleton instance for the given token joiner.
      */
    public static final TokenUtilities instance = new TokenUtilities();

    /**
     * Joins the tokens together
     * @param tokens the tokens to be joined
     * @param delim a hashset containing the potential delimiters, a delimiter
     *              is a string that splits the tokens.
     * @return a pair, the left of which would be a string if the token does
     * not start with a delimiter. The right would be a hash set containing the
     * information.
     */
    public Pair<String, Map<String, String>> joinTokens(
            String[] tokens, Set<String> delim) {
        String currentDelim = null;
        final StringBuilder left = new StringBuilder();
        final HashMap<String, StringBuilder> rightBuilders = new HashMap<>();
        for (String token: tokens) {
            if (delim.contains(token)) {
                currentDelim = token;
                rightBuilders.put(token, new StringBuilder());
                continue;
            }
            if (currentDelim == null) {
                left.append(token);
                left.append(" ");
            } else {
                final StringBuilder builder = rightBuilders.get(currentDelim);
                builder.append(token);
                builder.append(" ");
            }
        }
        final HashMap<String, String> right = new HashMap<>();
        for (final String key: rightBuilders.keySet()) {
            right.put(key, rightBuilders.get(key).toString().trim());
        }
        return new Pair<>(left.toString().trim(), right);
    }

    /**
     * Gets the subsequence of the tokens.
     * @param tokens the tokens from which the subsequence is get.
     * @param startIdx the start index of the subsequence.
     * @param endIdx the end index of the subsequence.
     * @return the subsequence starting from the startIdx ending with the
     * endIdx, inclusive of both sides.
     */
    public String[] getSubsequence(String[] tokens, int startIdx, int endIdx) {
        assert (endIdx < tokens.length) : "The end index should be smaller than" +
                "the length of the tokens.";
        if (startIdx > endIdx) {
            return new String[0];
        }
        final int length = endIdx - startIdx + 1;
        String[] res = new String[length];
        for (int i = 0; i < length; i ++) {
            res[i] = tokens[i + startIdx];
        }
        return res;
    }

    /**
     * Returns an array with the first item removed.
     * @param tokens the list of tokens.
     * @return the tokens with the first item removed.
     */
    public String[] removeFirst(String[] tokens) {
        if (tokens.length <= 1) {
            return new String[0];
        }
        return  getSubsequence(tokens, 1, tokens.length - 1);
    }
}
