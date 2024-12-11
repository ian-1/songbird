package com.portmoor.songbird.repositories;

import com.portmoor.songbird.models.User;
import com.portmoor.songbird.models.Utils.Gender;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    public User findByUserName(String userName);

    public List<User> findByYearOfBirth(int yearOfBirth);

    public List<User> findByGender(Gender gender);

}
