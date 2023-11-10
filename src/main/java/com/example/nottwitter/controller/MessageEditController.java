package com.example.nottwitter.controller;

import com.example.nottwitter.domain.Message;
import com.example.nottwitter.domain.User;
import com.example.nottwitter.domain.dto.MessageDto;
import com.example.nottwitter.repos.MessageRepo;
import com.example.nottwitter.service.FileService;
import com.example.nottwitter.service.MessageService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class MessageEditController {

    private final FileService fileService;
    private final MessageRepo messageRepo;
    private final MessageService messageService;

    public MessageEditController(FileService fileService, MessageRepo messageRepo, MessageService messageService) {
        this.fileService = fileService;
        this.messageRepo = messageRepo;
        this.messageService = messageService;
    }

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/user-messages/{author}")
    public String userMessages(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User author,
            Model model,
            @RequestParam(required = false) Message message,
            @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable
    ){
        Page<MessageDto> page = messageService.messageListForUser(pageable, currentUser, author);

        model.addAttribute("userChannel", author);
        model.addAttribute("subscriptionsCount", author.getSubscriptions().size());
        model.addAttribute("subscribersCount", author.getSubscribers().size());
        model.addAttribute("isSubscriber", author.getSubscribers().contains(currentUser));
        model.addAttribute("page", page);
        model.addAttribute("message", message);
        model.addAttribute("isCurrentUser", currentUser.equals(author));
        model.addAttribute("url", "/user-messages/" + author.getId());

        return "userMessages";
    }

    @PostMapping("/user-messages/{user}")
    public String updateMessage(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Message message,
            @RequestParam("text") String text,
            @RequestParam("tag") String tag,
            @RequestParam("file") MultipartFile file
            )throws IOException {

        if(message.getAuthor().equals(currentUser)){
            if(!StringUtils.isNullOrEmpty(text)){
                message.setText(text);
            }

            if(!StringUtils.isNullOrEmpty(tag)){
                message.setTag(tag);
            }

            fileService.saveFile(message, file);

            messageRepo.save(message);
        }

        return "redirect:/user-messages/" + user;

    }

}
