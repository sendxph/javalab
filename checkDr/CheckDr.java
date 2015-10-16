// Checking Dr.

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;


public class CheckDr {
	public static void main(String[] args) throws Exception {
		//String dr_url = "https://reg.ntuh.gov.tw/WebAdministration/DtQueryB.aspx?Name=%E7%8E%8B%E6%B2%BB%E5%85%83";		// real Dr url
		String dr_url = "https://reg.ntuh.gov.tw/WebAdministration/DtQueryA.aspx?x=SABvAHMAcAA9AFQAMAAmAE4AYQBtAGUAPQAhfE56wHk1";	// test Dr url
		int n = 1;			// the number of checking
		int retry = 1000;	// retry interval time
		String sta = "";		// Dr. status
		int hasFree = 0;		// 有多少空缺數可以掛號

		while (sta.equals("")) {
			try {
				Thread.sleep(retry);
								
				Document doc = Jsoup.connect(dr_url).get();
				Elements td = doc.select("td");
				System.out.printf("第 %d 次檢查: ", n);
				System.out.printf("有 %d 個td\n", td.size());
				for (int i=0; i < td.size(); i++) {
					sta = td.get(i).text();
					if ( ". 掛號 .".equals(sta) ) {
						hasFree += 1;
					}
				}

				if ( hasFree == 0 ) {
					System.out.println(">> XXX 仍然無法掛號！ XXX");
				} else {
					System.out.printf(">> %d 個時段有空缺，請趕快掛號！\n", hasFree);
					// 發送 mail 放在此
				}

				n += 1;
				sta = "";
				hasFree = 0;
				System.out.println();
			} catch (Exception e) {
				System.out.println("Has an exception");
			}	
		}
	}
}
