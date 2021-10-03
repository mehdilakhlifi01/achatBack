package org.sid.achatproject.Repository;

import org.sid.achatproject.entites.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<Article,Long> {

    Article findByNomArticle(String nomArticle);
}
