package infra.piles;

import java.util.Collection;

/**
 * @author alari
 * @since 2/27/13 10:57 PM
 */
public interface SortedPilesManager<T extends PiledItem, K extends SortablePile> extends PilesManager<T,K> {
    public void put(T item, K pile, int position);

    public Collection<T> remove(T item, K pile, boolean withTail);
}
