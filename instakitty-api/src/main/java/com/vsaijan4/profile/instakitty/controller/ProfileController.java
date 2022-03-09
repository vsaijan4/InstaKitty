package com.vsaijan4.profile.instakitty.controller;

import com.vsaijan4.profile.instakitty.model.Profile;
import com.vsaijan4.profile.instakitty.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/api")
public class ProfileController {
    @Autowired
    ProfileService profileService;

    @GetMapping("/profiles")
    public ResponseEntity<List<Profile>> getAllProfiles(@RequestParam(required = false) String name) {
        try {
            List<Profile> profiles = new ArrayList<>();
            if (name == null || name.equals(""))
                profiles.addAll(profileService.getAll());
            else
                profiles.addAll(profileService.getByNameContaining(name));
            if (profiles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(profiles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/profiles/{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable() String id) {
        Optional<Profile> profileData = profileService.get(id);
        if (profileData.isPresent()) {
            log.info("getProfile details for {}", id);
            return new ResponseEntity<>(profileData.get(), HttpStatus.OK);
        } else {
            log.info("getProfile Not Found for {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/profiles")
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        log.info("createProfile details");
        return new ResponseEntity<>(profileService.create(profile), HttpStatus.CREATED);
    }

    @PutMapping("/profiles/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable String id, @RequestBody Profile profile) {
        Optional<Profile> profileData = profileService.update(id, profile);
        if (profileData.isPresent()) {
            log.info("profile details Updated for {}", id);
            return new ResponseEntity<>(profileData.get(), HttpStatus.OK);
        } else {
            log.info("profile Not Found for {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/profiles/{id}")
    public ResponseEntity<HttpStatus> deleteProfile(@PathVariable String id) {
        log.info("deleteProfile details for {}", id);
        try {
            profileService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
