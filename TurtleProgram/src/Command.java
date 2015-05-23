
public abstract class Command {
	Turtle aTurtle;
	Context values = new Context();
	public abstract void execute();
	public abstract void undo();
}
