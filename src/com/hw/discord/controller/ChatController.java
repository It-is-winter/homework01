package com.hw.discord.controller;

import java.util.List;

import com.hw.discord.model.dto.ChatDto;
import com.hw.discord.model.service.ChatService;
import com.hw.discord.model.vo.Chat;

public class ChatController {
	private ChatService chatService = new ChatService();
	
	/*
	 * All chats
	 */
	public List<Chat> findAll() {
		return chatService.findAll();
	}
	
	/*
	 * Create a new chat
	 */
	public int createChat(ChatDto chat) {
		return chatService.createChat(chat);
	}
	
	/*
	 * Update the chat
	 */
	public int updateChat(int chatId, ChatDto chat) {
		return chatService.updateChat(chatId, chat);
	}
	
	/*
	 * Delete the chat
	 */
	public Chat deleteChat(int chatId) {
		return chatService.deleteChat(chatId);
	}
	
	/*
	 * Find the chat
	 */
	public Chat findById(int chatId) {
		return chatService.findById(chatId);
	}

}
