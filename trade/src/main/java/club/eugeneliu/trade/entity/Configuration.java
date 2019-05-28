package club.eugeneliu.trade.entity;

/**
 * <p>
 * 
 * </p>
 *
 * @author EugeneLiu
 * @since 2019-05-27
 */
public class Configuration{

    private String parameter_name;

    private String parameter_value;

    public String getParameter_name() {
        return parameter_name;
    }

    public void setParameter_name(String parameter_name) {
        this.parameter_name = parameter_name;
    }
    public String getParameter_value() {
        return parameter_value;
    }

    public void setParameter_value(String parameter_value) {
        this.parameter_value = parameter_value;
    }

    @Override
    public String toString() {
        return "Configuration{" +
        "parameter_name=" + parameter_name +
        ", parameter_value=" + parameter_value +
        "}";
    }
}
