package io.xlate.edi.internal.stream.validation;

import io.xlate.edi.internal.stream.tokenization.ValidationEventHandler;
import io.xlate.edi.schema.EDIReference;
import io.xlate.edi.stream.EDIStreamValidationError;

public class UsageError {
    final EDIReference typeReference;
    final EDIStreamValidationError error;
    final int depth;
    final String expectedValue;

    UsageError(EDIReference typeReference, EDIStreamValidationError error, int depth) {
        super();
        this.typeReference = typeReference;
        this.error = error;
        this.depth = depth;
        this.expectedValue = null;
    }

    UsageError(EDIStreamValidationError error) {
        super();
        this.error = error;
        this.typeReference = null;
        this.depth = -1;
        this.expectedValue = null;
    }

    UsageError(UsageNode node, EDIStreamValidationError error) {
        this(node, error, null);
    }

    UsageError(UsageNode node, EDIStreamValidationError error, String expectedValue) {
        super();
        this.typeReference = node.getLink();
        this.depth = node.getDepth();
        this.error = error;
        this.expectedValue = expectedValue;
    }

    boolean isDepthGreaterThan(int depth) {
        return this.depth > depth;
    }

    void handleSegmentError(ValidationEventHandler handler) {
        handler.segmentError(typeReference.getReferencedType().getId(), typeReference, error);
    }

    public EDIReference getTypeReference() {
        return typeReference;
    }

    public EDIStreamValidationError getError() {
        return error;
    }

    public String getExpectedValue() {
        return expectedValue;
    }
}
