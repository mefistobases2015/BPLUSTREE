package datamanagment;

/**
 * Objeto que que implementa Comparable 
 * para el caso de objeto Integer
 * @author Andres
 *
 */
public class IntComparable implements TComparable<Integer> {
	
	/**
	 * Llave que va a identificar este
	 * Objeto Comparable
	 */
	private int key;
	
	/**
	 * Constructor del objeto Comparable
	 * de Integers
	 * @param key
	 */
	public IntComparable(int key){
		this.key = key;
	}

	@Override
	public Integer getKey() {
		// TODO Auto-generated method stub
		return key;
	}
	
	public void setKey(int key){
		this.key = key;
	}

	@Override
	public boolean isGreater(TComparable<Integer> comp) {
		// TODO Auto-generated method stub
		return key > comp.getKey();
	}

	@Override
	public boolean isLesser(TComparable<Integer> comp) {
		// TODO Auto-generated method stub
		return key < comp.getKey();
	}

	@Override
	public boolean isEqual(TComparable<Integer> comp) {
		// TODO Auto-generated method stub
		return key == comp.getKey();
	}

}
