package com.portmoor.songbird.models;

import com.portmoor.songbird.models.Utils.Gender;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.UUID;

@Document("User")
public class User {

@Id
public UUID id;

public String userName;
public Gender gender;
public boolean isTrans;
public int yearOfBirth;

public User() {}

public User(String userName, Gender gender, boolean isTrans, int yearOfBirth) {
    id = UUID.randomUUID();
    this.userName = userName;
    this.gender = gender;
    this.isTrans = isTrans;
    this.yearOfBirth = yearOfBirth;
}

@Override
public String toString() {
    return String.format(
            "User[id=%s, %s (%s), born:%s]",
            id, userName, gender, yearOfBirth);
}

}
