package bibliothek;

public class Buch {

    private String name;
    private Person entleiher;

    public Buch(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Person getEntleiher() {
        return entleiher;
    }

    public boolean wirdAusgeliehen(Person p) {
        if (entleiher == null) {
            p.leihtAus(this);
            entleiher = p;
            return true;
        }
        return false;
    }

    public boolean wirdZurueckGegeben() {
        if (entleiher != null) {
            entleiher.gibtZurueck(this);
            entleiher = null;
            return true;
        }
        return false;
    }

    public void print() {
        System.out.println("Buch: " + name + ", Entleiher: "
            + (entleiher == null ? "kein Entleiher" : entleiher.getName()));
    }
}
