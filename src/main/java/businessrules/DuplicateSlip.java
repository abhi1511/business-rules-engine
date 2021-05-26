package businessrules;

public class DuplicateSlip implements Rule {
    @Override
    public String apply() {
        return "create a duplicate packing slip for the royalty department";
    }
}
