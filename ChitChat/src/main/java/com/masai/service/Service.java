package com.masai.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.masai.model.Chat;
import com.masai.model.Message;
import com.masai.model.Users;
import com.masai.repository.ChatRepository;
import com.masai.repository.MessageRepository;
import com.masai.repository.UsersRepository;
import com.masai.utils.ImgUtils;

@org.springframework.stereotype.Service
public class Service implements ServiceInterface{
	
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private ChatRepository chatRepository;
	@Autowired
	private MessageRepository messageRepository;
	@Override
	public Users addUsers(Users user) {
		usersRepository.save(user);
		return user;
	}

	@Override
	public Chat createChat(Chat chat, Integer userId1, Integer userId2){
		
		Optional<Users> u1=usersRepository.findById(userId1);
		Optional<Users> u2=usersRepository.findById(userId2);
		
		chat.setU1(u1.get());
		chat.setU2(u2.get());
		
		chatRepository.save(chat);
		
		return chat;
	}

	@Override
	public Message sentMessage(Message message, Integer userId,Integer chatId) {
		Optional<Users> u1=usersRepository.findById(userId);
		message.setUsers(u1.get());
		
		Optional<Chat> c= chatRepository.findById(chatId);
		Chat c1=c.get();
		
		if(userId==c1.getU1().getUserId() || userId==c1.getU2().getUserId()) {
			c1.getMessages().add(message);
		
		messageRepository.save(message);
		chatRepository.save(c1);
		return message;
		}else {
			return null;
		}	
	}

	@Override
	public List<Message> getMessage(Integer chatId) {
		Optional<Chat> c=chatRepository.findById(chatId);
		Chat c1=c.get();
		return c1.getMessages();
	}

	@Override
	public List<Users> getUsers() {
		List<Users> u1=usersRepository.findAll();
		List<Users> u2=new ArrayList<>();
		
//for(Users u:u1) {
//	u.setType(Base64.getEncoder().encodeToString(u.getImageData()) );
//	u2.add(u);
//}
//System.out.println(u2.size());
		return u1;
	}

	@Override
	public Users updateProfile(MultipartFile img, String name,Integer userId) {
		String base64ImageString ="";
		Users u1 = null;
		try {
			 u1=Users.builder().name(img.getOriginalFilename())
			.type(img.getContentType()).imageData(ImgUtils.compressImage(img.getBytes())).build() ;
			

			u1.setName(name);
			u1.setUserId(userId);
			usersRepository.save(u1);
			
			 base64ImageString = ImgUtils.convertToBase64(u1.getImageData());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		u1.setType(base64ImageString);
		return u1;
	}

	@Override
	public byte[] downloadImage(String fileName) {
//		
//Optional<Users> dbImageData=usersRepository.findByName(fileName);
//		
//		byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
		return null;
		
	}

}
