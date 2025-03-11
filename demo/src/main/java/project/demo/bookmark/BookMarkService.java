package project.demo.bookmark;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import project.demo.entity.BookMarkEntity;
import project.demo.entity.PostEntity;
import project.demo.post.PostService;
import project.demo.user.UserService;

@Service
@Transactional
public class BookMarkService {
	@Autowired
	BookMarkRepository bookMarkRepository;
	@Autowired
	UserService userService;
	@Autowired 
	PostService postService;
	public Long createBookmark(BookMarkEntity bookMarkEntity) {
		return bookMarkRepository.save(bookMarkEntity).getBookmarkId();
	}
	public void deleteBookmark(Long bmId) {
		bookMarkRepository.deleteBookmark(bmId);
	}
	public int findBookmarkByPost(Long postId) {
		return bookMarkRepository.findBookmarkByPost(postId);
	}
	public List<PostEntity> findPostByUser(Long userId){
		List<PostEntity> postList = new ArrayList<PostEntity>();
		List<Long> postIdList = bookMarkRepository.bookmarkListByUser(userId);
		for(Long postId:postIdList) {
			postList.add(postService.findPost(userId).get());
		}
		return postList;
	}
}
