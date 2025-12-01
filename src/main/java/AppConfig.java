import java.math.BigDecimal;

public class AppConfig {
    public final String inputFile;
    public final String mode;
    public final String dbUrl;
    public final String dbUser;
    public final String dbPassword;
    public final boolean onlyActive;
    public final BigDecimal minPrice;

    public AppConfig(String inputFile, String mode,
                     String dbUrl, String dbUser, String dbPassword,
                     boolean onlyActive, BigDecimal minPrice) {
        this.inputFile = inputFile;
        this.mode = mode;
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        this.onlyActive = onlyActive;
        this.minPrice = minPrice;
    }
}
