package infra.piles;

/**
 * @author alari
 * @since 2/26/13 11:44 PM
 */
import java.util.Collection;
import java.util.List;

/**
 * @author alari
 * @since 7/15/12 11:36 PM
 */
public interface PilesManager<T extends PiledItem, K extends SortablePile> {
    public void put(T item, K pile);

    public void remove(T item, K pile);

    public void empty(K pile);

    public List<T> draw(K pile, long limit, long offset);

    public Collection<K> getPiles(T item);

    public boolean inPile(T item, K pile);

    public long sizeOf(K pile);
}