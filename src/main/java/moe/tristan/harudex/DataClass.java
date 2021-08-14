package moe.tristan.harudex;

import static org.immutables.value.Value.Style.ImplementationVisibility.PUBLIC;

import org.immutables.value.Value.Style;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Style(
    /*
     * API SPECIFICATION
     * - Accessors are methods with name prefixed with "get" or "is" (for simple booleans)
     * - Naming strategy is `AbstractXyz` -> `Xyz`
     * - Generated class is always public while allowing (and expecting) the definition class to stay package-private
     */
    get = {"is*", "get*"},
    typeAbstract = "*Definition",
    typeImmutable = "*",
    visibility = PUBLIC,

    /*
     * IMPLEMENTATION DETAILS
     * - Optional fields allow `null` value being set, and translates this to an empty Optional when null values are passed to the builder methods
     * - A private no-arg constructor is always generated, mostly to accomodate Hibernate and other such frameworks
     * - Builder classes generated are `strict`, i.e. they only allow setting a property once (multiple sets of the same field often is involuntary)
     * - Guava collections are to never be used by the generated classes for compatibility reasons with Spring & friends
     */
    optionalAcceptNullable = true,
    privateNoargConstructor = true,
    strictBuilder = true,
    jdkOnly = true,

    /*
     * SERIALIZATION PROPERTIES
     * - Common Jackson annotations are passed down to the generated class (as they may be required on the underlying final implementation class)
     * - Immutables is to *NOT* generate Jackson property names, and instead let Jackson infer those itself, while we can still override the automatic ones
     * when needed
     */
    passAnnotations = {
        JsonTypeName.class,
        JsonPropertyOrder.class,
        JsonProperty.class,
        JsonSerialize.class
    },
    forceJacksonPropertyNames = false
)
@JsonSerialize
public @interface DataClass {
}
