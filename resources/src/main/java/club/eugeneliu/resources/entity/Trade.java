package club.eugeneliu.resources.entity;

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
public class Trade extends Model<Trade> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "serial_number", type = IdType.AUTO)
    private Integer serial_number;

    private Integer bill_id;

    private String in_bound_account;

    private String out_bound_account;

    private Double money;

    private Integer limit_months;

    private Integer pay_type;

    private Float pay_rate;

    private Date exact_date;

    private Double next_time_pay;

    /**
     * 已还本金
     */
    private Double repaid_principal;

    /**
     * 已还利息
     */
    private Double repaid_interest;

    /**
     * 已还违约金
     */
    private Double liquidated_money;

    /**
     * 每次已还本金
     */
    private Double should_repay_principal;

    /**
     * 每次应还利息
     */
    private Double should_repay_interest;

    /**
     * 总共应还违约金
     */
    private Double should_repay_liquidated_money;

    /**
     * 最终贷款还清日期
     */
    private Date finished_date;

    public Integer getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(Integer serial_number) {
        this.serial_number = serial_number;
    }
    public Integer getBill_id() {
        return bill_id;
    }

    public void setBill_id(Integer bill_id) {
        this.bill_id = bill_id;
    }
    public String getIn_bound_account() {
        return in_bound_account;
    }

    public void setIn_bound_account(String in_bound_account) {
        this.in_bound_account = in_bound_account;
    }
    public String getOut_bound_account() {
        return out_bound_account;
    }

    public void setOut_bound_account(String out_bound_account) {
        this.out_bound_account = out_bound_account;
    }
    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
    public Integer getLimit_months() {
        return limit_months;
    }

    public void setLimit_months(Integer limit_months) {
        this.limit_months = limit_months;
    }
    public Integer getPay_type() {
        return pay_type;
    }

    public void setPay_type(Integer pay_type) {
        this.pay_type = pay_type;
    }
    public Float getPay_rate() {
        return pay_rate;
    }

    public void setPay_rate(Float pay_rate) {
        this.pay_rate = pay_rate;
    }
    public Date getExact_date() {
        return exact_date;
    }

    public void setExact_date(Date exact_date) {
        this.exact_date = exact_date;
    }
    public Double getNext_time_pay() {
        return next_time_pay;
    }

    public void setNext_time_pay(Double next_time_pay) {
        this.next_time_pay = next_time_pay;
    }
    public Double getRepaid_principal() {
        return repaid_principal;
    }

    public void setRepaid_principal(Double repaid_principal) {
        this.repaid_principal = repaid_principal;
    }
    public Double getRepaid_interest() {
        return repaid_interest;
    }

    public void setRepaid_interest(Double repaid_interest) {
        this.repaid_interest = repaid_interest;
    }
    public Double getLiquidated_money() {
        return liquidated_money;
    }

    public void setLiquidated_money(Double liquidated_money) {
        this.liquidated_money = liquidated_money;
    }
    public Double getShould_repay_principal() {
        return should_repay_principal;
    }

    public void setShould_repay_principal(Double should_repay_principal) {
        this.should_repay_principal = should_repay_principal;
    }
    public Double getShould_repay_interest() {
        return should_repay_interest;
    }

    public void setShould_repay_interest(Double should_repay_interest) {
        this.should_repay_interest = should_repay_interest;
    }
    public Double getShould_repay_liquidated_money() {
        return should_repay_liquidated_money;
    }

    public void setShould_repay_liquidated_money(Double should_repay_liquidated_money) {
        this.should_repay_liquidated_money = should_repay_liquidated_money;
    }
    public Date getFinished_date() {
        return finished_date;
    }

    public void setFinished_date(Date finished_date) {
        this.finished_date = finished_date;
    }

    @Override
    protected Serializable pkVal() {
        return this.serial_number;
    }

    @Override
    public String toString() {
        return "Trade{" +
        "serial_number=" + serial_number +
        ", bill_id=" + bill_id +
        ", in_bound_account=" + in_bound_account +
        ", out_bound_account=" + out_bound_account +
        ", money=" + money +
        ", limit_months=" + limit_months +
        ", pay_type=" + pay_type +
        ", pay_rate=" + pay_rate +
        ", exact_date=" + exact_date +
        ", next_time_pay=" + next_time_pay +
        ", repaid_principal=" + repaid_principal +
        ", repaid_interest=" + repaid_interest +
        ", liquidated_money=" + liquidated_money +
        ", should_repay_principal=" + should_repay_principal +
        ", should_repay_interest=" + should_repay_interest +
        ", should_repay_liquidated_money=" + should_repay_liquidated_money +
        ", finished_date=" + finished_date +
        "}";
    }
}
