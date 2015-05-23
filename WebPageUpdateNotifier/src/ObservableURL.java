import java.io.File;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.sql.Date;
import java.sql.Time;
import java.util.Observable;

public class ObservableURL extends Observable {
	URLConnection connection;
	long time;
	URL nameURL;
    Momento momento;
    InetAddress address;
	public ObservableURL(URLConnection connect) {
		nameURL = connect.getURL();
		this.connection = connect;
		time = connect.getLastModified();
		try {
			address = InetAddress.getByName(nameURL.getHost());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String[] urlSplit = nameURL.toString().split("/");
		int length = urlSplit.length-1;
		momento = new Momento(new File(address.getHostAddress()+urlSplit[length-1]
				              +urlSplit[length]+ ".txt"));
		checkPreviousValues();
	}
	
	public Time checkPreviousValues(){
		String read = momento.read();
		if ( read!= null ){
			time = Long.parseLong(read);
			}
		return new Time(time);
	}
	
	public void checkForModification(){
		setValue(connection);
	}

	public String getValue() {
		return nameURL.toString() + " " + new Date(time)+ " "+ new Time(time);
	}

	public void setValue(URLConnection newConnection) {
		if (time != newConnection.getLastModified()) {
			this.connection = newConnection;
			time = newConnection.getLastModified();
			momento.setState(Long.toString(time));
			setChanged();
			notifyObservers();
		}
		}
}
