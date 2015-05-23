import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;


public class TurtleParser{
	/* when finds repeat stores the current index of syntaxList, count of repeats*/
	class RepeatStack {
	TurtleInterpreter repeatCount;
	int startIndex;
	RepeatStack(TurtleInterpreter value, int index){
		this.repeatCount = value;
		this.startIndex = index;
	}
	}
 	Scanner scanner;
	ArrayList<TurtleInterpreter> syntaxList = new ArrayList<TurtleInterpreter>();
	Stack<RepeatStack> stackData = new Stack<RepeatStack>();
	ArrayList<String> variableNames= new ArrayList<String>();
	RepeatStack data;
	int size;

	TurtleParser(File file){
		try {
			scanner = new Scanner(file).useDelimiter("\\s*=\\s*|\\s* \\s*|\\s*\n\\s*");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}
		
	public void evaluate(){
		while(scanner.hasNext()){
			String nextString = scanner.next();
			
			if (nextString.startsWith("$"))
				processStartsWith$(nextString);
			
			else if(nextString.equals("move"))
				processMove();				
			
			else if(nextString.equals("turn"))
				processTurn();
			
			else if(nextString.equals("penDown")){
				syntaxList.add(new PenDown());
				setNext();
			}
			
			else if(nextString.equals("penUp")){
				syntaxList.add(new PenUp());
				setNext();
			}
			
			else if(nextString.equals("repeat"))
				processRepeat();
			
			else if(nextString.equals("end"))
				processEnd();
			
			else
				throw new NoSuchElementException();
		}
		scanner.close();
	}
	
	public TurtleEvaluator getTurtleSyntaxTree(){
		evaluate();
		return new TurtleEvaluator(syntaxList.get(0));
	}
	
	public void processEnd(){
		data = stackData.pop();
		ArrayList<TurtleInterpreter> repeatCommands = new ArrayList<TurtleInterpreter>();
		repeatCommands.addAll(syntaxList.subList(data.startIndex,syntaxList.size()));
		syntaxList.removeAll(repeatCommands);
		syntaxList.add(new Repeat(data.repeatCount,repeatCommands.get(0)));
		setNext();
	}
	
	public void processMove(){
		String nextString = scanner.next();
		if(variableNames.contains(nextString))
		syntaxList.add(new Move(new Variable(nextString)));
		else
			syntaxList.add(new Move(new Constant(Integer.valueOf(nextString))));
		setNext();
	}
	
	public void processRepeat(){
		String nextName = scanner.next();
		if(variableNames.contains(nextName))
			stackData.push(new RepeatStack(new Variable(nextName),syntaxList.size()));
		else{
			int value = Integer.valueOf(nextName);
			stackData.push(new RepeatStack(new Constant(value),syntaxList.size()));
		}
	}
	
	public void processStartsWith$(String name){
		variableNames.add(name);
		String nextName = scanner.next();
		if(variableNames.contains(nextName))
			syntaxList.add(new AssignValue(name,new Variable(nextName)));
		else{
			int value = Integer.valueOf(nextName);
			syntaxList.add(new AssignValue(name,(new Constant(value))));
		}
		setNext();
	}
	
	public void processTurn(){
		String nextString = scanner.next();
		if(variableNames.contains(nextString))
		syntaxList.add(new Turn(new Variable(nextString)));
		else
			syntaxList.add(new Turn(new Constant(Integer.valueOf(nextString))));
		setNext();
	}
	
	public void setNext(){
		size = syntaxList.size();
		if(size > 1)
			syntaxList.get(size-2).setNextInterpreter(syntaxList.get(size-1));
	}
}
