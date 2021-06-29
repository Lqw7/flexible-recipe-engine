package com.qiwei.engine.domain;

import java.util.ArrayList;
import java.util.List;

public class RecipeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RecipeExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andIngredientsIsNull() {
            addCriterion("ingredients is null");
            return (Criteria) this;
        }

        public Criteria andIngredientsIsNotNull() {
            addCriterion("ingredients is not null");
            return (Criteria) this;
        }

        public Criteria andIngredientsEqualTo(String value) {
            addCriterion("ingredients =", value, "ingredients");
            return (Criteria) this;
        }

        public Criteria andIngredientsNotEqualTo(String value) {
            addCriterion("ingredients <>", value, "ingredients");
            return (Criteria) this;
        }

        public Criteria andIngredientsGreaterThan(String value) {
            addCriterion("ingredients >", value, "ingredients");
            return (Criteria) this;
        }

        public Criteria andIngredientsGreaterThanOrEqualTo(String value) {
            addCriterion("ingredients >=", value, "ingredients");
            return (Criteria) this;
        }

        public Criteria andIngredientsLessThan(String value) {
            addCriterion("ingredients <", value, "ingredients");
            return (Criteria) this;
        }

        public Criteria andIngredientsLessThanOrEqualTo(String value) {
            addCriterion("ingredients <=", value, "ingredients");
            return (Criteria) this;
        }

        public Criteria andIngredientsLike(String value) {
            addCriterion("ingredients like", value, "ingredients");
            return (Criteria) this;
        }

        public Criteria andIngredientsNotLike(String value) {
            addCriterion("ingredients not like", value, "ingredients");
            return (Criteria) this;
        }

        public Criteria andIngredientsIn(List<String> values) {
            addCriterion("ingredients in", values, "ingredients");
            return (Criteria) this;
        }

        public Criteria andIngredientsNotIn(List<String> values) {
            addCriterion("ingredients not in", values, "ingredients");
            return (Criteria) this;
        }

        public Criteria andIngredientsBetween(String value1, String value2) {
            addCriterion("ingredients between", value1, value2, "ingredients");
            return (Criteria) this;
        }

        public Criteria andIngredientsNotBetween(String value1, String value2) {
            addCriterion("ingredients not between", value1, value2, "ingredients");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andImageIsNull() {
            addCriterion("image is null");
            return (Criteria) this;
        }

        public Criteria andImageIsNotNull() {
            addCriterion("image is not null");
            return (Criteria) this;
        }

        public Criteria andImageEqualTo(String value) {
            addCriterion("image =", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotEqualTo(String value) {
            addCriterion("image <>", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThan(String value) {
            addCriterion("image >", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThanOrEqualTo(String value) {
            addCriterion("image >=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThan(String value) {
            addCriterion("image <", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThanOrEqualTo(String value) {
            addCriterion("image <=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLike(String value) {
            addCriterion("image like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotLike(String value) {
            addCriterion("image not like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageIn(List<String> values) {
            addCriterion("image in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotIn(List<String> values) {
            addCriterion("image not in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageBetween(String value1, String value2) {
            addCriterion("image between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotBetween(String value1, String value2) {
            addCriterion("image not between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andCooktimeIsNull() {
            addCriterion("cookTime is null");
            return (Criteria) this;
        }

        public Criteria andCooktimeIsNotNull() {
            addCriterion("cookTime is not null");
            return (Criteria) this;
        }

        public Criteria andCooktimeEqualTo(String value) {
            addCriterion("cookTime =", value, "cooktime");
            return (Criteria) this;
        }

        public Criteria andCooktimeNotEqualTo(String value) {
            addCriterion("cookTime <>", value, "cooktime");
            return (Criteria) this;
        }

        public Criteria andCooktimeGreaterThan(String value) {
            addCriterion("cookTime >", value, "cooktime");
            return (Criteria) this;
        }

        public Criteria andCooktimeGreaterThanOrEqualTo(String value) {
            addCriterion("cookTime >=", value, "cooktime");
            return (Criteria) this;
        }

        public Criteria andCooktimeLessThan(String value) {
            addCriterion("cookTime <", value, "cooktime");
            return (Criteria) this;
        }

        public Criteria andCooktimeLessThanOrEqualTo(String value) {
            addCriterion("cookTime <=", value, "cooktime");
            return (Criteria) this;
        }

        public Criteria andCooktimeLike(String value) {
            addCriterion("cookTime like", value, "cooktime");
            return (Criteria) this;
        }

        public Criteria andCooktimeNotLike(String value) {
            addCriterion("cookTime not like", value, "cooktime");
            return (Criteria) this;
        }

        public Criteria andCooktimeIn(List<String> values) {
            addCriterion("cookTime in", values, "cooktime");
            return (Criteria) this;
        }

        public Criteria andCooktimeNotIn(List<String> values) {
            addCriterion("cookTime not in", values, "cooktime");
            return (Criteria) this;
        }

        public Criteria andCooktimeBetween(String value1, String value2) {
            addCriterion("cookTime between", value1, value2, "cooktime");
            return (Criteria) this;
        }

        public Criteria andCooktimeNotBetween(String value1, String value2) {
            addCriterion("cookTime not between", value1, value2, "cooktime");
            return (Criteria) this;
        }

        public Criteria andRecipeyieldIsNull() {
            addCriterion("recipeYield is null");
            return (Criteria) this;
        }

        public Criteria andRecipeyieldIsNotNull() {
            addCriterion("recipeYield is not null");
            return (Criteria) this;
        }

        public Criteria andRecipeyieldEqualTo(String value) {
            addCriterion("recipeYield =", value, "recipeyield");
            return (Criteria) this;
        }

        public Criteria andRecipeyieldNotEqualTo(String value) {
            addCriterion("recipeYield <>", value, "recipeyield");
            return (Criteria) this;
        }

        public Criteria andRecipeyieldGreaterThan(String value) {
            addCriterion("recipeYield >", value, "recipeyield");
            return (Criteria) this;
        }

        public Criteria andRecipeyieldGreaterThanOrEqualTo(String value) {
            addCriterion("recipeYield >=", value, "recipeyield");
            return (Criteria) this;
        }

        public Criteria andRecipeyieldLessThan(String value) {
            addCriterion("recipeYield <", value, "recipeyield");
            return (Criteria) this;
        }

        public Criteria andRecipeyieldLessThanOrEqualTo(String value) {
            addCriterion("recipeYield <=", value, "recipeyield");
            return (Criteria) this;
        }

        public Criteria andRecipeyieldLike(String value) {
            addCriterion("recipeYield like", value, "recipeyield");
            return (Criteria) this;
        }

        public Criteria andRecipeyieldNotLike(String value) {
            addCriterion("recipeYield not like", value, "recipeyield");
            return (Criteria) this;
        }

        public Criteria andRecipeyieldIn(List<String> values) {
            addCriterion("recipeYield in", values, "recipeyield");
            return (Criteria) this;
        }

        public Criteria andRecipeyieldNotIn(List<String> values) {
            addCriterion("recipeYield not in", values, "recipeyield");
            return (Criteria) this;
        }

        public Criteria andRecipeyieldBetween(String value1, String value2) {
            addCriterion("recipeYield between", value1, value2, "recipeyield");
            return (Criteria) this;
        }

        public Criteria andRecipeyieldNotBetween(String value1, String value2) {
            addCriterion("recipeYield not between", value1, value2, "recipeyield");
            return (Criteria) this;
        }

        public Criteria andDatepublishedIsNull() {
            addCriterion("datePublished is null");
            return (Criteria) this;
        }

        public Criteria andDatepublishedIsNotNull() {
            addCriterion("datePublished is not null");
            return (Criteria) this;
        }

        public Criteria andDatepublishedEqualTo(String value) {
            addCriterion("datePublished =", value, "datepublished");
            return (Criteria) this;
        }

        public Criteria andDatepublishedNotEqualTo(String value) {
            addCriterion("datePublished <>", value, "datepublished");
            return (Criteria) this;
        }

        public Criteria andDatepublishedGreaterThan(String value) {
            addCriterion("datePublished >", value, "datepublished");
            return (Criteria) this;
        }

        public Criteria andDatepublishedGreaterThanOrEqualTo(String value) {
            addCriterion("datePublished >=", value, "datepublished");
            return (Criteria) this;
        }

        public Criteria andDatepublishedLessThan(String value) {
            addCriterion("datePublished <", value, "datepublished");
            return (Criteria) this;
        }

        public Criteria andDatepublishedLessThanOrEqualTo(String value) {
            addCriterion("datePublished <=", value, "datepublished");
            return (Criteria) this;
        }

        public Criteria andDatepublishedLike(String value) {
            addCriterion("datePublished like", value, "datepublished");
            return (Criteria) this;
        }

        public Criteria andDatepublishedNotLike(String value) {
            addCriterion("datePublished not like", value, "datepublished");
            return (Criteria) this;
        }

        public Criteria andDatepublishedIn(List<String> values) {
            addCriterion("datePublished in", values, "datepublished");
            return (Criteria) this;
        }

        public Criteria andDatepublishedNotIn(List<String> values) {
            addCriterion("datePublished not in", values, "datepublished");
            return (Criteria) this;
        }

        public Criteria andDatepublishedBetween(String value1, String value2) {
            addCriterion("datePublished between", value1, value2, "datepublished");
            return (Criteria) this;
        }

        public Criteria andDatepublishedNotBetween(String value1, String value2) {
            addCriterion("datePublished not between", value1, value2, "datepublished");
            return (Criteria) this;
        }

        public Criteria andPreptimeIsNull() {
            addCriterion("prepTime is null");
            return (Criteria) this;
        }

        public Criteria andPreptimeIsNotNull() {
            addCriterion("prepTime is not null");
            return (Criteria) this;
        }

        public Criteria andPreptimeEqualTo(String value) {
            addCriterion("prepTime =", value, "preptime");
            return (Criteria) this;
        }

        public Criteria andPreptimeNotEqualTo(String value) {
            addCriterion("prepTime <>", value, "preptime");
            return (Criteria) this;
        }

        public Criteria andPreptimeGreaterThan(String value) {
            addCriterion("prepTime >", value, "preptime");
            return (Criteria) this;
        }

        public Criteria andPreptimeGreaterThanOrEqualTo(String value) {
            addCriterion("prepTime >=", value, "preptime");
            return (Criteria) this;
        }

        public Criteria andPreptimeLessThan(String value) {
            addCriterion("prepTime <", value, "preptime");
            return (Criteria) this;
        }

        public Criteria andPreptimeLessThanOrEqualTo(String value) {
            addCriterion("prepTime <=", value, "preptime");
            return (Criteria) this;
        }

        public Criteria andPreptimeLike(String value) {
            addCriterion("prepTime like", value, "preptime");
            return (Criteria) this;
        }

        public Criteria andPreptimeNotLike(String value) {
            addCriterion("prepTime not like", value, "preptime");
            return (Criteria) this;
        }

        public Criteria andPreptimeIn(List<String> values) {
            addCriterion("prepTime in", values, "preptime");
            return (Criteria) this;
        }

        public Criteria andPreptimeNotIn(List<String> values) {
            addCriterion("prepTime not in", values, "preptime");
            return (Criteria) this;
        }

        public Criteria andPreptimeBetween(String value1, String value2) {
            addCriterion("prepTime between", value1, value2, "preptime");
            return (Criteria) this;
        }

        public Criteria andPreptimeNotBetween(String value1, String value2) {
            addCriterion("prepTime not between", value1, value2, "preptime");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}