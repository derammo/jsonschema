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

/**
 * This class represents the current JSON schema supported by this package,
 * and will change as the JSON schema specification evolves.
 * 
 * To extend a specific schema version, extend the generic types that are its
 * ancestors.
 * 
 * @author ammo
 */
public class JsonSchema extends HyperSchemaDraft3<JsonSchema> {
}
