package model;

import java.sql.Date;

public class Recommendation {
    private int id;
    private int user_id;
    private String recommendation_text;
    private Date date;

    public Recommendation(int id, int user_id, String recommendation_text, Date date) {
        this.id = id;
        this.user_id = user_id;
        this.recommendation_text = recommendation_text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getRecommendation_text() {
        return recommendation_text;
    }

    public void setRecommendation_text(String recommendation_text) {
        this.recommendation_text = recommendation_text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
