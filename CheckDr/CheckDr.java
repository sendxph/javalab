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
		int n = 1;			// Check number
		int retry = 5000;	// retry time
		String sta = "";		// Dr. status
		
		while (sta.equals("")) {
			try {
				Thread.sleep(retry);
				URL url = new URL(dr_url);
				Document doc = Jsoup.parse(url, 3000);
				
				Elements td = doc.select("td");
				sta = td.get(19).text();
				System.out.println(String.format("第 %d 次檢查:", n));
				System.out.println(">> " + sta);
				if ( sta.equals(". 掛號 .") ) {
					n += 1;
					System.out.println(">> ^^可以掛號了^^");
				} else {
					n += 1;
					System.out.println(">> !!仍不能掛號!!");
					sta = "";
				}
			} catch (Exception e) {
				System.out.println("Has an exception");
			}	
		}
	}
}
