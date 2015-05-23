import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

public class TestNotifier {
	File test1 = new File("File.txt");
	File testTranscript = new File("test-transcript.txt");
	File testEmail = new File("test-Email.txt");
	URLConnection urlcon;
	long value = 1429811410934L;

	public URLConnection create() {
		Random randomGenerator = new Random();
		urlcon = mock(URLConnection.class);
		int randomInt = randomGenerator.nextInt(100000);
		value += randomInt;
		when(urlcon.getLastModified()).thenReturn(value);
		return urlcon;
	}

	@Test
	public void testEmail() throws InterruptedException {
		MockUrlParser parse = new MockUrlParser(testEmail);
		ArrayList<ObservableURL> Objs = parse.getSubjects();
		for (ObservableURL objects : Objs) {
			objects.setValue(create());
				}
	}
	
	//run this test couple of times by changing last modified value to see
	//if the state is saving
	@Test
	public void testMomento() throws IOException {
		urlcon = mock(URLConnection.class);
		when(urlcon.getLastModified()).thenReturn(142981141094L);
		ObservableURL obj = new ObservableURL((new URL("http://en.wikipedia.org/wiki/Washington_%28state%29")).openConnection());
		System.out.println("previous"+obj.checkPreviousValues());
		 obj.setValue(urlcon);
		System.out.println("current"+obj.checkPreviousValues());
	}
	
	@Test
	public void testTranscript() throws InterruptedException {
		MockUrlParser parse = new MockUrlParser(testTranscript);
		ArrayList<ObservableURL> Objs = parse.getSubjects();
		for (ObservableURL objects : Objs) {
			objects.setValue(create());
				}
	}
	@Test
	public void testTranscriptEmail() throws InterruptedException {
		MockUrlParser parse = new MockUrlParser(test1);
		ArrayList<ObservableURL> Objs = parse.getSubjects();
		for (ObservableURL objects : Objs) {
			objects.setValue(create());
				}
	}
}
