package infra.piles;

import java.util.Collection;

/**
 * @author alari
 * @since 3/10/13 12:02 AM
 */
public interface ItemsRepo<P extends Pile<? extends I>, I extends Item<? extends P>> {
    public void addToPile(I item, P pile);
    public void removeFromPile(I item, P pile);
    public void setPiles(I item, Collection<P> piles);
    public Collection<I> getAllByPile(P pile);
    public I getById(String id);
}
