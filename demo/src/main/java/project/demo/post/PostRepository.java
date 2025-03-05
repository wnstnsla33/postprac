package project.demo.post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long>{
	@Modifying
	@Query("UPDATE PostEntity p SET p.viewCount = p.viewCount + 1 WHERE p.postId = :id")
	void incrementHit(@Param("id")Long id);
}
