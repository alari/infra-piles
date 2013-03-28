package infra.piles;

import java.io.Serializable;
import java.util.Set;

/**
 * @author alari
 * @since 3/10/13 12:40 AM
 */
public interface Item<P extends Pile> {
    public Serializable getId();

    public Set<P> getPiles();
    public void setPiles(Set<P> tags);
}
