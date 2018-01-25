/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.codecraft.webmagic.example;

import com.alibaba.fastjson.annotation.JSONField;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FileMapPipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 *
 * @author yangguanqun
 */
public class MITrepoPageProcessor implements PageProcessor {
    
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        page.addTargetRequests(page.getHtml().links().regex("(https://www\\.technologyreview\\.com/\\w+/\\w+)").all());
//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        page.putField("author", page.getHtml().xpath("//div[@class='article-topper__hgroup--bottom']/ul/li/a/text()").toString());
//        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
//        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        page.putField("title", page.getHtml().xpath("//h1[@class='article-topper__title']/text()").toString());
        page.putField("subtitle", page.getHtml().xpath("//h2[@class='article-topper__subtitle']/text()").toString());

        if (page.getResultItems().get("title")==null){
            //skip this page
            page.setSkip(true);
        }
        //page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
        
    }

    @Override
    public Site getSite() {
        return site;
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public static void main(String[] args){
//        Spider.create(new MITrepoPageProcessor()).addUrl("https://github.com/code4craft").addPipeline(new FilePipeline("/")).thread(5).run();
            Spider.create(new MITrepoPageProcessor()).addPipeline(new FileMapPipeline("/Volumes/MITDATA/")).addUrl("https://www.technologyreview.com/").thread(5).run();
        
        
    }
    
    
    
}
