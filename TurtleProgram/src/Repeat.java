
public class Repeat implements TurtleInterpreter {
	TurtleInterpreter count;
	TurtleInterpreter commands;
	TurtleInterpreter nextTurtleInterpreter = new NullTurtleInterpreter();
	
	Repeat(TurtleInterpreter repeatValue, TurtleInterpreter turtleInterpreters){
	this.count = repeatValue;
	this.commands = turtleInterpreters;
	}
	
	public void accept(Visitor visitee) {
		visitee.visitRepeat(count, commands);
		nextTurtleInterpreter.accept(visitee);
	}

	public int evaluate(Turtle turtleObject, Context values){
	for(int i = 0; i < count.evaluate(turtleObject,values); i++){
		commands.evaluate(turtleObject,values);
	}
	nextTurtleInterpreter.evaluate(turtleObject, values);
	return 0;
	}

	public void setNextInterpreter(TurtleInterpreter next) {
		this.nextTurtleInterpreter = next;		
	}
	
}
