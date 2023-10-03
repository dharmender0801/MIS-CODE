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

import org.springframework.stereotype.Component;

import com.consolidate.Model.Quiz2playMisModel;

@Component
public class sendEmail {
	static double newActRev = 0;
	static double RenActRev = 0;
	static double totalRevAtpl = 0;
	static double grossRev = 0;
	static double totalMtd = 0;
	static double grossMtd = 0;

	public static void sendEmailer(Map<String, List<Quiz2playMisModel>> oneDay) {
		try {
			System.out.println(" mail send started...");
			final String from = "mis@info2cell.com";
			final File file = new File("/home/mailer.txt");
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

			message.setSubject("Quiz2Play MIS Report");
			BodyPart messageBodyPart = (BodyPart) new MimeBodyPart();
			final String msg = "<html><i>Dear All,</i><br><br><b>Please find below the snapshot ::-</b><br>";
			final String table = "<table border=1 bordercolor=Black border=1 cellpadding=0 "
					+ "cellspacing=0 style=\"width:1000px\"> <tr bgcolor=\"#98FB98\">"
					+ "<td style=\"width:45%;text-align=center;padding:10px\"><b>DATE</b>"
					+ "<td style=\"width:50%;text-align=center;padding:10px\">Operator Name</td>"
					+ "<td style=\"width:50%;text-align=center;padding:10px\">Active Base</td>"
					+ "<td style=\"width:50%;text-align=center;padding:10px\">New Subscription</td>"
					+ "<td style=\"width:50%;text-align=center;padding:10px\">Billed Activation</td>"
					+ "<td style=\"width:50%;text-align=center;padding:10px\">New Billed Activation</td>"
					+ "<td style=\"width:50%;text-align=center;padding:10px\">Renewal Activation</td>"
					+ "<td style=\"width:50%;text-align=center;padding:10px\">Churn Count</td>"
					+ "<td style=\"width:50%;text-align=center;padding:10px\">Invol Churn</td>"
					+ "<td style=\"width:50%;text-align=center;padding:10px\">Vol Churn</td>"
					+ "<td style=\"width:50%;text-align=center;padding:10px\">New Activation Revenue</td>"
					+ "<td style=\"width:50%;text-align=center;padding:10px\">Renewal Revenue</td>"
					+ "<td style=\"width:50%;text-align=center;padding:10px\">Total Revenue(ATPL share)</td>"
					+ "<td style=\"width:50%;text-align=center;padding:10px\">Gross Revenue</td>"
					+ "<td style=\"width:50%;text-align=center;padding:10px\">Mtd (ATPL share)</td>"
					+ "<td style=\"width:50%;text-align=center;padding:10px\">Gross Mtd</td>" +

					"</tr>";
			String body = "";
			StringBuilder bodyContent = new StringBuilder();
			for (Map.Entry<String, List<Quiz2playMisModel>> data : oneDay.entrySet()) {
				body = createall(data.getValue(), body);
				bodyContent.append(body);
			}
			body = "<td align=center colspan=\"10\">Total</td><td align=center>" + newActRev + "</td>"
					+ "<td align=center>" + RenActRev + "</td>" + "<td align=center>" + totalRevAtpl
					+ "</td><td align=center>" + grossRev + "</td>" + "<td align=center>" + totalMtd + "</td>"
					+ "<td align=center>" + grossMtd + "</td>" + "</tr>";
			bodyContent.append(body);
			messageBodyPart.setContent(msg + "<br>" + table + bodyContent + "</table>", "text/html");

			final Multipart multipart = (Multipart) new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = (BodyPart) new MimeBodyPart();
			final String filename = "/usr/shfiles/quiz2play_mis/zain_iq/Report.xlsx";
//			final String filename = "D://Reports.xlsx";

			final DataSource source = (DataSource) new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("quiz2Play_mis.xlsx");
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
			newActRev += Double.valueOf(mrb.getNewActivationRevenue());
			RenActRev += Double.valueOf(mrb.getRenewalRevenue());
			totalRevAtpl += Double.valueOf(mrb.getTotalRevenue());
			totalMtd += Double.valueOf(mrb.getMtd());
			grossMtd += Double.valueOf(mrb.getGrossMtd());
			grossRev += Double.valueOf(mrb.getTotalRevenuelc());
			body = body + "<tr style=\"width:1200px\"><td bgcolor=\"#98FB98\">" + mrb.getDate() + "</td>"

					+ "<td align=center>" + mrb.getOperatorName() + "</td>" + "<td align=center>" + mrb.getActiveBase()
					+ "</td>" + "<td align=center>" + mrb.getNewSubscription() + "</td>" + "<td align=center>"
					+ mrb.getBilledActivation() + "</td>" + "<td align=center>" + mrb.getNewBilledActivation() + "</td>"
					+ "<td align=center>" + mrb.getRenewalActivation() + "</td>" + "<td align=center>"
					+ mrb.getChurnCount() + "</td>" + "<td align=center>" + mrb.getInvolChurn() + "</td>"
					+ "<td align=center>" + mrb.getVolChurn() + "<td align=center>" + mrb.getNewActivationRevenue()
					+ "</td>" + "<td align=center>" + mrb.getRenewalRevenue() + "</td>" + "<td align=center>"
					+ mrb.getTotalRevenue() + "</td>" + "<td align=center>" + mrb.getTotalRevenuelc() + "</td>"
					+ "<td align=center>" + mrb.getMtd() + "</td><td align=center>" + mrb.getGrossMtd() + "</td></tr>";

		}

		return body;

	}
}
