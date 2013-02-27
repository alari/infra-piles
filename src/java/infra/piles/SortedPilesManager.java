package infra.piles;

/**
 * @author alari
 * @since 2/27/13 10:57 PM
 */
public interface SortedPilesManager<T extends PiledItem, K extends Pile> extends PilesManager<T,K> {
    public void put(T item, K pile, int position);

    public void remove(T item, K pile, boolean withTail);
}
