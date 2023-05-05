package com.sjsu.HealthConnect.utility;

import com.sjsu.HealthConnect.dto.AppointmentStatus;
import com.sjsu.HealthConnect.entity.Appointment;
import com.sjsu.HealthConnect.entity.PatientVaccination;
import com.sjsu.HealthConnect.repositories.AppointmentRepository;
import com.sjsu.HealthConnect.repositories.PatientVaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.MessageFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

/**

 This class defines a scheduled cron job that runs every day at midnight. The job is responsible for sending
 appointment reminders to patients for appointments scheduled for the following day, and vaccination appointment
 reminders to patients for vaccinations that are due within the next 4 days.
 */

@Component
public class CronJob {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CommonUtilities utilities;

    @Autowired
    private PatientVaccinationRepository patientVaccinationRepository;

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

    static String vaccineRem = "Dear {0},\n" +
            "\n" +
            "We hope this message finds you well. As a friendly reminder, it's time to schedule your next appointment for the upcoming dose of vaccination {1}. It's important to stay up-to-date with your vaccinations to ensure you remain protected against preventable diseases.\n" +
            "\n" +
            "To schedule your appointment, login to the HealthConnect application.\n" +
            "\n" +
            "Thank you for prioritizing your health and wellness.\n" +
            "\n" +
            "Best regards,\n" +
            "HealthConnect Team";

    // The cron expression specifies that this method should run every day at midnight
    @Scheduled(cron = "0 0 0 * * *") // every day
    public void runJob() throws ParseException {

        // Calculate the date for tomorrow
        LocalDate tomorrow = LocalDate.now().plusDays(1); // calculate the date for tomorrow

        System.out.println(tomorrow);

        // Retrieve a list of appointments that are scheduled for tomorrow and have the status SCHEDULED
        List<Appointment> appointments = appointmentRepository.findAll()
                .stream().filter(app -> app.getStatus().equals(AppointmentStatus.SCHEDULED))
                .filter(app -> app.getDate().toString().equals(tomorrow.toString()))
                .collect(Collectors.toList());

        // For each appointment, generate a reminder email and send it to the patient's email address
        for(Appointment app : appointments){
            String doctor = app.getDoctor().getFirstName() + " " + app.getDoctor().getLastName();
            String body = MessageFormat.format(appRem, app.getPatient().getFirstName(), doctor);
            System.out.println("Sending app reminder to " + app.getPatient().getFirstName() + " on " + app.getPatient().getEmail());
            utilities.sendEmail(app.getPatient().getEmail(), "Reminder: Upcoming Vaccination Appointment", body,null);
        }

        LocalDate nextDate = LocalDate.now().plusDays(4);
        Date date = (Date) Date.from(nextDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        // Retrieve a list of patient vaccinations where the next dosage date is after the date calculated above
        List<PatientVaccination> patientVaccinations = patientVaccinationRepository.findAll()
                .stream().filter(vacc -> vacc.getNextDoseDate().after(date))
                .collect(Collectors.toList());

        // For each patient vaccination, generate a reminder email and send it to the patient's email address
        for (PatientVaccination pv: patientVaccinations){
            System.out.println("Sending app reminder to " + pv.getPatient().getFirstName() + " on " + pv.getPatient().getEmail());
            String body = MessageFormat.format(vaccineRem, pv.getPatient().getFirstName(), pv.getVaccine().getName());
            utilities.sendEmail(pv.getPatient().getEmail(), "Reminder: Schedule Appointment for Next dosage of vaccination", body,null);
        }
    }
}