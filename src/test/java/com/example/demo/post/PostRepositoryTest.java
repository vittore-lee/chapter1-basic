package com.example.demo.post;

import com.example.demo.post.model.Post;
import com.example.demo.post.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    void save_post_test() {
        // given
        final String comment = "test";
        final String userName = "min";
        final String pw = "1234";

        final Post post = Post.builder()
                .comment(comment)
                .userName(userName)
                .pw(pw)
                .build();

        // when
        final Post actual = postRepository.save(post);
        System.out.println(actual.getId());

        // then
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotNull();
        assertThat(actual.getCreatedAt()).isNotNull();
        assertThat(actual.getUpdatedAt()).isNotNull();
        assertThat(actual.getDeletedAt()).isNull();
        assertThat(actual.getComment()).isEqualTo(comment);
        assertThat(actual.getUserName()).isEqualTo(userName);
        assertThat(actual.getPw()).isEqualTo(pw);
    }
}
