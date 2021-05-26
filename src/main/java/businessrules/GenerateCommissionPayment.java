package businessrules;

public class GenerateCommissionPayment implements Rule {
    @Override
    public String apply() {
        return "generate a commission payment to the agent";
    }
}
