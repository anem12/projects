import java.io.File;
import java.util.ArrayList;


public class WebPageUpdateNotifier {
	public static void main(String[] args) throws InterruptedException {
			URLParser parse = new URLParser(new File("File.txt"));
			ArrayList<ObservableURL> subjects = parse.getSubjects();
			while(true){
				Thread.sleep(1000);
			for (ObservableURL objects : subjects) {
				objects.checkForModification();
				}
			}
	}
}
