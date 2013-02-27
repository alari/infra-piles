package infra.piles;

import java.util.List;

/**
 * @author alari
 * @since 2/27/13 11:02 PM
 */
public interface CompositePilesManager<T extends PiledItem, K extends Pile> extends SortedPilesManager<T,K> {
    SortedPilesManager<T,K> getSortedManager();
    HidePilesManager<T,K> getPlainManager();

    public void release(T item, K pile, boolean withTail);

    public List<T> drawSorted(K pile, long limit, long offset);
}
