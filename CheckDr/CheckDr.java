// Checking Dr. Open

import java.net.URL;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;


public class CheckDr {
	public static void main(String[] args) throws Exception {
		URL url = new URL("https://reg.ntuh.gov.tw/WebAdministration/ClinicDt.aspx?x=QQBNAFAATQA9ADEAJgBEAHQAQwBvAGQAZQA9ADYAMAAwADAAMAAyACYAQwBsAGkAbgBpAGMAQwBvAGQAZQA9ADAAMQAmAFMAdQBiAEQAZQBwAHQAQwBvAGQAZQA9ADAAMgAmAEgAbwBzAHAAPQBUADAAJgBEAGUAcAB0AD0ATQBFAEQAJgBEAGEAdABlAD0AMgAwADEANQAvADEAMAAvADAAMgAmAFMAcgB2AEkARAA9ADIAMgAyADgAOAAyADcAJgBUAGUAbQBwAEkARAA9ADIAMgA5ADUA0");
		Document doc = Jsoup.parse(url, 3000);
		
	}
}