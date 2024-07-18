package bibliothek;

import java.util.LinkedList;
import java.util.List;

public class Person {

    private String name;
    private List<Buch> ausgelieheneBuecher = new LinkedList<Buch>();

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAnzahlAusgelieheneBuecher() {
        return ausgelieheneBuecher.size();
    }

    public boolean leihtAus(Buch b) {
        return b.getEntleiher() == null && ausgelieheneBuecher.add(b);
    }

    public boolean gibtZurueck(Buch b) {
        return ausgelieheneBuecher.remove(b);
    }

    public void print() {
        System.out.println("Person: " + name);
        if (ausgelieheneBuecher.isEmpty()) {
            System.out.println("Keine Ausgeliehene Buuecher");
            return;
        }

        System.out.println("Ausgeliehene Buuecher: ");
        for (Buch b : ausgelieheneBuecher) {
            System.out.println(b.getName());
        }
    }
}
