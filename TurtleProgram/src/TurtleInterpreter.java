
public interface TurtleInterpreter {
	public void accept(Visitor visitee);
	public int evaluate(Turtle turtleObject, Context values);
	public void setNextInterpreter(TurtleInterpreter next);
}
