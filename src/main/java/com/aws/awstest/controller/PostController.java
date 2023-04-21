package com.aws.awstest.controller;

import com.aws.awstest.controller.form.PostForm;
import com.aws.awstest.service.MemberService;
import com.aws.awstest.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/posts/new")
    public String createPostForm(Model model) {

        model.addAttribute("postForm", new PostForm());

        return "posts/createPostForm";
    }
}
