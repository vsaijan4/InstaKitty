package com.vsaijan4.profile.instakitty.repository;

import com.vsaijan4.profile.instakitty.model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<Profile, String> {
}
