package com.sjsu.HealthConnect.utility;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.sjsu.HealthConnect.entity.PatientProfile;
import com.sjsu.HealthConnect.entity.PatientVaccination;
import com.sjsu.HealthConnect.entity.User;
import com.sjsu.HealthConnect.repositories.PatientProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

import static java.lang.Thread.sleep;

@Component
public class CommonUtilities {

    @Value("${email.user-name}")
    private String username;

    @Value("${email.password}")
    private String password;

    private final String vaccEmailBody = "Dear {0},\n" +
            "\n" +
            "We hope this email finds you in good health. This email is to confirm that you have received the {1} vaccination. Please find attached a copy of your vaccination record for your reference.\n" +
            "\n" +
            "Thank you for doing your part in keeping our community safe and healthy.\n" +
            "\n" +
            "Best Regards,\n" +
            "HealthConnect Team";

    @Autowired
    private PatientProfileRepository patientProfileRepository;

    public void sendEmail(String recipientEmail, String subject, String body, String attachmentFilePath) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);

            MimeMultipart multipart = new MimeMultipart();

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            if(attachmentFilePath != null){
                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(new File(attachmentFilePath));
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                attachmentBodyPart.setFileName(source.getName());
                multipart.addBodyPart(attachmentBodyPart);
            }

            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            System.out.println("Exception:" + e.getMessage());
        }
    }

    public void sendVaccination(PatientVaccination patientVaccination) {

        Document document = new Document();
        User patient = patientVaccination.getPatient();
        PatientProfile profile = patientProfileRepository.findByUser(patient).get();


        try {
            PdfWriter.getInstance(document, new FileOutputStream("proof.pdf"));
            document.open();

            // Load the logo image
            Image logo = Image.getInstance("logo.jpeg");
            logo.scaleAbsolute(50, 30); // Adjust the size of the image
            logo.setAbsolutePosition(30, document.getPageSize().getHeight() - 50); // Set the position of the image

            // Add the logo image to the document
            document.add(logo);
            document.addCreator("HealthConnect");
            // Add the vaccination proof content
            Paragraph title = new Paragraph("Vaccination Certificate");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n\n"));
            document.add(new Paragraph("        Name            : "+patient.getFirstName() + " " + patient.getLastName()));
            document.add(new Paragraph("        Date of Birth   : "+patient.getDateOfBirth()));
            document.add(new Paragraph("        Gender          : "+patient.getGender()));
            document.add(new Paragraph("        Insurance Number: "+profile.getInsuranceNumber()));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("        Vaccine         : "+patientVaccination.getVaccine().getName()));
            document.add(new Paragraph("        Manufacturer    : "+patientVaccination.getVaccine().getManufacturer()));
            document.add(new Paragraph("        Dose batch      : "+getDoseBatchNumber()));
            document.add(new Paragraph("        Date of Dose    : "+ getDate()));
            document.add(new Paragraph("        Administered By : "+patientVaccination.getDoctor().getFirstName() + " " +
                                    patientVaccination.getDoctor().getLastName()));
            document.close();
            String body = MessageFormat.format(vaccEmailBody, patient.getFirstName(), patientVaccination.getVaccine().getName());
            System.out.println("Sending vaccination report to " + patient.getFirstName() + " on " + patient.getEmail());
            sendEmail(patient.getEmail(), "Vaccination certificate", body, "proof.pdf");
            sleep(2000);
            File file = new File("proof.pdf");
            if (file.delete()) {
                System.out.println("File deleted successfully");
            } else {
                System.out.println("Failed to delete file");
            }

        } catch (DocumentException | IOException | InterruptedException e) {
            System.out.println("Message: " + e.getMessage());
        }
    }

    private String getDoseBatchNumber(){
        Random random = new Random();
        int randomNumber = random.nextInt(16); // generates a random number between 0 and 15
        String formattedNumber = String.format("%04d", randomNumber); // formats the number as a 4-digit string with leading zeros
        return formattedNumber;
    }
    private String getDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = date.format(formatter);
        return formattedDate;
    }
}
