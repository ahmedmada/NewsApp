package com.example.ahmed.newsapp;

/**
 * Created by master on 31/10/2016.
 */
public class NewsItem {
    String title;
    String link;
    String author;
    String pubDate;
    String description;
    String bigPhoto;
    String smallPhoto;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBigPhoto() {
        return "http://www6.mashy.com/"+bigPhoto;
    }

    public void setBigPhoto(String bigPhoto) {
        this.bigPhoto = bigPhoto;
    }

    public String getSmallPhoto() {
        return "http://www6.mashy.com/"+smallPhoto;
    }

    public void setSmallPhoto(String smallPhoto) {
        this.smallPhoto = smallPhoto;
    }
}
