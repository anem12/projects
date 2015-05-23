
public class Constant implements TurtleInterpreter {
	int value;
	public Constant( int value){
		this.value = value;
	}
	public void accept(Visitor visitee) {
		// TODO Auto-generated method stub
	}
	public int evaluate(Turtle turtleObject, Context values){
		return value;
	}
	public void setNextInterpreter(TurtleInterpreter next) {
		// TODO Auto-generated method stub
	}
}
