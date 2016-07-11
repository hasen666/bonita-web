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
package org.bonitasoft.web.toolkit.client.data.item.attribute.reader;

import java.util.Arrays;
import java.util.List;

/**
 * @author Séverin Moussel
 * 
 */
public class CountedAttributeReader extends AttributeReader implements HasCounters {

    public CountedAttributeReader(final String attributeToRead) {
        super(attributeToRead, false);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.web.toolkit.client.data.item.attribute.reader.HasCounters#getCounters()
     */
    @Override
    public List<String> getCounters() {
        return Arrays.asList(getLeadAttribute());
    }

}
