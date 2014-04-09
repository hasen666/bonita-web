package org.bonitasoft.console.client.mvp;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.*;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import org.bonitasoft.console.client.mvp.event.DirtyInputEvent;
import org.bonitasoft.console.client.mvp.event.DirtyInputHandler;
import org.bonitasoft.web.toolkit.client.eventbus.MainEventBus;

/**
 * @author Vincent Elcrin
 */
public class LineTemplate<T> extends AbstractCell<T> {

    public interface Line<T> {

        public SafeHtml render(Cell.Context context, T value);
    }

    private final Line<T> line;

    private String style;

    public LineTemplate(Line<T> line, String style) {
        super(BrowserEvents.CLICK, BrowserEvents.KEYDOWN);
        this.line = line;
        this.style = style;
    }

    public String getStyle() {
        return style;
    }

    @Override
    public void render(Context context, T value, SafeHtmlBuilder sb) {
        if (value == null) {
            return;
        }
        sb.append(line.render(context, value));
    }

    @Override
    public void onBrowserEvent(Context context, Element parent, T value, NativeEvent event, ValueUpdater<T> valueUpdater) {
        Element element = Element.as(event.getEventTarget());
        if (element.getTagName().equalsIgnoreCase(InputElement.TAG)) {
            final InputElement input = InputElement.as(element);
            notify(context, event, input);
        }
        super.onBrowserEvent(context, parent, value, event, valueUpdater);
    }

    public void listen(DirtyInputHandler handler) {
        MainEventBus.getInstance().addHandler(DirtyInputEvent.TYPE, handler);
    }

    private void notify(Context context, NativeEvent event, InputElement input) {
        MainEventBus.getInstance().fireEventFromSource(new DirtyInputEvent(event, context, input), this);
    }

    @Override
    public boolean resetFocus(Context context, Element parent, T value) {
        final NodeList<Element> elements = parent.getElementsByTagName("input");
        if (elements.getLength() > 0) {
            InputElement.as(elements.getItem(0)).focus();
            return true;
        }
        return false;
    }
}
