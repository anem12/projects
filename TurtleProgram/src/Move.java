
public class Move implements TurtleInterpreter {
	TurtleInterpreter moveDistance;
	TurtleInterpreter nextTurtleInterpreter = new NullTurtleInterpreter();
	
	Move(TurtleInterpreter d){
	moveDistance = d;
	}
	
	public void accept(Visitor visitee) {
		visitee.visitMove(moveDistance);
		nextTurtleInterpreter.accept(visitee);
	}
	
	public int evaluate(Turtle turtleObject, Context values){
		turtleObject.move(moveDistance.evaluate(turtleObject,values));
		nextTurtleInterpreter.evaluate(turtleObject, values);
		return 0;
	}

	public void setNextInterpreter(TurtleInterpreter next){
		this.nextTurtleInterpreter = next;
	}
	
}
