// Checking Dr.

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


public class CheckDr {
	public static void main(String[] args) throws Exception {
		// Set Dr name & Dr URL
		String drName = "眼科：王清泓醫師";
		String drURL = "https://reg.ntuh.gov.tw/WebAdministration/DtQueryB.aspx?Name=%u738b%u6cbb%u5143";
		
		//String drName = "甲亢：王治元醫師";
		//String drURL = "https://reg.ntuh.gov.tw/WebAdministration/DtQueryB.aspx?Name=%E7%8E%8B%E6%B2%BB%E5%85%83";
		
		//String drName = "測試：簡穎秀醫師";
		//String drURL = "https://reg.ntuh.gov.tw/WebAdministration/DtQueryA.aspx?x=SABvAHMAcAA9AFQAMAAmAE4AYQBtAGUAPQAhfE56wHk1";

		// Control parameters
		int n = 1;			// the number of checking
		int retry = 2000;	// retry interval time
		String sta = "";		// Dr. status
		int hasFree = 0;		// 有多少空缺數可以掛號

		// set mail function
		String host = "localhost";
		String from = "CheckDr@jc.com";
		String toJC = "macamd@gmail.com";
		String toYv = "yoyo67890@gmail.com";
		String subj = "台大可掛號通知";
		String msg = "";

		while (sta.equals("")) {
			try {
				Thread.sleep(retry);
								
				Document doc = Jsoup.connect(drURL).get();
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
					System.out.printf(">> !!! %s仍然無法掛號 !!!", drName);
					System.out.println();
				} else {
					msg = ">> " + drName + "，" + String.format("%d 個時段有空缺，趕快掛號！", hasFree);
					System.out.println(msg);
					// Send mail in here
					sendMail(host, from, toJC, subj, msg);
					//sendMail(host, from, toYv, subj, msg);
				}

				n += 1;
				sta = "";
				msg = "";
				hasFree = 0;
				System.out.println();
			} catch (Exception e) {
				System.out.println("Has an exception");
			}	
		}
	}


	static void sendMail(String h, String f, String t, String s, String m) {
		try {
			Properties props = System.getProperties();
			props.put("mail.smtp.host", h);
			Session mailSession = Session.getInstance(props, null);
			Message mailMessage = new MimeMessage(mailSession);
			mailMessage.setFrom(new InternetAddress(f));
			mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(t));
			mailMessage.setSubject(s);
			mailMessage.setText(m);
			Transport.send(mailMessage);
			System.out.println("Mail was sent successfully.");
		} catch (Exception e) {
			System.out.println("sendMail has an exception");
			System.out.println(e.getMessage());
		}
	}
}
