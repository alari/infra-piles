package infra.piles;

import java.util.List;

/**
 * @author alari
 * @since 2/27/13 11:02 PM
 */
public interface CompositePilesManager<T extends PiledItem, K extends SortablePile> extends SortedPilesManager<T,K> {
    SortedPilesManager<T,K> getSortedManager();
    PilesManager<T,K> getPlainManager();

    public void release(T item, K pile, boolean withTail);

    public List<T> drawSorted(K pile, long limit, long offset);
}
