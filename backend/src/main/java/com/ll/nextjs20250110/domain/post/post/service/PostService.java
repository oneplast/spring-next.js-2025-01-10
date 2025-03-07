package com.ll.nextjs20250110.domain.post.post.service;

import com.ll.nextjs20250110.domain.member.member.entity.Member;
import com.ll.nextjs20250110.domain.post.post.entity.Post;
import com.ll.nextjs20250110.domain.post.post.repository.PostRepository;
import com.ll.nextjs20250110.global.search.SearchKeywordTypeV1;
import com.ll.nextjs20250110.util.Ut;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public long count() {
        return postRepository.count();
    }

    public Post write(Member author, String title, String content, boolean published, boolean listed) {
        Post post = Post.builder()
                .author(author)
                .title(title)
                .content(content)
                .published(published)
                .listed(listed)
                .build();

        return postRepository.save(post);
    }

    public List<Post> findAllByOrderByIdDesc() {
        return postRepository.findAllByOrderByIdDesc();
    }

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public void modify(Post post, String title, String content, boolean published, boolean listed) {
        post.setTitle(title);
        post.setContent(content);
        post.setPublished(published);
        post.setListed(listed);
    }

    public void flush() {
        postRepository.flush();
    }

    public Optional<Post> findLatest() {
        return this.postRepository.findFirstByOrderByIdDesc();
    }

    public Page<Post> findByListedPaged(boolean listed, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("id")));

        return postRepository.findByListed(listed, pageRequest);
    }

    public Page<Post> findByListedPaged(
            boolean listed,
            SearchKeywordTypeV1 searchKeywordType,
            String searchKeyword,
            int page,
            int pageSize
    ) {
        if (Ut.str.isBlank(searchKeyword)) {
            return findByListedPaged(listed, page, pageSize);
        }

        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("id")));

        searchKeyword = "%" + searchKeyword + "%";

        return switch (searchKeywordType) {
            case SearchKeywordTypeV1.content -> postRepository.findByListedAndContentLike(listed, searchKeyword, pageRequest);
            default -> postRepository.findByListedAndTitleLike(listed, searchKeyword, pageRequest);
        };
    }

    public Page<Post> findByAuthorPaged(Member author, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("id")));

        return postRepository.findByAuthor(author, pageRequest);
    }

    public Page<Post> findByAuthorPaged(
            Member author,
            SearchKeywordTypeV1 searchKeywordType,
            String searchKeyword,
            int page,
            int pageSize
    ) {
        if (Ut.str.isBlank(searchKeyword)) {
            return findByAuthorPaged(author, page, pageSize);
        }

        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("id")));

        searchKeyword = "%" + searchKeyword + "%";

        return switch (searchKeywordType) {
            case SearchKeywordTypeV1.content -> postRepository.findByAuthorAndContentLike(author, searchKeyword, pageRequest);
            default -> postRepository.findByAuthorAndTitleLike(author, searchKeyword, pageRequest);
        };
    }
}
