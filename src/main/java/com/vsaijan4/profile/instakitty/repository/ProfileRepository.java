package com.vsaijan4.profile.instakitty.repository;

import com.vsaijan4.profile.instakitty.model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProfileRepository extends MongoRepository<Profile, String> {
    List<Profile> findByNameContaining(String name);
}
