package liste;

/**
 * @author Mohammed Ali Al-Saiaf
 */
public abstract class AbstractFrequencyTable<T> implements FrequencyTable<T> {
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
    public void add(T t) {
		add(t, 1);
    }

	@Override
	public void addAll(FrequencyTable<? extends T> fq) {
		// * foreach loop
		for (var it = fq.iterator(); it.hasNext();) {
			var e = it.next();
			add(e.getElement(), e.getFrequency());
		}
	}

	@Override
	public void collectNMostFrequent(int n, FrequencyTable<? super T> fq) {
		FrequencyTable<T> temp = new ArrayFrequencyTable<>();
		n = Math.min(n, size());

		// * foreach loop
		for (var it = iterator(); it.hasNext();) {
			var e = it.next();
			temp.add(e.getElement(), e.getFrequency());

			n--;
			if (n == 0)
				break;
		}

 		fq.clear();
		fq.addAll(temp);
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("{");

		for (var it = iterator(); it.hasNext();) {
			var e = it.next();
			s.append(e.toString()).append(", ");
		}

		s.append("} size = ").append(size());

		return s.toString();
	}
}