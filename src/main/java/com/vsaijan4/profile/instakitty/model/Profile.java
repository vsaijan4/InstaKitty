package com.vsaijan4.profile.instakitty.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "profiles")
public class Profile {

    @Id
    private String id;

    private String name;
    private String photo;
    private List<String> tags;
    private int age;
    private String gender;
    private String pronoun;
    private String type;
    private String parentId;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
