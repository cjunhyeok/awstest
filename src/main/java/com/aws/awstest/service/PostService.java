package com.aws.awstest.service;

import com.aws.awstest.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Long createPost(Long memberId, String title, String content);

    Post findById(Long postId);

    Page<Post> findAllPosts(Pageable pageable);
}
