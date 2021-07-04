package com.qiwei.engine.resp;

public class RecipeResp {
    private Long id;

    private String name;

    private String ingredients;

    private String url;

    private String image;

    private String cooktime;

    private String recipeyield;

    private String datepublished;

    private String preptime;

    private String description;

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

    public String getRecipeyield() {
        return recipeyield;
    }

    public void setRecipeyield(String recipeyield) {
        this.recipeyield = recipeyield;
    }

    public String getDatepublished() {
        return datepublished;
    }

    public void setDatepublished(String datepublished) {
        this.datepublished = datepublished;
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
        sb.append(", recipeyield=").append(recipeyield);
        sb.append(", datepublished=").append(datepublished);
        sb.append(", preptime=").append(preptime);
        sb.append(", description=").append(description);
        sb.append("]");
        return sb.toString();
    }
}