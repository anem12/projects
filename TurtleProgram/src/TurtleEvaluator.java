
public class TurtleEvaluator{
	TurtleInterpreter syntaxTree;
	Context values = new Context();
	
	TurtleEvaluator(TurtleInterpreter interpreterTree){
		this.syntaxTree = interpreterTree;
	}
	public void accept(Visitor visitee) {
		syntaxTree.accept(visitee);
	}
	
	public void evaluate(Turtle turtleObject) {
		syntaxTree.evaluate(turtleObject, values);
	}
}
