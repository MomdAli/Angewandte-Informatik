package liste;

/**
 * Klasse für Wörter mit ihren Häufigkeiten.
 * @author oliverbittel
 * @since 31.07.2023
 */
public class Element<T> {
	final private T element;
	private int frequency;
	
	/**
	 * Konstruktor.
	 * @param element Wort
	 * @param f H&auml;ufgkeit
	 */
	public Element(T element, int f) {
		this.element = element;
		this.frequency = f;
	}

	/**
	 * Liefert Element zur&uuml;ck.
	 * @return element
	 */
	public T getElement() {
		return element;
	}

	/**
	 * Liefert H&auml;ufgkeit zur&uuml;ck.
	 * @return H&auml;ufgkeit
	 */
	public int getFrequency() {
		return frequency;
	}
	
	/**
	 * Addiert zur H&auml;ufgkeit f dazu.
	 * @param f H&auml;ufgkeits&auml;nderung.
	 */
	public void addFrequency(int f) {
		frequency += f;
	}

	@Override
	public String toString() {
		return element + ":" + frequency;
	}
}