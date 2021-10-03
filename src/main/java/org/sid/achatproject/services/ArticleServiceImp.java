package org.sid.achatproject.services;

import org.modelmapper.ModelMapper;
import org.sid.achatproject.Repository.ArticleRepository;
import org.sid.achatproject.Repository.UserRepository;
import org.sid.achatproject.entites.Article;
import org.sid.achatproject.entites.DossierImport;
import org.sid.achatproject.entites.UserEntity;
import org.sid.achatproject.shared.ArticleDto;
import org.sid.achatproject.shared.DossierImportDto;
import org.sid.achatproject.shared.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImp implements ArticleService {



    @Autowired
    UserRepository userRepository;
    @Autowired
    ArticleRepository articleRepository;

    @Override
    public ArticleDto createArticle(ArticleDto article, String email) {

        UserEntity currentUser=userRepository.findByEmail(email);
        ModelMapper modelMapper=new ModelMapper();
        UserDto userDto=modelMapper.map(currentUser,UserDto.class);



        Article article1=modelMapper.map(article,Article.class);
        Article art=articleRepository.save(article1);
        ArticleDto articleDto=modelMapper.map(art,ArticleDto.class);
        return articleDto;
    }

    @Override
    public void deleteArticler(String nomArticle) {

        Article article=articleRepository.findByNomArticle(nomArticle);

        if(article==null) throw new RuntimeException("article not  found ");

        articleRepository.delete(article);
    }

    @Override
    public ArticleDto updateArticle(String id, ArticleDto articleDto) {

        Article  article  =	articleRepository.findByNomArticle(id);

        if(article==null) throw new UsernameNotFoundException(id);


        article.setId(articleDto.getId());
        article.setNomArticle(articleDto.getNomArticle());
        article.setDesignationArticle(articleDto.getDesignationArticle());

        Article userEn= articleRepository.save(article);

        ArticleDto userD=new ArticleDto();

        BeanUtils.copyProperties(article, userD);

        return userD;
    }

}
