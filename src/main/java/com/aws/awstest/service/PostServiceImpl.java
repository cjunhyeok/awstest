package com.aws.awstest.service;

import com.aws.awstest.domain.Member;
import com.aws.awstest.domain.Post;
import com.aws.awstest.repository.MemberRepository;
import com.aws.awstest.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional
    public Long createPost(Long memberId, String title, String content) {

        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not exist"));

        Post savedPost = postRepository.save(
                Post.builder()
                        .title(title)
                        .content(content)
                        .member(findMember)
                        .build()
        );

        return savedPost.getId();
    }

    @Override
    public Post findById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not exist"));
    }

    @Override
    public Page<Post> findAllPosts(Pageable pageable) {
        return postRepository.findAllPostsWithMember(pageable);
    }
}
