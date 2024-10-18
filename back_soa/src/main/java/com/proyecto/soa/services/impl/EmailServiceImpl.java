package com.proyecto.soa.services.impl;

import com.proyecto.soa.services.EmailService;
import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

//    @Value("${spring.mail.username}")
//    private String fromEmailAddress;
//
//    private final JavaMailSender mailSender;

    //Servicio para enviar un correo electronico
    @Async
    @Override
    public void sendMail(String to, String subject, String content) throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//        helper.setFrom(new InternetAddress(fromEmailAddress));
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(content,true);
//        mailSender.send(message);
    }

    //Servicio para enviar un correo electronico con una imagen
    @Async
    @Override
    public void sendMailWithImage(String to, String subject, String content) throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message,true);
//        helper.setFrom(new InternetAddress(fromEmailAddress));
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(content,true);

        //Se agrega la imagen al correo
//        FileSystemResource logo = new FileSystemResource(new File(image));
//        helper.addInline("logo",logo);
//        mailSender.send(message);
    }

    //Servicio para enviar un correo electronico con un archivo adjunto
//    @Async
//    @Override
//    public void sendMailAndAttachment(String to, String subject, String content, String filePath) throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message,true);
//        helper.setFrom(new InternetAddress(fromEmailAddress));
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(content,true);

        //Se agrega el archivo adjunto al correo
//        DataSource dataSource = new ByteArrayDataSource(bucketService.getS3FileContent(filePath), "application/octet-stream");
//        helper.addAttachment(filePath,dataSource);
//        mailSender.send(message);
//    }

    //Servicio para enviar multiples correos electronicos
    @Async
    @Override
    public void sendMultipleMail(List<String> to, String subject, String content) throws MessagingException {
//        to.forEach(t->{
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message);
//            try{
//                helper.setFrom(new InternetAddress(fromEmailAddress));
//                helper.setTo(t);
//                helper.setSubject(subject);
//                helper.setText(content,true);
//            }catch (MessagingException e){
//                throw new RuntimeException(e);
//            }
//                mailSender.send(message);
//        });
    }
}
