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
public class Repay_money_flow extends Model<Repay_money_flow> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "serial_number", type = IdType.AUTO)
    private Integer serial_number;

    private Integer bill_id;

    /**
     * 钱转入账户
     */
    private String in_bound_account;

    /**
     * 钱转出账户
     */
    private String out_bound_account;

    private Date exact_date;

    private Double money;

    private Double interest_money;

    private Double liquidated_money;

    private Double principal_money;

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
    public Date getExact_date() {
        return exact_date;
    }

    public void setExact_date(Date exact_date) {
        this.exact_date = exact_date;
    }
    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
    public Double getInterest_money() {
        return interest_money;
    }

    public void setInterest_money(Double interest_money) {
        this.interest_money = interest_money;
    }
    public Double getLiquidated_money() {
        return liquidated_money;
    }

    public void setLiquidated_money(Double liquidated_money) {
        this.liquidated_money = liquidated_money;
    }
    public Double getPrincipal_money() {
        return principal_money;
    }

    public void setPrincipal_money(Double principal_money) {
        this.principal_money = principal_money;
    }

    @Override
    protected Serializable pkVal() {
        return this.serial_number;
    }

    @Override
    public String toString() {
        return "Repay_money_flow{" +
        "serial_number=" + serial_number +
        ", bill_id=" + bill_id +
        ", in_bound_account=" + in_bound_account +
        ", out_bound_account=" + out_bound_account +
        ", exact_date=" + exact_date +
        ", money=" + money +
        ", interest_money=" + interest_money +
        ", liquidated_money=" + liquidated_money +
        ", principal_money=" + principal_money +
        "}";
    }
}
