/*
 * Copyright 2011 Ammo Goettsch
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package net.derammo.jsonschema;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import net.derammo.jsonschema.TypeDeserializer.TypeVariant;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 * A Jackson binding-based JSON ApplicationSchema draft 3 reader. 
 * 
 * @author ammo
 */
public class SchemaDraft3<ApplicationSchema extends SchemaDraft3<ApplicationSchema> > {
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.1">section-5.1</a> of JSON ApplicationSchema draft 3
     */
    @JsonDeserialize(using = TypeDeserializer.class)
    private TypeDeserializer.TypeVariant type;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.2">section-5.2</a> of JSON ApplicationSchema draft 3
     */
    private LinkedHashMap<String, ApplicationSchema> properties = new LinkedHashMap<String, ApplicationSchema>();
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.3">section-5.3</a> of JSON ApplicationSchema draft 3
     */
    private HashSet<String> patternProperties;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.4">section-5.4</a> of JSON ApplicationSchema draft 3
     */
    private boolean additionalProperties;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.5">section-5.5</a> of JSON ApplicationSchema draft 3
     * 
     * Note that the Jackson ObjectMapper is configured to convert single schema object 
     * to an array with one entry. 
     */
    private ArrayList<ApplicationSchema> items = new ArrayList<ApplicationSchema>();
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.6">section-5.6</a> of JSON ApplicationSchema draft 3
     */
    private TypeDeserializer.TypeVariant additionalItems;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.7">section-5.7</a> of JSON ApplicationSchema draft 3
     */
    private boolean required;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.8">section-5.8</a> of JSON ApplicationSchema draft 3
     */
    private LinkedHashMap<String, TypeDeserializer.TypeVariant> dependencies;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.9">section-5.9</a> of JSON ApplicationSchema draft 3
     */
    private long minimum;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.10">section-5.10</a> of JSON ApplicationSchema draft 3
     */
    private long maximum;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.11">section-5.11</a> of JSON ApplicationSchema draft 3
     */
    private boolean exclusiveMinimum;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.12">section-5.12</a> of JSON ApplicationSchema draft 3
     */
    private boolean exclusiveMaximum;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.13">section-5.13</a> of JSON ApplicationSchema draft 3
     */
    private int minItems;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.14">section-5.14</a> of JSON ApplicationSchema draft 3
     */
    private int maxItems;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.15">section-5.15</a> of JSON ApplicationSchema draft 3
     */
    private boolean uniqueItems;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.16">section-5.16</a> of JSON ApplicationSchema draft 3
     */
    private String pattern;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.17">section-5.17</a> of JSON ApplicationSchema draft 3
     */
    private int minLength;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.18">section-5.18</a> of JSON ApplicationSchema draft 3
     */
    private int maxlength;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.19">section-5.19</a> of JSON ApplicationSchema draft 3
     */
    @JsonProperty("enum")
    private LinkedHashSet<String> enumerated;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.20">section-5.20</a> of JSON ApplicationSchema draft 3
     */
    @JsonProperty("default")
    private String defaultValue;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.21">section-5.21</a> of JSON ApplicationSchema draft 3
     */
    private String title;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.22">section-5.22</a> of JSON ApplicationSchema draft 3
     */
    private String description;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.23">section-5.23</a> of JSON ApplicationSchema draft 3
     */
    private String format;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.24">section-5.24</a> of JSON ApplicationSchema draft 3
     */
    private int divisibleBy = 1;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.25">section-5.25</a> of JSON ApplicationSchema draft 3
     */
    private TypeDeserializer.TypeVariant disallow;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.26">section-5.26</a> of JSON ApplicationSchema draft 3
     *
     * Note that the Jackson ObjectMapper is configured to convert a single string to an array containing a single string. 
     */
    @JsonProperty("extends")
    private ArrayList<String> extendsSchemas;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.27">section-5.27</a> of JSON ApplicationSchema draft 3
     */
    private URI id;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.28">section-5.28</a> of JSON ApplicationSchema draft 3
     */
    @JsonProperty("$ref")
    private URI reference;
    /**
     * @see <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.29">section-5.29</a> of JSON ApplicationSchema draft 3
     */
    @JsonProperty("$schema")
    private URI schemaReference;
    
    /**
     * Setter called with different helper objects based on the format of 'type'
     * @param type helper object created by custom deserializer
     */
    @SuppressWarnings("unchecked")
    public void setType(TypeDeserializer.TypeVariant type) {
        // convert based on type
        this.simpleTypes = new HashSet<String>();
        this.schemaTypes = new ArrayList<ApplicationSchema>();

        if (type instanceof TypeDeserializer.SimpleType) {
            simpleTypes.add(((TypeDeserializer.SimpleType) type).getName());
        }
        if (type instanceof TypeDeserializer.UnionType) {
            simpleTypes.addAll(((TypeDeserializer.UnionType<ApplicationSchema>) type).getSimpleTypes());
            schemaTypes.addAll(((TypeDeserializer.UnionType<ApplicationSchema>) type).getSchemaTypes());
        }
        
        // also store the original version, for serialization purposes
        this.type = type;
    }

    // implementation of the various forms of 'type'
    @JsonIgnore
    protected ArrayList<ApplicationSchema>              schemaTypes;
    @JsonIgnore
    protected HashSet<String>                           simpleTypes;

    // this reference is needed to construct the correct deserializer 
    // TODO: how do you bind this to make sure this is the class of ApplicationSchema?  Type erasure
    // causes the static parts of this class to be untyped.
    private static Class<?> applicationSchemaClass = JsonSchema.class;
    public static void setApplicationSchemaClass(Class<? extends SchemaDraft3<?> > clazz) {
        SchemaDraft3.applicationSchemaClass = clazz;
    }
    public static Class<?> getApplicationSchemaClass() {
        return applicationSchemaClass;
    }
    
    public ArrayList<ApplicationSchema> getSchemaTypes() {
        return schemaTypes;
    }

    public HashSet<String> getSimpleTypes() {
        return simpleTypes;
    }

    public ArrayList<ApplicationSchema> getItems() {
        return items;
    }

    public void setItems(ArrayList<ApplicationSchema> items) {
        this.items = items;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public LinkedHashMap<String, ApplicationSchema> getProperties() {
        return properties;
    }

    public void setProperties(LinkedHashMap<String, ApplicationSchema> properties) {
        this.properties = properties;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public HashSet<String> getPatternProperties() {
        return patternProperties;
    }

    public void setPatternProperties(HashSet<String> patternProperties) {
        this.patternProperties = patternProperties;
    }

    public boolean isAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(boolean additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public Object getAdditionalItems() {
        return additionalItems;
    }

    public void setAdditionalItems(TypeDeserializer.TypeVariant additionalItems) {
        // TODO: do something intelligent with FalseType and SchemaType
        this.additionalItems = additionalItems;
    }

    public LinkedHashMap<String, TypeVariant> getDependencies() {
        return dependencies;
    }

    public void setDependencies(LinkedHashMap<String, TypeVariant> dependencies) {
        // TODO: match up the dependencies to the properties and do something with them
        // TypeVariant may be SimpleType, SchemaType, or 
        // UnionType (need to check to make sure it only contains SimpleType)
        this.dependencies = dependencies;
    }

    public long getMinimum() {
        return minimum;
    }

    public void setMinimum(long minimum) {
        this.minimum = minimum;
    }

    public long getMaximum() {
        return maximum;
    }

    public void setMaximum(long maximum) {
        this.maximum = maximum;
    }

    public boolean isExclusiveMinimum() {
        return exclusiveMinimum;
    }

    public void setExclusiveMinimum(boolean exclusiveMinimum) {
        this.exclusiveMinimum = exclusiveMinimum;
    }

    public boolean isExclusiveMaximum() {
        return exclusiveMaximum;
    }

    public void setExclusiveMaximum(boolean exclusiveMaximum) {
        this.exclusiveMaximum = exclusiveMaximum;
    }

    public int getMinItems() {
        return minItems;
    }

    public void setMinItems(int minItems) {
        this.minItems = minItems;
    }

    public int getMaxItems() {
        return maxItems;
    }

    public void setMaxItems(int maxItems) {
        this.maxItems = maxItems;
    }

    public boolean isUniqueItems() {
        return uniqueItems;
    }

    public void setUniqueItems(boolean uniqueItems) {
        this.uniqueItems = uniqueItems;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxlength() {
        return maxlength;
    }

    public void setMaxlength(int maxlength) {
        this.maxlength = maxlength;
    }

    public LinkedHashSet<String> getEnumerated() {
        return enumerated;
    }

    public void setEnumerated(LinkedHashSet<String> enumerated) {
        this.enumerated = enumerated;
    }
    /*
     *  Copyright 2011 Ammo Goettsch
     *
     * Licensed under the Apache License, Version 2.0 (the "License");
     * you may not use this file except in compliance with the License.
     * You may obtain a copy of the License at
     *
     * http://www.apache.org/licenses/LICENSE-2.0
     *
     * Unless required by applicable law or agreed to in writing, software
     * distributed under the License is distributed on an "AS IS" BASIS,
     * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     * See the License for the specific language governing permissions and
     * limitations under the License.
    */

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDivisibleBy() {
        return divisibleBy;
    }

    public void setDivisibleBy(int divisibleBy) {
        this.divisibleBy = divisibleBy;
    }

    public TypeDeserializer.TypeVariant getDisallow() {
        return disallow;
    }

    public void setDisallow(TypeDeserializer.TypeVariant disallow) {
        this.disallow = disallow;
    }

    public ArrayList<String> getExtendsSchemas() {
        return extendsSchemas;
    }

    public void setExtendsSchemas(ArrayList<String> extendsSchemas) {
        this.extendsSchemas = extendsSchemas;
    }

    public URI getId() {
        return id;
    }

    public void setId(URI id) {
        this.id = id;
    }

    public URI getReference() {
        return reference;
    }

    public void setReference(URI reference) {
        this.reference = reference;
    }

    public URI getSchemaReference() {
        return schemaReference;
    }

    public void setSchemaReference(URI schemaReference) {
        this.schemaReference = schemaReference;
    }

    public TypeDeserializer.TypeVariant getType() {
        return type;
    }
}