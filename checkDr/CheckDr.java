// Checking Dr.

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


public class CheckDr {
	public static void main(String[] args) throws Exception {
		/* Set Dr name & Dr URL */
		String drName = "台大眼科：王清泓醫師";
		String drURL = "https://reg.ntuh.gov.tw/webadministration/DtQueryA.aspx?x=SABvAHMAcAA9AFQAMAAmAE4AYQBtAGUAPQCLcwVu02w1";
		
		//String drName = "台大甲亢：王治元醫師";
		//String drURL = "https://reg.ntuh.gov.tw/WebAdministration/DtQueryB.aspx?Name=%E7%8E%8B%E6%B2%BB%E5%85%83";
		
		//String drName = "台大測試：簡穎秀醫師";
		//String drURL = "https://reg.ntuh.gov.tw/WebAdministration/DtQueryA.aspx?x=SABvAHMAcAA9AFQAMAAmAE4AYQBtAGUAPQAhfE56wHk1";

		// Control parameters
		int retry = 2000;	// retry interval time
		String sta = "";		// Dr. status
		int hasFree = 0;		// 有多少空缺數可以掛號
		String msg = "";		// message show to console

		// set mail properties
		String host = "localhost";
		String from = "CheckDr@jc.com";
		String toJC = "macamd@gmail.com";
		String toYv = "yoyo67890@gmail.com";
		String subj = "台大可掛號通知";

		while (sta.equals("")) {
			try {
				//Thread.sleep(retry);
								
				Document doc = Jsoup.connect(drURL).get();
				Elements td = doc.select("td");
				System.out.printf("%s 檢查: ", showTime());
				System.out.printf("共有 %d 個td\n", td.size());
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

				sta = "";
				msg = "";
				hasFree = 0;
				System.out.println();
			} catch (Exception e) {
				System.out.println("Has an exception");
			}	
		}
	}


	// send mail if if find free number of Dr
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


	// show current date and time
	static String showTime() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd, HH:ss E");
		return dateFormat.format(date);
	}
}
