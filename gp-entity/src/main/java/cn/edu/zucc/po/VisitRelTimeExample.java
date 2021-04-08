package cn.edu.zucc.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class VisitRelTimeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VisitRelTimeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andVisitIdIsNull() {
            addCriterion("visit_id is null");
            return (Criteria) this;
        }

        public Criteria andVisitIdIsNotNull() {
            addCriterion("visit_id is not null");
            return (Criteria) this;
        }

        public Criteria andVisitIdEqualTo(Long value) {
            addCriterion("visit_id =", value, "visitId");
            return (Criteria) this;
        }

        public Criteria andVisitIdNotEqualTo(Long value) {
            addCriterion("visit_id <>", value, "visitId");
            return (Criteria) this;
        }

        public Criteria andVisitIdGreaterThan(Long value) {
            addCriterion("visit_id >", value, "visitId");
            return (Criteria) this;
        }

        public Criteria andVisitIdGreaterThanOrEqualTo(Long value) {
            addCriterion("visit_id >=", value, "visitId");
            return (Criteria) this;
        }

        public Criteria andVisitIdLessThan(Long value) {
            addCriterion("visit_id <", value, "visitId");
            return (Criteria) this;
        }

        public Criteria andVisitIdLessThanOrEqualTo(Long value) {
            addCriterion("visit_id <=", value, "visitId");
            return (Criteria) this;
        }

        public Criteria andVisitIdIn(List<Long> values) {
            addCriterion("visit_id in", values, "visitId");
            return (Criteria) this;
        }

        public Criteria andVisitIdNotIn(List<Long> values) {
            addCriterion("visit_id not in", values, "visitId");
            return (Criteria) this;
        }

        public Criteria andVisitIdBetween(Long value1, Long value2) {
            addCriterion("visit_id between", value1, value2, "visitId");
            return (Criteria) this;
        }

        public Criteria andVisitIdNotBetween(Long value1, Long value2) {
            addCriterion("visit_id not between", value1, value2, "visitId");
            return (Criteria) this;
        }

        public Criteria andVisitPeriodIsNull() {
            addCriterion("visit_period is null");
            return (Criteria) this;
        }

        public Criteria andVisitPeriodIsNotNull() {
            addCriterion("visit_period is not null");
            return (Criteria) this;
        }

        public Criteria andVisitPeriodEqualTo(Byte value) {
            addCriterion("visit_period =", value, "visitPeriod");
            return (Criteria) this;
        }

        public Criteria andVisitPeriodNotEqualTo(Byte value) {
            addCriterion("visit_period <>", value, "visitPeriod");
            return (Criteria) this;
        }

        public Criteria andVisitPeriodGreaterThan(Byte value) {
            addCriterion("visit_period >", value, "visitPeriod");
            return (Criteria) this;
        }

        public Criteria andVisitPeriodGreaterThanOrEqualTo(Byte value) {
            addCriterion("visit_period >=", value, "visitPeriod");
            return (Criteria) this;
        }

        public Criteria andVisitPeriodLessThan(Byte value) {
            addCriterion("visit_period <", value, "visitPeriod");
            return (Criteria) this;
        }

        public Criteria andVisitPeriodLessThanOrEqualTo(Byte value) {
            addCriterion("visit_period <=", value, "visitPeriod");
            return (Criteria) this;
        }

        public Criteria andVisitPeriodIn(List<Byte> values) {
            addCriterion("visit_period in", values, "visitPeriod");
            return (Criteria) this;
        }

        public Criteria andVisitPeriodNotIn(List<Byte> values) {
            addCriterion("visit_period not in", values, "visitPeriod");
            return (Criteria) this;
        }

        public Criteria andVisitPeriodBetween(Byte value1, Byte value2) {
            addCriterion("visit_period between", value1, value2, "visitPeriod");
            return (Criteria) this;
        }

        public Criteria andVisitPeriodNotBetween(Byte value1, Byte value2) {
            addCriterion("visit_period not between", value1, value2, "visitPeriod");
            return (Criteria) this;
        }

        public Criteria andVisitDayIsNull() {
            addCriterion("visit_day is null");
            return (Criteria) this;
        }

        public Criteria andVisitDayIsNotNull() {
            addCriterion("visit_day is not null");
            return (Criteria) this;
        }

        public Criteria andVisitDayEqualTo(Date value) {
            addCriterionForJDBCDate("visit_day =", value, "visitDay");
            return (Criteria) this;
        }

        public Criteria andVisitDayNotEqualTo(Date value) {
            addCriterionForJDBCDate("visit_day <>", value, "visitDay");
            return (Criteria) this;
        }

        public Criteria andVisitDayGreaterThan(Date value) {
            addCriterionForJDBCDate("visit_day >", value, "visitDay");
            return (Criteria) this;
        }

        public Criteria andVisitDayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("visit_day >=", value, "visitDay");
            return (Criteria) this;
        }

        public Criteria andVisitDayLessThan(Date value) {
            addCriterionForJDBCDate("visit_day <", value, "visitDay");
            return (Criteria) this;
        }

        public Criteria andVisitDayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("visit_day <=", value, "visitDay");
            return (Criteria) this;
        }

        public Criteria andVisitDayIn(List<Date> values) {
            addCriterionForJDBCDate("visit_day in", values, "visitDay");
            return (Criteria) this;
        }

        public Criteria andVisitDayNotIn(List<Date> values) {
            addCriterionForJDBCDate("visit_day not in", values, "visitDay");
            return (Criteria) this;
        }

        public Criteria andVisitDayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("visit_day between", value1, value2, "visitDay");
            return (Criteria) this;
        }

        public Criteria andVisitDayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("visit_day not between", value1, value2, "visitDay");
            return (Criteria) this;
        }

        public Criteria andTimePeriodIsNull() {
            addCriterion("time_period is null");
            return (Criteria) this;
        }

        public Criteria andTimePeriodIsNotNull() {
            addCriterion("time_period is not null");
            return (Criteria) this;
        }

        public Criteria andTimePeriodEqualTo(String value) {
            addCriterion("time_period =", value, "timePeriod");
            return (Criteria) this;
        }

        public Criteria andTimePeriodNotEqualTo(String value) {
            addCriterion("time_period <>", value, "timePeriod");
            return (Criteria) this;
        }

        public Criteria andTimePeriodGreaterThan(String value) {
            addCriterion("time_period >", value, "timePeriod");
            return (Criteria) this;
        }

        public Criteria andTimePeriodGreaterThanOrEqualTo(String value) {
            addCriterion("time_period >=", value, "timePeriod");
            return (Criteria) this;
        }

        public Criteria andTimePeriodLessThan(String value) {
            addCriterion("time_period <", value, "timePeriod");
            return (Criteria) this;
        }

        public Criteria andTimePeriodLessThanOrEqualTo(String value) {
            addCriterion("time_period <=", value, "timePeriod");
            return (Criteria) this;
        }

        public Criteria andTimePeriodLike(String value) {
            addCriterion("time_period like", value, "timePeriod");
            return (Criteria) this;
        }

        public Criteria andTimePeriodNotLike(String value) {
            addCriterion("time_period not like", value, "timePeriod");
            return (Criteria) this;
        }

        public Criteria andTimePeriodIn(List<String> values) {
            addCriterion("time_period in", values, "timePeriod");
            return (Criteria) this;
        }

        public Criteria andTimePeriodNotIn(List<String> values) {
            addCriterion("time_period not in", values, "timePeriod");
            return (Criteria) this;
        }

        public Criteria andTimePeriodBetween(String value1, String value2) {
            addCriterion("time_period between", value1, value2, "timePeriod");
            return (Criteria) this;
        }

        public Criteria andTimePeriodNotBetween(String value1, String value2) {
            addCriterion("time_period not between", value1, value2, "timePeriod");
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
    }
}