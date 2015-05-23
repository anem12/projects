
public class AssignValueCommand extends Command{
	String name;
	int oldValue;
	boolean hasOldValue = false;
	
	AssignValueCommand(String name,int value,Turtle newTurtle){
		this.name = name;
		this.oldValue = value;
		this.aTurtle = newTurtle;
	}
	
	@Override
	public void execute() {
		if(values.containsKey(name)){
			hasOldValue = true;
		}
		values.setValue(name, oldValue);	
	}

	@Override
	public void undo() {
			if (hasOldValue)
				values.setValue(name,oldValue);
			else
				values.removeKey(name);
	}
}
