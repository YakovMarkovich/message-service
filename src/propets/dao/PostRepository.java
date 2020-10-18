package propets.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import propets.entities.GeneralPost;



public interface PostRepository extends MongoRepository<GeneralPost, Long> {

	Optional<GeneralPost[]> findAllByUserLogin(String string);

}
