package com.bench.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class S3UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public S3UserExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAccountIsNull() {
            addCriterion("account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("account like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("account not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("account not between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andPwdIsNull() {
            addCriterion("pwd is null");
            return (Criteria) this;
        }

        public Criteria andPwdIsNotNull() {
            addCriterion("pwd is not null");
            return (Criteria) this;
        }

        public Criteria andPwdEqualTo(String value) {
            addCriterion("pwd =", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotEqualTo(String value) {
            addCriterion("pwd <>", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThan(String value) {
            addCriterion("pwd >", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThanOrEqualTo(String value) {
            addCriterion("pwd >=", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLessThan(String value) {
            addCriterion("pwd <", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLessThanOrEqualTo(String value) {
            addCriterion("pwd <=", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLike(String value) {
            addCriterion("pwd like", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotLike(String value) {
            addCriterion("pwd not like", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdIn(List<String> values) {
            addCriterion("pwd in", values, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotIn(List<String> values) {
            addCriterion("pwd not in", values, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdBetween(String value1, String value2) {
            addCriterion("pwd between", value1, value2, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotBetween(String value1, String value2) {
            addCriterion("pwd not between", value1, value2, "pwd");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andUlevelIsNull() {
            addCriterion("ulevel is null");
            return (Criteria) this;
        }

        public Criteria andUlevelIsNotNull() {
            addCriterion("ulevel is not null");
            return (Criteria) this;
        }

        public Criteria andUlevelEqualTo(Integer value) {
            addCriterion("ulevel =", value, "ulevel");
            return (Criteria) this;
        }

        public Criteria andUlevelNotEqualTo(Integer value) {
            addCriterion("ulevel <>", value, "ulevel");
            return (Criteria) this;
        }

        public Criteria andUlevelGreaterThan(Integer value) {
            addCriterion("ulevel >", value, "ulevel");
            return (Criteria) this;
        }

        public Criteria andUlevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("ulevel >=", value, "ulevel");
            return (Criteria) this;
        }

        public Criteria andUlevelLessThan(Integer value) {
            addCriterion("ulevel <", value, "ulevel");
            return (Criteria) this;
        }

        public Criteria andUlevelLessThanOrEqualTo(Integer value) {
            addCriterion("ulevel <=", value, "ulevel");
            return (Criteria) this;
        }

        public Criteria andUlevelIn(List<Integer> values) {
            addCriterion("ulevel in", values, "ulevel");
            return (Criteria) this;
        }

        public Criteria andUlevelNotIn(List<Integer> values) {
            addCriterion("ulevel not in", values, "ulevel");
            return (Criteria) this;
        }

        public Criteria andUlevelBetween(Integer value1, Integer value2) {
            addCriterion("ulevel between", value1, value2, "ulevel");
            return (Criteria) this;
        }

        public Criteria andUlevelNotBetween(Integer value1, Integer value2) {
            addCriterion("ulevel not between", value1, value2, "ulevel");
            return (Criteria) this;
        }

        public Criteria andRegtimeIsNull() {
            addCriterion("regtime is null");
            return (Criteria) this;
        }

        public Criteria andRegtimeIsNotNull() {
            addCriterion("regtime is not null");
            return (Criteria) this;
        }

        public Criteria andRegtimeEqualTo(Date value) {
            addCriterion("regtime =", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeNotEqualTo(Date value) {
            addCriterion("regtime <>", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeGreaterThan(Date value) {
            addCriterion("regtime >", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("regtime >=", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeLessThan(Date value) {
            addCriterion("regtime <", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeLessThanOrEqualTo(Date value) {
            addCriterion("regtime <=", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeIn(List<Date> values) {
            addCriterion("regtime in", values, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeNotIn(List<Date> values) {
            addCriterion("regtime not in", values, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeBetween(Date value1, Date value2) {
            addCriterion("regtime between", value1, value2, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeNotBetween(Date value1, Date value2) {
            addCriterion("regtime not between", value1, value2, "regtime");
            return (Criteria) this;
        }

        public Criteria andSignatureIsNull() {
            addCriterion("signature is null");
            return (Criteria) this;
        }

        public Criteria andSignatureIsNotNull() {
            addCriterion("signature is not null");
            return (Criteria) this;
        }

        public Criteria andSignatureEqualTo(String value) {
            addCriterion("signature =", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureNotEqualTo(String value) {
            addCriterion("signature <>", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureGreaterThan(String value) {
            addCriterion("signature >", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureGreaterThanOrEqualTo(String value) {
            addCriterion("signature >=", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureLessThan(String value) {
            addCriterion("signature <", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureLessThanOrEqualTo(String value) {
            addCriterion("signature <=", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureLike(String value) {
            addCriterion("signature like", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureNotLike(String value) {
            addCriterion("signature not like", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureIn(List<String> values) {
            addCriterion("signature in", values, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureNotIn(List<String> values) {
            addCriterion("signature not in", values, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureBetween(String value1, String value2) {
            addCriterion("signature between", value1, value2, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureNotBetween(String value1, String value2) {
            addCriterion("signature not between", value1, value2, "signature");
            return (Criteria) this;
        }

        public Criteria andUrichIsNull() {
            addCriterion("urich is null");
            return (Criteria) this;
        }

        public Criteria andUrichIsNotNull() {
            addCriterion("urich is not null");
            return (Criteria) this;
        }

        public Criteria andUrichEqualTo(String value) {
            addCriterion("urich =", value, "urich");
            return (Criteria) this;
        }

        public Criteria andUrichNotEqualTo(String value) {
            addCriterion("urich <>", value, "urich");
            return (Criteria) this;
        }

        public Criteria andUrichGreaterThan(String value) {
            addCriterion("urich >", value, "urich");
            return (Criteria) this;
        }

        public Criteria andUrichGreaterThanOrEqualTo(String value) {
            addCriterion("urich >=", value, "urich");
            return (Criteria) this;
        }

        public Criteria andUrichLessThan(String value) {
            addCriterion("urich <", value, "urich");
            return (Criteria) this;
        }

        public Criteria andUrichLessThanOrEqualTo(String value) {
            addCriterion("urich <=", value, "urich");
            return (Criteria) this;
        }

        public Criteria andUrichLike(String value) {
            addCriterion("urich like", value, "urich");
            return (Criteria) this;
        }

        public Criteria andUrichNotLike(String value) {
            addCriterion("urich not like", value, "urich");
            return (Criteria) this;
        }

        public Criteria andUrichIn(List<String> values) {
            addCriterion("urich in", values, "urich");
            return (Criteria) this;
        }

        public Criteria andUrichNotIn(List<String> values) {
            addCriterion("urich not in", values, "urich");
            return (Criteria) this;
        }

        public Criteria andUrichBetween(String value1, String value2) {
            addCriterion("urich between", value1, value2, "urich");
            return (Criteria) this;
        }

        public Criteria andUrichNotBetween(String value1, String value2) {
            addCriterion("urich not between", value1, value2, "urich");
            return (Criteria) this;
        }

        public Criteria andUheadIsNull() {
            addCriterion("uhead is null");
            return (Criteria) this;
        }

        public Criteria andUheadIsNotNull() {
            addCriterion("uhead is not null");
            return (Criteria) this;
        }

        public Criteria andUheadEqualTo(String value) {
            addCriterion("uhead =", value, "uhead");
            return (Criteria) this;
        }

        public Criteria andUheadNotEqualTo(String value) {
            addCriterion("uhead <>", value, "uhead");
            return (Criteria) this;
        }

        public Criteria andUheadGreaterThan(String value) {
            addCriterion("uhead >", value, "uhead");
            return (Criteria) this;
        }

        public Criteria andUheadGreaterThanOrEqualTo(String value) {
            addCriterion("uhead >=", value, "uhead");
            return (Criteria) this;
        }

        public Criteria andUheadLessThan(String value) {
            addCriterion("uhead <", value, "uhead");
            return (Criteria) this;
        }

        public Criteria andUheadLessThanOrEqualTo(String value) {
            addCriterion("uhead <=", value, "uhead");
            return (Criteria) this;
        }

        public Criteria andUheadLike(String value) {
            addCriterion("uhead like", value, "uhead");
            return (Criteria) this;
        }

        public Criteria andUheadNotLike(String value) {
            addCriterion("uhead not like", value, "uhead");
            return (Criteria) this;
        }

        public Criteria andUheadIn(List<String> values) {
            addCriterion("uhead in", values, "uhead");
            return (Criteria) this;
        }

        public Criteria andUheadNotIn(List<String> values) {
            addCriterion("uhead not in", values, "uhead");
            return (Criteria) this;
        }

        public Criteria andUheadBetween(String value1, String value2) {
            addCriterion("uhead between", value1, value2, "uhead");
            return (Criteria) this;
        }

        public Criteria andUheadNotBetween(String value1, String value2) {
            addCriterion("uhead not between", value1, value2, "uhead");
            return (Criteria) this;
        }

        public Criteria andUviplevelIsNull() {
            addCriterion("uviplevel is null");
            return (Criteria) this;
        }

        public Criteria andUviplevelIsNotNull() {
            addCriterion("uviplevel is not null");
            return (Criteria) this;
        }

        public Criteria andUviplevelEqualTo(String value) {
            addCriterion("uviplevel =", value, "uviplevel");
            return (Criteria) this;
        }

        public Criteria andUviplevelNotEqualTo(String value) {
            addCriterion("uviplevel <>", value, "uviplevel");
            return (Criteria) this;
        }

        public Criteria andUviplevelGreaterThan(String value) {
            addCriterion("uviplevel >", value, "uviplevel");
            return (Criteria) this;
        }

        public Criteria andUviplevelGreaterThanOrEqualTo(String value) {
            addCriterion("uviplevel >=", value, "uviplevel");
            return (Criteria) this;
        }

        public Criteria andUviplevelLessThan(String value) {
            addCriterion("uviplevel <", value, "uviplevel");
            return (Criteria) this;
        }

        public Criteria andUviplevelLessThanOrEqualTo(String value) {
            addCriterion("uviplevel <=", value, "uviplevel");
            return (Criteria) this;
        }

        public Criteria andUviplevelLike(String value) {
            addCriterion("uviplevel like", value, "uviplevel");
            return (Criteria) this;
        }

        public Criteria andUviplevelNotLike(String value) {
            addCriterion("uviplevel not like", value, "uviplevel");
            return (Criteria) this;
        }

        public Criteria andUviplevelIn(List<String> values) {
            addCriterion("uviplevel in", values, "uviplevel");
            return (Criteria) this;
        }

        public Criteria andUviplevelNotIn(List<String> values) {
            addCriterion("uviplevel not in", values, "uviplevel");
            return (Criteria) this;
        }

        public Criteria andUviplevelBetween(String value1, String value2) {
            addCriterion("uviplevel between", value1, value2, "uviplevel");
            return (Criteria) this;
        }

        public Criteria andUviplevelNotBetween(String value1, String value2) {
            addCriterion("uviplevel not between", value1, value2, "uviplevel");
            return (Criteria) this;
        }

        public Criteria andCertificainfoIsNull() {
            addCriterion("certificaInfo is null");
            return (Criteria) this;
        }

        public Criteria andCertificainfoIsNotNull() {
            addCriterion("certificaInfo is not null");
            return (Criteria) this;
        }

        public Criteria andCertificainfoEqualTo(String value) {
            addCriterion("certificaInfo =", value, "certificainfo");
            return (Criteria) this;
        }

        public Criteria andCertificainfoNotEqualTo(String value) {
            addCriterion("certificaInfo <>", value, "certificainfo");
            return (Criteria) this;
        }

        public Criteria andCertificainfoGreaterThan(String value) {
            addCriterion("certificaInfo >", value, "certificainfo");
            return (Criteria) this;
        }

        public Criteria andCertificainfoGreaterThanOrEqualTo(String value) {
            addCriterion("certificaInfo >=", value, "certificainfo");
            return (Criteria) this;
        }

        public Criteria andCertificainfoLessThan(String value) {
            addCriterion("certificaInfo <", value, "certificainfo");
            return (Criteria) this;
        }

        public Criteria andCertificainfoLessThanOrEqualTo(String value) {
            addCriterion("certificaInfo <=", value, "certificainfo");
            return (Criteria) this;
        }

        public Criteria andCertificainfoLike(String value) {
            addCriterion("certificaInfo like", value, "certificainfo");
            return (Criteria) this;
        }

        public Criteria andCertificainfoNotLike(String value) {
            addCriterion("certificaInfo not like", value, "certificainfo");
            return (Criteria) this;
        }

        public Criteria andCertificainfoIn(List<String> values) {
            addCriterion("certificaInfo in", values, "certificainfo");
            return (Criteria) this;
        }

        public Criteria andCertificainfoNotIn(List<String> values) {
            addCriterion("certificaInfo not in", values, "certificainfo");
            return (Criteria) this;
        }

        public Criteria andCertificainfoBetween(String value1, String value2) {
            addCriterion("certificaInfo between", value1, value2, "certificainfo");
            return (Criteria) this;
        }

        public Criteria andCertificainfoNotBetween(String value1, String value2) {
            addCriterion("certificaInfo not between", value1, value2, "certificainfo");
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