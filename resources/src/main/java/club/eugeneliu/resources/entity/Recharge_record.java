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
public class Recharge_record extends Model<Recharge_record> {

    private static final long serialVersionUID = 1L;

    /**
     * 充值资金流水id
     */
    @TableId(value = "serial_number", type = IdType.AUTO)
    private Integer serial_number;

    private Date recharge_date;

    private Double recharge_money;

    private String bank_account;

    public Integer getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(Integer serial_number) {
        this.serial_number = serial_number;
    }
    public Date getRecharge_date() {
        return recharge_date;
    }

    public void setRecharge_date(Date recharge_date) {
        this.recharge_date = recharge_date;
    }
    public Double getRecharge_money() {
        return recharge_money;
    }

    public void setRecharge_money(Double recharge_money) {
        this.recharge_money = recharge_money;
    }
    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }

    @Override
    protected Serializable pkVal() {
        return this.serial_number;
    }

    @Override
    public String toString() {
        return "Recharge_record{" +
        "serial_number=" + serial_number +
        ", recharge_date=" + recharge_date +
        ", recharge_money=" + recharge_money +
        ", bank_account=" + bank_account +
        "}";
    }
}
