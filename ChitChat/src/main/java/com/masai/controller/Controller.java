package com.masai.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import com.masai.model.Chat;
import com.masai.model.Message;
import com.masai.model.Users;
import com.masai.service.ServiceInterface;


@RestController
@CrossOrigin("*")
public class Controller {
	
	@Autowired
private ServiceInterface serviceInterface;
	
	@PostMapping("/addUsers")
	public ResponseEntity<Users> addUsers(@RequestBody Users user) {
		return new ResponseEntity<Users>(serviceInterface.addUsers(user),HttpStatus.CREATED);
	}
	
	@PostMapping("/createChat/{userId1}/{userId2}")
	public ResponseEntity<Chat> createChat(@RequestBody Chat chat, @PathVariable Integer userId1, @PathVariable Integer userId2) {
		return new ResponseEntity<Chat>(serviceInterface.createChat(chat,userId1,userId2),HttpStatus.CREATED);
	}
	
	@PostMapping("/sentMessage/{userId}/{chatId}")
	public ResponseEntity<Message> sentMessage(@RequestBody Message message, @PathVariable Integer userId,@PathVariable Integer chatId) {
		return new ResponseEntity<Message>(serviceInterface.sentMessage(message,userId,chatId),HttpStatus.CREATED);
	}
	
	@GetMapping("/getMessage/{chatId}")
	public ResponseEntity<List<Message>> getMessage(@PathVariable Integer chatId) {
		return new ResponseEntity<List<Message>> (serviceInterface.getMessage(chatId),HttpStatus.OK);
	}
	
	@GetMapping("/getUsers")
	public ResponseEntity<List<Users>> getUsers() {
		return new ResponseEntity<List<Users>> (serviceInterface.getUsers(),HttpStatus.OK);
	}
	
	@PutMapping("/updateProfile/{name}/{userId}")
	public ResponseEntity<Users> updateProfile(@RequestParam("image")MultipartFile img, @PathVariable String name,@PathVariable Integer userId) {
		
		return new ResponseEntity<Users>(serviceInterface.updateProfile(img,name,userId),HttpStatus.CREATED);
	}
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity<?> download(@PathVariable String fileName){
		
		byte []  ima=serviceInterface.downloadImage(fileName);
		
		return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(ima);
		
		
		
	}
}
