/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.web.rest.server.datastore.profile.entry;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.engine.profile.ProfileEntrySearchDescriptor;
import org.bonitasoft.web.rest.api.model.portal.profile.ProfileEntryItem;
import org.bonitasoft.web.rest.server.datastore.converter.AttributeConverter;

/**
 * @author Vincent Elcrin
 * 
 */
public class ProfileEntrySearchDescriptorConverter implements AttributeConverter {

    private static final Map<String, String> mapping = new HashMap<String, String>();

    static {
        mapping.put(ProfileEntryItem.ATTRIBUTE_ID, ProfileEntrySearchDescriptor.ID);
        mapping.put(ProfileEntryItem.ATTRIBUTE_INDEX, ProfileEntrySearchDescriptor.INDEX);
        mapping.put(ProfileEntryItem.ATTRIBUTE_NAME, ProfileEntrySearchDescriptor.NAME);
        mapping.put(ProfileEntryItem.ATTRIBUTE_PARENT_ID, ProfileEntrySearchDescriptor.PARENT_ID);
        mapping.put(ProfileEntryItem.ATTRIBUTE_PROFILE_ID, ProfileEntrySearchDescriptor.PROFILE_ID);
    }

    @Override
    public String convert(String attribute) {
        String desciptor = mapping.get(attribute);
        if (attribute == null) {
            throw new RuntimeException(attribute + " has no valid search descriptor");
        }
        return desciptor;
    }

}
