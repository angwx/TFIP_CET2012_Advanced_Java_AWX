package receiver;

import java.util.regex.Pattern; // for email validation

/**
 * Email validator based on company restrictions
 * Local part: A-Z, a-z, 0-9, ._- (not first/last, not consecutive except _)
 * Domain: format yyyy.xxx where xxx is 2-3 lowercase letters
 */
public class EmailValidator {

    // Regex for local part: starts/ends with alphanumeric, middle can have ._- but not consecutive
    private static final String LOCAL_PART = "[a-zA-Z0-9]([a-zA-Z0-9._-]*[a-zA-Z0-9])?";

    // Regex for domain: alphanumeric with optional .- (not first/last), then .xxx (2-3 lowercase)
    private static final String DOMAIN_PART = "[a-zA-Z0-9]([a-zA-Z0-9.-]*[a-zA-Z0-9])?\\.[a-z]{2,3}";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^" + LOCAL_PART + "@" + DOMAIN_PART + "$");

    /**
     * Validates email according to company restrictions
     * @param email the email to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValid(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }
}