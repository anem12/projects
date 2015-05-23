import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MockUrlParser extends URLParser {

	MockUrlParser(File file) {
		super(file);
	}

	@Override
	URLConnection urlFactory(String url) {
		URLConnection urlcon = mock(URLConnection.class);
		when(urlcon.getLastModified()).thenReturn(1429811410934L);
		try {
			when(urlcon.getURL()).thenReturn(new URL(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return urlcon;
	}

}
