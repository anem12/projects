
public class AssignValue implements TurtleInterpreter {
	String name;
	TurtleInterpreter value;
	TurtleInterpreter nextTurtleInterpreter = new NullTurtleInterpreter();
	
	AssignValue(String name, TurtleInterpreter value){
		this.name = name;
		this.value = value;
	}
	
	public void accept(Visitor visitee) {
		visitee.visitAssignValue(name, value);
		nextTurtleInterpreter.accept(visitee);
	}
	
	public int evaluate(Turtle turtleObject, Context values){
		values.setValue(name, value.evaluate(turtleObject, values));
		nextTurtleInterpreter.evaluate(turtleObject, values);
		return 0;
	}

	public void setNextInterpreter(TurtleInterpreter next){
		this.nextTurtleInterpreter = next;
	}
}
