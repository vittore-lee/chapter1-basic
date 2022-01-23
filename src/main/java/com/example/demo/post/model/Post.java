package com.example.demo.post.model;

import com.example.demo.post.payload.PostCreateInput;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners({AuditingEntityListener.class})
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String pw;

    @Builder
    public Post(Long id,
                LocalDateTime createdAt,
                LocalDateTime updatedAt,
                LocalDateTime deletedAt,
                String comment,
                String userName,
                String pw) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.comment = comment;
        this.userName = userName;
        this.pw = pw;
    }

    public Post updateUserName(final String userName) {
        this.userName = userName;
        return this;
    }

    public Post updateComment(final String comment) {
        this.comment = comment;
        return this;
    }

    public static Post of(final PostCreateInput payload) {
        return Post.builder()
                .comment(payload.getComment())
                .userName(payload.getUserName())
                .pw(payload.getPw())
                .build();
    }
}
