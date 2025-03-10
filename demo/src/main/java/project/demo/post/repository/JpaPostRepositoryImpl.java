package project.demo.post.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import project.demo.entity.PostEntity;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository // 빈 이름 지정
public class JpaPostRepositoryImpl implements PostRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void incrementHit(Long postId) {
        PostEntity post = em.find(PostEntity.class, postId);
        if (post != null) {
            post.setViewCount(post.getViewCount() + 1);
        }
    }

    @Override
    public int updatePost(Long postId, String content, String title) {
        PostEntity post = em.find(PostEntity.class, postId);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
            return 1; // 업데이트 성공
        }
        return 0; // 업데이트 실패
    }

    @Override
    public Optional<PostEntity> findById(Long id) {
        return Optional.ofNullable(em.find(PostEntity.class, id));
    }

    @Override
    public void save(PostEntity postEntity) {
        em.persist(postEntity);
    }

    @Override
    public List<PostEntity> findAll() {
        return em.createQuery("SELECT p FROM PostEntity p", PostEntity.class)
                 .getResultList();
    }

	@Override
	public void delete(Long id) {
		em.remove(em.find(PostEntity.class, id));
	}

}