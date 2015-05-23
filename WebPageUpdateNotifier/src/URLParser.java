import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Scanner;

public class URLParser {
	Scanner scanner;
	Observer tob;
	ArrayList<ObservableURL> subjects = new ArrayList<ObservableURL>();
	
	URLParser(File file) {
		try {
			scanner = new Scanner(file)
					.useDelimiter("\\s* \\s*|\\s*\n\\s*");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void evaluate() {
		while (scanner.hasNext()) {
			String nextString = scanner.next();

			if (nextString.startsWith("http:")) {
				subjects.add(new ObservableURL(urlFactory(nextString)));
			}

			else if (nextString.startsWith("transcript") && subjects != null) {
				int index = subjects.size() - 1;
				tob = new TranscriptObserver(subjects.get(index));
				subjects.get(index).addObserver(tob);
			}

			else if (nextString.startsWith("mail") && subjects != null) {
				int index = subjects.size() - 1;
				tob = new MailObserver(subjects.get(index));
				subjects.get(index).addObserver(tob);
			}
		}
	}

	public ArrayList<ObservableURL> getSubjects() {
		evaluate();
		return subjects;
	}
	
	URLConnection urlFactory(String url) {
		try {
			return (new URL(url)).openConnection();
			} catch (IOException e) {
				e.printStackTrace();
				}
		return null;
		}
	}
