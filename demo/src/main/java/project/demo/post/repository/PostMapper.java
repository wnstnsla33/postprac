//package project.demo.post.repository;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//import project.demo.post.PostEntity;
//
//@Mapper
//public interface PostMapper {
//    void incrementHit(@Param("id") Long id);
//    int updatePost(@Param("id") Long id, @Param("title") String title, @Param("content") String content);
//    Optional<PostEntity> findById(@Param("id") Long id);
//    void save(PostEntity postEntity);
//    List<PostEntity> findAll();
//    void delete(@Param("postId") Long postId);
//}
