package com.task.resume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("name") String name, @RequestParam("email") String email) {
        try {
            sendEmailWithAttachment(file, name, email);
            return "File uploaded and email sent successfully.";
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            return "Failed to upload file and send email.";
        }
    }

    private void sendEmailWithAttachment(MultipartFile file, String name, String email) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo("tarunswaroop199@gmail.com");
        helper.setSubject("New Resume Uploaded");
        helper.setText("name: " + name + "\nemail: "+ email  + "\nA new resume has been uploaded. See the attached file.");

        helper.addAttachment(file.getOriginalFilename(), file);

        mailSender.send(message);
    }
}
