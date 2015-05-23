
public class PenUpCommand extends Command{
		
	PenUpCommand(Turtle newTurtle){
		this.aTurtle = newTurtle;
	}
	
	@Override
	public void execute() {
		aTurtle.penUp();
	}

	@Override
	public void undo() {
		aTurtle.penDown();		
	}

}
