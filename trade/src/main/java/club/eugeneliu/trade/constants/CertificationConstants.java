package club.eugeneliu.trade.constants;

public enum  CertificationConstants {
    BORROWER("0"),LENDER("1");
    private String identity;

    CertificationConstants(String identity){
        this.identity = identity;
    }

    public String getIdentity() {
        return identity;
    }
}
