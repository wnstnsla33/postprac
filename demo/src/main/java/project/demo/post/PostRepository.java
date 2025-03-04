package project.demo.post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long>{
	//findpost,savepost,list
	
}
