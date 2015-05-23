import java.util.Observable;
import java.util.Observer;

public class TranscriptObserver implements Observer {
	ObservableURL urlconnection = null;

	public TranscriptObserver(ObservableURL ov) {
		this.urlconnection = ov;
	}

	public void update(Observable obs, Object obj) {
		if (obs == urlconnection) {
			System.out.println(urlconnection.getValue());
		}
	}

}
