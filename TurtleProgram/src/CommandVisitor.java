import java.util.ArrayList;


public class CommandVisitor extends Visitor{
	
	ArrayList<Command> commandObjects = new ArrayList<Command>();
	Turtle aTurtle = new Turtle();
			
	CommandVisitor(Turtle newTurtle){
		aTurtle = newTurtle;
	}

	ArrayList<Command> getCommandList(){
		return commandObjects;
	}

	@Override
	void visitAssignValue(String name, TurtleInterpreter value) {
		int thisValue = value.evaluate(aTurtle, values);
		values.setValue(name,thisValue );
		commandObjects.add(new AssignValueCommand(name,thisValue,aTurtle));
	}

	@Override
	void visitMove(TurtleInterpreter moveDistance) {
		commandObjects.add(new MoveCommand(moveDistance,aTurtle));
	}

	@Override
	void visitPenDown() {
		commandObjects.add(new PenDownCommand(aTurtle));
	}

	@Override
	void visitPenUp() {
		commandObjects.add(new PenUpCommand(aTurtle));
	}

	@Override
	void visitRepeat(TurtleInterpreter count, TurtleInterpreter commands) {
		for(int i = 0; i < count.evaluate(aTurtle,values); i++){
			commands.accept(this);
		}
		}
	
	@Override
	void visitTurn(TurtleInterpreter turnAngel) {
		commandObjects.add(new TurnCommand(turnAngel,aTurtle));
	}
}
