package chungus;

import chungus.util.Pair;

/**
 * A chonk is a basic unit of serialization which can be used for any string.
 */
class Chonk {
    /**
     * Serializes a string into a chonk.
     * 
     * @param s The string to serialize.
     * @return The serialized string.
     */
    public static String chonkify(String s) {
        return s.length() + " " + s;
    }

    /**
     * Tries to identify a chonk starting at some index of a string.
     * 
     * @param s   The serialized string to extract a chonk from.
     * @param idx Index of string to start searching from.
     * @return A pair of the deserialized string and final index of the chonk, or
     *         null if no valid chonk is found.
     */
    public static Pair<String, Integer> dechonkify(String s, int idx) {
        int chonkLen = 0;
        for (; idx < s.length(); idx++) {
            char c = s.charAt(idx);
            if (c == ' ') {
                idx++;
                break;
            }
            if (c < '0' || c > '9') {
                return null;
            }
            chonkLen *= 10;
            chonkLen += c - '0';
        }
        if (idx + chonkLen > s.length()) {
            return null;
        }
        String v = s.substring(idx, idx + chonkLen);
        return new Pair<>(v, idx + chonkLen);
    }
}
