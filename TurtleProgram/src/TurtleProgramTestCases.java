import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.util.Iterator;

import org.junit.Test;

public class TurtleProgramTestCases {
	File test1 = new File("turtleFile1.txt");
	File test2 = new File("turtleFile2.txt");
	File test3 = new File("turtleFile3.txt");
	File test4 = new File("turtleFile4.txt");
	File test5 = new File("turtleFile5.txt");
	File test6 = new File("turtleFile6.txt");
	File test7 = new File("turtleFile7.txt");

	@Test
	public void testCommandVisitor(){
		Turtle newTurtle = new Turtle();
		TurtleParser turtleinterpreter = new TurtleParser(test7);
		TurtleEvaluator syntaxtree7 = turtleinterpreter.getTurtleSyntaxTree();
		CommandVisitor visitee = new CommandVisitor(newTurtle);
		syntaxtree7.accept(visitee);
		Iterator<Command> aCommand = visitee.getCommandList().iterator();
		Command next;
		assertEquals(8, visitee.getCommandList().size());
		if(aCommand.hasNext()){
			next = 	aCommand.next();
			next.execute();
			assertEquals("Point2D.Double[12.0, 0.0]", newTurtle.location().toString());
		   	next.undo();
		   	assertEquals("Point2D.Double[0.0, 0.0]", newTurtle.location().toString());
		   	next.execute();
		   	assertEquals("Point2D.Double[12.0, 0.0]", newTurtle.location().toString());
		}
		
		if(aCommand.hasNext()){
			next = 	aCommand.next();
			next.execute();
			assertEquals("Point2D.Double[12.0, 0.0]", newTurtle.location().toString());
		   	next.undo();
		   	assertEquals("Point2D.Double[12.0, 0.0]", newTurtle.location().toString());
		   	next.execute();
		   	assertEquals("Point2D.Double[12.0, 0.0]", newTurtle.location().toString());
		}
		
		if(aCommand.hasNext()){
			next = 	aCommand.next();
			next.execute();
			assertEquals("Point2D.Double[12.0, 0.0]", newTurtle.location().toString());
		   	next.undo();
		   	assertEquals("Point2D.Double[12.0, 0.0]", newTurtle.location().toString());
		   	next.execute();
		   	assertEquals("Point2D.Double[12.0, 0.0]", newTurtle.location().toString());
		}
		
		if(aCommand.hasNext()){
			next = 	aCommand.next();
			next.execute();
			assertEquals("Point2D.Double[12.0, 2.0]", newTurtle.location().toString());
		   	next.undo();
		   	assertEquals("Point2D.Double[12.0, 0.0]", newTurtle.location().toString());
		   	next.execute();
		   	assertEquals("Point2D.Double[12.0, 2.0]", newTurtle.location().toString());
		}
		
		if(aCommand.hasNext()){
			next = 	aCommand.next();
			next.execute();
			assertEquals("Point2D.Double[12.0, 4.0]", newTurtle.location().toString());
		   	next.undo();
		   	assertEquals("Point2D.Double[12.0, 2.0]", newTurtle.location().toString());
		   	next.execute();
		   	assertEquals("Point2D.Double[12.0, 4.0]", newTurtle.location().toString());
		}
		
		if(aCommand.hasNext()){
			next = 	aCommand.next();
			next.execute();
			assertEquals("Point2D.Double[12.0, 4.0]", newTurtle.location().toString());
		   	next.undo();
		   	assertEquals("Point2D.Double[12.0, 4.0]", newTurtle.location().toString());
		   	next.execute();
		   	assertEquals("Point2D.Double[12.0, 4.0]", newTurtle.location().toString());
		}
		
		if(aCommand.hasNext()){
			next = 	aCommand.next();
			next.execute();
			assertEquals("Point2D.Double[12.0, 6.0]", newTurtle.location().toString());
		   	next.undo();
		   	assertEquals("Point2D.Double[12.0, 4.0]", newTurtle.location().toString());
		   	next.execute();
		   	assertEquals("Point2D.Double[12.0, 6.0]", newTurtle.location().toString());
		}
		
		if(aCommand.hasNext()){
			next = 	aCommand.next();
			next.execute();
			assertEquals("Point2D.Double[12.0, 6.0]", newTurtle.location().toString());
		   	next.undo();
		   	assertEquals("Point2D.Double[12.0, 6.0]", newTurtle.location().toString());
		   	next.execute();
		   	assertEquals("Point2D.Double[12.0, 6.0]", newTurtle.location().toString());
		}
		
		assertFalse(aCommand.hasNext());	
	}
	
	@Test
	public void testTotalDistanceVisitor(){
		Turtle newTurtle = new Turtle();
		TurtleParser turtleinterpreter = new TurtleParser(test6);
		TurtleEvaluator syntaxtree6 = turtleinterpreter.getTurtleSyntaxTree();
		TotalDistanceVisitor visitee = new TotalDistanceVisitor(newTurtle);
		syntaxtree6.accept(visitee);
		assertEquals(120, visitee.totalDistance());		
	}
	
	@Test
	public void testTurtleFile1() {
		Turtle newTurtle = new Turtle();
		TurtleParser turtleinterpreter = new TurtleParser(test1);
		TurtleEvaluator syntaxtree1 = turtleinterpreter.getTurtleSyntaxTree();
		syntaxtree1.evaluate(newTurtle);	
		assertEquals("Point2D.Double[22.99038105676658, 27.5]",newTurtle.location().toString());
		Turtle aTurtle = new Turtle();
		TotalDistanceVisitor visitee = new TotalDistanceVisitor(aTurtle);
		syntaxtree1.accept(visitee);
		assertEquals(45, visitee.totalDistance());
		CommandVisitor aCommandVisitee = new CommandVisitor(aTurtle);
		syntaxtree1.accept(aCommandVisitee);
		assertEquals(6, aCommandVisitee.getCommandList().size());
	}
	
	@Test
	public void testTurtleFile2() {
		Turtle aTurtle = new Turtle();
		TurtleParser turtleinterpreter = new TurtleParser(test2);
		TurtleEvaluator syntaxtree2 = turtleinterpreter.getTurtleSyntaxTree();
		syntaxtree2.evaluate(aTurtle);	
		assertEquals("Point2D.Double[53.11180453793251, 29.88625273369191]",aTurtle.location().toString());
		TotalDistanceVisitor visitee = new TotalDistanceVisitor(aTurtle);
		syntaxtree2.accept(visitee);
		assertEquals(62, visitee.totalDistance());
		CommandVisitor aCommandVisitee = new CommandVisitor(aTurtle);
		syntaxtree2.accept(aCommandVisitee);
		assertEquals(26, aCommandVisitee.getCommandList().size());
	}
	
	@Test
	public void testTurtleFile3() {
		Turtle aTurtle = new Turtle();
		TurtleParser turtleinterpreter = new TurtleParser(test3);
		TurtleEvaluator syntaxtree3 = turtleinterpreter.getTurtleSyntaxTree();
		syntaxtree3.evaluate(aTurtle);	
		assertEquals("Point2D.Double[9.999999999999998, 1.7763568394002505E-15]",aTurtle.location().toString());
		TotalDistanceVisitor visitee = new TotalDistanceVisitor(aTurtle);
		syntaxtree3.accept(visitee);
		assertEquals(50, visitee.totalDistance());
		CommandVisitor aCommandVisitee = new CommandVisitor(aTurtle);
		syntaxtree3.accept(aCommandVisitee);
		assertEquals(11, aCommandVisitee.getCommandList().size());
	}
	@Test
	public void testTurtleFile4() {
		Turtle aTurtle = new Turtle();
		TurtleParser turtleinterpreter = new TurtleParser(test4);
		TurtleEvaluator syntaxtree4 = turtleinterpreter.getTurtleSyntaxTree();
		syntaxtree4.evaluate(aTurtle);	
		assertEquals("Point2D.Double[9.999999999999998, 1.7763568394002505E-15]",aTurtle.location().toString());
		TotalDistanceVisitor visitee = new TotalDistanceVisitor(aTurtle);
		syntaxtree4.accept(visitee);
		assertEquals(50, visitee.totalDistance());
		CommandVisitor aCommandVisitee = new CommandVisitor(aTurtle);
		syntaxtree4.accept(aCommandVisitee);
		assertEquals(11, aCommandVisitee.getCommandList().size());
	}
	
	@Test
	public void testTurtleFile5() {
		Turtle aTurtle = new Turtle();
		TurtleParser turtleinterpreter = new TurtleParser(test5);
		TurtleEvaluator syntaxtree5 = turtleinterpreter.getTurtleSyntaxTree();
		syntaxtree5.evaluate(aTurtle);	
		assertEquals("Point2D.Double[0.0, -48.0]", aTurtle.location().toString());
		TotalDistanceVisitor visitee = new TotalDistanceVisitor(aTurtle);
		syntaxtree5.accept(visitee);
		assertEquals(352, visitee.totalDistance());
		CommandVisitor aCommandVisitee = new CommandVisitor(aTurtle);
		syntaxtree5.accept(aCommandVisitee);
		assertEquals(126, aCommandVisitee.getCommandList().size());
	}
}
