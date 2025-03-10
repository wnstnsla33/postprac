//package project.demo.post.repository;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import project.demo.post.PostEntity;
//
//@Repository
//public class MybatisPostRepositoryImpl implements PostRepository{
//	 @Autowired
//	    private PostMapper postMapper;
//
//	    @Override
//	    public void incrementHit(Long postId) {
//	        postMapper.incrementHit(postId);
//	    }
//
//	    @Override
//	    public int updatePost(Long postId, String content, String title) {
//	        return postMapper.updatePost(postId, title, content);
//	    }
//
//	    @Override
//	    public Optional<PostEntity> findById(Long id) {
//	        return postMapper.findById(id);
//	    }
//
//	    @Override
//	    public void save(PostEntity postEntity) {
//	        postMapper.save(postEntity);
//	    }
//
//	    @Override
//	    public List<PostEntity> findAll() {
//	        return postMapper.findAll();
//	    }
//
//	    @Override
//	    public void delete(Long id) {
//	        postMapper.delete(id);
//	    }
//	
//}
