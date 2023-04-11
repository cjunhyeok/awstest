package com.aws.awstest.service;

import com.aws.awstest.domain.Member;

public interface MemberService {

    Long join(String username, String password, String nickname);

    Member findById(Long id);
}
