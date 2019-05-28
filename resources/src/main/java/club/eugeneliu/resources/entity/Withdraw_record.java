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
public class Withdraw_record extends Model<Withdraw_record> {

    private static final long serialVersionUID = 1L;

    /**
     * 提现资金流水id
     */
    @TableId(value = "serial_number", type = IdType.AUTO)
    private Integer serial_number;

    private Date withdraw_date;

    private Double withdraw_money;

    private String bank_account;

    public Integer getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(Integer serial_number) {
        this.serial_number = serial_number;
    }
    public Date getWithdraw_date() {
        return withdraw_date;
    }

    public void setWithdraw_date(Date withdraw_date) {
        this.withdraw_date = withdraw_date;
    }
    public Double getWithdraw_money() {
        return withdraw_money;
    }

    public void setWithdraw_money(Double withdraw_money) {
        this.withdraw_money = withdraw_money;
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
        return "Withdraw_record{" +
        "serial_number=" + serial_number +
        ", withdraw_date=" + withdraw_date +
        ", withdraw_money=" + withdraw_money +
        ", bank_account=" + bank_account +
        "}";
    }
}
