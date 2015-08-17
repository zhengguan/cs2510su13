import java.util.ArrayList;

import tester.Tester;

// Ex 06 ~ 09
interface Predicate<T> {
	// Does this predicate hold for t?
	Boolean apply(T t);
}

public class Map<T> {
	Operation<T, Boolean> op = new Operation<T, Boolean>();
	
	private class OrComb<T> implements Combiner<T, Boolean> {
		Predicate<T> p;
		
		OrComb(Predicate<T> p) {
			this.p = p;
		}
		
		public Boolean combine(T t, Boolean rst) {
			return this.p.apply(t) || rst;
		}
	}
	
	Boolean ormap(ArrayList<T> is, Predicate<T> p) {
		return op.foldl(is, false, new OrComb<T>(p));
	}
	
	
}
