package com.example.demo.post;

import com.example.demo.post.mapper.PostMapper;
import com.example.demo.post.payload.PostCreateInput;
import com.example.demo.post.payload.PostUpdateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/v1/post")
@RestController
public class PostController {
    private final PostService postService;

    @GetMapping("{page}/{limit}")
    public ResponseEntity<List<PostMapper>> findPosts(@PathVariable("page") final int page,
                                                      @PathVariable("limit") final int limit) {
        return ResponseEntity.ok(postService.findPosts(page, limit));
    }

    @PostMapping
    public ResponseEntity<Long> savePost(@RequestBody final PostCreateInput payload) {
        return new ResponseEntity<>(postService.savePost(payload), HttpStatus.CREATED);
    }

    @GetMapping("{postId}")
    public ResponseEntity<PostMapper> findPostById(@PathVariable("postId") final Long postId) {
        return ResponseEntity.ok(postService.findPostById(postId));
    }

    @PutMapping("{postId}")
    public ResponseEntity<PostMapper> updatePost(@PathVariable("postId") final Long postId,
                                                 @RequestBody PostUpdateInput payload) {
        return ResponseEntity.ok(postService.updatePost(postId, payload));
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<?> deletePostById(@PathVariable("postId") final Long postId) {
        postService.deletePostById(postId);
        return ResponseEntity.ok().build();
    }
}
