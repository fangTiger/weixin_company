/*  
 * @(#) NewsMessage.java Create on 2014-5-29 上午9:39:08   
 *   
 * Copyright 2014 by xl.   
 */


package cn.system.weixin.init.bean.resp;

import java.util.List;

/**
 * 图文消息
 * @author zhangshiyuan
 * @date   2014-5-29
 */
public class NewsMessage extends BaseMessage {
	private static final long serialVersionUID = -2707586653762198900L;

	// 图文消息个数，限制为10条以内  
    private int ArticleCount;  
    // 多条图文消息信息，默认第一个item为大图  
    private List<Article> Articles;  
  
    public int getArticleCount() {  
        return ArticleCount;  
    }  
  
    public void setArticleCount(int articleCount) {  
        ArticleCount = articleCount;  
    }  
  
    public List<Article> getArticles() {  
        return Articles;  
    }  
  
    public void setArticles(List<Article> articles) {  
        Articles = articles;  
    }  
    
}
