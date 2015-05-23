
public class MoveCommand extends Command{
	TurtleInterpreter moveDistance;
		
	MoveCommand(TurtleInterpreter command,Turtle newTurtle){
		this.moveDistance = command;
		this.aTurtle = newTurtle;
	}
	
	@Override
	public void execute() {
		aTurtle.move(moveDistance.evaluate(aTurtle, values));
	}

	@Override
	public void undo() {
		aTurtle.move(-moveDistance.evaluate(aTurtle, values));	
	}

}
