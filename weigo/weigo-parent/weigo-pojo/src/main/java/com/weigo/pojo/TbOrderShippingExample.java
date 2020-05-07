package com.weigo.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbOrderShippingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbOrderShippingExample() {
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

        public Criteria andOrderItemIdIsNull() {
            addCriterion("order_item_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdIsNotNull() {
            addCriterion("order_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdEqualTo(String value) {
            addCriterion("order_item_id =", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdNotEqualTo(String value) {
            addCriterion("order_item_id <>", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdGreaterThan(String value) {
            addCriterion("order_item_id >", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_item_id >=", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdLessThan(String value) {
            addCriterion("order_item_id <", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdLessThanOrEqualTo(String value) {
            addCriterion("order_item_id <=", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdLike(String value) {
            addCriterion("order_item_id like", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdNotLike(String value) {
            addCriterion("order_item_id not like", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdIn(List<String> values) {
            addCriterion("order_item_id in", values, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdNotIn(List<String> values) {
            addCriterion("order_item_id not in", values, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdBetween(String value1, String value2) {
            addCriterion("order_item_id between", value1, value2, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdNotBetween(String value1, String value2) {
            addCriterion("order_item_id not between", value1, value2, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andStartAddressIsNull() {
            addCriterion("start_address is null");
            return (Criteria) this;
        }

        public Criteria andStartAddressIsNotNull() {
            addCriterion("start_address is not null");
            return (Criteria) this;
        }

        public Criteria andStartAddressEqualTo(String value) {
            addCriterion("start_address =", value, "startAddress");
            return (Criteria) this;
        }

        public Criteria andStartAddressNotEqualTo(String value) {
            addCriterion("start_address <>", value, "startAddress");
            return (Criteria) this;
        }

        public Criteria andStartAddressGreaterThan(String value) {
            addCriterion("start_address >", value, "startAddress");
            return (Criteria) this;
        }

        public Criteria andStartAddressGreaterThanOrEqualTo(String value) {
            addCriterion("start_address >=", value, "startAddress");
            return (Criteria) this;
        }

        public Criteria andStartAddressLessThan(String value) {
            addCriterion("start_address <", value, "startAddress");
            return (Criteria) this;
        }

        public Criteria andStartAddressLessThanOrEqualTo(String value) {
            addCriterion("start_address <=", value, "startAddress");
            return (Criteria) this;
        }

        public Criteria andStartAddressLike(String value) {
            addCriterion("start_address like", value, "startAddress");
            return (Criteria) this;
        }

        public Criteria andStartAddressNotLike(String value) {
            addCriterion("start_address not like", value, "startAddress");
            return (Criteria) this;
        }

        public Criteria andStartAddressIn(List<String> values) {
            addCriterion("start_address in", values, "startAddress");
            return (Criteria) this;
        }

        public Criteria andStartAddressNotIn(List<String> values) {
            addCriterion("start_address not in", values, "startAddress");
            return (Criteria) this;
        }

        public Criteria andStartAddressBetween(String value1, String value2) {
            addCriterion("start_address between", value1, value2, "startAddress");
            return (Criteria) this;
        }

        public Criteria andStartAddressNotBetween(String value1, String value2) {
            addCriterion("start_address not between", value1, value2, "startAddress");
            return (Criteria) this;
        }

        public Criteria andStartPhoneIsNull() {
            addCriterion("start_phone is null");
            return (Criteria) this;
        }

        public Criteria andStartPhoneIsNotNull() {
            addCriterion("start_phone is not null");
            return (Criteria) this;
        }

        public Criteria andStartPhoneEqualTo(String value) {
            addCriterion("start_phone =", value, "startPhone");
            return (Criteria) this;
        }

        public Criteria andStartPhoneNotEqualTo(String value) {
            addCriterion("start_phone <>", value, "startPhone");
            return (Criteria) this;
        }

        public Criteria andStartPhoneGreaterThan(String value) {
            addCriterion("start_phone >", value, "startPhone");
            return (Criteria) this;
        }

        public Criteria andStartPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("start_phone >=", value, "startPhone");
            return (Criteria) this;
        }

        public Criteria andStartPhoneLessThan(String value) {
            addCriterion("start_phone <", value, "startPhone");
            return (Criteria) this;
        }

        public Criteria andStartPhoneLessThanOrEqualTo(String value) {
            addCriterion("start_phone <=", value, "startPhone");
            return (Criteria) this;
        }

        public Criteria andStartPhoneLike(String value) {
            addCriterion("start_phone like", value, "startPhone");
            return (Criteria) this;
        }

        public Criteria andStartPhoneNotLike(String value) {
            addCriterion("start_phone not like", value, "startPhone");
            return (Criteria) this;
        }

        public Criteria andStartPhoneIn(List<String> values) {
            addCriterion("start_phone in", values, "startPhone");
            return (Criteria) this;
        }

        public Criteria andStartPhoneNotIn(List<String> values) {
            addCriterion("start_phone not in", values, "startPhone");
            return (Criteria) this;
        }

        public Criteria andStartPhoneBetween(String value1, String value2) {
            addCriterion("start_phone between", value1, value2, "startPhone");
            return (Criteria) this;
        }

        public Criteria andStartPhoneNotBetween(String value1, String value2) {
            addCriterion("start_phone not between", value1, value2, "startPhone");
            return (Criteria) this;
        }

        public Criteria andStartNameIsNull() {
            addCriterion("start_name is null");
            return (Criteria) this;
        }

        public Criteria andStartNameIsNotNull() {
            addCriterion("start_name is not null");
            return (Criteria) this;
        }

        public Criteria andStartNameEqualTo(String value) {
            addCriterion("start_name =", value, "startName");
            return (Criteria) this;
        }

        public Criteria andStartNameNotEqualTo(String value) {
            addCriterion("start_name <>", value, "startName");
            return (Criteria) this;
        }

        public Criteria andStartNameGreaterThan(String value) {
            addCriterion("start_name >", value, "startName");
            return (Criteria) this;
        }

        public Criteria andStartNameGreaterThanOrEqualTo(String value) {
            addCriterion("start_name >=", value, "startName");
            return (Criteria) this;
        }

        public Criteria andStartNameLessThan(String value) {
            addCriterion("start_name <", value, "startName");
            return (Criteria) this;
        }

        public Criteria andStartNameLessThanOrEqualTo(String value) {
            addCriterion("start_name <=", value, "startName");
            return (Criteria) this;
        }

        public Criteria andStartNameLike(String value) {
            addCriterion("start_name like", value, "startName");
            return (Criteria) this;
        }

        public Criteria andStartNameNotLike(String value) {
            addCriterion("start_name not like", value, "startName");
            return (Criteria) this;
        }

        public Criteria andStartNameIn(List<String> values) {
            addCriterion("start_name in", values, "startName");
            return (Criteria) this;
        }

        public Criteria andStartNameNotIn(List<String> values) {
            addCriterion("start_name not in", values, "startName");
            return (Criteria) this;
        }

        public Criteria andStartNameBetween(String value1, String value2) {
            addCriterion("start_name between", value1, value2, "startName");
            return (Criteria) this;
        }

        public Criteria andStartNameNotBetween(String value1, String value2) {
            addCriterion("start_name not between", value1, value2, "startName");
            return (Criteria) this;
        }

        public Criteria andEndAddressIsNull() {
            addCriterion("end_address is null");
            return (Criteria) this;
        }

        public Criteria andEndAddressIsNotNull() {
            addCriterion("end_address is not null");
            return (Criteria) this;
        }

        public Criteria andEndAddressEqualTo(String value) {
            addCriterion("end_address =", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressNotEqualTo(String value) {
            addCriterion("end_address <>", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressGreaterThan(String value) {
            addCriterion("end_address >", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressGreaterThanOrEqualTo(String value) {
            addCriterion("end_address >=", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressLessThan(String value) {
            addCriterion("end_address <", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressLessThanOrEqualTo(String value) {
            addCriterion("end_address <=", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressLike(String value) {
            addCriterion("end_address like", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressNotLike(String value) {
            addCriterion("end_address not like", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressIn(List<String> values) {
            addCriterion("end_address in", values, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressNotIn(List<String> values) {
            addCriterion("end_address not in", values, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressBetween(String value1, String value2) {
            addCriterion("end_address between", value1, value2, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressNotBetween(String value1, String value2) {
            addCriterion("end_address not between", value1, value2, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndPhoneIsNull() {
            addCriterion("end_phone is null");
            return (Criteria) this;
        }

        public Criteria andEndPhoneIsNotNull() {
            addCriterion("end_phone is not null");
            return (Criteria) this;
        }

        public Criteria andEndPhoneEqualTo(String value) {
            addCriterion("end_phone =", value, "endPhone");
            return (Criteria) this;
        }

        public Criteria andEndPhoneNotEqualTo(String value) {
            addCriterion("end_phone <>", value, "endPhone");
            return (Criteria) this;
        }

        public Criteria andEndPhoneGreaterThan(String value) {
            addCriterion("end_phone >", value, "endPhone");
            return (Criteria) this;
        }

        public Criteria andEndPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("end_phone >=", value, "endPhone");
            return (Criteria) this;
        }

        public Criteria andEndPhoneLessThan(String value) {
            addCriterion("end_phone <", value, "endPhone");
            return (Criteria) this;
        }

        public Criteria andEndPhoneLessThanOrEqualTo(String value) {
            addCriterion("end_phone <=", value, "endPhone");
            return (Criteria) this;
        }

        public Criteria andEndPhoneLike(String value) {
            addCriterion("end_phone like", value, "endPhone");
            return (Criteria) this;
        }

        public Criteria andEndPhoneNotLike(String value) {
            addCriterion("end_phone not like", value, "endPhone");
            return (Criteria) this;
        }

        public Criteria andEndPhoneIn(List<String> values) {
            addCriterion("end_phone in", values, "endPhone");
            return (Criteria) this;
        }

        public Criteria andEndPhoneNotIn(List<String> values) {
            addCriterion("end_phone not in", values, "endPhone");
            return (Criteria) this;
        }

        public Criteria andEndPhoneBetween(String value1, String value2) {
            addCriterion("end_phone between", value1, value2, "endPhone");
            return (Criteria) this;
        }

        public Criteria andEndPhoneNotBetween(String value1, String value2) {
            addCriterion("end_phone not between", value1, value2, "endPhone");
            return (Criteria) this;
        }

        public Criteria andEndNameIsNull() {
            addCriterion("end_name is null");
            return (Criteria) this;
        }

        public Criteria andEndNameIsNotNull() {
            addCriterion("end_name is not null");
            return (Criteria) this;
        }

        public Criteria andEndNameEqualTo(String value) {
            addCriterion("end_name =", value, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameNotEqualTo(String value) {
            addCriterion("end_name <>", value, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameGreaterThan(String value) {
            addCriterion("end_name >", value, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameGreaterThanOrEqualTo(String value) {
            addCriterion("end_name >=", value, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameLessThan(String value) {
            addCriterion("end_name <", value, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameLessThanOrEqualTo(String value) {
            addCriterion("end_name <=", value, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameLike(String value) {
            addCriterion("end_name like", value, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameNotLike(String value) {
            addCriterion("end_name not like", value, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameIn(List<String> values) {
            addCriterion("end_name in", values, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameNotIn(List<String> values) {
            addCriterion("end_name not in", values, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameBetween(String value1, String value2) {
            addCriterion("end_name between", value1, value2, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameNotBetween(String value1, String value2) {
            addCriterion("end_name not between", value1, value2, "endName");
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

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
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

        public Criteria andUpdatedIsNull() {
            addCriterion("updated is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNotNull() {
            addCriterion("updated is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedEqualTo(Date value) {
            addCriterion("updated =", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotEqualTo(Date value) {
            addCriterion("updated <>", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThan(Date value) {
            addCriterion("updated >", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThanOrEqualTo(Date value) {
            addCriterion("updated >=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThan(Date value) {
            addCriterion("updated <", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThanOrEqualTo(Date value) {
            addCriterion("updated <=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedIn(List<Date> values) {
            addCriterion("updated in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotIn(List<Date> values) {
            addCriterion("updated not in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedBetween(Date value1, Date value2) {
            addCriterion("updated between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotBetween(Date value1, Date value2) {
            addCriterion("updated not between", value1, value2, "updated");
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