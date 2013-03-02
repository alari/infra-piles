package infra.piles;

/**
 * @author alari
 * @since 2/27/13 10:57 PM
 */
public interface HidePilesManager<T extends PiledItem, K extends SortablePile> extends PilesManager<T,K> {
    public void hide(T item, K pile);

    public boolean isHidden(T item, K pile);

    public void reveal(T item, K pile);
}
