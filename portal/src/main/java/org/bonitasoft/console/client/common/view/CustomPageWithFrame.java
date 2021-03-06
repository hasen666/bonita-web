/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.console.client.common.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.Window;
import org.bonitasoft.console.client.user.cases.view.IFrameView;
import org.bonitasoft.web.toolkit.client.ClientApplicationURL;
import org.bonitasoft.web.toolkit.client.common.i18n.AbstractI18n;
import org.bonitasoft.web.toolkit.client.ui.Page;
import org.bonitasoft.web.toolkit.client.ui.component.containers.Container;
import org.bonitasoft.web.toolkit.client.ui.component.core.AbstractComponent;
import org.bonitasoft.web.toolkit.client.ui.component.core.Component;
import org.bonitasoft.web.toolkit.client.ui.component.core.UiComponent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;

/**
 * @author Anthony Birembaut
 *
 */
public class CustomPageWithFrame extends Page {

    public final static String TOKEN = "custompage_";

    public static final String PROCESS_LIST_USER = "custompage_processlistBonita";
    
    //For Technical user menu, don't forget to modify APISession and ConsoleFactoryClient
    public static final String TENANT_STATUS = "custompage_tenantStatusBonita";

    public static final String INSTALL_EXPORT_ORGANIZATION = "custompage_installExportOrganizationBonita";

    private final String pageName;

    private static Map<String, List<String>> privilegesMap = new HashMap<String, List<String>>();

    public CustomPageWithFrame(final String pageName) {
        this.pageName = pageName;
    }

    @Override
    public String defineToken() {
        return TOKEN;
    }

    @Override
    public String getToken() {
        return pageName;
    }

    @Override
    public void buildView() {
        addBody(createIframe(pageName));
    }

    private Component createIframe(final String pageName) {
        return new UiComponent(new IFrameView(buildCustomPageURL(pageName)));
    }

    protected String buildCustomPageURL(final String pageName) {
        final StringBuilder servletURL = new StringBuilder().append(GWT.getModuleBaseURL()).append("custom-page/").append(pageName).append("/?locale=")
                .append(AbstractI18n.getDefaultLocale().toString()).append("&profile=").append(ClientApplicationURL.getProfileId()).append(getHashQueryString());
        return servletURL.toString();
    }

    private String getHashQueryString() {
        int qmIndex = Window.Location.getHash().indexOf("?");
        if (qmIndex > -1) {
            String hashQueryString = "&" + Window.Location.getHash().substring(qmIndex + 1);
            // Clean unused parameters '_pf' and '_p'
            return hashQueryString.replaceAll("&_pf?=[^&\\?#]*", "");
        }
        return "";
    }

    /**
     * We don't need any header and it screw up the page's size.
     *
     * @param header
     * @return
     */
    @Override
    protected List<Element> makeHeaderElements(final Container<AbstractComponent> header) {
        return null;
    }

    /**
     * We don't need any footer
     *
     * @param footer
     * @return
     */
    @Override
    protected List<Element> makeFooterElements(final Container<AbstractComponent> footer) {
        return null;
    }

    @Override
    public void defineTitle() {
        setTitle(pageName);
    }

   /**
     * add a token support to customPage and associated privileges
     *
     * @param token the token to match
     * @param privileges the privilege to be available
     */
    public static void addTokenSupport(final String token, String... privileges) {
        privilegesMap.put(token, Arrays.asList(privileges));
    }

    /**
     * Get privileges associated to this token
     */
    public static List<String> getPrivileges(String token) {
        List<String> result = new ArrayList<String>();
        result.add(token);
        List<String> all = privilegesMap.get(token);
        if (all != null) {
            result.addAll(all);
        }
        return result;
    }

}
