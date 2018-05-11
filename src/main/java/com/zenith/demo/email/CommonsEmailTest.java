package com.zenith.demo.email;

import java.io.File;
import java.util.Date;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import com.zenith.demo.rocketmq.RocketMQConsumer;
import com.zenith.demo.rocketmq.RocketMQListener;
/*
 * ˵���������ʼ�����Demo
 * 
 */
public class CommonsEmailTest {
	private String host = "mail.gs.cq";
	private int port = 25;
	private String userName = "111@gs.cq";
	private String password = "jishubu@123";
	private String to = "xtyw_xxzx@gs.cq";

	/**
	 * �����ı��ʼ�
	 * 
	 * @throws Exception
	 */
	public void sendTextMail() throws Exception {
		SimpleEmail mail = new SimpleEmail();
		// ���������������Ϣ
		// mail.setSmtpPort(port);
		mail.setHostName(host);
		// ����������֤��
		mail.setAuthentication(userName, password);

		// �����ʼ�������
		mail.setFrom(userName);
		// �����ʼ�������
		mail.addTo(to);
		// �����ʼ�����
		mail.setCharset("UTF-8");
		// �����ʼ�����
		mail.setSubject("Test Email");
		// �����ʼ�����
		mail.setMsg("this is a test Text mail");
		// �����ʼ�����ʱ��
		mail.setSentDate(new Date());
		// �����ʼ�
		mail.send();
	}

	/**
	 * ����Html�ʼ�
	 * 
	 * @throws Exception
	 */
	public void sendHtmlMail() throws Exception {
		HtmlEmail mail = new HtmlEmail();
		// ���������������Ϣ
		mail.setSmtpPort(port);
		mail.setHostName(host);
		// ����������֤��
		mail.setAuthentication(userName, password);
		// �����ʼ�������
		mail.setFrom(userName);
		// �����ʼ�������
		mail.addTo(to);
		// �����ʼ�����
		mail.setCharset("UTF-8");
		// �����ʼ�����
		mail.setSubject("Test Email");
		// �����ʼ�����
		mail.setHtmlMsg(
				"<html><body><img src='http://avatar.csdn.net/A/C/A/1_jianggujin.jpg'/><div>this is a HTML email.</div></body></html>");
		// �����ʼ�����ʱ��
		mail.setSentDate(new Date());
		// �����ʼ�
		mail.send();
	}

	/**
	 * ������ǶͼƬ�ʼ�
	 * 
	 * @throws Exception
	 */
	public void sendImageMail() throws Exception {
		HtmlEmail mail = new HtmlEmail();
		// ���������������Ϣ
		mail.setSmtpPort(port);
		mail.setHostName(host);
		// ����������֤��
		mail.setAuthentication(userName, password);
		// �����ʼ�������
		mail.setFrom(userName);
		// �����ʼ�������
		mail.addTo(to);
		// �����ʼ�����
		mail.setCharset("UTF-8");
		// �����ʼ�����
		mail.setSubject("Test Email");
		mail.embed(new File("1_jianggujin.jpg"), "image");
		// �����ʼ�����
		String htmlText = "<html><body><img src='cid:image'/><div>this is a HTML email.</div></body></html>";
		mail.setHtmlMsg(htmlText);
		// �����ʼ�����ʱ��
		mail.setSentDate(new Date());
		// �����ʼ�
		mail.send();
	}

	/**
	 * ���͸����ʼ�
	 * 
	 * @throws Exception
	 */
	public void sendAttachmentMail() throws Exception {
		MultiPartEmail mail = new MultiPartEmail();
		// ���������������Ϣ
		mail.setSmtpPort(port);
		mail.setHostName(host);
		// ����������֤��
		mail.setAuthentication(userName, password);
		// �����ʼ�������
		mail.setFrom(userName);
		// �����ʼ�������
		mail.addTo(to);
		// �����ʼ�����
		mail.setCharset("UTF-8");
		// �����ʼ�����
		mail.setSubject("Test Email");

		mail.setMsg("this is a Attachment email.this email has a attachment!");
		// ��������
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("1_jianggujin.jpg");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setName("1_jianggujin.jpg");
		mail.attach(attachment);

		// �����ʼ�����ʱ��
		mail.setSentDate(new Date());
		// �����ʼ�
		mail.send();
	}

	/**
	 * ������ǶͼƬ�͸����ʼ�
	 * 
	 * @throws Exception
	 */
	public void sendImageAndAttachmentMail() throws Exception {
		HtmlEmail mail = new HtmlEmail();
		// ���������������Ϣ
		mail.setSmtpPort(port);
		mail.setHostName(host);
		// ����������֤��
		mail.setAuthentication(userName, password);
		// �����ʼ�������
		mail.setFrom(userName);
		// �����ʼ�������
		mail.addTo(to);
		// �����ʼ�����
		mail.setCharset("UTF-8");
		// �����ʼ�����
		mail.setSubject("Test Email");
		mail.embed(new File("1_jianggujin.jpg"), "image");
		// �����ʼ�����
		String htmlText = "<html><body><img src='cid:image'/><div>this is a HTML email.</div></body></html>";
		mail.setHtmlMsg(htmlText);
		// ��������
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("1_jianggujin.jpg");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setName("1_jianggujin.jpg");
		mail.attach(attachment);
		// �����ʼ�����ʱ��
		mail.setSentDate(new Date());
		// �����ʼ�
		mail.send();
	}

	public static void main(String[] args) {
		CommonsEmailTest ce = new CommonsEmailTest();
		try {
			ce.sendTextMail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}