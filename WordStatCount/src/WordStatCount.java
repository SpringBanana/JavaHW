/*
 * Count the number of words in a file and print them in ascending order
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import classes.Scanner;

public class WordStatCount {
    public static void main(String[] args) {
        BufferedWriter writer = null;
        Scanner sc = new Scanner(args[0], "utf-8");
        LinkedHashMap<String, Integer> words = new LinkedHashMap<>();
        boolean in = false;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf-8"));
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                input = input.toLowerCase();
                int pointer = 0;
                for (int j = 0; j <= input.length(); j++) {
                    if (j == input.length()) {
                        in = true;
                    } else if ((Character.getType(input.charAt(j)) != Character.DASH_PUNCTUATION && input.charAt(j) != '\'' && !Character.isLetter(input.charAt(j)))) {
                        in = true;
                    } else {
                        in = false;
                    }
                    if (in) {
                        if (pointer > 0) {
                            String word = input.substring(j - pointer, j);
                            boolean unique = true;
                            if (words.containsKey(word)) {
                                unique = false;
                                words.replace(word, words.get(word) + 1);
                            }
                            if (unique) {
                                words.put(word, 1);
                            }
                        }
                        pointer = 0;
                    } else {
                        pointer++;
                    }
                }
            }

            final Map<String, Integer> sorted = words.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            for (String word : sorted.keySet()) {
                try {
                    String key = word.toString();
                    Integer value = words.get(word);
                    writer.write(key + " " + value + '\n');
                } catch (IOException e) {
                    e.getMessage();
                }
            }

            try {
                assert writer != null;
                writer.close();
            } catch (IOException e) {
                e.getMessage();
            }

        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.getMessage();
        }

        sc.close();
    }
}