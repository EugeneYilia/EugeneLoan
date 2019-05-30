package club.eugeneliu.information.entity;

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
public class User_required_info extends Model<User_required_info> {

    private static final long serialVersionUID = 1L;

    private String phone_number;

    private String id_card;

    private String user_name;

    private String bank_account;

    private Integer user_type;

    private String password;

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }
    public Integer getUser_type() {
        return user_type;
    }

    public void setUser_type(Integer user_type) {
        this.user_type = user_type;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected Serializable pkVal() {
        return this.id_card;
    }

    @Override
    public String toString() {
        return "User_required_info{" +
        "phone_number=" + phone_number +
        ", id_card=" + id_card +
        ", user_name=" + user_name +
        ", bank_account=" + bank_account +
        ", user_type=" + user_type +
        ", password=" + password +
        "}";
    }
}
