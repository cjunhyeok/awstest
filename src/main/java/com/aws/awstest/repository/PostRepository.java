package com.aws.awstest.repository;

import com.aws.awstest.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select p from Post p join fetch p.member",
    countQuery = "select p from Post p")
    Page<Post> findAllPostsWithMember(Pageable pageable);
}
