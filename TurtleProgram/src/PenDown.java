
public class PenDown implements TurtleInterpreter {
	TurtleInterpreter nextTurtleInterpreter = new NullTurtleInterpreter();
	
	public void accept(Visitor visitee) {
		visitee.visitPenDown();
		nextTurtleInterpreter.accept(visitee);
	}
	
	public int evaluate(Turtle turtleObject, Context values){
		turtleObject.penDown();
		nextTurtleInterpreter.evaluate(turtleObject, values);
		return 0;
	}

	public void setNextInterpreter(TurtleInterpreter next){
		this.nextTurtleInterpreter = next;
	}
}
