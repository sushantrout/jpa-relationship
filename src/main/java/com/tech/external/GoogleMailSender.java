package com.tech.external;

import org.springframework.stereotype.Service;

@Service
public class GoogleMailSender {
    public void sendmail(String email, String welcome, String welcomeToOurPlatform) {
        // Send email
        try {
            Thread.sleep(10000);
            System.out.println("Email sent to " + email);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
