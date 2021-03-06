package com.intuit.karate.validator;

import com.intuit.karate.ScriptValue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author pthomas3
 */
public class RegexValidator implements Validator {
    
    private final Pattern pattern;
    
    public RegexValidator(String regex) {
        regex = StringUtils.trim(regex);
        pattern = Pattern.compile(regex);
    }

    @Override
    public ValidationResult validate(ScriptValue value) {
        if (!value.isString()) {
            return ValidationResult.fail("not a string");
        }
        String strValue = value.getValue(String.class);
        Matcher matcher = pattern.matcher(strValue);
        if (matcher.matches()) {
            return ValidationResult.PASS;
        }
        return ValidationResult.fail("regex match failed");
    }
    
}
