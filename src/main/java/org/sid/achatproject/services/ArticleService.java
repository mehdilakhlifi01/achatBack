package org.sid.achatproject.services;

import org.sid.achatproject.shared.ArticleDto;

public interface ArticleService {
    ArticleDto createArticle(ArticleDto article, String name);

    void deleteArticler(String nomArticle);

    ArticleDto updateArticle(String id, ArticleDto articleDto);
}
