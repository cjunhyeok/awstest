package com.aws.awstest.controller;

import com.aws.awstest.controller.form.PostForm;
import com.aws.awstest.domain.Member;
import com.aws.awstest.domain.Post;
import com.aws.awstest.service.MemberService;
import com.aws.awstest.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @PostMapping("/posts/new")
    public String createPost(PostForm form,
                             Model model,
                             @AuthenticationPrincipal Member member) {

        postService.createPost(member.getId(), form.getTitle(), form.getContent());
        return "redirect:/";
    }

    @GetMapping("/posts")
    public String posts(Model model, @PageableDefault(size = 10)Pageable pageable) {

        Page<Post> findAllPostsPage = postService.findAllPosts(pageable);
        List<Post> findAllPosts = findAllPostsPage.getContent();

        model.addAttribute("findAllPosts", findAllPosts);
        return "posts/posts";
    }
}
