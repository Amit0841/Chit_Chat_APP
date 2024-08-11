package com.masai.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Chat {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int chatId;
	
	@JsonIgnore
	@ManyToOne
	private Users u1;
	
	@JsonIgnore
	@ManyToOne
	private Users u2;
	 
	@JsonIgnore
	 @OneToMany
	 private List<Message> messages;
	
}
