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
public class Borrow_money_flow extends Model<Borrow_money_flow> {

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

    private Double money;

    private Date exact_date;

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
    public Date getExact_date() {
        return exact_date;
    }

    public void setExact_date(Date exact_date) {
        this.exact_date = exact_date;
    }

    @Override
    protected Serializable pkVal() {
        return this.serial_number;
    }

    @Override
    public String toString() {
        return "Borrow_money_flow{" +
        "serial_number=" + serial_number +
        ", bill_id=" + bill_id +
        ", in_bound_account=" + in_bound_account +
        ", out_bound_account=" + out_bound_account +
        ", money=" + money +
        ", exact_date=" + exact_date +
        "}";
    }
}
