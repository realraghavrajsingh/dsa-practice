package com.leetcode.easy.string;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Refactored version of the character sorting utility.
 */
public class ReverseAlphabeticalCharacters {

    public static void main(String[] args) {
        String input = "Java Spring Boot Microservices Developer";
        String result = transformSentence(input);
        
        System.out.println(result);
    }

    /**
     * Splits a sentence and sorts the characters of each word descending.
     */
    public static String transformSentence(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return "";
        }

        return Arrays.stream(sentence.split("\\s+"))
                .map(ReverseAlphabeticalCharacters::sortWordDescending)
                .collect(Collectors.joining(" "));
    }

    /**
     * Takes a single word and returns it with characters sorted Z-A (case-insensitive).
     *
     * <p>This specific stream pipeline is where the actual "sorting" of the word happens.
     * Since Java's {@code String} class doesn't have a direct way to sort characters,
     * we use a <b>Stream</b> to treat the string as a flow of data.</p>
     *
     * <h3>Breakdown of Each Method in the Chain:</h3>
     *
     * <h4>1. {@code chars()}</h4>
     * <p>In Java, a {@code String} is internally an array of characters, but the
     * {@code chars()} method returns an <b>{@code IntStream}</b>.</p>
     * <ul>
     *   <li><b>What it does:</b> It provides a stream of the Unicode values (integers)
     *       of the characters in the word.</li>
     *   <li><b>Why not characters?</b> Historically, Java's Stream API handles primitives
     *       like {@code int}, {@code long}, and {@code double} more efficiently. So, it
     *       gives you {@code int} values instead of {@code char} objects initially.</li>
     * </ul>
     *
     * <h4>2. {@code mapToObj(c -> (char) c)}</h4>
     * <p>Because {@code chars()} gave us an {@code IntStream} (integers), we need to turn
     * those numbers back into objects we can work with.</p>
     * <ul>
     *   <li><b>What it does:</b> It "maps" (transforms) each integer back into a
     *       {@code Character} object.</li>
     *   <li><b>Why:</b> We need {@code Stream<Character>} instead of {@code IntStream}
     *       because the {@code sorted()} method only allows custom logic (like descending
     *       order) when working with <b>Objects</b>.</li>
     * </ul>
     *
     * <h4>3. {@code sorted((a, b) -> ...)}</h4>
     * <p>This is where the rearrangement happens. By default, {@code sorted()} puts
     * things in ascending order (A to Z).</p>
     * <ul>
     *   <li><b>The Comparator:</b> We provide a lambda expression {@code (a, b)} to
     *       define how two characters should be compared.</li>
     *   <li><b>Logic: {@code Character.toLowerCase(b) - Character.toLowerCase(a)}</b>
     *     <ul>
     *       <li><b>Case-Insensitivity:</b> Using {@code toLowerCase} ensures that 'A'
     *           and 'a' are treated as the same letter during the sort.</li>
     *       <li><b>Descending Logic:</b> In Java, if a comparison returns a positive
     *           number, it swaps the elements. By subtracting {@code a} from {@code b}
     *           (instead of {@code b} from {@code a}), we essentially tell Java:
     *           <i>"Higher letters should come first,"</i> resulting in a <b>Z to A</b> order.</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * <h4>4. {@code map(String::valueOf)}</h4>
     * <p>At this point, we have a sorted stream of {@code Character} objects. We need to
     * prepare them to be merged back into a single String.</p>
     * <ul>
     *   <li><b>What it does:</b> It converts each {@code Character} object into a small
     *       {@code String} (e.g., the character {@code 'v'} becomes the string {@code "v"}).</li>
     *   <li><b>Why:</b> The final step (joining) is designed to work with Strings.</li>
     * </ul>
     *
     * <h4>5. {@code collect(Collectors.joining())}</h4>
     * <p>This is the <b>Terminal Operation</b>. Nothing actually happens in a stream until
     * you call a terminal operation like this.</p>
     * <ul>
     *   <li><b>What it does:</b> It takes all the individual Strings we created in the
     *       previous step and "stitches" them together into one final result.</li>
     *   <li><b>Result:</b> If the sorted stream was {@code ["v", "a", "a", "J"]}, this
     *       method returns {@code "vaaJ"}.</li>
     * </ul>
     *
     * <h3>Summary of the Flow:</h3>
     * <table border="1">
     *   <tr>
     *     <th>Step</th>
     *     <th>Data Type</th>
     *     <th>State of "Java"</th>
     *   </tr>
     *   <tr>
     *     <td>{@code chars()}</td>
     *     <td>{@code IntStream}</td>
     *     <td>{@code [74, 97, 118, 97]}</td>
     *   </tr>
     *   <tr>
     *     <td>{@code mapToObj()}</td>
     *     <td>{@code Stream<Character>}</td>
     *     <td>{@code ['J', 'a', 'v', 'a']}</td>
     *   </tr>
     *   <tr>
     *     <td>{@code sorted()}</td>
     *     <td>{@code Stream<Character>}</td>
     *     <td>{@code ['v', 'a', 'a', 'J']} (sorted Z-A)</td>
     *   </tr>
     *   <tr>
     *     <td>{@code map()}</td>
     *     <td>{@code Stream<String>}</td>
     *     <td>{@code ["v", "a", "a", "J"]}</td>
     *   </tr>
     *   <tr>
     *     <td>{@code collect()}</td>
     *     <td>{@code String}</td>
     *     <td>{@code "vaaJ"}</td>
     *   </tr>
     * </table>
     */
    private static String sortWordDescending(String word) {
        return word.chars()
                .mapToObj(c -> (char) c)
                .sorted((a, b) -> {
                    // Compare characters based on their lowercase values in reverse order
                    return Character.toLowerCase(b) - Character.toLowerCase(a);
                })
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}

/*
 * COMPLEXITY ANALYSIS:
 * 
 * Time Complexity: O(N log K)
 * - N is the total number of characters in the sentence.
 * - K is the length of the longest word.
 * - We iterate through all characters once to split and stream (O(N)).
 * - For each word, we perform a sort. Sorting a word of length 'k' takes O(k log k).
 * - Summing this up across all words results in O(N log K).
 * 
 * Space Complexity: O(N)
 * - We store the split words in a stream/array, and the StringBuilder/Collectors 
 *   logic creates a new string of length N.
 * - Note: The use of mapToObj(Character) introduces some object overhead (autoboxing),
 *   but it remains linear relative to input size.
 * 
 * OPTIMIZATION LEVEL:
 * - This code is highly optimized for READABILITY and MAINTAINABILITY.
 * - From a PERFORMANCE standpoint, it is "Efficient enough" for standard text processing.
 * - To achieve absolute peak performance (at the cost of readability), one could use 
 *   primitive char arrays and a manual Dual-Pivot Quicksort to avoid the overhead 
 *   of Stream object allocations and Character autoboxing.
 */
