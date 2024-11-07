package dictionary;

import java.io.File;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class UserDictionary {
    public static void main(String[] args) {
        new UserDictionary().run();
    }

    private Dictionary<String, String> dictionary = null;
    private double start;

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        dictionary = new SortedArrayDictionary<>();

        while (running) {
            System.out.print("Kommando eingeben: ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            switch (parts[0]) {
                case "create":
                    if (parts.length == 1) {
                        createDictionary(parts[1]);
                    } else {
                        createDictionary("array");
                    }
                    break;
                case "r":
                    if (parts.length == 1) {
                        readFromFile(".");
                    } else if (parts.length == 2) {
                        readFromFile(parts[1]);
                    } else if (parts.length == 3) {
                        try {
                            int count = Integer.parseInt(parts[1]);
                            readFromFile(count, parts[2]);
                        } catch (NumberFormatException e) {
                            System.out.println("Ungültige Anzahl: " + parts[1]);
                        }
                    } else {
                        System.out.println("Ungültige Anzahl an Argumenten.");
                    }
                    break;
                case "p":
                    printDictionary();
                    break;
                case "s":
                    if (parts.length == 2) {
                        search(parts[1]);
                    } else {
                        System.out.println("Bitte ein deutsches Wort angeben.");
                    }
                    break;
                case "i":
                    if (parts.length == 3) {
                        insert(parts[1], parts[2]);
                    } else {
                        System.out.println(
                                "Bitte deutsches und englisches Wort angeben.");
                    }
                    break;
                case "d":
                    if (parts.length == 2) {
                        delete(parts[1]);
                    } else {
                        System.out.println("Bitte ein deutsches Wort angeben.");
                    }
                    break;
                case "exit":
                    running = false;
                    System.out.println("Programm beendet.");
                    continue;
                case "help":
                    start = System.nanoTime();
                    System.out.println("create <Default:array|hash|tree>");
                    System.out.println("r");
                    System.out.println("r <word-count>");
                    System.out.println("p");
                    System.out.println("s <deutsches Wort>");
                    System.out.println("i <deutsches Wort> <englisches Wort>");
                    System.out.println("d <deutsches Wort>");
                    System.out.println("exit");
                    break;
                default:
                    System.out.println("Ungültiges Kommando.");
            }

            double duration = (System.nanoTime() - start)
                    / 1_000_000_000.0;
            if (duration >= 0.01)
                System.out.printf("Laufzeit: %.2f s%n", duration);
        }

        scanner.close();
    }

    private void createDictionary(String arg) {
        switch (arg) {
            case "array":
                dictionary = new SortedArrayDictionary<>();
                System.out.println("SortedArrayDictionary erstellt.");
                break;
            case "hash":
                dictionary = new HashDictionary<>();
                System.out.println("HashDictionary erstellt.");
                break;
            case "tree":
                dictionary = new BinaryTreeDictionary<>();
                System.out.println("BinaryTreeDictionary erstellt.");
                break;
            default:
                System.out.println("Ungültiger Typ.");
        }
    }

    private void delete(String arg) {
        start = System.nanoTime();
        System.out.println("Gelöscht: " + dictionary.remove(arg));
    }

    private void insert(String arg1, String arg2) {
        start = System.nanoTime();
        dictionary.insert(arg1, arg2);
        System.out.println("Eingefügt: " + arg1 + " -> " + arg2);
    }

    private void search(String arg) {
        start = System.nanoTime();
        System.out.println(dictionary.search(arg));
    }

    private void printDictionary() {
        start = System.nanoTime();
        for (Dictionary.Entry<String, String> entry : dictionary) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    private void readFromFile(String arg) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(arg));

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue != JFileChooser.APPROVE_OPTION) {
            System.out.println("Keine Datei ausgewählt.");
            return;
        }

        start = System.nanoTime();
        try (Scanner fileScanner = new Scanner(
                fileChooser.getSelectedFile())) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    dictionary.insert(parts[0], parts[1]);
                } else {
                    System.out
                            .println("Ungültiges Format in Datei: " + line);
                }
            }
        } catch (Exception e) {
            System.out.println(
                    "Fehler beim Lesen der Datei: " + e.getMessage());
        }

    }

    private void readFromFile(int arg1, String arg2) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(arg2));

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue != JFileChooser.APPROVE_OPTION) {
            System.out.println("Keine Datei ausgewählt.");
            return;
        }

        start = System.nanoTime();
        try (Scanner fileScanner = new Scanner(
                fileChooser.getSelectedFile())) {
            int count = 0;
            while (fileScanner.hasNextLine() && count < arg1) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    dictionary.insert(parts[0], parts[1]);
                    count++;
                } else {
                    System.out
                            .println("Ungültiges Format in Datei: " + line);
                }
            }
        } catch (Exception e) {
            System.out.println(
                    "Fehler beim Lesen der Datei: " + e.getMessage());
        }
    }
}
