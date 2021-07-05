package com.turstpilot.rabbitchallenge;

import com.turstpilot.rabbitchallenge.service.AnagramServiceIterationImpl;
import com.turstpilot.rabbitchallenge.service.AnagramServiceRecursionImpl;
import com.turstpilot.rabbitchallenge.service.Md5Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
public class RabbitChallengeApplication implements CommandLineRunner {

    @Autowired
    Md5Service md5Service;

    public static void main(String[] args) {
        SpringApplication.run(RabbitChallengeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Set<String> phrase1 = md5Service.decodeMD5Hashed("poultry outwits ants", "e4820b45d2277f3844eac66c903e84be");
//        Set<String> phrase2 = md5Service.decodeMD5Hashed("poultry outwits ants", "23170acc097c24edb98fc5488ab033fe");
//        Set<String> phrase3 = md5Service.decodeMD5Hashed("poultry outwits ants", "665e5bcb0c20062fe8abaaf4628bb154");
    }

}
