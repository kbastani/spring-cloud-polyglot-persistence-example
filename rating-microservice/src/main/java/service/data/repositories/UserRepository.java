package service.data.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import service.data.domain.entity.User;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    @Override
    @Query("MATCH (u:User) RETURN u")
    Page<User> findAll(Pageable pageable);

    @Override
    @Query("MATCH (u:User) WHERE id(u) = {id} RETURN u")
    User findOne(@Param(value = "id") Long id);
}
