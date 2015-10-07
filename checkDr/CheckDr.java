// Checking Dr.

import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;


public class CheckDr {
	public static void main(String[] args) throws Exception {
		//String dr_url = "https://reg.ntuh.gov.tw/WebAdministration/DtQueryB.aspx?Name=%E7%8E%8B%E6%B2%BB%E5%85%83";		// real Dr url
		String dr_url = "https://reg.ntuh.gov.tw/WebAdministration/DtQueryA.aspx?x=SABvAHMAcAA9AFQAMAAmAE4AYQBtAGUAPQAhfE56wHk1";	// test Dr url
		int n = 1;			// Check number
		int retry = 2000;	// retry time
		String sta = "";		// Dr. status
		
		while (sta.equals("")) {
			try {
				Thread.sleep(retry);
				Document doc = Jsoup.connect(dr_url).get();
				
				Elements td = doc.select("td");
				sta = td.get(20).text();
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
