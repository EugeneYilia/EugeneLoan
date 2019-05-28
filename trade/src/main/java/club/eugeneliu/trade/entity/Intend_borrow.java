package club.eugeneliu.trade.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author EugeneLiu
 * @since 2019-05-27
 */
public class Intend_borrow extends Model<Intend_borrow> {

    private static final long serialVersionUID = 1L;

    /**
     * 账目id
     */
    @TableId(value = "bill_id", type = IdType.AUTO)
    private Integer bill_id;

    private String id_card;

    private Double intend_money;

    private Date start_date;

    private Float pay_rate;

    /**
     * 还款方式按月付 季付,算利息的方式，不影响limit_months的填写
     */
    private Integer pay_type;

    /**
     * 一个月 三个月 半年
     */
    private Integer limit_months;

    /**
     * -1 represent fail,0 represent start,1 represent successful
     */
    private Integer state;

    private Double raised_money;

    public Integer getBill_id() {
        return bill_id;
    }

    public void setBill_id(Integer bill_id) {
        this.bill_id = bill_id;
    }
    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }
    public Double getIntend_money() {
        return intend_money;
    }

    public void setIntend_money(Double intend_money) {
        this.intend_money = intend_money;
    }
    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }
    public Float getPay_rate() {
        return pay_rate;
    }

    public void setPay_rate(Float pay_rate) {
        this.pay_rate = pay_rate;
    }
    public Integer getPay_type() {
        return pay_type;
    }

    public void setPay_type(Integer pay_type) {
        this.pay_type = pay_type;
    }
    public Integer getLimit_months() {
        return limit_months;
    }

    public void setLimit_months(Integer limit_months) {
        this.limit_months = limit_months;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    public Double getRaised_money() {
        return raised_money;
    }

    public void setRaised_money(Double raised_money) {
        this.raised_money = raised_money;
    }

    @Override
    protected Serializable pkVal() {
        return this.bill_id;
    }

    @Override
    public String toString() {
        return "Intend_borrow{" +
        "bill_id=" + bill_id +
        ", id_card=" + id_card +
        ", intend_money=" + intend_money +
        ", start_date=" + start_date +
        ", pay_rate=" + pay_rate +
        ", pay_type=" + pay_type +
        ", limit_months=" + limit_months +
        ", state=" + state +
        ", raised_money=" + raised_money +
        "}";
    }
}
