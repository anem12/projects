

public class TotalDistanceVisitor extends Visitor{
	int totalDistanceMoved=0;
	
	TotalDistanceVisitor(Turtle t){
		aTurtle = t;
	}

	int totalDistance(){
		return totalDistanceMoved;
	}

	@Override
	void visitAssignValue(String name, TurtleInterpreter value) {
		values.setValue(name, value.evaluate(aTurtle, values));
	}

	@Override
	void visitMove(TurtleInterpreter moveDistance) {
		totalDistanceMoved += moveDistance.evaluate(aTurtle, values);
		aTurtle.move(moveDistance.evaluate(aTurtle, values));
	}

	@Override
	void visitPenDown() {
		aTurtle.penDown();
	}

	@Override
	void visitPenUp() {
		aTurtle.penDown();;
	}
	
	@Override
	void visitRepeat(TurtleInterpreter count, TurtleInterpreter commands) {
		for(int i = 0; i < count.evaluate(aTurtle,values); i++)
			commands.accept(this);
	}

	@Override
	void visitTurn(TurtleInterpreter turnAngel) {
		aTurtle.turn(turnAngel.evaluate(aTurtle, values));
	}

}
