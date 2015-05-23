import java.util.Hashtable;


public class Context {
	private Hashtable<String,Integer> values = new Hashtable<String,Integer>();
	public boolean containsKey(String variableName){
		return values.containsKey(variableName);
	}
	
	public int getValue( String variableName ) {
		return values.get( variableName );
	}
	public void removeKey(String variableName){
		values.remove(variableName);
	}
	public void setValue( String variableName, int value ) {
		values.put(variableName, value );
	}
}
