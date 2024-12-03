package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TimeMeasurer {

    private static String FILE_PATH = "Algorithmen\\dictionary\\dictionary\\dtengl.txt";

    public static void main(String[] args) {
        String[] germanWords;
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(FILE_PATH));
            List<String> wordsList = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String germanWord = line.split(" ")[0];
                wordsList.add(germanWord);
            }
            germanWords = wordsList.toArray(new String[0]);

            new TimeMeasurer().measureArrayTime(germanWords);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public void measureArrayTime(String[] germanWords) {
        @SuppressWarnings("unchecked")
        Dictionary<String, String>[] dictionaries = new Dictionary[3];
        dictionaries[0] = new SortedArrayDictionary<>();
        dictionaries[1] = new HashDictionary<>();
        dictionaries[2] = new BinaryTreeDictionary<>();

        for (Dictionary<String, String> dictionary : dictionaries) {
            System.out.println(dictionary.getClass().getSimpleName());
            read(dictionary, 8000);
            successSearch(dictionary, germanWords, 8000);
            failSearch(dictionary, 8000);

            read(dictionary, germanWords.length);
            successSearch(dictionary, germanWords, germanWords.length);
            failSearch(dictionary, germanWords.length);
        }
    }

    private void read(Dictionary<String, String> dictionary, int count) {
        System.out.println(
                "=====Starten des Einlesens von " + count + " Wörtern=====");

        long start = System.nanoTime();

        try {
            Scanner scanner = new Scanner(new File(FILE_PATH));
            for (int i = 0; i < count; i++) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                dictionary.insert(parts[0], parts[1]);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        double duration = (System.nanoTime() - start) / 1_000_000.0;
        System.out.println("Einlesen von " + count + " Wörtern in " + duration
                + " ms");
    }

    private void successSearch(Dictionary<String, String> dictionary,
            String[] germanWords,
            int count) {

        long totalTime = 0;

        for (int i = 0; i < count; i++) {
            String s = germanWords[i];
            long start = System.nanoTime();
            dictionary.search(s);
            totalTime += System.nanoTime() - start;
        }

        double duration = totalTime / 1_000_000.0;
        System.out.println("Suchen von " + count + " vorhandenen Wörtern in "
                + duration + " ms");
    }

    private void failSearch(Dictionary<String, String> dictionary, int count) {
        long totalTime = 0;

        for (int i = 0; i < count; i++) {
            long start = System.nanoTime();
            dictionary.search("gedoejgfjghsts");
            totalTime += System.nanoTime() - start;
        }

        double duration = totalTime / 1_000_000.0;
        System.out.println(
                "Suchen von " + count + " nicht vorhandenen Wörtern in "
                        + duration + " ms");
        System.out.println();
    }
}
