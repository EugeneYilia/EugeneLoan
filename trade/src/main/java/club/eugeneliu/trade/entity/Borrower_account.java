package club.eugeneliu.trade.entity;

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
public class Borrower_account extends Model<Borrower_account> {

    private static final long serialVersionUID = 1L;

    private String funds_account;

    private String id_card;

    private Double account_balance;

    private Double borrowed_money;

    private Integer credit_score;

    /**
     * 总额度
     */
    private Double total_limit;

    /**
     * 可用额度
     */
    private Double available_limit;

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
    public Double getBorrowed_money() {
        return borrowed_money;
    }

    public void setBorrowed_money(Double borrowed_money) {
        this.borrowed_money = borrowed_money;
    }
    public Integer getCredit_score() {
        return credit_score;
    }

    public void setCredit_score(Integer credit_score) {
        this.credit_score = credit_score;
    }
    public Double getTotal_limit() {
        return total_limit;
    }

    public void setTotal_limit(Double total_limit) {
        this.total_limit = total_limit;
    }
    public Double getAvailable_limit() {
        return available_limit;
    }

    public void setAvailable_limit(Double available_limit) {
        this.available_limit = available_limit;
    }

    @Override
    protected Serializable pkVal() {
        return this.funds_account;
    }

    @Override
    public String toString() {
        return "Borrower_account{" +
        "funds_account=" + funds_account +
        ", id_card=" + id_card +
        ", account_balance=" + account_balance +
        ", borrowed_money=" + borrowed_money +
        ", credit_score=" + credit_score +
        ", total_limit=" + total_limit +
        ", available_limit=" + available_limit +
        "}";
    }
}
