package businessrules;

public class EmailNotification implements Rule {
    private final String reason;

    public EmailNotification(String reason) {
        this.reason = reason;
    }

    @Override
    public String apply() {
        return String.format("e-mail the owner and inform them of the %s", this.reason);
    }
}
