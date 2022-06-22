package my.apps.service;

import my.apps.db.ArticleRepository;
import my.apps.domain.Article;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * @author flo
 * @since 22.06.2022.
 */
public class ArticleService {
    private ArticleRepository articleRepository = new ArticleRepository();

    public Article addArticle(String link, Date date, String summary, String domain) throws SQLException, ClassNotFoundException {
        Article article = new Article(link, date, summary, domain);
        articleRepository.insert(article);
        return article;
    }

    public List<Article> list() throws SQLException, ClassNotFoundException {
        return articleRepository.read();
    }
}
