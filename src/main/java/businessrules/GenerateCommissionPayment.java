package businessrules;

public class GenerateCommissionPayment implements Rule {
    private final String reason;

    public GenerateCommissionPayment(String reason) {
        this.reason = reason;
    }

    @Override
    public String apply() {
        return "generate a commission payment to the agent for" + reason;
    }
}
