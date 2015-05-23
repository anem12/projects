
public class PenUp implements TurtleInterpreter {
	TurtleInterpreter nextTurtleInterpreter = new NullTurtleInterpreter();
	
	public void accept(Visitor visitee) {
		visitee.visitPenUp();
		nextTurtleInterpreter.accept(visitee);
	}
	
	public int evaluate(Turtle turtleObject, Context values){
		turtleObject.penUp();
		nextTurtleInterpreter.evaluate(turtleObject, values);
		return 0;
	}

	public void setNextInterpreter(TurtleInterpreter next){
		this.nextTurtleInterpreter = next;
	}

}
