package club.eugeneliu.information.entity;

import java.sql.Blob;
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
public class User_optional_info extends Model<User_optional_info> {

    private static final long serialVersionUID = 1L;

    private String id_card;

    private String sex;

    private String educational_level;

    private String marriage;

    private String profession;

    private String address;

    private Object avatar;

    private String special_identity;

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getEducational_level() {
        return educational_level;
    }

    public void setEducational_level(String educational_level) {
        this.educational_level = educational_level;
    }
    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }
    public String getSpecial_identity() {
        return special_identity;
    }

    public void setSpecial_identity(String special_identity) {
        this.special_identity = special_identity;
    }

    @Override
    protected Serializable pkVal() {
        return this.id_card;
    }

    @Override
    public String toString() {
        return "User_optional_info{" +
        "id_card=" + id_card +
        ", sex=" + sex +
        ", educational_level=" + educational_level +
        ", marriage=" + marriage +
        ", profession=" + profession +
        ", address=" + address +
        ", avatar=" + avatar +
        ", special_identity=" + special_identity +
        "}";
    }
}
