package datamanagment;

/**
 * 
 * @author Andres
 *
 *Esa clase implementa el comparable
 *y lo hace para objetos String
 *
 */
public class StringComparable implements TComparable<String> {

	/**
	 * Llave que identifica este comparable
	 */
	private String key;
	
	/**
	 * Constructor del comparador con 
	 * un String implementado
	 * @param key String que va a ser 
	 * el que diferencie este objeto
	 */
	public StringComparable(String key){
		this.key = key;
	}
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return key;
	}

	@Override
	public boolean isGreater(TComparable<String> comp) {
		// TODO Auto-generated method stub
		return key.compareTo(comp.getKey()) > 0;
	}

	@Override
	public boolean isLesser(TComparable<String> comp) {
		// TODO Auto-generated method stub
		return key.compareTo(comp.getKey()) < 0;
	}

	@Override
	public boolean isEqual(TComparable<String> comp) {
		// TODO Auto-generated method stub
		return key.compareTo(comp.getKey()) == 0;
	}

}
