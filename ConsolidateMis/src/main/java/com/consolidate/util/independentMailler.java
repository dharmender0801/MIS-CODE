package com.consolidate.util;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.consolidate.Model.Quiz2playMisModel;

public class independentMailler {
	public static void sendEmailer(String mailerText, String massage, String filePath) {

		try {
			System.out.println(" mail send started...");
			final String from = "mis@info2cell.com";
			final File file = new File(mailerText);
			Scanner sc = new Scanner(file);
			String to = "";
			while (sc.hasNextLine()) {
				to = to + sc.nextLine();
			}

//			String to = "dharmender.kumar@altruistindia.com";

			System.out.println(to);

			final String username = "mis@info2cell.com";
			final String password = "6}-KVck?5zd";
			// String host = "smtpout.secureserver.net";

			final String host = "r6qf-shqm.accessdomain.com";

			Properties prop = new Properties();
			prop.put("mail.smtp.auth", "true");
//		prop.put("mail.smtp.starttls.enable", "true");
			prop.put("mail.smtp.host", host);
			prop.put("mail.smtp.port", "25");
			System.out.println("Checking");

			Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);

				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			message.setSubject("Quiz2Play Vendor Wap Report");
			BodyPart messageBodyPart = (BodyPart) new MimeBodyPart();
			final String msg = "<html><i>Dear All,</i><br><br><b>" + massage + " ::-</b><br>";
			messageBodyPart.setContent(msg + "<br>", "text/html");

			final Multipart multipart = (Multipart) new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = (BodyPart) new MimeBodyPart();
//			final String filename = "/usr/shfiles/quiz2play_mis/zain_iq/Report.xlsx";

			final DataSource source = (DataSource) new FileDataSource(filePath);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("VendoWap.xlsx");
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport.send(message);

//		String filename = "D://Report.xlsx";
//
//		messageBodyPart.setContent(msg, "text/html");
//		Multipart multipart = new MimeMultipart();
//		multipart.addBodyPart(messageBodyPart);
//		messageBodyPart = (BodyPart) new MimeBodyPart();
//		messageBodyPart = new MimeBodyPart();
//
//		DataSource source = new FileDataSource(filename);
//		messageBodyPart.setDataHandler(new DataHandler(source));
//
//		messageBodyPart.setFileName("ConsolidateReport.xlsx");
//		multipart.addBodyPart(messageBodyPart);
//
//		message.setContent(multipart);
//
//		System.out.println(message.getContent().toString());
//		Transport.send(message);

			System.out.println("Sent message successfully....");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static String createall(List<Quiz2playMisModel> list, String mtd) {
		// TODO Auto-generated method stub

		String body = "";
		for (int i = 0; i < list.size(); ++i) {
			final Quiz2playMisModel mrb = list.get(i);

			body = body + "<tr style=\"width:1200px\"><td bgcolor=\"#98FB98\">" + mrb.getDate() + "</td>"

					+ "<td align=center>" + mrb.getOperatorName() + "</td>" + "<td align=center>" + mrb.getActiveBase()
					+ "</td>" + "<td align=center>" + mrb.getNewSubscription() + "</td>" + "<td align=center>"
					+ mrb.getBilledActivation() + "</td>" + "<td align=center>" + mrb.getNewBilledActivation() + "</td>"
					+ "<td align=center>" + mrb.getRenewalActivation() + "</td>" + "<td align=center>"
					+ mrb.getChurnCount() + "</td>" + "<td align=center>" + mrb.getInvolChurn() + "</td>"
					+ "<td align=center>" + mrb.getVolChurn() + "<td align=center>" + mrb.getNewActivationRevenue()
					+ "</td>" + "<td align=center>" + mrb.getRenewalRevenue() + "</td>" + "<td align=center>"
					+ mrb.getTotalRevenue() + "</td>" + "<td align=center>" + mrb.getMtd() + "</td></tr>";

		}
		return body;

	}

}
