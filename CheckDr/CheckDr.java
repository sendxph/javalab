// Checking Dr. Open

import java.net.URL;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;


public class CheckDr {
	public static void main(String[] args) throws Exception {
		URL url = new URL("https://reg.ntuh.gov.tw/WebAdministration/DtQueryB.aspx?Name=%E7%8E%8B%E6%B2%BB%E5%85%83");
		Document doc = Jsoup.parse(url, 3000);
		Elements sta = doc.select("td");
		System.out.println(sta.get(19).text());
	}
}