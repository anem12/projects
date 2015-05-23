
public class Variable implements TurtleInterpreter {
	private String name;
	public Variable( String name ) {
		this.name = name;
	}
	public void accept(Visitor visitee) {
		// TODO Auto-generated method stub
	}
	public int evaluate( Turtle turtleObject, Context values ) {
		return values.getValue(name);
	}
	public void setNextInterpreter(TurtleInterpreter next) {
		// TODO Auto-generated method stub
		
	}
}
