package com.hw.discord.model.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hw.discord.model.dto.ChatDto;
import com.hw.discord.model.vo.Chat;

public class ChatService {
	private List<Chat> chats = new ArrayList();
	int chatId;
	
	/*
	 * All chats
	 */
	public List<Chat> findAll() {
		return chats;
		// 여기서 null이 아닐때만 chats를 return하게 되면 view에서 계속 null을 받기 때문에 NullPointerException 발생
	}
	
	/*
	 * Create a new chat
	 * Not Empty
	 * The text at most 30
	 */
	public int createChat(ChatDto chat) {
		String chatText = chat.getChatText();
		boolean checkedText = validateChatText(chatText);
		if(checkedText) {
			chats.add(new Chat(++chatId, chatText, new SimpleDateFormat("yyyy년 MM월 dd일").format(new Date())));
			return 1;
		}
		return 0;
	}
	
	/*
	 * Update the chat
	 * Only chat's text can be edited
	 */
	public int updateChat(int chatId, ChatDto chatDto) {
		int index = indexOf(chatId);
		Chat chat = findById(chatId);
		if(index != -1) {
			String text = chatDto.getChatText();
			boolean checkedText = validateChatText(text);
			if(checkedText) {
				chats.set(index, new Chat(chatId, text, chat.createDate()));
				return 1;
			}
		}
		return 0;
	}
	
	/*
	 * Delete the chat that matches the chatId
	 */
	public Chat deleteChat(int chatId) {
		int index = indexOf(chatId);
		if(index != -1) {
			return chats.remove(index);
		}
		return null;
	}
	
	/*
	 * Find the chat that matches the chatId
	 */
	public Chat findById(int chatId) {
		int index = indexOf(chatId);
		if(index != -1) {
			for(int i = 0; i < chats.size(); i++) {
				if(i == index) {
					return chats.get(i);
				}
			}
		}
		return null;
	}
	
	/*
	 * Check that the text is not empty and its length is at most 30
	 */
	private boolean validateChatText(String text) {
		if((0 < text.length()) && (text.length() <= 30)) {
			return true;
		}
		return false;
	}
	
	/*
	 * Find the index that matches the chatId
	 */
	private int indexOf(int chatId) {
		int index = -1;
		for(int i = 0; i < chats.size(); i++) {
			if(chats.get(i).chatId() == chatId) {
				index = i;
				break;
			}
		}
		return index;
	}

}
