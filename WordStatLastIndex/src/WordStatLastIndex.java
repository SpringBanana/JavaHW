/*
 * Count the number of words and last appearence in a line
 */

import java.io.*;
import java.util.*;
import classes.Scanner;

public class WordStatLastIndex {
    public static void main(String[] args) {
        String input = "";
        BufferedWriter writer = null;
        boolean in = false;
        try {
            Scanner sc = new Scanner(args[0], "utf-8");
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf-8"));
            int line = 0;
            LinkedHashMap<String, Integer[]> words = new LinkedHashMap<>();
            while (sc.hasNextLine()) {
                input = sc.nextLine();
                line++;
                input = input.toLowerCase();
                int num = 1;
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
                            if (words.size() > 0) {
                                boolean unique = true;
                                if (words.keySet().contains(word)) {
                                    Integer[] count = words.get(word);
                                    count[0]++;
                                    count[line] = num++;
                                    words.replace(word, count);
                                    unique = false;
                                }
                                if (unique) {
                                    Integer[] count = new Integer[input.length()];
                                    count[0] = 1;
                                    count[line] = num++;
                                    words.put(word, count);

                                }
                            } else {
                                Integer[] count = new Integer[input.length()];
                                count[0] = 1;
                                count[line] = num++;
                                words.put(word, count);
                            }
                        }
                        pointer = 0;
                    } else {
                        pointer++;
                    }
                }
            }
            int k = 0;
            line = 0;
            for (String s : words.keySet()) {
                try {
                    assert writer != null;
                    Integer[] count = words.get(s);
                    writer.write(s);
                    for (int i = 0; i < count.length; i++) {
                        if (count[i] != null) {
                            writer.write(' ');
                            String r = Integer.toString(count[i]);
                            writer.write(r);
                        }
                    }

                    writer.write('\n');
                    k++;
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
            sc.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.getMessage();
        }
    }
}
