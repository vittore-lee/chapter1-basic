package com.example.demo.post;

import com.example.demo.exception.ForbiddenException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.post.mapper.PostMapper;
import com.example.demo.post.model.Post;
import com.example.demo.post.payload.PostCreateInput;
import com.example.demo.post.payload.PostUpdateInput;
import com.example.demo.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long savePost(final PostCreateInput payload) {
        return postRepository.save(Post.of(payload)).getId();
    }

    @Transactional(readOnly = true)
    public PostMapper findPostById(final Long id) {
        return PostMapper.of(findPost(id));
    }

    @Transactional(readOnly = true)
    public List<PostMapper> findPosts(final int page, final int limit) {
        final PageRequest pageRequest = PageRequest.of(page, limit, Sort.by("createdAt").descending());
        return postRepository.findAll(pageRequest).stream()
                .map(PostMapper::of)
                .collect(Collectors.toList());
    }

    private Post findPost(final Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
    }

    @Transactional
    public PostMapper updatePost(final Long id, final PostUpdateInput payload) {
        final Post post = findPost(id);
        if (!post.getPw().equals(payload.getPw())) {
            throw new ForbiddenException();
        }
        return PostMapper.of(post.updateUserName(payload.getUserName())
                .updateComment(payload.getComment()));
    }

    @Transactional
    public void deletePostById(final Long id) {
        postRepository.deleteById(findPost(id).getId());
    }
}
