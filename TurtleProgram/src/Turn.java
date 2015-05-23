
public class Turn implements TurtleInterpreter {
	TurtleInterpreter turnAngle;
	TurtleInterpreter nextTurtleInterpreter = new NullTurtleInterpreter();
	
	Turn(TurtleInterpreter t){
	turnAngle = t;
	}
	
	public void accept(Visitor visitee) {
		visitee.visitTurn(turnAngle);
		nextTurtleInterpreter.accept(visitee);
	}
	
	public int evaluate(Turtle turtleObject, Context values){
	turtleObject.turn(turnAngle.evaluate(turtleObject,values));
	nextTurtleInterpreter.evaluate(turtleObject, values);
	return 0;
	}
	
	public void setNextInterpreter(TurtleInterpreter next){
		this.nextTurtleInterpreter = next;
	}
}
