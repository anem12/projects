
public class PenDownCommand extends Command{
		
	PenDownCommand(Turtle newTurtle){
		this.aTurtle = newTurtle;
	}

	@Override
	public void execute() {
		aTurtle.penDown();		
	}

	@Override
	public void undo() {
		aTurtle.penUp();
	}

}
