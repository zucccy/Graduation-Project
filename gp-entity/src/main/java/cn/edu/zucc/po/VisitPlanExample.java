package cn.edu.zucc.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class VisitPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VisitPlanExample() {
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

        public Criteria andHospitalIdIsNull() {
            addCriterion("hospital_id is null");
            return (Criteria) this;
        }

        public Criteria andHospitalIdIsNotNull() {
            addCriterion("hospital_id is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalIdEqualTo(Long value) {
            addCriterion("hospital_id =", value, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdNotEqualTo(Long value) {
            addCriterion("hospital_id <>", value, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdGreaterThan(Long value) {
            addCriterion("hospital_id >", value, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdGreaterThanOrEqualTo(Long value) {
            addCriterion("hospital_id >=", value, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdLessThan(Long value) {
            addCriterion("hospital_id <", value, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdLessThanOrEqualTo(Long value) {
            addCriterion("hospital_id <=", value, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdIn(List<Long> values) {
            addCriterion("hospital_id in", values, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdNotIn(List<Long> values) {
            addCriterion("hospital_id not in", values, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdBetween(Long value1, Long value2) {
            addCriterion("hospital_id between", value1, value2, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdNotBetween(Long value1, Long value2) {
            addCriterion("hospital_id not between", value1, value2, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdIsNull() {
            addCriterion("office_id is null");
            return (Criteria) this;
        }

        public Criteria andOfficeIdIsNotNull() {
            addCriterion("office_id is not null");
            return (Criteria) this;
        }

        public Criteria andOfficeIdEqualTo(Long value) {
            addCriterion("office_id =", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdNotEqualTo(Long value) {
            addCriterion("office_id <>", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdGreaterThan(Long value) {
            addCriterion("office_id >", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("office_id >=", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdLessThan(Long value) {
            addCriterion("office_id <", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdLessThanOrEqualTo(Long value) {
            addCriterion("office_id <=", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdIn(List<Long> values) {
            addCriterion("office_id in", values, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdNotIn(List<Long> values) {
            addCriterion("office_id not in", values, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdBetween(Long value1, Long value2) {
            addCriterion("office_id between", value1, value2, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdNotBetween(Long value1, Long value2) {
            addCriterion("office_id not between", value1, value2, "officeId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdIsNull() {
            addCriterion("doctor_id is null");
            return (Criteria) this;
        }

        public Criteria andDoctorIdIsNotNull() {
            addCriterion("doctor_id is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorIdEqualTo(Long value) {
            addCriterion("doctor_id =", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdNotEqualTo(Long value) {
            addCriterion("doctor_id <>", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdGreaterThan(Long value) {
            addCriterion("doctor_id >", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("doctor_id >=", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdLessThan(Long value) {
            addCriterion("doctor_id <", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdLessThanOrEqualTo(Long value) {
            addCriterion("doctor_id <=", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdIn(List<Long> values) {
            addCriterion("doctor_id in", values, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdNotIn(List<Long> values) {
            addCriterion("doctor_id not in", values, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdBetween(Long value1, Long value2) {
            addCriterion("doctor_id between", value1, value2, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdNotBetween(Long value1, Long value2) {
            addCriterion("doctor_id not between", value1, value2, "doctorId");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIsNull() {
            addCriterion("visit_type is null");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIsNotNull() {
            addCriterion("visit_type is not null");
            return (Criteria) this;
        }

        public Criteria andVisitTypeEqualTo(Byte value) {
            addCriterion("visit_type =", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNotEqualTo(Byte value) {
            addCriterion("visit_type <>", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeGreaterThan(Byte value) {
            addCriterion("visit_type >", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("visit_type >=", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeLessThan(Byte value) {
            addCriterion("visit_type <", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeLessThanOrEqualTo(Byte value) {
            addCriterion("visit_type <=", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIn(List<Byte> values) {
            addCriterion("visit_type in", values, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNotIn(List<Byte> values) {
            addCriterion("visit_type not in", values, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeBetween(Byte value1, Byte value2) {
            addCriterion("visit_type between", value1, value2, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("visit_type not between", value1, value2, "visitType");
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

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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