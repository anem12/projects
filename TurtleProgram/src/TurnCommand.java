
public class TurnCommand extends Command{
	TurtleInterpreter angleValue;
	
	TurnCommand(TurtleInterpreter angleValue,Turtle newTurtle){
		this.angleValue = angleValue;
		this.aTurtle = newTurtle;
	}
	
	@Override
	public void execute() {
		aTurtle.turn(angleValue.evaluate(aTurtle, values));
	}
	@Override
	public void undo() {
		aTurtle.turn(-angleValue.evaluate(aTurtle, values));	
	}
}
