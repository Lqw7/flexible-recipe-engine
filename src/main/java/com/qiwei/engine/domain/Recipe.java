package com.qiwei.engine.domain;

import java.util.Objects;

public class Recipe {
    private Long id;

    private String name;

    private String ingredients;

    private String url;

    private String image;

    private String cooktime;

    private String preptime;

    private String description;

    private Integer voteCount;

    private Integer viewCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCooktime() {
        return cooktime;
    }

    public void setCooktime(String cooktime) {
        this.cooktime = cooktime;
    }

    public String getPreptime() {
        return preptime;
    }

    public void setPreptime(String preptime) {
        this.preptime = preptime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", ingredients=").append(ingredients);
        sb.append(", url=").append(url);
        sb.append(", image=").append(image);
        sb.append(", cooktime=").append(cooktime);
        sb.append(", preptime=").append(preptime);
        sb.append(", description=").append(description);
        sb.append(", voteCount=").append(voteCount);
        sb.append(", viewCount=").append(viewCount);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id) && Objects.equals(name, recipe.name) && Objects.equals(ingredients, recipe.ingredients) && Objects.equals(url, recipe.url) && Objects.equals(image, recipe.image) && Objects.equals(cooktime, recipe.cooktime) && Objects.equals(preptime, recipe.preptime) && Objects.equals(description, recipe.description) && Objects.equals(voteCount, recipe.voteCount) && Objects.equals(viewCount, recipe.viewCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ingredients, url, image, cooktime, preptime, description, voteCount, viewCount);
    }
}