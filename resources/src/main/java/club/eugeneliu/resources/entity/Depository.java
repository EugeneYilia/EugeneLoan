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
public class Depository extends Model<Depository> {

    private static final long serialVersionUID = 1L;

    private String funds_account;

    private String depository_account;

    public String getFunds_account() {
        return funds_account;
    }

    public void setFunds_account(String funds_account) {
        this.funds_account = funds_account;
    }
    public String getDepository_account() {
        return depository_account;
    }

    public void setDepository_account(String depository_account) {
        this.depository_account = depository_account;
    }

    @Override
    protected Serializable pkVal() {
        return this.funds_account;
    }

    @Override
    public String toString() {
        return "Depository{" +
        "funds_account=" + funds_account +
        ", depository_account=" + depository_account +
        "}";
    }
}
