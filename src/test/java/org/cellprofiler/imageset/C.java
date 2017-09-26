package org.cellprofiler.imageset;

import java.util.*;

/**
 * @author Lee Kamentsky
 * Stupid extension to the array list to
 * let you do things like
 * new C<Foo<Bar>>(a).c(b).c(c);
 * which you can't do with Arrays.asList()
 * @param <T>
 */
@SuppressWarnings("serial") class C<T> extends ArrayList<T> {
	public C() {}
	public <U extends T> C(U x) { add(x); }
	public C(List<? extends T> x) { addAll(x);}
	public <U extends T> C<T> c(U x) { add(x); return this; }
	public C<T> c(Collection<? extends T> x) { addAll(x); return this; }
	/**
	 * @return a deterministically shuffled copy of this list (you get the same one every time)
	 */
	public List<T> shuffle() { 
		List<T> result = new ArrayList<T>(this);
		Random r = new Random(result.hashCode());
		Collections.shuffle(result, r);
		return result;
	}
	/**
	 * Return a deterministicaly shuffled copy of this list (with seed to give you different per seed)
	 * @param o seed for randomizer
	 * @return
	 */
	public List<T> shuffle(Object o) {
		List<T> result = new ArrayList<T>(this);
		Random r = new Random(result.hashCode() + o.hashCode());
		Collections.shuffle(result, r);
		return result;
	}
}