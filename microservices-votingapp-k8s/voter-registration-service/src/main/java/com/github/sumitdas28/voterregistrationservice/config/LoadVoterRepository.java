package com.github.sumitdas28.voterregistrationservice.config;
import static com.github.sumitdas28.voterregistrationservice.utils.VoterRegistrationUtils.*;

import com.github.sumitdas28.voterregistrationservice.entity.Gender;
import com.github.sumitdas28.voterregistrationservice.entity.Voter;
import com.github.sumitdas28.voterregistrationservice.entity.VoterStatus;
import com.github.sumitdas28.voterregistrationservice.repository.VoterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadVoterRepository {
    private static final Logger log = LoggerFactory.getLogger(LoadVoterRepository.class);

    @Bean
    public CommandLineRunner initializeVoterRepository(VoterRepository voterRepository){
        return args -> {
            // loading the voter repository
            log.info("Preloading..." + voterRepository.save(new Voter(generateRandom(12),"adam", "01-01-1980", Gender.MALE.getValue(), "Surat", VoterStatus.ACTIVE, "768907890")));
            log.info("Preloading..." + voterRepository.save(new Voter(generateRandom(12),"sam", "02-01-1980", Gender.MALE.getValue(), "Delhi", VoterStatus.ACTIVE, "768907890")));
            log.info("Preloading..." + voterRepository.save(new Voter(generateRandom(12),"tam", "03-01-1980", Gender.FEMALE.getValue(), "Shimla", VoterStatus.ACTIVE, "7689088890")));
            log.info("Preloading..." + voterRepository.save(new Voter(generateRandom(12),"tina", "04-01-1980", Gender.MALE.getValue(), "Delhi", VoterStatus.ACTIVE, "768906890")));
            log.info("Preloading..." + voterRepository.save(new Voter(generateRandom(12),"jon", "01-01-1980", Gender.MALE.getValue(), "Delhi", VoterStatus.ACTIVE, "768907890")));
            log.info("Preloading..." + voterRepository.save(new Voter(generateRandom(12),"son", "02-01-1980", Gender.FEMALE.getValue(), "Goa", VoterStatus.ACTIVE, "76555890")));
            log.info("Preloading..." + voterRepository.save(new Voter(generateRandom(12),"tom", "02-01-1980", Gender.MALE.getValue(), "Delhi", VoterStatus.ACTIVE, "768907890")));
            log.info("Preloading..." + voterRepository.save(new Voter(generateRandom(12),"jerry", "03-01-1980", Gender.FEMALE.getValue(), "Delhi", VoterStatus.ACTIVE, "766907890")));
            log.info("Preloading..." + voterRepository.save(new Voter(generateRandom(12),"bob", "04-01-1980", Gender.MALE.getValue(), "Delhi", VoterStatus.ACTIVE, "768966890")));
            log.info("Preloading..." + voterRepository.save(new Voter(generateRandom(12),"benny", "04-01-1980", Gender.MALE.getValue(), "Delhi", VoterStatus.ACTIVE, "768907890")));
            log.info("Preloading..." + voterRepository.save(new Voter(generateRandom(12),"roger", "01-01-1980", Gender.MALE.getValue(), "Delhi", VoterStatus.ACTIVE, "768955890")));
            log.info("Preloading..." + voterRepository.save(new Voter(generateRandom(12),"ted", "07-01-1980", Gender.FEMALE.getValue(), "Kochi", VoterStatus.ACTIVE, "768907770")));
            log.info("Preloading..." + voterRepository.save(new Voter(generateRandom(12),"fred", "09-01-1980", Gender.MALE.getValue(), "Delhi", VoterStatus.ACTIVE, "768907890")));
            log.info("Preloading..." + voterRepository.save(new Voter(generateRandom(12),"greg", "02-01-1980", Gender.FEMALE.getValue(), "Delhi", VoterStatus.ACTIVE, "768966890")));
            log.info("Preloading..." + voterRepository.save(new Voter(generateRandom(12),"max", "01-01-1980", Gender.MALE.getValue(), "Delhi", VoterStatus.ACTIVE, "768907890")));
        };
    }
}
