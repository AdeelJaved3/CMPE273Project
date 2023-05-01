package com.sjsu.HealthConnect.utility;

import com.sjsu.HealthConnect.dto.AppointmentStatus;
import com.sjsu.HealthConnect.entity.Appointment;
import com.sjsu.HealthConnect.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.MessageFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CronJob {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CommonUtilities utilities;

    static String appRem = "Dear {0},\n" +
            "\n" +
            "We wanted to remind you of your upcoming appointment tomorrow with {1}.\n" +
            "\n" +
            "Please be sure to arrive 10-15 minutes early to allow time for check-in and any necessary paperwork.\n" +
            "\n" +
            "If you need to reschedule or cancel your appointment please log in to the app and make the necessary changes.\n" +
            "\n" +
            "Thank you and we look forward to seeing you soon.\n" +
            "\n" +
            "Best regards,\n" +
            "HealthConnect Team";

    //@Scheduled(cron = "0 0 0 * * *") // every day
    @Scheduled(cron = "*/30 * * * * *") // 10 seconds
    public void runJob() throws ParseException {

       /* long currentTime = System.currentTimeMillis();*//**//*
        java.sql.Date tomorrow = new Date(currentTime);
        System.out.println(tomorrow);*/

        LocalDate tomorrow = LocalDate.now().plusDays(1); // calculate the date for tomorrow

        System.out.println(tomorrow);
        List<Appointment> appointments = appointmentRepository.findAll()
                .stream().filter(app -> app.getStatus().equals(AppointmentStatus.SCHEDULED))
                .filter(app -> app.getDate().toString().equals(tomorrow.toString()))
                .collect(Collectors.toList());
        for(Appointment app : appointments){
            String doctor = app.getDoctor().getFirstName() + " " + app.getDoctor().getLastName();
            String body = MessageFormat.format(appRem, app.getPatient().getFirstName(), doctor);
            System.out.println("Sending app reminder to " + app.getPatient().getFirstName() + " on " + app.getPatient().getEmail());
            utilities.sendEmail(app.getPatient().getEmail(), "Reminder: Upcoming Vaccination Appointment", body,null);
        }
        System.out.println("Print statement from cron job");
    }
}