package infra.piles

/**
 * @author alari
 * @since 2/28/13 12:34 AM
 */
class TestCompositePilesManager<T extends PiledItem,K extends SortablePile> implements CompositePilesManager<T,K> {
    SortedPilesManager<T, K> sortedManager
    PilesManager<T,K> plainManager

    @Override
    void release(T item, K pile, boolean withTail) {
        if (sortedManager.inPile(item, pile)) {
            sortedManager.remove(item, pile, withTail).each {
                plainManager.put(item, pile)
            }
        }
        println pile
    }

    @Override
    List<T> drawSorted(K pile, long limit, long offset) {
        sortedManager.draw(pile, limit, offset)
    }

    @Override
    void put(T item, K pile, int position) {
        int sortedSize = sortedManager.sizeOf(pile)
        plainManager.remove(item, pile)
        if (position <= sortedSize) {
            sortedManager.put(item, pile, position)
            plainManager.remove(item, pile)
            dump("put/reorder", pile)
            return;
        }
        plainManager.draw(pile, position-sortedSize-1, 0).each {
            putSorted(it, pile)
        }
        putSorted(item, pile)
    }

    private void putSorted(T item, K pile) {
        sortedManager.put(item, pile)
        dump "putSorted", pile
    }

    @Override
    void put(T item, K pile) {
        if (!inPile(item, pile)) {
            plainManager.put(item, pile)
            dump "put new", pile
        } else {
            dump("old in / ${item}", pile)
        }
    }

    @Override
    Collection<T> remove(T item, K pile, boolean withTail) {
        plainManager.remove(item, pile)
        if (sortedManager.inPile(item, pile)) {
            sortedManager.remove(item, pile, withTail).each {
                plainManager.remove(item, pile)
                dump "remove with:${withTail} ${item}", pile
            }
        } else {
            [item]
        }
    }

    @Override
    void empty(K pile) {
        plainManager.empty(pile)
        sortedManager.empty(pile)
    }

    @Override
    void remove(T item, K pile) {
        plainManager.remove(item, pile)
        sortedManager.remove(item, pile)
    }

    @Override
    List<T> draw(K pile, long limit, long offset) {
        List<T> items = []
        items.addAll drawSorted(pile, limit, offset)
        println "sorted: ${items}"
        if (!limit || items.size() < limit) {
            items.addAll(plainManager.draw(pile, limit ? limit-items.size() : 0, offset > items.size() ? offset - items.size() : 0))
        }
        println "total: ${items}"
        items
    }

    @Override
    Collection<K> getPiles(T item) {
        (plainManager.getPiles(item) + sortedManager.getPiles(item)).unique()
    }

    @Override
    boolean inPile(T item, K pile) {
        dump("in / ${item}", pile)
        plainManager.inPile(item, pile) || sortedManager.inPile(item,pile)
    }

    @Override
    long sizeOf(K pile) {
        dump "sizeOf", pile
        plainManager.sizeOf(pile) + sortedManager.sizeOf(pile)
    }

    void dump(String t, K pile) {
        println "${t} (${pile.plainItems}) : [${pile.sortedItems}]"
    }
}
