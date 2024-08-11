package com.masai.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GroupChat {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int chatId;
	
}
