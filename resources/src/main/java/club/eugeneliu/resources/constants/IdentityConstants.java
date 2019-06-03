package club.eugeneliu.resources.constants;

public enum IdentityConstants {
    BORROWER("0"),LENDER("1");
    private String identity;
    IdentityConstants(String identity){
        this.identity = identity;
    }

    public String getIdentity() {
        return identity;
    }

    @Override
    public String toString() {
        return identity;
    }
}
