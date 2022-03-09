package com.vsaijan4.profile.instakitty.service;

import com.vsaijan4.profile.instakitty.model.Profile;
import com.vsaijan4.profile.instakitty.repository.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProfileService {
    @Autowired
    ProfileRepository profileRepository;

    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    public List<Profile> getByNameContaining(String name) {
        return profileRepository.findByNameContaining(name);
    }

    public Optional<Profile> get(String id) {
        return profileRepository.findById(id);
    }

    public Profile create(Profile profile) {
        profile.setCreatedAt(ZonedDateTime.now(ZoneOffset.UTC));
        profile.setUpdatedAt(ZonedDateTime.now(ZoneOffset.UTC));
        return profileRepository.save(profile);
    }

    public Optional<Profile> update(String id, Profile profile) {
        Optional<Profile> profileData = get(id);
        if (profileData.isPresent()) {
            profile.setId(id);
            profile.setCreatedAt(profileData.get().getCreatedAt());
            profile.setUpdatedAt(ZonedDateTime.now(ZoneOffset.UTC));
            return Optional.of(profileRepository.save(profile));
        }
        return profileData;
    }

    public void delete(String id) {
        profileRepository.deleteById(id);
    }
}
