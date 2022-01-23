package com.example.demo.post.mapper;

import com.example.demo.post.model.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostMapper {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String comment;
    private String userName;

    @Builder
    public PostMapper(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String comment, String userName) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.comment = comment;
        this.userName = userName;
    }

    public static PostMapper of(final Post post) {
        return PostMapper.builder()
                .id(post.getId())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .comment(post.getComment())
                .userName(post.getUserName())
                .build();
    }
}
