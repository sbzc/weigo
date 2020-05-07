package com.weigo.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbEvaluateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbEvaluateExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Long value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Long value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Long value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Long value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Long value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Long value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Long> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Long> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Long value1, Long value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Long value1, Long value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andEvaluatemsgIsNull() {
            addCriterion("evaluateMsg is null");
            return (Criteria) this;
        }

        public Criteria andEvaluatemsgIsNotNull() {
            addCriterion("evaluateMsg is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluatemsgEqualTo(String value) {
            addCriterion("evaluateMsg =", value, "evaluatemsg");
            return (Criteria) this;
        }

        public Criteria andEvaluatemsgNotEqualTo(String value) {
            addCriterion("evaluateMsg <>", value, "evaluatemsg");
            return (Criteria) this;
        }

        public Criteria andEvaluatemsgGreaterThan(String value) {
            addCriterion("evaluateMsg >", value, "evaluatemsg");
            return (Criteria) this;
        }

        public Criteria andEvaluatemsgGreaterThanOrEqualTo(String value) {
            addCriterion("evaluateMsg >=", value, "evaluatemsg");
            return (Criteria) this;
        }

        public Criteria andEvaluatemsgLessThan(String value) {
            addCriterion("evaluateMsg <", value, "evaluatemsg");
            return (Criteria) this;
        }

        public Criteria andEvaluatemsgLessThanOrEqualTo(String value) {
            addCriterion("evaluateMsg <=", value, "evaluatemsg");
            return (Criteria) this;
        }

        public Criteria andEvaluatemsgLike(String value) {
            addCriterion("evaluateMsg like", value, "evaluatemsg");
            return (Criteria) this;
        }

        public Criteria andEvaluatemsgNotLike(String value) {
            addCriterion("evaluateMsg not like", value, "evaluatemsg");
            return (Criteria) this;
        }

        public Criteria andEvaluatemsgIn(List<String> values) {
            addCriterion("evaluateMsg in", values, "evaluatemsg");
            return (Criteria) this;
        }

        public Criteria andEvaluatemsgNotIn(List<String> values) {
            addCriterion("evaluateMsg not in", values, "evaluatemsg");
            return (Criteria) this;
        }

        public Criteria andEvaluatemsgBetween(String value1, String value2) {
            addCriterion("evaluateMsg between", value1, value2, "evaluatemsg");
            return (Criteria) this;
        }

        public Criteria andEvaluatemsgNotBetween(String value1, String value2) {
            addCriterion("evaluateMsg not between", value1, value2, "evaluatemsg");
            return (Criteria) this;
        }

        public Criteria andEvaluatescoreIsNull() {
            addCriterion("evaluateScore is null");
            return (Criteria) this;
        }

        public Criteria andEvaluatescoreIsNotNull() {
            addCriterion("evaluateScore is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluatescoreEqualTo(Long value) {
            addCriterion("evaluateScore =", value, "evaluatescore");
            return (Criteria) this;
        }

        public Criteria andEvaluatescoreNotEqualTo(Long value) {
            addCriterion("evaluateScore <>", value, "evaluatescore");
            return (Criteria) this;
        }

        public Criteria andEvaluatescoreGreaterThan(Long value) {
            addCriterion("evaluateScore >", value, "evaluatescore");
            return (Criteria) this;
        }

        public Criteria andEvaluatescoreGreaterThanOrEqualTo(Long value) {
            addCriterion("evaluateScore >=", value, "evaluatescore");
            return (Criteria) this;
        }

        public Criteria andEvaluatescoreLessThan(Long value) {
            addCriterion("evaluateScore <", value, "evaluatescore");
            return (Criteria) this;
        }

        public Criteria andEvaluatescoreLessThanOrEqualTo(Long value) {
            addCriterion("evaluateScore <=", value, "evaluatescore");
            return (Criteria) this;
        }

        public Criteria andEvaluatescoreIn(List<Long> values) {
            addCriterion("evaluateScore in", values, "evaluatescore");
            return (Criteria) this;
        }

        public Criteria andEvaluatescoreNotIn(List<Long> values) {
            addCriterion("evaluateScore not in", values, "evaluatescore");
            return (Criteria) this;
        }

        public Criteria andEvaluatescoreBetween(Long value1, Long value2) {
            addCriterion("evaluateScore between", value1, value2, "evaluatescore");
            return (Criteria) this;
        }

        public Criteria andEvaluatescoreNotBetween(Long value1, Long value2) {
            addCriterion("evaluateScore not between", value1, value2, "evaluatescore");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
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