package com.masai.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.masai.model.Chat;
import com.masai.model.Message;
import com.masai.model.Users;

public interface ServiceInterface {

	Users addUsers(Users user);

	Chat createChat(Chat chat, Integer userId1, Integer userId2);

	Message sentMessage(Message message, Integer userId, Integer chatId);

	List<Message> getMessage(Integer chatId);

	List<Users> getUsers();

	Users updateProfile(MultipartFile img, String name, Integer userId);

	byte[] downloadImage(String fileName);

}
