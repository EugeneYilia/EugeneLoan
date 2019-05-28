package club.eugeneliu.trade.entity;

import java.util.Date;
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
public class Intend_lend extends Model<Intend_lend> {

    private static final long serialVersionUID = 1L;

    /**
     * 和意向借入表中的账目id是一致的
     */
    private Integer bill_id;

    private String id_card;

    private Double lend_money;

    private Date start_date;

    private Integer state;

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
    public Double getLend_money() {
        return lend_money;
    }

    public void setLend_money(Double lend_money) {
        this.lend_money = lend_money;
    }
    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    protected Serializable pkVal() {
        return this.bill_id;
    }

    @Override
    public String toString() {
        return "Intend_lend{" +
        "bill_id=" + bill_id +
        ", id_card=" + id_card +
        ", lend_money=" + lend_money +
        ", start_date=" + start_date +
        ", state=" + state +
        "}";
    }
}
