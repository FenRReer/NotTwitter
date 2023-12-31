package com.example.nottwitter.service;

import com.example.nottwitter.domain.Message;
import com.example.nottwitter.domain.User;
import com.example.nottwitter.domain.dto.MessageDto;
import com.example.nottwitter.repos.MessageRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepo messageRepo;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public Page<MessageDto> messageList(Pageable pageable, String filter, User user){
        if(filter !=null && !filter.isEmpty()){
            return messageRepo.findByTag(filter, pageable, user);
        }else{
            return messageRepo.findAll(pageable, user);
        }
    }

    public Page<MessageDto> messageListForUser(Pageable pageable, User currentUser, User author) {
        return messageRepo.findByUser(pageable, author, currentUser);
    }
}
