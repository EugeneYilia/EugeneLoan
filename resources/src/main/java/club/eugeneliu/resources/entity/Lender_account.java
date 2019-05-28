package club.eugeneliu.resources.entity;

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
public class Lender_account extends Model<Lender_account> {

    private static final long serialVersionUID = 1L;

    private String funds_account;

    private String id_card;

    private Double account_balance;

    private Double lent_money;

    private Double current_income;

    private Double expected_income;

    public String getFunds_account() {
        return funds_account;
    }

    public void setFunds_account(String funds_account) {
        this.funds_account = funds_account;
    }
    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }
    public Double getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(Double account_balance) {
        this.account_balance = account_balance;
    }
    public Double getLent_money() {
        return lent_money;
    }

    public void setLent_money(Double lent_money) {
        this.lent_money = lent_money;
    }
    public Double getCurrent_income() {
        return current_income;
    }

    public void setCurrent_income(Double current_income) {
        this.current_income = current_income;
    }
    public Double getExpected_income() {
        return expected_income;
    }

    public void setExpected_income(Double expected_income) {
        this.expected_income = expected_income;
    }

    @Override
    protected Serializable pkVal() {
        return this.funds_account;
    }

    @Override
    public String toString() {
        return "Lender_account{" +
        "funds_account=" + funds_account +
        ", id_card=" + id_card +
        ", account_balance=" + account_balance +
        ", lent_money=" + lent_money +
        ", current_income=" + current_income +
        ", expected_income=" + expected_income +
        "}";
    }
}
