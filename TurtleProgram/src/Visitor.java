public abstract class Visitor {
	Turtle aTurtle;
	Context values = new Context();
	abstract void visitAssignValue(String name, TurtleInterpreter value);
	abstract void visitMove(TurtleInterpreter moveDistance);
	abstract void visitPenDown();
	abstract void visitPenUp();
	abstract void visitRepeat(TurtleInterpreter count, TurtleInterpreter interpreters);
	abstract void visitTurn(TurtleInterpreter turnAngel);
}
