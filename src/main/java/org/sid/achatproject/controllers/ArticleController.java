package org.sid.achatproject.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.sid.achatproject.Repository.ArticleRepository;
import org.sid.achatproject.Request.ArticleRequest;
import org.sid.achatproject.Request.DemandeRequest;
import org.sid.achatproject.Request.DossierRequest;
import org.sid.achatproject.Response.ArticleResponse;
import org.sid.achatproject.Response.DemandeResponse;
import org.sid.achatproject.Response.DossierResponse;
import org.sid.achatproject.entites.Article;
import org.sid.achatproject.services.ArticleService;
import org.sid.achatproject.shared.ArticleDto;
import org.sid.achatproject.shared.DemandeDto;
import org.sid.achatproject.shared.DossierImportDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {


    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleService articleService;

    @GetMapping
    public List<Article>  getDemandes(){

        List<Article> article = articleRepository.findAll();

        return article;
    }

    @PostMapping
    public ResponseEntity storeDossier(@RequestBody ArticleRequest articleRequest, Principal principal){

        ModelMapper modelMapper=new ModelMapper();

        ArticleDto article=modelMapper.map(articleRequest,ArticleDto.class);
        ArticleDto createArticle=articleService.createArticle(article,principal.getName());

        ArticleResponse newArticle=modelMapper.map(createArticle,ArticleResponse.class);

        return new ResponseEntity(newArticle,HttpStatus.CREATED);

    }

    /*@PatchMapping(path = "/{id}")
    public ResponseEntity<ArticleResponse> updateUser(@PathVariable(name = "id") String id, @RequestBody ArticleRequest articleRequest) {

        ArticleDto articleDto=new ArticleDto();

        BeanUtils.copyProperties(articleRequest, articleDto);

        ArticleDto updateDemande = articleService.updateArticle(id, articleDto);

        ArticleResponse demanRes = new ArticleResponse();

        BeanUtils.copyProperties(updateDemande, demanRes);

        return new ResponseEntity<ArticleResponse>(demanRes,HttpStatus.ACCEPTED);

    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDossier(@PathVariable(name = "id") String nomArticle){

        articleService.deleteArticler(nomArticle);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
