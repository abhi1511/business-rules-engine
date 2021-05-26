package businessrules;

public class GenerateSlip implements Rule {

    @Override
    public String apply() {
        return "generate a packing slip for shipping";
    }
}
