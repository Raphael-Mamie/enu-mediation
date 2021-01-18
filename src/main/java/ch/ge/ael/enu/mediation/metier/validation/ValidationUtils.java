package ch.ge.ael.enu.mediation.metier.validation;

import ch.ge.ael.enu.mediation.metier.exception.IllegalEnumValueException;
import ch.ge.ael.enu.mediation.metier.exception.IllegalStringSizeException;
import ch.ge.ael.enu.mediation.metier.exception.MalformedDateException;
import ch.ge.ael.enu.mediation.metier.exception.MissingFieldException;
import ch.ge.ael.enu.mediation.metier.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ValidationUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationUtils.class);

    private ValidationUtils() {
    }

    public static void checkExistence(Object value, String fieldName) {
        if (value instanceof String) {
            value = StringUtils.isBlank((String) value) ? null : value;
        }
        if (value == null) {
            LOGGER.info("Erreur metier : le champ [{}] manque dans le message", fieldName);
            throw new MissingFieldException(fieldName);
        }
    }

    public static <T> void checkEnum(String value, Class<T> clazz, String fieldName) {
        if (value != null) {
            if (!clazz.isEnum()) {
                throw new IllegalArgumentException("Cette methode doit etre appelee avec un parametre de type enum, mais "
                        + clazz + " n'est pas un type enum");
            }
            List<T> enumValues = Arrays.asList(clazz.getEnumConstants());
            boolean found = enumValues.stream()
                    .map(Object::toString)
                    .anyMatch(e -> e.equals(value));
            if (!found) {
                LOGGER.info("Erreur metier : la valeur [{}] du champ [{}] n'existe pas dans l'enum {}",
                        value, fieldName, clazz.getSimpleName());
                throw new IllegalEnumValueException(value, enumValues, fieldName);
            }
        }
    }

    public static void checkSize(String value, int minSize, int maxSize, String fieldName) {
        if (value != null) {
            value = value.trim();
            int size = value.length();
            if (size < minSize || size > maxSize) {
                LOGGER.info("Erreur metier : taille incorrecte de la valeur [{}] du champ [{}]. Intervalle permis : [{}, {}]",
                        value, fieldName, minSize, maxSize);
                throw new IllegalStringSizeException(value, minSize, maxSize, fieldName);
            }
        }
    }

    public static void checkDate(String value, String fieldName) {
        if (value != null) {
            try {
                LocalDate.parse(value);
            } catch (RuntimeException e) {
                LOGGER.info("Erreur metier : valeur incorrecte [{}] du champ date [{}]", value, fieldName);
                throw new MalformedDateException(value, fieldName);
            }
        }
    }

    public static void checkUrl(String value, String fieldName) {
        final int URL_MIN_LENGTH = 10;
        final int URL_MAX_LENGTH = 200;
        checkSize(value, URL_MIN_LENGTH, URL_MAX_LENGTH, fieldName);
    }

    /**
     * Leve une erreur si value est non null alors que otherValue est null.
     */
    public static void checkAbsentIfOtherAbsent(String value, String fieldName,
                                                String otherValue, String otherFieldName) {
        if (value != null && otherValue == null) {
            LOGGER.info("Erreur metier : le champ [{}] valant [{}] doit etre null quand le champ [{}] est null",
                    fieldName, value, otherFieldName);
            throw new ValidationException("Le champ \"" + fieldName + "\" ne peut pas être fourni quand le champ \""
                    + otherFieldName + "\" n'est pas fourni.");
        }
    }

    /**
     * Leve une erreur si value est non null alors que otherValue vaut someOtherValue.
     */
    public static void checkAbsentIfOtherHasValue(String value, String fieldName,
                                                  String otherValue, String otherFieldName, String someOtherValue) {
        if (value != null && Objects.equals(otherValue, someOtherValue)) {
            LOGGER.info("Erreur metier : le champ [{}] doit etre nul quand le champ [{}] vaut [{}]",
                    fieldName, otherFieldName, someOtherValue);
            throw new ValidationException("Le champ \"" + fieldName + "\" ne peut pas être fourni quand le champ \""
                    + otherFieldName + "\" vaut \"" + someOtherValue + "\".");
        }
    }

    /**
     * Leve une erreur si value est null alors que otherValue est non null.
     */
    public static void checkPresentIfOtherPresent(String value, String fieldName,
                                                  String otherValue, String otherFieldName) {
        if (value == null && otherValue != null) {
            LOGGER.info("Erreur metier : le champ [{}] doit etre non nul quand le champ [{}] valant [{}] est non nul",
                    fieldName, otherFieldName, otherValue);
            throw new ValidationException("Le champ \"" + fieldName + "\" doit être fourni quand le champ \""
                    + otherFieldName + "\" est fourni.");
        }
    }

    /**
     * Leve une erreur si value est null alors que otherValue vaut someOtherValue.
     */
    public static void checkPresentIfOtherHasValue(String value, String fieldName,
                                                   String otherValue, String otherFieldName, String someOtherValue) {
        if (value == null && Objects.equals(otherValue, someOtherValue)) {
            LOGGER.info("Erreur metier : le champ [{}] doit etre non nul quand le champ [{}] vaut [{}]",
                    fieldName, otherFieldName, someOtherValue);
            throw new ValidationException("Le champ \"" + fieldName + "\" doit etre fourni quand le champ \""
                    + otherFieldName + "\" vaut \"" + someOtherValue + "\".");
        }
    }

    public static void checkSizeIdPrestation(String value) {
        checkSize(value, 1, 50, "idPrestation");
    }

    public static void checkSizeIdUsager(String value) {
        checkSize(value, 1, 50, "idUsager");
    }

    public static void checkSizeIdDemarcheSiMetier(String value) {
        checkSize(value, 1, 50, "idDemarcheSiMetier");
    }

}
