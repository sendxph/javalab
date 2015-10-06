// Checking Dr.

import java.net.URL;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;


public class CheckDr {
	public static void main(String[] args) throws Exception {
		String dr_url = "https://reg.ntuh.gov.tw/WebAdministration/DtQueryB.aspx?Name=%E7%8E%8B%E6%B2%BB%E5%85%83";		// Set Dr. url
		int retry = 10;		// Set retry time
		String sta = "";	// Dr. status
		
		URL url = new URL(dr_url);
		Document doc = Jsoup.parse(url, 3000);
		Elements td = doc.select("td");
		//System.out.println(td.get(100).text());
		System.out.println(td.get(19).text());
		if ( (td.get(19).text()).equals("停止掛號") ) {
			System.out.println("No......無法掛號");
		} else {
			System.out.println("Yes.....可以掛號了!!");
		}
	}
}
