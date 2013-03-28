package infra.piles;

import java.io.Serializable;

/**
 * @author alari
 * @since 3/10/13 12:38 AM
 */
public interface Pile<I extends Item<? extends Pile<I>>> {
    public Serializable getId();

    public String getTitle();
    public void setTitle(String title);

    public I getItem();
    public void setItem(I card);
}
