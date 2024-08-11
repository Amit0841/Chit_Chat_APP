package com.masai.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Users {
@Id
@GeneratedValue(strategy =GenerationType.IDENTITY)
private int userId;
private String name;
private String email;
private String password;

private String type;
@Lob
@Column(name = "imagedata", columnDefinition="LONGBLOB")
private byte[] imageData;
}
