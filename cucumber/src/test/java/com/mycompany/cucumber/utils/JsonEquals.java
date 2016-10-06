package com.mycompany.cucumber.utils;

import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * Utility to compare two JSON objects. Also with ignoring the order of elements
 * in arrays
 *
 */

public class JsonEquals {

	// Check equality with ignoring the element order in arrays
	public static boolean equalsIgnoreArrayOrdering(JsonNode a, JsonNode b) {

		if (a == null)
			return b == null;

		// Check Json objects
		if (a.isObject()) {
			if (!b.isObject())
				return false;

			// check each fields of the objects
			Iterator<String> keysA = a.fieldNames();
			Iterator<String> keysB = b.fieldNames();
			while (keysA.hasNext() && keysB.hasNext()) {
				String keyA = keysA.next();
				String keyB = keysB.next();

				if (!keyA.equals(keyB) || !equalsIgnoreArrayOrdering(a.get(keyA), b.get(keyB))) {
					// Field was not found in B
					return false;
				}
			}

			// OK if none of the objects has more fields left
			if (keysA.hasNext() || keysB.hasNext()) {
				return false;
			}
			return true;
		}

		if (a.isArray()) {
			if (!b.isArray())
				return false;

			ArrayNode arrayA = (ArrayNode) a;
			ArrayNode arrayB = (ArrayNode) b;
			// Check element in the array
			for (JsonNode elementA : arrayA) {
				boolean existsInB = false;
				for (int i = 0; !existsInB && i < arrayB.size(); i++) {
					if (equalsIgnoreArrayOrdering(elementA, arrayB.get(i))) {
						existsInB = true;
						// Remove element from B if it was found as a match for
						// elementA. This is to handle duplicates properly.
						arrayB.remove(i);
					}

				}
				if (!existsInB) {
					// Element was not found in the other array
					return false;
				}

			}
			// Check if extra elements were left in arrayB
			if (arrayB.size() != 0)
				return false;
			return true;
		}
		// Simple equals for other type of nodes (values)
		return a.equals(b);

	}

	// Check equality with ignoring the element order in arrays
	public static boolean equalsIgnoreArrayOrdering(String a, String b) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		return equalsIgnoreArrayOrdering(objectMapper.readValue(a, JsonNode.class),
				objectMapper.readValue(b, JsonNode.class));
	}

	// Check equality
	public static boolean equalsJson(String a, String b) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(a, JsonNode.class).equals(objectMapper.readValue(b, JsonNode.class));
	}

}
