public class CommandLineConfig {

    private String inputFile;
    private String mode;
    //filter ja nach active und price
    private boolean onlyActive = false;
    private double minPrice = 0;

    // Getter
    public boolean isOnlyActive() {
        return onlyActive;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public CommandLineConfig(String[] args) {
        parse(args);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            if (arg.startsWith("--input=")) {
                inputFile = arg.substring("--input=".length());
            } else if (arg.startsWith("--mode=")) {
                mode = arg.substring("--mode=".length());
            }
        }
    }

    public String getInputFile() {
        return inputFile;
    }

    public String getMode() {
        return mode;
    }

    public class ListFilterConfig {
        public boolean onlyActive = false;
        public double minPrice = 0;

        public ListFilterConfig(String[] args) {
            for (String arg : args) {
                if (arg.startsWith("--only-active=")) {
                    onlyActive = Boolean.parseBoolean(arg.substring("--only-active=".length()));
                } else if (arg.startsWith("--min-price=")) {
                    minPrice = Double.parseDouble(arg.substring("--min-price=".length()));
                }
            }
        }
    }

    public void parseArgs(String[] args) {
        for (String arg : args) {
            if (arg.startsWith("--only-active=")) {
                onlyActive = Boolean.parseBoolean(arg.substring("--only-active=".length()));
            } else if (arg.startsWith("--min-price=")) {
                minPrice = Double.parseDouble(arg.substring("--min-price=".length()));
            }
        }
    }
}
