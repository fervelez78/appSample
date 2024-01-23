package mx.bancosabadell.condusef.services;

import javax.validation.ConstraintViolation;

import mx.bancosabadell.condusef.models.Queja;

import java.util.Set;

public class QuejaValidationException extends RuntimeException {
    private final Set<ConstraintViolation<Queja>> violations;

    public QuejaValidationException(Set<ConstraintViolation<Queja>> violations) {
        this.violations = violations;
    }

    public Set<ConstraintViolation<Queja>> getViolations() {
        return violations;
    }
}