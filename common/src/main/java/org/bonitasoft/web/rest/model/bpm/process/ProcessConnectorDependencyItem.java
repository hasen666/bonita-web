/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.web.rest.model.bpm.process;

import org.bonitasoft.web.toolkit.client.data.APIID;
import org.bonitasoft.web.toolkit.client.data.item.IItem;
import org.bonitasoft.web.toolkit.client.data.item.Item;
import org.bonitasoft.web.toolkit.client.data.item.ItemDefinition;

/**
 * @author Séverin Moussel
 */
public class ProcessConnectorDependencyItem extends Item {

    public ProcessConnectorDependencyItem() {
        super();
    }

    public ProcessConnectorDependencyItem(final IItem item) {
        super(item);
    }

    // //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ATTRIBUTES
    // //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final String ATTRIBUTE_PROCESS_ID = "connector_process_id";

    public static final String ATTRIBUTE_CONNECTOR_NAME = "connector_name";

    public static final String ATTRIBUTE_CONNECTOR_VERSION = "connector_version";

    public static final String ATTRIBUTE_FILENAME = "filename";

    // //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SETTERS AND GETTERS
    // //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Setters

    public void setConnectorId(final APIID id) {
        setProcessId(id.getPart(ATTRIBUTE_PROCESS_ID));
        setConnectorName(id.getPart(ATTRIBUTE_CONNECTOR_NAME));
        setConnectorVersion(id.getPart(ATTRIBUTE_CONNECTOR_VERSION));
    }

    public void setProcessId(final String id) {
        setAttribute(ATTRIBUTE_PROCESS_ID, id);
    }

    public void setProcessId(final Long id) {
        setAttribute(ATTRIBUTE_PROCESS_ID, id);
    }

    public void setProcessId(final APIID id) {
        setAttribute(ATTRIBUTE_PROCESS_ID, id);
    }

    public void setConnectorName(final String name) {
        setAttribute(ATTRIBUTE_CONNECTOR_NAME, name);
    }

    public void setConnectorVersion(final String version) {
        setAttribute(ATTRIBUTE_CONNECTOR_VERSION, version);
    }

    public void setFilename(final String filename) {
        setAttribute(ATTRIBUTE_FILENAME, filename);
    }

    // Getters

    public APIID getProcessId() {
        return getAttributeValueAsAPIID(ATTRIBUTE_PROCESS_ID);
    }

    public String getConnectorName() {
        return getAttributeValue(ATTRIBUTE_CONNECTOR_NAME);
    }

    public String getConnectorVersion() {
        return getAttributeValue(ATTRIBUTE_CONNECTOR_VERSION);
    }

    public String getFilename() {
        return getAttributeValue(ATTRIBUTE_FILENAME);
    }

    // Deploys

    public ProcessItem getProcess() {
        return new ProcessItem(getItemDeploy(ATTRIBUTE_PROCESS_ID));
    }

    // //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // UTILS
    // //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public ItemDefinition getItemDefinition() {
        return ProcessConnectorDependencyDefinition.get();
    }

}
