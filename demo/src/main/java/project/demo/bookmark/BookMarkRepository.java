package project.demo.bookmark;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.demo.entity.BookMarkEntity;
import project.demo.entity.PostEntity;

@Repository
public interface BookMarkRepository extends JpaRepository<BookMarkEntity, Long>{
	@Modifying
	@Query("delete from BookMarkEntity b where bookmarkId =:bmId")
	void deleteBookmark(@Param("bmId")long bmId);
	
	@Query("select count(b) from BookMarkEntity b where b.post.postId=:postId")
	int findBookmarkByPost(@Param("postId") Long postId);
	@Query("select b.post.postId from BookMarkEntity b where b.user.userId=:userId" )
	List<Long> bookmarkListByUser(@Param("userId")Long userId);
}
