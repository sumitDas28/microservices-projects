package com.github.sumitdas28.voterregistrationservice.controller;

import com.github.sumitdas28.voterregistrationservice.entity.Gender;
import com.github.sumitdas28.voterregistrationservice.entity.Voter;
import com.github.sumitdas28.voterregistrationservice.entity.VoterStatus;
import com.github.sumitdas28.voterregistrationservice.exception.VoterNotFoundException;
import com.github.sumitdas28.voterregistrationservice.repository.VoterRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/voter-registration")
public class VoterRegistrationController {
    private Logger logger = LoggerFactory.getLogger(VoterRegistrationController.class);

    private final VoterRepository voterRepository;

    public VoterRegistrationController(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }

    @PostMapping("/addVoter")
    public ResponseEntity<Voter> addNewVoter(@RequestBody Voter newVoter) {
        try{
            newVoter.setVoterId(generateRandom(12));
            newVoter.setVoterStatus(VoterStatus.ACTIVE);
            var createdVoter = voterRepository.save(newVoter);
            return new ResponseEntity<>(createdVoter, HttpStatus.CREATED);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Oops! something went wrong...",e);
        }
    }

    @GetMapping("/getAllActiveVoters")
    public ResponseEntity<List<Voter>> getAllActiveVoters(){
        try{
            var activeVoters = voterRepository.findByVoterStatus(VoterStatus.ACTIVE);
            return new ResponseEntity<>(activeVoters, HttpStatus.OK);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Oops! something went wrong...",e);
        }
    }

    @GetMapping("/getVoterById/{id}")
    public ResponseEntity<Voter> getVoterById(@PathVariable Long id){
        try{
            var voter = voterRepository.findById(id);
            if(voter.isPresent()){
                return new ResponseEntity<>(voter.get(), HttpStatus.OK);
            } else {
                throw new VoterNotFoundException(id);
            }
        } catch (VoterNotFoundException e){
            logger.error("Voter Id not found",e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No voter found with Id:"+id,e);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Oops! something went wrong...",e);
        }
    }

    @PutMapping("/updateVoter/{id}")
    public ResponseEntity<Voter> updateVoter(@Valid @RequestBody Voter newVoter, @PathVariable Long id) {
        try {
            return voterRepository.findById(id).map(voter -> {
                if(newVoter.getName()!=null && newVoter.getName().length()>0)voter.setName(newVoter.getName());
                if(newVoter.getGender()!=null && newVoter.getGender().length()>0)voter.setGender(newVoter.getGender());
                if(newVoter.getAddress()!=null && newVoter.getAddress().length()>0)voter.setAddress(newVoter.getAddress());
                if(newVoter.getVoterStatus()!=null) voter.setVoterStatus(newVoter.getVoterStatus());
                var updatedVoter = voterRepository.save(voter);
                return new ResponseEntity<>(updatedVoter, HttpStatus.OK);
            }).orElseThrow(() -> new VoterNotFoundException(id));
        } catch (VoterNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No voter found with Id:"+id,e);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Oops! something went wrong...",e);
        }
    }

    @DeleteMapping("/deleteVoter/{id}")
    public void deleteVoter(@PathVariable Long id){
        try{
            voterRepository.deleteById(id);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Oops! something went wrong...",e);
        }
    }

    private static long generateRandom(int length) {
        Random random = new Random();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }

}
