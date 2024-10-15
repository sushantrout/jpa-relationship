package com.tech.service;

import com.tech.external.GoogleMailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class MailService {
    private final GoogleMailSender googleMailSender;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public void sendmail(String email, String welcome, String welcomeToOurPlatform) {
        executorService.execute(() -> googleMailSender.sendmail(email, welcome, welcomeToOurPlatform));
    }
}
